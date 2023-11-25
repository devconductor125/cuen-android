//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments;

import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;

import com.orquitech.development.cuencaVerde.CuencaVerdeApp;
import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.dependencyInjection.component.DaggerViewsComponent;
import com.orquitech.development.cuencaVerde.dependencyInjection.component.ViewsComponent;
import com.orquitech.development.cuencaVerde.logic.DialogManager;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.AppDialogManager;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.factories.DialogsFactory;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseFragment extends Fragment {

    protected static final String LIST_STATE = "listView";
    protected static final String LIST_OF_PROPERTY_CORRELATIONS = "listOfPropertyCorrelationItems";
    protected static final String LIST_OF_DEPENDENCIES = "listOfDependenciesItems";
    protected static final String LIST_OF_PQRS_TYPE = "listOfPQRSTypeItems";
    public static final String MEDICION_DEL_PREDIO = "1";
    public static final String MONITOREO = "monitoreo";
    public static final String CONTRACT_SIEMBRA = "siembra";
    public static final String EROSIVE_PROCESS = "procesoErosivo";
    public static final String HYDROLOGICAL_PROCESS = "procesoRecuroHidrico";
    public static final String PSA = "psa";
    public static final String EXECUTION = "ejecución";
    public static final String COMMUNICATIONS = "comunicaciones";
    public static final String CONTRACTOR = "contratista";
    public static final String ENCUESTA = "3";

    protected CompositeDisposable disposables;

    protected FragmentListener activity;
    protected DialogManager dialogManager;
    private ViewsComponent component;
    private int enter = R.anim.slide_from_right;
    private int exit = R.anim.slide_to_left;
    private int popEnter = R.anim.slide_from_right;
    private int popExit = R.anim.slide_to_left;
    private boolean addOnStack = true;
    protected boolean animate = false;
    private int container = R.id.container;
    private boolean canBack = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        component = DaggerViewsComponent.builder()
                .appComponent(CuencaVerdeApp.getApp().getAppComponent())
                .build();

        disposables = new CompositeDisposable();
        dialogManager = new AppDialogManager(new DialogsFactory());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initListener(context);
    }

    private void initListener(Context context) {
        try {
            activity = (FragmentListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void setUpToolBar(androidx.appcompat.widget.Toolbar toolBar) {
        toolBar.setNavigationOnClickListener(view -> activity.onBackPressed());
    }

    protected void disableToolBarBackButton(androidx.appcompat.widget.Toolbar toolBar) {
        toolBar.setNavigationIcon(null);
        toolBar.setNavigationOnClickListener(null);
    }

    protected ViewsComponent getComponent() {
        return component;
    }

    public abstract String getName();

    public void backPressed() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    public int getEnter() {
        return enter;
    }

    public BaseFragment setEnter(int enter) {
        this.enter = enter;
        this.popEnter = enter;
        return this;
    }

    public int getExit() {
        return exit;
    }

    public BaseFragment setExit(int exit) {
        this.exit = exit;
        this.popExit = exit;
        return this;
    }

    public int getPopEnter() {
        return popEnter;
    }

    public BaseFragment setPopEnter(int popEnter) {
        this.popEnter = popEnter;
        return this;
    }

    public int getPopExit() {
        return popExit;
    }

    public BaseFragment setPopExit(int popExit) {
        this.popExit = popExit;
        return this;
    }

    public boolean isAddOnStack() {
        return addOnStack;
    }

    public boolean isFragmentAnimate() {
        return animate;
    }

    public int getContainer() {
        return container;
    }

    public BaseFragment addOnStack() {
        this.addOnStack = true;
        return this;
    }

    public BaseFragment animate(boolean value) {
        this.animate = value;
        return this;
    }

    public BaseFragment id(int container) {
        this.container = container;
        return this;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (!isFragmentAnimate()) {
            onEnterAnimationEnd();
            return super.onCreateAnimation(transit, enter, nextAnim);
        }

        Animation anim;
        if (enter) {
            anim = AnimationUtils.loadAnimation(getContext(), this.enter);
        } else {
            anim = AnimationUtils.loadAnimation(getContext(), this.exit);
        }

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                onEnterAnimationEnd();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }
        });
        return anim;
    }

    protected void onEnterAnimationEnd() {
    }

    public boolean canGoBack() {
        return canBack;
    }

    public void setCanBack(boolean canBack) {
        this.canBack = canBack;
    }

    public void onResetState() {
    }

    protected void showKeyboard(View view) {
        if (isAdded()) {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Service.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, 0);
        }
    }

    protected void hideKeyboard(View view) {
        if (isAdded()) {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Service.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void runListItemsLayoutAnimation(final RecyclerView recyclerView) {
        animate(false);
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    public void showErrorMessage(int messageResource) {
        activity.showMessage(getString(messageResource), R.color.colorAccent);
    }
}
