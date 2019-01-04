package com.vkhooda24.knowyourcountry.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.vkhooda24.knowyourcountry.R;
import com.vkhooda24.knowyourcountry.domain.model.Countries;
import com.vkhooda24.knowyourcountry.ui.activities.CountriesListActivity;

import java.util.List;

/**
 * Created by Vikram Hooda on 12/23/18.
 */
public class CountriesRecyclerAdapter extends RecyclerView.Adapter<CountriesRecyclerAdapter.RecyclerViewHolder> {


    private final CountriesListActivity countriesListActivity;
    private final List<Countries> countriesList;
    private OnClickCountryName onItemClickListener;

    public CountriesRecyclerAdapter(CountriesListActivity countriesListActivity, List<Countries> countries) {
        this.countriesListActivity = countriesListActivity;
        this.countriesList = countries;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(countriesListActivity).inflate(R.layout.adapter_countries_recycler_view, viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int position) {

        String countryName = countriesList.get(position).getName();
        recyclerViewHolder.countryNameTextView.setText(countryName);

        recyclerViewHolder.itemView.setOnClickListener(v -> onItemClickListener.onClickItem(countryName));
    }

    @Override
    public int getItemCount() {
        return countriesList.size();
    }


    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView countryNameTextView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            countryNameTextView = itemView.findViewById(R.id.countryNameTextView);
        }
    }

    public void setOnItemClickListener(OnClickCountryName onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnClickCountryName {
        void onClickItem(String countryName);
    }
}
