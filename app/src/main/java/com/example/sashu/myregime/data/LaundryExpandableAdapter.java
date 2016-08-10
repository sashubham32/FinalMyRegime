package com.example.sashu.myregime.data;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.sashu.myregime.R;

import java.util.List;

/**
 * Created by sashu on 10-08-2016.
 */
public class LaundryExpandableAdapter extends ExpandableRecyclerAdapter<LaundryListParentViewHolder, LaundryListChildViewHolder> {

    LayoutInflater mInflater;

    public LaundryExpandableAdapter(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public LaundryListParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {

        View view = mInflater.inflate(R.layout.card_view, viewGroup, false);
        return new LaundryListParentViewHolder(view);

    }

    @Override
    public LaundryListChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {

        View view = mInflater.inflate(R.layout.child_view, viewGroup, false);
        return new LaundryListChildViewHolder(view);

    }

    @Override
    public void onBindParentViewHolder(LaundryListParentViewHolder laundryListParentViewHolder, int i, Object o) {
        ClothBundleClass clothBundleClass = (ClothBundleClass) o;
        laundryListParentViewHolder.mAreClothesReturnedTextView.setText("Clothes Not Returned");
        laundryListParentViewHolder.mNumberOfClothesTextView.setText("Number of Clothes: " + clothBundleClass.getClothes().size());
        laundryListParentViewHolder.mTitleTextView.setText(Common.formatDate(clothBundleClass.getDate()));
    }

    @Override
    public void onBindChildViewHolder(LaundryListChildViewHolder laundryListChildViewHolder, int i, Object o) {

        ClothClass cloth = (ClothClass) o;
        laundryListChildViewHolder.mTestTextView.setText(cloth.getName());

    }
}
