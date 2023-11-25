//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.appbar.AppBarLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.BaseItem;
import com.orquitech.development.cuencaVerde.data.model.PhotographicRegistryPhoto;
import com.orquitech.development.cuencaVerde.databinding.FragmentTaskPhotographyRegistryBinding;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.activities.BaseActivity;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.ListAdapter;
import com.orquitech.development.cuencaVerde.presentation.views.decorators.PhotosListItemDecorator;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.ItemList;
import com.orquitech.development.cuencaVerde.presentation.views.utils.LayoutManagerUtil;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.PhotographyRegistryViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;

import javax.inject.Inject;

public class PhotographyRegistryFragment extends BaseFragment implements PhotographyRegistryViewMvvm.View, ItemList {

    private FragmentTaskPhotographyRegistryBinding binding;
    private PhotographyRegistryViewMvvm.ViewModel viewModel;
    private Bundle savedInstanceState;
    private RecyclerView.LayoutManager layoutManager;
    private Parcelable listState;
    private BaseItem item;
    private AppBarLayout.OnOffsetChangedListener offsetChangedListener;
    protected RecyclerView.ItemDecoration itemDecoration;

    @Inject
    ViewModelsFactory viewModelFactory;

    public static PhotographyRegistryFragment getInstance(Bundle data) {
        PhotographyRegistryFragment fragment = new PhotographyRegistryFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initListener(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        getComponent().inject(this);
        Bundle bundle = getArguments();
        if (bundle != null) {
            item = bundle.getParcelable(RxBus.PAYLOAD);
        }
        offsetChangedListener = (appBarLayout, verticalOffset) -> {
            if (Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() == 0) {
                Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_white_24px);
                upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
                binding.mainToolbar.setNavigationIcon(upArrow);
                binding.mainToolbar.animate()
                        .alpha(1);
            } else {
                Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_white_24px);
                upArrow.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                binding.mainToolbar.setNavigationIcon(upArrow);
                binding.mainToolbar.animate()
                        .alpha(0);
            }
            binding.customFab.collapse();
        };
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            listState = savedInstanceState.getParcelable(LIST_STATE);
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_photography_registry, container, false);
        viewModel = (PhotographyRegistryViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.PHOTOGRAPHY_REGISTRY_LIST_VIEW_MODEL);
        viewModel.setItem(item);
        binding.setViewModel(viewModel);
        initSwipeRefresh();
        initList();
        viewModel.onReadyForSubscriptions();
        setUpToolBar(binding.mainToolbar);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.mainAppbar.addOnOffsetChangedListener(offsetChangedListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.mainAppbar.removeOnOffsetChangedListener(offsetChangedListener);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ((BaseViewModel) viewModel).clearSubscriptions();
    }

    private void initList() {
        layoutManager = LayoutManagerUtil.getPhotosGridListLayoutManager(getContext(), savedInstanceState, LIST_STATE);
        binding.tasksList.setLayoutManager(layoutManager);
        binding.tasksList.setItemAnimator(new DefaultItemAnimator());
    }

    private void initSwipeRefresh() {
        binding.swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        binding.swipeRefresh.setOnRefreshListener(() -> {
            animate(true);
            refreshList();
        });
        binding.swipeRefresh.setRefreshing(true);
    }

    @Override
    public void refreshList() {
        binding.swipeRefresh.setRefreshing(true);
        fadeOutList();
        viewModel.getPhotographicRegistries(true);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle state) {
        super.onSaveInstanceState(state);
        Parcelable listState = binding.tasksList.getLayoutManager().onSaveInstanceState();
        state.putParcelable(LIST_STATE, listState);
    }

    private void initListener(Context context) {
        try {
            activity = (FragmentListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public void onListAdapterReady(ListAdapter adapter) {
        binding.tasksList.setAdapter((RecyclerView.Adapter) adapter);
    }

    @Override
    public void onGotItems() {
        if (listState != null) {
            binding.tasksList.getLayoutManager().onRestoreInstanceState(listState);
            listState = null;
        }
        binding.swipeRefresh.setRefreshing(false);
        setItemDecoration(binding.tasksList, R.dimen.dimen_small);
        fadeInList();
        if (savedInstanceState == null || animate) {
            runListItemsLayoutAnimation(binding.tasksList);
        } else {
            try {
                // binding.tasksList.startLayoutAnimation();
            } catch (Exception ex) {
                Log.d(getClass().getSimpleName(), ex.getMessage());
            }
        }
    }

    protected void setItemDecoration(final RecyclerView recyclerView, int dimensionResource) {
        int space = (int) getResources().getDimension(dimensionResource);
        boolean isPortrait = getContext().getResources().getBoolean(R.bool.is_portrait);
        if (itemDecoration != null) {
            recyclerView.removeItemDecoration(itemDecoration);
        }
        itemDecoration = new PhotosListItemDecorator(space, isPortrait);
        recyclerView.addItemDecoration(itemDecoration);
    }

    private void fadeOutList() {
        binding.tasksList.animate()
                .alpha(0.5f)
                .start();
    }

    private void fadeInList() {
        binding.tasksList.animate()
                .alpha(1f)
                .start();
    }

    @Override
    public void onGetItemsError() {
        binding.swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onItemClicked(Item item) {

    }

    @Override
    public void onItemClicked(View sharedElement, Item item) {
        if (!binding.swipeRefresh.isRefreshing()) {
            Bundle bundle = new Bundle();
            if (item instanceof PhotographicRegistryPhoto) {
                PhotographicRegistryPhoto photographicRegistryPhoto = (PhotographicRegistryPhoto) item;
                bundle.putParcelable(RxBus.PAYLOAD, photographicRegistryPhoto);
                activity.changeToView(AppViewsFactory.PHOTO_DETAIL_VIEW, bundle, false);
            }
        }
    }

    @Override
    public void onBackClick() {
        if (isAdded() && getActivity() != null) {
            getActivity().onBackPressed();
        }
    }

    @Override
    public void onNewPhoto() {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).requestFileReadPermission(true);
        }
    }

    @Override
    public PhotographyRegistryViewMvvm.ViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void onViewModelUpdated() {
        binding.setViewModel(viewModel);
    }

    @Override
    public void addNewPhoto(Bitmap bitmap) {
        viewModel.addNewPhoto(bitmap);
    }
}
