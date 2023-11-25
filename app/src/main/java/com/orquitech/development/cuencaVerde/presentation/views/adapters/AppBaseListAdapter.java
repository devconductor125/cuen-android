package com.orquitech.development.cuencaVerde.presentation.views.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.factories.ItemView;
import com.orquitech.development.cuencaVerde.presentation.factories.ViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.ListAdapter;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;
import com.orquitech.development.cuencaVerde.presentation.views.lists.ListItemViewHolder;

import java.util.ArrayList;
import java.util.List;

public class AppBaseListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ListAdapter {

    private final List<ItemView> viewsList;
    private final AppListView view;
    private List<Item> items;

    private ViewsFactory viewFactory;

    public AppBaseListAdapter(ViewsFactory viewFactory, AppListView view) {
        this.viewFactory = viewFactory;
        this.view = view;
        viewsList = new ArrayList<>();
        notifyItemsSet();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = (View) this.viewFactory.getItemView(viewType, this.view);
        viewsList.add((ItemView) view);
        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemView itemView = (ItemView) holder.itemView;
        itemView.bind(getItem(position), position);
    }

    @Override
    public void setItems(List<Item> items) {
        try {
            this.items = new ArrayList<>();
            if (items != null) {
                this.items.addAll(items);
            } else {
                this.items = new ArrayList<>();
            }
            this.notifyItemRangeChanged(0, this.items.size());
        } catch (Exception ex) {
            Log.e(getClass().getName(), ex.getMessage());
        }
    }

    @Override
    public void addNewItems(List<Item> items) {
        for (Item item : items) {
            addItem(item);
        }
    }

    @Override
    public void removeAllItems() {
        int numberOfItemsToRemove = getItemCount();
        List<Item> itemsToRemove = new ArrayList<>();
        if (items != null && items.size() >= numberOfItemsToRemove) {
            for (int i = items.size() - 1; i >= items.size() - numberOfItemsToRemove; i--) {
                itemsToRemove.add(items.get(i));
            }
            for (Item item : itemsToRemove) {
                removeItem(item);
            }
        }
    }

    @Override
    public void removeLastItems(int numberOfItemsToRemove) {
        List<Item> itemsToRemove = new ArrayList<>();
        if (items != null && items.size() > numberOfItemsToRemove) {
            for (int i = items.size() - 1; i >= items.size() - numberOfItemsToRemove; i--) {
                itemsToRemove.add(items.get(i));
            }
            for (Item item : itemsToRemove) {
                removeItem(item);
            }
        }
    }

    @Override
    public void addNewItemsToBeginning(List<Item> newItems) {
        if (items == null) {
            items = new ArrayList<>();
        }
        for (Item item : newItems) {
            items.add(0, item);
        }
        notifyItemRangeInserted(0, newItems.size() - 1);
    }

    @Override
    public void removeItem(Item item) {
        int index = this.items.indexOf(item);
        if (index != -1) {
            this.items.remove(index);
            this.notifyItemRemoved(index);
        }
    }

    public void addItem(Item item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    @Override
    public List<Item> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }
        return items;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            items = new ArrayList<>();
        }
        return items.size();
    }

    @Override
    public boolean hasNoItems() {
        return items.size() == 0;
    }

    @Override
    public void addItemToIndex(int index, Item item) {
        if (items != null) {
            if (index >= items.size()) {
                items.add(item);
                notifyItemInserted(items.size() - 1);
            } else {
                items.add(index, item);
                notifyItemInserted(index);
            }
        }
    }

    public Item getItem(int position) {
        return items.get(position);
    }

    private void notifyItemsSet() {
        notifyDataSetChanged();
    }
}
