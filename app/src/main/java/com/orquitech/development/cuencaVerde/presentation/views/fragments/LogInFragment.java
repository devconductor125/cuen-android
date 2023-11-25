//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.EditorInfo;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.databinding.FragmentLogInBinding;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.factories.ViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;
import com.orquitech.development.cuencaVerde.presentation.views.activities.MainActivity;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.DialogListenerAdapter;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.factories.DialogsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.utils.ViewUtil;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.LogInViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;

import javax.inject.Inject;

public class LogInFragment extends BaseFragment implements LogInViewMvvm.View, AppView {

    private static final String LOG_IN_CREDENTIALS = "logInCredentials";
    private static final String LOG_IN_STATUS = "logInStatus";
    private FragmentLogInBinding binding;
    private FragmentListener activity;
    private LogInViewMvvm.ViewModel viewModel;
    private boolean isFragmentAnimate;
    private boolean isLoggingIn;

    @Inject
    App app;

    @Inject
    ViewModelsFactory viewModelFactory;

    @Inject
    ViewsFactory viewFactory;

    public static LogInFragment getInstance(Bundle data) {
        LogInFragment fragment = new LogInFragment();
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
        getComponent().inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_log_in, container, false);
        viewModel = (LogInViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.LOG_IN_VIEW_MODEL);
        binding.setViewModel(viewModel);
        if (savedInstanceState == null) {
            animateViews();
        } else {
            viewModel.setLogInCredentials(savedInstanceState.getParcelable(LOG_IN_CREDENTIALS));
            if (savedInstanceState.getBoolean(LOG_IN_STATUS)) {
                showProgressDialog();
            }
        }
        setUpKeyActionListeners();
        ((MainActivity) activity).hideNavigationBar();
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.onReadyForSubscriptions();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dialogManager.dismissDialog();
        viewModel.clearSubscriptions();
    }

    private void setUpKeyActionListeners() {
        binding.password.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.logIn(null);
            }
            return false;
        });
    }

    @Override
    public void onLogInTriggered() {
        showProgressDialog();
    }

    private void showProgressDialog() {
        hideKeyboard(binding.getRoot());
        isLoggingIn = true;
        dialogManager.showDialog(getContext(), DialogsFactory.PROGRESS_BAR, new DialogListenerAdapter() {
            @Override
            public void onDismiss() {
                isLoggingIn = false;
                viewModel.onLogInCancelled();
            }
        });
    }

    @Override
    public void onLogInSuccess() {
        isFragmentAnimate = true;
        isLoggingIn = false;
        dialogManager.dismissDialog();
        activity.changeToView(AppViewsFactory.DASHBOARD_VIEW);
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).evaluateRole();
        }
    }

    @Override
    public void onInvalidUsername() {
        activity.showToast(TextUtil.getString(getContext(), R.string.invalid_username));
        //binding.usernameContainer.setError(TextUtil.getString(getContext(), R.string.invalid_username));
    }

    @Override
    public void onInvalidPassword() {
        activity.showToast(TextUtil.getString(getContext(), R.string.invalid_password));
        //binding.passwordContainer.setError(TextUtil.getString(getContext(), R.string.invalid_password));
    }

    @Override
    public void onLogInError() {
        dialogManager.dismissDialog();
    }

    private void animateViews() {
        ViewUtil.measureView(binding.getRoot(), () -> {

            binding.logo.setAlpha(0f);
            float logoTargetY = binding.logo.getY();
            binding.logo.setY(-binding.logo.getHeight());

            binding.logo.animate()
                    .alpha(1f)
                    .y(logoTargetY)
                    .setDuration(600)
                    .setStartDelay(300)
                    .setInterpolator(new DecelerateInterpolator())
                    .start();

            binding.logoName.setAlpha(0f);
            float logoNameTargetY = binding.logoName.getY();
            binding.logoName.setY(logoNameTargetY + binding.logoName.getHeight() / 2);

            binding.logoName.animate()
                    .alpha(1f)
                    .y(logoNameTargetY)
                    .setDuration(600)
                    .setStartDelay(400)
                    .setInterpolator(new DecelerateInterpolator())
                    .start();
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(LOG_IN_CREDENTIALS, viewModel.getLogInCredentials());
        outState.putBoolean(LOG_IN_STATUS, isLoggingIn); //TODO this saves the dialog state
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
    public boolean isFragmentAnimate() {
        return this.isFragmentAnimate;
    }

    @Override
    public int getExit() {
        return R.anim.slide_to_left;
    }

    @Override
    public int getPopExit() {
        return R.anim.slide_to_left;
    }
}
