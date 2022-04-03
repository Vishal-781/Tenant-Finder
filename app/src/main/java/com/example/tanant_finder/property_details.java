package com.example.tanant_finder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class property_details extends AppCompatActivity {
  Button submit,preview;
  EditText details,address,rent,contact_no;
  DatabaseReference DatabaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_details);
        submit= findViewById(R.id.submit);
        preview=findViewById(R.id.preview);
        details= findViewById(R.id.property_type);
        address= findViewById(R.id.address);
        rent= findViewById(R.id.rent);
        contact_no= findViewById(R.id.contact_no);
        DatabaseUsers= FirebaseDatabase.getInstance().getReference();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 insertData();
            }
        });
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(property_details.this,Dashboard.class));
                finish();
            }
        });


    }

    private void insertData() {
        String dls= details.getText().toString();
        String adrs=address.getText().toString();
        String charge=rent.getText().toString();
        String phn_no =contact_no.getText().toString();

        user User=new user(dls,adrs,charge,phn_no);
       DatabaseUsers.child("Property Details").child(dls).setValue(User).addOnCompleteListener(new OnCompleteListener<Void>() {
           @Override
           public void onComplete(@NonNull Task<Void> task) {
               if (task.isSuccessful()){
                   Toast.makeText(property_details.this, "Propety Added", Toast.LENGTH_SHORT).show();
               }
           }
       });

    }


}