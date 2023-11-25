package com.orquitech.development.cuencaVerde.presentation.views.activities.form;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.Action;
import com.orquitech.development.cuencaVerde.data.model.cartaIntencion.CartaIntencion;
import com.orquitech.development.cuencaVerde.databinding.ActivityCartaIntencionBinding;
import com.orquitech.development.cuencaVerde.logic.PrediosManager;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.interfaces.NavigationFlowViewGetter;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ListDialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.FormFragmentListener;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.cartaIntencion.FormCartaIntencionFragmentPart1;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.cartaIntencion.CartaIntencionViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.cartaIntencion.FormCartaIntencionBaseViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.cartaIntencion.FormCartaIntencionViewMvvm;

import javax.inject.Inject;

public class CartaIntencionActivityForm extends BaseActivityForm implements CartaIntencionViewMvvm.View, ListDialogListener, FormFragmentListener {

    private ActivityCartaIntencionBinding binding;
    private CartaIntencionViewMvvm.ViewModel viewModel;

    @Inject
    PrediosManager prediosManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_carta_intencion);
        getComponent().inject(this);

        viewModel = (CartaIntencionViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.FORM_CARTA_INTENCION_MAIN_VIEW_VIEW_MODEL);
        binding.setViewModel(viewModel);

        if (savedInstanceState == null) {
            bundle = getIntent().getExtras();
            changeToView(AppViewsFactory.FORM_CARTA_INTENCION_MAIN_VIEW_PART_1, bundle);
        }

        setUpProgressBar();
        setProgressBarViews(progressBarPosition);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onReadyForSubscriptions();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.clearSubscriptions();
        FormCartaIntencionViewMvvm.View currentFragment = (FormCartaIntencionViewMvvm.View) getCurrentFragment();
        CartaIntencion cartaIntencion = currentFragment.getCartaIntencion();
        if (cartaIntencion != null) {
            prediosManager.saveCartaIntencion(cartaIntencion);
        }
    }

    @Override
    public void changeToView(int viewId, int newPosition) {
        FormCartaIntencionBaseViewMvvm.View currentFragment = (FormCartaIntencionBaseViewMvvm.View) getCurrentFragment();
        CartaIntencion cartaIntencion = currentFragment.getCartaIntencion();
        bundle.putParcelable(PARCELABLE, cartaIntencion);
        super.changeToView(viewId, bundle);
        viewModel.saveCartaIntencion(cartaIntencion);
        setProgressBarViews(newPosition);
    }

    @Override
    public void onItemClick(Bundle bundle) {
        FormCartaIntencionBaseViewMvvm.View currentFragment = (FormCartaIntencionBaseViewMvvm.View) getCurrentFragment();
        Action action = bundle.getParcelable(RxBus.PAYLOAD);
        if (action != null && currentFragment instanceof FormCartaIntencionFragmentPart1) {
            ((FormCartaIntencionFragmentPart1) currentFragment).setPropertyCorrelation(action.getTitle());
        }
    }

    @Override
    public void showMessage(String string, int color) {
        showSnackBar(string, color, Snackbar.LENGTH_SHORT, binding.mainContainer);
    }

    @Override
    protected NavigationFlowViewGetter getNavigationFlowViewGetter() {
        return new NavigationFlowViewGetter() {
            @Override
            public View getRoot() {
                return binding.getRoot();
            }

            @Override
            public View getBottomNavigation() {
                return binding.bottomNavigation;
            }

            @Override
            public View getCircle1() {
                return binding.circle1;
            }

            @Override
            public View getCircle2() {
                return binding.circle1;
            }

            @Override
            public View getProgressBar() {
                return binding.progressBar;
            }

            @Override
            public View getProgressBarTop() {
                return binding.progressBarTop;
            }

            @Override
            public ViewGroup getMainContainer() {
                return binding.mainContainer;
            }
        };
    }
}
