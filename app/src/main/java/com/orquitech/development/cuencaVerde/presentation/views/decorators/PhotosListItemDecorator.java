package com.orquitech.development.cuencaVerde.presentation.views.decorators;

import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public class PhotosListItemDecorator extends RecyclerView.ItemDecoration {

    private final boolean isPortrait;
    private final int space;

    public PhotosListItemDecorator(int space, boolean isPortrait) {
        this.isPortrait = isPortrait;
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);

        outRect.left = 0;
        outRect.top = 0;
        outRect.right = space;
        outRect.bottom = space;

        if (isPortrait) {
            if (position == 0 || position == 1) {
                outRect.top = space;
            }
            if (position % 2 == 0) {
                outRect.left = space;
            }
        } else {
            if (position == 0 || position == 1 || position == 2) {
                outRect.top = space;
            }
            if (position % 3 == 0) {
                outRect.left = space;
            }
        }
    }
}
