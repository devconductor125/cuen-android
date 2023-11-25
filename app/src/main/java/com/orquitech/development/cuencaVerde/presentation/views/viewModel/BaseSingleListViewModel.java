package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.ListHeader;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.ListAdapter;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseSingleListViewModel extends BaseViewModel {

    protected AppListView view;
    protected CompositeDisposable itemsDisposable;
    protected ListAdapter adapter;

    public BaseSingleListViewModel(AppListView view) {
        super(view);
        this.view = view;
        itemsDisposable = new CompositeDisposable();
        getComponent().inject(this);
        initAdapterAndSendToView(view);
    }

    public void onReadyForSubscriptions() {
        subscribeToBus();
    }

    private void initAdapterAndSendToView(AppListView view) {
        if (adapter == null) {
            adapter = listAdapterFactory.getListAdapter(view);
            this.view.onListAdapterReady(adapter);
        }
    }

    protected void subscribeToDashboardItemsListObservable(Observable<List<Item>> observable, int viewType) {
        itemsDisposable.add(observable
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> {
                })
                .doOnNext(items -> {
                    addHeaders(items);
                    adapter.removeAllItems();
                    adapter.setItems(items);
                    if (items.size() == 0) {
                        adapter.removeAllItems();
                    }
                    view.onGotItems();
                })
                .subscribe());
    }

    protected void subscribeToItemsListObservable(Observable<List<Item>> observable, int viewType) {
        itemsDisposable.add(observable
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> {
                })
                .doOnNext(items -> {
                    boolean areValidItems = items.size() > 0 && items.get(0).getType() == viewType;
                    if (areValidItems) {
                        adapter.removeAllItems();
                        addPendingItemsToAdd(items);
                        adapter.addNewItems(items);
                    }
                    if (items.size() == 0) {
                        adapter.removeAllItems();
                    }
                    view.onGotItems();
                })
                .subscribe());
    }

    private void addPendingItemsToAdd(List<Item> items) {
        List<Item> pendingItemsToAdd = getPendingItemsToAdd();
        if (items.size() > 0) {
            items.addAll(0, pendingItemsToAdd);
        } else {
            items.addAll(pendingItemsToAdd);
        }
    }

    protected List<Item> getPendingItemsToAdd() {
        return new ArrayList<>();
    }

    private void addHeaders(List<Item> items) {
        if (items.size() > 0) {
            ListIterator<Item> iterator = items.listIterator();
            while (iterator.hasNext()) {
                if (iterator.next().getType() == AppViewsFactory.LIST_HEADER_ITEM_VIEW) {
                    iterator.remove();
                }
            }
            ListHeader tasksHeader = new ListHeader();
            tasksHeader.setType(AppViewsFactory.LIST_HEADER_ITEM_VIEW);
            tasksHeader.setText(TextUtil.getString(view.getContext(), R.string.due_tasks));
            // items.add(0, tasksHeader);
        }
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getType() == AppViewsFactory.PROJECTS_LIST_ITEM_VIEW) {
                ListHeader projectsHeader = new ListHeader();
                projectsHeader.setType(AppViewsFactory.LIST_HEADER_ITEM_VIEW);
                projectsHeader.setText(TextUtil.getString(view.getContext(), R.string.last_projects));
                items.add(i, projectsHeader);
                break;
            }
        }
    }

    private void subscribeToBus() {
        disposables.add(bus.getObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(bundle -> {
                    switch (bundle.getInt(RxBus.CODE)) {
                        case RxBus.SERVICE_ERROR:
                            view.onGetItemsError();
                            break;
                    }
                })
                .subscribe());
    }

    @Override
    public void clearSubscriptions() {
        super.clearSubscriptions();
        itemsDisposable.clear();
    }
}
