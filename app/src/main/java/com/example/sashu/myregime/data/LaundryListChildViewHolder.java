package com.example.sashu.myregime.data;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.example.sashu.myregime.R;

public class LaundryListChildViewHolder extends ChildViewHolder {

    public TextView mTestTextView;

    public LaundryListChildViewHolder(View itemView) {
        super(itemView);

        mTestTextView = (TextView) itemView.findViewById(R.id.child_list_item_crime_date_text_view);

    }
}
