package com.orquitech.development.cuencaVerde.presentation.views.activities;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.CuencaVerdeApp;
import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.SerializationManager;
import com.orquitech.development.cuencaVerde.data.api.cuencaVerdeApi.ApiConfigDevelopment;
import com.orquitech.development.cuencaVerde.data.db.PersistenceManager;
import com.orquitech.development.cuencaVerde.data.managers.ApiManager;
import com.orquitech.development.cuencaVerde.data.managers.ConnectivityStatusManager;
import com.orquitech.development.cuencaVerde.data.utils.FilesUtils;
import com.orquitech.development.cuencaVerde.dependencyInjection.component.DaggerViewsComponent;
import com.orquitech.development.cuencaVerde.dependencyInjection.component.ViewsComponent;
import com.orquitech.development.cuencaVerde.logic.Bus;
import com.orquitech.development.cuencaVerde.logic.DialogManager;
import com.orquitech.development.cuencaVerde.logic.PrediosManager;
import com.orquitech.development.cuencaVerde.logic.ProceduresManager;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.logic.UserAccountManager;
import com.orquitech.development.cuencaVerde.logic.preferences.AppPreferencesObjectFactory;
import com.orquitech.development.cuencaVerde.logic.preferences.PreferencesManager;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.factories.ViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.DialogListenerAdapter;
import com.orquitech.development.cuencaVerde.presentation.views.decorators.DetailsTransition;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.AppDialogManager;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.factories.DialogsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.FragmentListener;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.TasksFragment;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.Toolbar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseActivity extends AppCompatActivity implements FragmentListener {

    public static final String END_TASK_DIALOG = "endTaskDialog";
    public static final String SEND_TASK_DATA_DIALOG = "sendTaskDataDialog";
    public static final String NEW_SURVEY_DUMMY_TASK_ID = "-99999";
    private ViewsComponent component;
    private List<BaseFragment> pendingForClose = new ArrayList<>();
    private List<BaseFragment> pendingForOpen = new ArrayList<>();
    private FragmentManager fragmentManager;
    private boolean pause = false;
    private static final int READ_EXTERNAL_STORAGE_INTENT_ID = 4;
    private static final int CHOOSE_FILE_STORAGE_INTENT_ID = 5;
    private static final int PICK_FROM_GALLERY_INTENT_ID = 6;
    protected static final int CAMERA_INTENT_ID = 7;
    protected static final int CAMERA_FULL_PHOTO_INTENT_ID = 8;

    protected CompositeDisposable disposables;
    protected DialogManager dialogManager;
    private boolean isForCamera;
    private Uri imageUri;
    private String currentPhotoPath;
    private final String IMAGE_FILE_NAME = "cuenca_photo_";

    public static BaseActivity m_this;

    public static BaseActivity getInstacnce()
    {
        return m_this;
    }

    @Inject
    App app;

    @Inject
    ViewsFactory viewsFactory;

    @Inject
    public Bus bus;

    @Inject
    ConnectivityStatusManager connectivityManager;

    @Inject
    UserAccountManager accountManager;

    @Inject
    public ViewModelsFactory viewModelFactory;

    @Inject
    PersistenceManager persistenceManager;

    @Inject
    public SerializationManager serializationManager;

    @Inject
    public ApiManager apiManager;

    @Inject
    PreferencesManager preferencesManager;

    @Inject
    public PrediosManager prediosManager;

    @Inject
    public ProceduresManager projectsManager;

    private BroadcastReceiver connectivityListener = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isConnected = app.isNetworkConnected(getApplicationContext());
            connectivityManager.setIsConnected(isConnected);
            if (isConnected) {
                hideConnectivityMessage();
            } else {
                showConnectivityMessage(getString(R.string.no_connection_error_message));
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        m_this = this;
        fragmentManager = getSupportFragmentManager();

        component = DaggerViewsComponent.builder()
                .appComponent(CuencaVerdeApp.getApp().getAppComponent())
                .build();

        component.inject(this);
        disposables = new CompositeDisposable();
        connectivityManager.setIsConnected(app.isNetworkConnected(getApplicationContext()));
        dialogManager = new AppDialogManager(new DialogsFactory());

        if (connectivityManager.isConnected()) {
            hideConnectivityMessage();
        } else {
            showConnectivityMessage(getString(R.string.no_connection_error_message));
        }
    }

    public Context getContext() {
        return this;
    }

    protected void subscribeToObservables() {
        /*disposables.add(bus.getObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(bundle -> {
                    switch (bundle.getInt(RxBus.CODE)) {
                        case RxBus.SERVICE_ERROR:
                            showSnackBar(getNetworkErrorMessage(bundle.getInt(RxBus.PAYLOAD)), R.color.colorAccent);
                            break;
                    }
                })
                .subscribe());*/
    }

    private String getNetworkErrorMessage(int errorCode) {
        String message = "";
        switch (errorCode) {
            case ApiConfigDevelopment.UNAUTHORIZED:
                message = getString(R.string.unauthorized);
                break;
            case ApiConfigDevelopment.CONNECT_EXCEPTION:
                message = getString(R.string.connection_exception);
                break;
            case ApiConfigDevelopment.BAD_RESPONSE:
                message = getString(R.string.bad_response);
                break;
            default:
                message = getString(R.string.connection_exception);
                break;
        }
        return message;
    }

    @Override
    protected void onResume() {
        super.onResume();
        pause = false;
        checkPendingView();
        subscribeToObservables();
        registerReceiver(connectivityListener, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        pause = true;
        disposables.clear();
        unregisterReceiver(connectivityListener);
        super.onPause();
    }

    protected ViewsComponent getComponent() {
        return component;
    }

    @Override
    public void showMessage(String string, int color) {
        showSnackBar(string, color);
    }

    @Override
    public void showMessage(String string, int color, int coordinatorView) {
        showSnackBar(string, color, Snackbar.LENGTH_SHORT, coordinatorView);
    }

    @Override
    public void showMessage(String string, int color, View container) {
        showSnackBar(string, color, Snackbar.LENGTH_SHORT, container);
    }

    void addSharedFragment(View sharedElement, BaseFragment baseFragment) {
        baseFragment.setSharedElementEnterTransition(new DetailsTransition());
        baseFragment.setEnterTransition(new Fade());
        baseFragment.setExitTransition(new Fade());
        baseFragment.setSharedElementReturnTransition(new DetailsTransition());

        try {
            fragmentManager.beginTransaction()
                    .addSharedElement(sharedElement, "photo")
                    .add(baseFragment.getContainer(), baseFragment, baseFragment.getName())
                    .addToBackStack(baseFragment.getName())
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void addFragment(BaseFragment baseFragment) {
        try {
            if (isPause()) {
                pendingForOpen.add(baseFragment);
            } else {
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                if (fragmentManager.getBackStackEntryCount() >= 0) {
                    transaction.setCustomAnimations(baseFragment.getEnter(), baseFragment.getExit(), baseFragment.getPopEnter(), baseFragment.getPopExit());
                }

                transaction.add(baseFragment.getContainer(), baseFragment, baseFragment.getName());
                transaction.addToBackStack(baseFragment.getName());
                transaction.commit();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void replaceFragment(@Nullable BaseFragment newFragment) {
        if (newFragment != null) {
            if (isPause()) {
                pendingForOpen.add(newFragment);
            } else {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                BaseFragment oldFragment = null;

                int countF = fragmentManager.getBackStackEntryCount();
                if (countF > 0) {
                    oldFragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(fragmentManager.getBackStackEntryAt(countF - 1).getName());
                }

                if (!newFragment.getName().equals(oldFragment != null ? oldFragment.getName() : "")) {
                    if (oldFragment != null && oldFragment.isAddOnStack()) {
                        fragmentManager.popBackStack(oldFragment.getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    }
                    if (newFragment.isAddOnStack()) {
                        transaction.addToBackStack(newFragment.getName());
                    }
                    if (newFragment.isFragmentAnimate()) {
                        transaction.setCustomAnimations(newFragment.getEnter(), newFragment.getExit(), newFragment.getPopEnter(), newFragment.getPopExit());
                    }
                    transaction.replace(newFragment.getContainer(), newFragment, newFragment.getName());
                    transaction.commit();

                } else {
                    if (oldFragment != null) {
                        oldFragment.onResetState();
                    }
                }
            }
        }
    }

    protected BaseFragment getCurrentFragment() {
        int countF = fragmentManager.getBackStackEntryCount();
        BaseFragment currentFragment = null;
        if (countF > 0) {
            currentFragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(fragmentManager.getBackStackEntryAt(countF - 1).getName());
        }
        return currentFragment;
    }

    private boolean isPause() {
        return pause;
    }

    @Override
    public void onBackPressed() {
        int countF = fragmentManager.getBackStackEntryCount();
        if (countF != 0) {
            String fragmentTag = fragmentManager.getBackStackEntryAt(countF - 1).getName();
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragmentTag);
            if (fragment instanceof BaseFragment) {
                BaseFragment currentFragment = (BaseFragment) fragment;
                if (currentFragment.canGoBack()) {
                    super.onBackPressed();
                } else {
                    currentFragment.backPressed();
                }
            }
        } else {
            onBackPressFromEmptyStack();
        }
    }

    protected String getActiveFragmentName() {
        int countF = fragmentManager.getBackStackEntryCount();
        String fragmentName = "";
        if (countF != 0) {
            fragmentName = fragmentManager.getBackStackEntryAt(countF - 1).getName();
        }
        return fragmentName;
    }

    private void onBackPressFromEmptyStack() {
        super.onBackPressed();
    }

    private void checkPendingView() {
        for (BaseFragment BaseFragment : pendingForOpen) {
            addFragment(BaseFragment.setEnter(0).setPopEnter(0));
        }

        pendingForOpen.clear();
        pendingForOpen = new ArrayList<>();

        for (BaseFragment fragment : pendingForClose) {
            onClose(fragment);
        }
        pendingForClose.clear();
        pendingForClose = new ArrayList<>();
    }

    @Override
    public void onClose(BaseFragment view) {
        if (!isPause()) {
            if (fragmentManager.getBackStackEntryCount() < 1) {
                finish();
            }
            fragmentManager.popBackStack(view.getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            pendingForClose.add(view);
        }
    }

    @Override
    public void finish() {
        if (isTaskRoot() && !(this instanceof MainActivity)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.scale_front_main, R.anim.slide_to_right);
            super.finish();
        } else {
            super.finish();
        }
    }

    protected void showSnackBar(String message, int color, int viewId) {
        showSnackBar(message, color, Snackbar.LENGTH_LONG, viewId);
    }

    protected void showCoordinatedSnackBar(String message, int color) {
        showSnackBar(message, color, Snackbar.LENGTH_LONG, R.id.main_container);
    }

    protected void showSnackBar(String message, int color) {
        showSnackBar(message, color, Snackbar.LENGTH_LONG, R.id.container);
    }

    protected void showSnackBar(String message, int color, int duration, View container) {
        Snackbar snackBar = Snackbar.make(container, message, duration);
        snackBar.setActionTextColor(Color.WHITE);

        View sbView = snackBar.getView();
        TextView textView = sbView.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        sbView.setBackgroundColor(ContextCompat.getColor(getBaseContext(), color));

        snackBar.show();
    }

    protected void showSnackBar(String message, int color, int duration, int viewId) {
        Snackbar snackBar = Snackbar.make(findViewById(viewId), message, duration);
        snackBar.setActionTextColor(Color.WHITE);

        View sbView = snackBar.getView();
        TextView textView = sbView.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        sbView.setBackgroundColor(ContextCompat.getColor(getBaseContext(), color));

        snackBar.show();
    }

    private void showConnectivityMessage(String message) {
        Toolbar toolbar = findViewById(R.id.toolBar);
        if (toolbar != null) {
            toolbar.showErrorMessage(message);
        }
    }

    private void hideConnectivityMessage() {
        Toolbar toolbar = findViewById(R.id.toolBar);
        if (toolbar != null) {
            toolbar.hideErrorMessage();
        }
    }

    @Override
    public void requestFileReadPermission(boolean isForCamera) {
        this.isForCamera = isForCamera;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Log.e(getClass().getSimpleName(), "Show an explanation to the user *asynchronously* -- don't block");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_INTENT_ID);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_INTENT_ID);
            }
        } else {
            if (isForCamera) {
                requestCameraGalleryPermission();
            } else {
                getFileFromProvider();
            }
        }
    }

    private void requestCameraGalleryPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                Log.e(getClass().getSimpleName(), "Show an explanation to the user *asynchronously* -- don't block");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PICK_FROM_GALLERY_INTENT_ID);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PICK_FROM_GALLERY_INTENT_ID);
            }
        } else {
            getImageProvider();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case READ_EXTERNAL_STORAGE_INTENT_ID: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (isForCamera) {
                        requestCameraGalleryPermission();
                    } else {
                        getFileFromProvider();
                    }
                } else {
                    Log.e(getClass().getSimpleName(), "onRequestStoragePermissionsResult -> Permission not granted!");
                }
                break;
            }
            case PICK_FROM_GALLERY_INTENT_ID: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getImageProvider();
                } else {
                    Log.e(getClass().getSimpleName(), "onRequestGalleryPermissionsResult -> Permission not granted!");
                }
                break;
            }
        }
    }

    private void getFileFromProvider() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intent, CHOOSE_FILE_STORAGE_INTENT_ID);
    }

    private void getImageProvider() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = createImageFile();
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.orquitech.development.cuencaVerde.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_FULL_PHOTO_INTENT_ID);
            }
        }
    }

    private File createImageFile() {
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File file = new File(storageDir, IMAGE_FILE_NAME + ".jpg");
        currentPhotoPath = file.getAbsolutePath();
        return file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CHOOSE_FILE_STORAGE_INTENT_ID:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Log.i(getClass().getSimpleName(), "Got files");
                        Bundle bundle = new Bundle();
                        bundle.putInt(RxBus.CODE, RxBus.ON_FILES_RESULT);
                        bundle.putParcelable(RxBus.PAYLOAD, data);
                        bus.publish(bundle);
                        break;
                    case Activity.RESULT_CANCELED:
                        Log.e(getClass().getSimpleName(), "Error getting file");
                        break;
                }
                break;
            case CAMERA_INTENT_ID:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Log.i(getClass().getSimpleName(), "Got images");
                        Bundle extras = data.getExtras();
                        if (extras != null) {
                            Bitmap bitmap = (Bitmap) extras.get("data");
                            onImageResult(bitmap);
                        }
                        break;
                    case Activity.RESULT_CANCELED:
                        Log.e(getClass().getSimpleName(), "Error getting images");
                        break;
                }
                break;
            case CAMERA_FULL_PHOTO_INTENT_ID:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Log.i(getClass().getSimpleName(), "Got images");
                        try {
                            File file = createImageFile();
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(file));
                            FilesUtils.deleteFile(this, file.getName());
                            if (bitmap != null) {
                                onImageResult(bitmap);
                            }
                        } catch (IOException e) {
                            Log.e(getClass().getSimpleName(), e.getMessage());
                        }
                        break;
                    case Activity.RESULT_CANCELED:
                        Log.e(getClass().getSimpleName(), "Error getting images");
                        break;
                }
                break;
            case AppViewsFactory.TASK_DETAIL_VIEW:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        if (getSupportFragmentManager().findFragmentByTag(TasksFragment.class.getName()) != null) {
                            TasksFragment tasksFragment = (TasksFragment) getSupportFragmentManager().findFragmentByTag(TasksFragment.class.getName());
                            tasksFragment.refreshTasks();
                        }
                        showMessage(TextUtil.getString(this, R.string.success), R.color.colorSecondary);
                        break;
                }
                break;
        }
    }

    protected void onImageResult(Bitmap bitmap) {

    }

    @Override
    public void changeToView(int viewType) {
        if (viewType == AppViewsFactory.PREDIO_POTENCIAL_VIEW) {
            changeToActivity(viewType, new Bundle());
        } else {
            changeToView(viewType, new Bundle(), true);
        }
    }

    @Override
    public void changeToViewWithSharedElement(View sharedElement, int viewType, Bundle bundle) {
        changeToSharedView(sharedElement, viewType, bundle);
    }

    public void changeToView(int viewType, Bundle bundle) {
        changeToView(viewType, bundle, true);
    }

    public void changeToView(int viewType, Bundle bundle, boolean shouldReplace) {
        BaseFragment fragment = viewsFactory.getAppFragmentView(viewType, bundle);
        switch (viewType) {
            case (AppViewsFactory.DASHBOARD_VIEW):
                sendOneSignalId();
                break;
        }
        if (fragment != null) {
            fragment.animate(true);
            fragment.setEnter(R.anim.fade_in);
            fragment.setExit(R.anim.fade_out);

            if (shouldReplace) {
                replaceFragment(fragment);
            } else {
                addFragment(fragment);
            }
        }
    }

    public void changeToSharedView(View sharedElement, int viewType, Bundle bundle) {
        BaseFragment fragment = viewsFactory.getAppFragmentView(viewType, bundle);
        if (fragment != null) {
            fragment.animate(true);
            addSharedFragment(sharedElement, fragment);
        }
    }

    protected void sendOneSignalId(String userId) {
        apiManager.sendOneSignalId(userId);
    }

    protected void sendOneSignalId() {
        if (preferencesManager != null) {
            String oneSignalId = (String) preferencesManager.get(AppPreferencesObjectFactory.ONE_SIGNAL_ID, String.class);
            if (!TextUtils.isEmpty(oneSignalId)) {
                apiManager.sendOneSignalId(oneSignalId);
            }
        }
    }

    @Override
    public void changeToActivity(int viewId, Bundle bundle) {
        Class activityClass = viewsFactory.getActivityClass(viewId);
        if (activityClass != null) {
            Intent intent = new Intent(this, activityClass);
            intent.putExtras(bundle);
            startActivityForResult(intent, viewId);
        }
    }

    public void showToast(String message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getContext(), message, duration);
        toast.show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public int getDimension(int dimensionResource) {
        return (int) getResources().getDimension(dimensionResource);
    }

    protected void showProgressDialog(DialogListenerAdapter listener) {
        dialogManager.showDialog(getContext(), DialogsFactory.PROGRESS_BAR, new DialogListenerAdapter() {
            @Override
            public void onDismiss() {
                listener.onDismiss();
            }
        });
    }

    protected void fadeInButton(android.view.View... views) {
        for (android.view.View view : views) {
            view.animate()
                    .alpha(1f)
                    .setStartDelay(150)
                    .setDuration(300)
                    .start();
        }
    }

    protected void fadeOutButton(android.view.View... views) {
        for (android.view.View view : views) {
            view.animate()
                    .alpha(0f)
                    .setStartDelay(0)
                    .setDuration(300)
                    .start();
        }
    }
}
