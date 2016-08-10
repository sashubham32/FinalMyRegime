package com.example.sashu.myregime.UI;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

import java.lang.reflect.Array;
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
    LinearLayout mainLinearLayout;
    int clothCount;
    Button addEditTextButton;
    ArrayList<EditText> editTextList;

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
        mainLinearLayout = (LinearLayout) findViewById(R.id.mainLinearLayout);
        addEditTextButton = (Button) findViewById(R.id.addEditText);
        clothCount = 3;
        editTextList = new ArrayList<>();
        editTextList.add(cloth1EditText);
        editTextList.add(cloth2EditText);
        editTextList.add(cloth3EditText);
        clothesList = new ArrayList<ClothClass>();
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mLaundryRef = mRootRef.child("Laundry");

        addClothesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addClothes();
            }
        });

        addEditTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewEditField();
            }
        });

    }

    public void addClothes(){

        //Get Clothes From EditText

        for ( EditText editText : editTextList ){
            if ( editText.getText().toString() != null && !editText.getText().toString().equals(""))
                clothesList.add(new ClothClass(editText.getText().toString(), false));
        }

        /*if ( cloth1EditText.getText().toString() != null && !cloth1EditText.getText().toString().equals(""))
            clothesList.add(new ClothClass(cloth1EditText.getText().toString(), false));
        if ( cloth2EditText.getText().toString() != null && !cloth2EditText.getText().toString().equals(""))
            clothesList.add(new ClothClass(cloth2EditText.getText().toString(), false));
        if ( cloth3EditText.getText().toString() != null && !cloth3EditText.getText().toString().equals(""))
            clothesList.add(new ClothClass(cloth3EditText.getText().toString(), false));*/

        //Add Clothes to Server

        clothes = new ClothBundleClass(clothesList, currentDate);

        DatabaseReference temp = mLaundryRef.child("Laundry for " + clothes.getDate().toString());
        temp.setValue(clothes);

        Toast.makeText(AddLaundryUI.this, "Clothes Added!", Toast.LENGTH_SHORT).show();

        //Send user to the main page
        finish();
        startActivity(new Intent(AddLaundryUI.this, UILaundry.class));

    }

    public void addNewEditField(){

        Toast.makeText(AddLaundryUI.this, "Hey!", Toast.LENGTH_SHORT).show();

        clothCount++;
        EditText tempEditText = new EditText(AddLaundryUI.this);
        editTextList.add(tempEditText);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        p.setMargins(120,20,120,10);

        //p.addRule(RelativeLayout.BELOW, R.id.date_textView);
        //p.addRule(RelativeLayout.ABOVE, R.id.cloth1EditText);

        tempEditText.setLayoutParams(p);
        tempEditText.setHint("Cloth " + clothCount + " Name");

        mainLinearLayout.addView(tempEditText);






    }
}
