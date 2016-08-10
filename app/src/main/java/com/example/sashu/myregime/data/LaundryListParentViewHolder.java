package com.example.sashu.myregime.data;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.example.sashu.myregime.R;


public class LaundryListParentViewHolder extends ParentViewHolder {

    public TextView mTitleTextView;
    public TextView mNumberOfClothesTextView;
    public TextView mAreClothesReturnedTextView;
    public ImageButton mParentDropDownArrow;

    public LaundryListParentViewHolder(View itemView) {
        super(itemView);

        mTitleTextView = (TextView) itemView.findViewById(R.id.title);
        mParentDropDownArrow = (ImageButton) itemView.findViewById(R.id.parent_list_item_expand_arrow);
        mNumberOfClothesTextView = (TextView) itemView.findViewById(R.id.numberOfClothesTextView);
        mAreClothesReturnedTextView = (TextView) itemView.findViewById(R.id.isReturnedTextView);
    }
}