package com.example.sashu.myregime.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sashu.myregime.MainActivity;
import com.example.sashu.myregime.R;
import com.example.sashu.myregime.data.ClothBundleClass;
import com.example.sashu.myregime.data.ClothClass;
import com.example.sashu.myregime.data.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddLaundryUI extends AppCompatActivity {

    Date currentDate;
    TextView dateTextView;
    EditText cloth1EditText;
    EditText cloth2EditText;
    EditText cloth3EditText;
    Button addClothesButton;

    String cloth1;
    String cloth2;
    String cloth3;

    ClothBundleClass clothes;
    ArrayList<ClothClass> clothesList;
    ClothClass tempCloth;

    DatabaseReference mRootRef;
    DatabaseReference mLaundryRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_laundry_ui);

        init();


    }

    public void init(){

        currentDate = new Date();
        dateTextView = (TextView) findViewById(R.id.date_textView);
        dateTextView.setText("Laundry For: " + Common.formatDate(currentDate));
        cloth1EditText = (EditText) findViewById(R.id.cloth1EditText);
        cloth2EditText = (EditText) findViewById(R.id.cloth2EditText);
        cloth3EditText = (EditText) findViewById(R.id.cloth3EditText);
        addClothesButton = (Button) findViewById(R.id.addClothesButton);
        clothesList = new ArrayList<ClothClass>();
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mLaundryRef = mRootRef.child("Laundry");
        addClothesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addClothes();
            }
        });

    }

    public void addClothes(){

        //Get Clothes From EditText

        if ( cloth1EditText.getText().toString() != null && !cloth1EditText.getText().toString().equals(""))
            clothesList.add(new ClothClass(cloth1EditText.getText().toString(), false));
        if ( cloth2EditText.getText().toString() != null && !cloth2EditText.getText().toString().equals(""))
            clothesList.add(new ClothClass(cloth2EditText.getText().toString(), false));
        if ( cloth3EditText.getText().toString() != null && !cloth3EditText.getText().toString().equals(""))
            clothesList.add(new ClothClass(cloth3EditText.getText().toString(), false));

        //Add Clothes to Server

        clothes = new ClothBundleClass(clothesList, currentDate);

        DatabaseReference temp = mLaundryRef.child("Laundry for " + clothes.getDate().toString());
        temp.setValue(clothes);

        Toast.makeText(AddLaundryUI.this, "Clothes Added!", Toast.LENGTH_SHORT).show();

        //Send user to the main page
        finish();
        startActivity(new Intent(AddLaundryUI.this, UILaundry.class));

    }
}
