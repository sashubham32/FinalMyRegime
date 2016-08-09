package com.example.sashu.myregime.data;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sashu.myregime.R;

import java.util.List;

public class ClothesAdapter extends RecyclerView.Adapter<ClothesAdapter.ClothesViewHolder> {

    private List<ClothBundleClass> clothesList;

    public ClothesAdapter ( List<ClothBundleClass> clothesList ){
        this.clothesList = clothesList;
    }

    @Override
    public ClothesAdapter.ClothesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_view, parent, false);

        return new ClothesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ClothesAdapter.ClothesViewHolder holder, int position) {

        ClothBundleClass clothes = clothesList.get(position);
        holder.laundryDateTextView.setText(Common.formatDate(clothes.getDate()));
        holder.numberOfClothesTextView.setText("Number of Clothes: " + String.valueOf(clothes.getSize()));
        holder.isReturnedTextView.setText("Clothes Not Returned");

    }

    @Override
    public int getItemCount() {
        return clothesList.size();
    }

    public static class ClothesViewHolder extends RecyclerView.ViewHolder {

        protected TextView laundryDateTextView;
        protected TextView numberOfClothesTextView;
        protected TextView isReturnedTextView;

        public ClothesViewHolder(View v) {
            super(v);
            laundryDateTextView =  (TextView) v.findViewById(R.id.title);
            numberOfClothesTextView = (TextView)  v.findViewById(R.id.numberOfClothesTextView);
            isReturnedTextView = (TextView)  v.findViewById(R.id.isReturnedTextView);
        }
    }

}
