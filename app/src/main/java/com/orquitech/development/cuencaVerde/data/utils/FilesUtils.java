package com.orquitech.development.cuencaVerde.data.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import okhttp3.ResponseBody;

public class FilesUtils {

    public static boolean saveFileFromStream(Context context, ResponseBody responseBody, String fileName) {
        try {
            File file = new File(context.getFilesDir(), fileName);

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = responseBody.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = responseBody.byteStream();
                outputStream = new FileOutputStream(file);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                }

                outputStream.flush();

                return true;
            } catch (IOException | NullPointerException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

    public static String readFromFile(Context context, String fileName) {
        String response = "";

        try {
            InputStream inputStream = context.openFileInput(fileName);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                response = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return response;
    }

    public static Bitmap saveImageFileFromStream(Context context, InputStream inputStream, String fileName) {
        Bitmap bitmap = null;
        try {
            File file = new File(context.getFilesDir(), fileName);
            OutputStream fos = new FileOutputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            bitmap = BitmapFactory.decodeStream(bufferedInputStream);
            saveImageFileFromBitmap(context, bitmap, fileName);
        } catch (FileNotFoundException e) {
            Log.d("Test", "File not found: " + e.getMessage());
        }
        return bitmap;
    }

    public static Uri saveImageFileFromBitmap(Context context, Bitmap image, String fileName) {
        Uri uri = null;
        try {
            File file = new File(context.getFilesDir(), fileName);
            OutputStream fos = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.JPEG, 90, fos);
            fos.close();
            uri = Uri.fromFile(file);
            Log.d("saveImageFileFromBitmap", "File path on save: " + file.getPath());
        } catch (FileNotFoundException e) {
            Log.d("saveImageFileFromBitmap", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d("saveImageFileFromBitmap", "Error accessing file: " + e.getMessage());
        }
        return uri;
    }

    public static Bitmap getOutputMediaBitmap(Context context, String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                return null;
            }
        }
        File mediaFile = new File(file.getPath());
        String filePath = mediaFile.getPath();
        return BitmapFactory.decodeFile(filePath);
    }

    public static File getOutputMediaFile(Context context, String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                return null;
            }
        }
        return new File(file.getPath());
    }

    public static void clearApplicationData(File cacheDirectory) {
        File applicationDirectory = new File(cacheDirectory.getParent());
        if (applicationDirectory.exists()) {
            String[] fileNames = applicationDirectory.list();
            for (String fileName : fileNames) {
                if (!fileName.equals("lib")) {
                    deleteFile(new File(applicationDirectory, fileName));
                }
            }
        }
    }

    public static boolean deleteFile(Context context, String fileName) {
        boolean result = false;
        try {
            File file = new File(context.getFilesDir(), fileName);
            result = file.delete();
            Log.d(FilesUtils.class.getSimpleName(), "Success on deleting file: " + file.getPath());
        } catch (Exception e) {
            Log.d(FilesUtils.class.getSimpleName(), "File delete error: " + e.getMessage());
        }
        return result;
    }

    private static boolean deleteFile(File file) {
        boolean deletedAll = true;
        if (file != null) {
            if (file.isDirectory()) {
                String[] children = file.list();
                for (int i = 0; i < children.length; i++) {
                    deletedAll = deleteFile(new File(file, children[i])) && deletedAll;
                }
            } else {
                deletedAll = file.delete();
            }
        }

        return deletedAll;
    }

    public static Bitmap getResizedBitmap(Bitmap bitmap) {
        int sampleSize = calculateInSampleSize(bitmap, 100, 100);
        int width = bitmap.getWidth() / sampleSize;
        int height = bitmap.getHeight() / sampleSize;
        Bitmap result = Bitmap.createScaledBitmap(bitmap, width, height, true);
        bitmap.recycle();
        return result;
    }

    private static int calculateInSampleSize(Bitmap bitmap, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = bitmap.getHeight();
        final int width = bitmap.getWidth();
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
