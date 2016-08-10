package com.example.sashu.myregime.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.ArrayRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.sashu.myregime.R;
import com.example.sashu.myregime.data.ClothBundleClass;
import com.example.sashu.myregime.data.ClothClass;
import com.example.sashu.myregime.data.ClothesAdapter;
import com.example.sashu.myregime.data.LaundryExpandableAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UILaundry extends AppCompatActivity {

    FloatingActionButton addLaundryButton;
    ListView laundryListView;
    DatabaseReference mRootRef;
    DatabaseReference mLaundryRef;
    ClothBundleClass clothesBundle;
    ArrayList<ClothClass> clothesList;
    ArrayList<String> stringArrayList;
    ArrayAdapter listViewAdapter;
    RecyclerView clothesListRecyclerView;
    List<ClothBundleClass> clothesBundleList;
    ProgressDialog progressDialog;
    List<String> strings;
    List<ParentObject> parentObjects;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uilaundry);

        init();

    }

    public void init(){

        parentObjects = new ArrayList<>();

        clothesBundleList = new ArrayList<>();
        addLaundryButton = (FloatingActionButton) findViewById(R.id.add_laundry_button);
        clothesBundle = new ClothBundleClass();
        progressDialog = new ProgressDialog(UILaundry.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Wait Please!");


        mRootRef = FirebaseDatabase.getInstance().getReference();
        mLaundryRef = mRootRef.child("Laundry");

        addLaundryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UILaundry.this, AddLaundryUI.class));
            }
        });

        progressDialog.show();
        mLaundryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for ( DataSnapshot postSnapshot : dataSnapshot.getChildren() ){

                    clothesBundle = postSnapshot.getValue(ClothBundleClass.class);
                    clothesBundleList.add(clothesBundle);

                }

                for ( ClothBundleClass clothBundleClass : clothesBundleList ){


                    List<Object> list = new ArrayList<>();

                    for ( ClothClass cloth : clothBundleClass.getClothes() ) {
                        list.add(cloth);
                    }

                    clothBundleClass.setChildObjectList(list);
                    parentObjects.add(clothBundleClass);
                }

                clothesListRecyclerView = (RecyclerView) findViewById(R.id.clothesList);
                clothesListRecyclerView.setHasFixedSize(true);
                LinearLayoutManager llm = new LinearLayoutManager(UILaundry.this);
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                clothesListRecyclerView.setLayoutManager(llm);

                /*ClothesAdapter ca = new ClothesAdapter(clothesBundleList);

                clothesListRecyclerView.setAdapter(ca);*/

                LaundryExpandableAdapter laundryExpandableAdapter = new LaundryExpandableAdapter(UILaundry.this, parentObjects);
                laundryExpandableAdapter.setCustomParentAnimationViewId(R.id.parent_list_item_expand_arrow);
                laundryExpandableAdapter.setParentClickableViewAnimationDefaultDuration();
                laundryExpandableAdapter.setParentAndIconExpandOnClick(true);

                clothesListRecyclerView.setAdapter(laundryExpandableAdapter);

                progressDialog.hide();

                //listViewAdapter = new ArrayAdapter<>(UILaundry.this,android.R.layout.simple_list_item_1, stringArrayList);
                //laundryListView.setAdapter(listViewAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
