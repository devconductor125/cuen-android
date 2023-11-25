package com.orquitech.development.cuencaVerde.presentation.views.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;

import java.util.ArrayList;

public class CustomAutoCompleteAdapter extends ArrayAdapter<String> implements Filterable {

    private ArrayList<String> resultList;
    private String[] data;

    public CustomAutoCompleteAdapter(Context context, int textViewResourceId, String[] data) {
        super(context, textViewResourceId);
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public String getItem(int index) {
        return resultList.get(index);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    // Retrieve the autocomplete results.
                    resultList = autocomplete(constraint.toString());

                    // Assign the data to the FilterResults
                    filterResults.values = resultList;
                    filterResults.count = resultList.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
    }

    private ArrayList<String> autocomplete(String constraint) {
        String comparableConstraint = TextUtil.stripAccents(constraint).replaceAll("\\s+","");
        ArrayList<String> result = new ArrayList<>();
        for (String value : data) {
            String comparableString = TextUtil.stripAccents(value).replaceAll("\\s+","");
            if (!TextUtils.isEmpty(comparableString) && comparableString.toLowerCase().contains(comparableConstraint.toLowerCase())) {
                result.add(value);
            }
        }
        return result;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
