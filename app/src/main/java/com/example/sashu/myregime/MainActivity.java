package com.example.sashu.myregime;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sashu.myregime.data.ClothBundleClass;
import com.example.sashu.myregime.data.ClothClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    TextView mText;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mLaundryRef = mRootRef.child("Laundry");
    ArrayList<DatabaseReference> laundryRefList;
    ClothBundleClass clothes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = (TextView) findViewById(R.id.textViewTest);

        ClothClass cloth1 = new ClothClass("Jeans", true);
        ClothClass cloth2 = new ClothClass("Shirt", false);
        /*
        ArrayList<ClothClass> clothClassArrayList = new ArrayList<>();

        clothClassArrayList.add(cloth1);
        clothClassArrayList.add(cloth2);

        ClothBundleClass clothBundleClass = new ClothBundleClass(clothClassArrayList, new Date());

        DatabaseReference temp = mLaundryRef.child("Laundry for " + clothBundleClass.getDate().toString());
        temp.setValue(clothBundleClass);

        temp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                clothes = dataSnapshot.getValue(ClothBundleClass.class);

                mText.setText("Laundry For: " + clothes.getDate());

                ArrayList<ClothClass> list = new ArrayList<ClothClass>();

                list = (ArrayList<ClothClass>) clothes.getClothes();

                Toast.makeText(MainActivity.this, list.get(0).getName(), Toast.LENGTH_SHORT).show();

                //Toast.makeText(MainActivity.this, list.get(1).getName(), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        })*/


    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
