package com.orquitech.development.cuencaVerde.presentation.views.dialogs.factories;

import android.content.Context;
import android.os.Bundle;

import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.DialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ActionSelectorDialog;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.AppDialog;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ConfirmationDialogFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.FeatureDetailsFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.FeatureDetailsWithCommentFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ItemsListFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.MapMarkerCommentFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.MapMonitorMaintenanceCommentFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.NewTrackDialog;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.OfflineDialogFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ProgressBarDialog;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.SavePredioPotencialDialog;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.SaveTrackDialog;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseDialogFragment;

public class DialogsFactory {
    public static final int NEW_TRACK = 100;
    public static final int SAVE_TRACK = 200;
    public static final int ACTION_SELECTOR = 300;
    public static final int COMMENT_MAP_MARKER = 310;
    public static final int PROGRESS_BAR = 400;
    public static final int SAVE_PREDIO_POTENCIAL = 500;

    public static final int PROPERTY_CORRELATION_PICKER = 1000;
    public static final int DEPENDENCY_PICKER = 1100;
    public static final int PQRS_TYPE_PICKER = 1200;
    public static final int MAP_ACTIONS = 2000;
    public static final int MAP_ACTIONS_POINTS = 2100;
    public static final int MAP_AREAS = 2150;
    public static final int MAP_MATERIALS = 2200;
    public static final int MAP_COMMENT_MARKER = 3000;
    public static final int MAP_MONITOR_MAINTENANCE_COMMENT_MARKER = 3100;
    public static final int ACTION_TYPE = 4000;
    public static final int POINT_ACTION_TYPE = 4010;
    public static final int FEATURE_DETAIL = 5000;
    public static final int FEATURE_DETAIL_WITH_COMMENT = 5010;
    public static final int CONFIRMATION = 6000;
    public static final int OFFLINE = 7000;

    public AppDialog getDialog(int type, Context context, DialogListener listener) {
        AppDialog dialog;
        switch (type) {
            case NEW_TRACK:
                dialog = new NewTrackDialog(context, listener);
                break;
            case SAVE_TRACK:
                dialog = new SaveTrackDialog(context, listener);
                break;
            case ACTION_SELECTOR:
                dialog = new ActionSelectorDialog(context, listener);
                break;
            case PROGRESS_BAR:
                dialog = new ProgressBarDialog(context, listener);
                break;
            case SAVE_PREDIO_POTENCIAL:
                dialog = new SavePredioPotencialDialog(context, listener);
                break;
            default:
                dialog = null;
        }
        return dialog;
    }

    public BaseDialogFragment getDialogFragment(int type, Bundle bundle) {
        BaseDialogFragment dialog;
        switch (type) {
            case PROPERTY_CORRELATION_PICKER:
            case MAP_AREAS:
            case MAP_ACTIONS:
            case MAP_ACTIONS_POINTS:
            case MAP_MATERIALS:
            case ACTION_TYPE:
            case POINT_ACTION_TYPE:
            case DEPENDENCY_PICKER:
            case PQRS_TYPE_PICKER:
                dialog = ItemsListFragment.getInstance(bundle);
                break;
            case MAP_COMMENT_MARKER:
                dialog = MapMarkerCommentFragment.getInstance(bundle);
                break;
            case FEATURE_DETAIL:
                dialog = FeatureDetailsFragment.getInstance(bundle);
                break;
            case FEATURE_DETAIL_WITH_COMMENT:
                dialog = FeatureDetailsWithCommentFragment.getInstance(bundle);
                break;
            case CONFIRMATION:
                dialog = ConfirmationDialogFragment.getInstance(bundle);
                break;
            case OFFLINE:
                dialog = OfflineDialogFragment.getInstance(bundle);
                break;
            case MAP_MONITOR_MAINTENANCE_COMMENT_MARKER:
                dialog = MapMonitorMaintenanceCommentFragment.getInstance(bundle);
                break;
            default:
                dialog = null;
        }
        return dialog;
    }
}
