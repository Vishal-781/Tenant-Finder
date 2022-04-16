package com.example.tanant_finder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class property_details extends AppCompatActivity {
  Button submit,preview;
  EditText details,address,rent,contact_no;
  DatabaseReference DatabaseUsers;
  FirebaseStorage mStorage;
  FirebaseAuth mAuth;
//    ImageView img1,img2,img3,img4;
//    Uri uri1,uri2,uri3,uri4;
 public ImageView imagesin;
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
        mAuth=FirebaseAuth.getInstance();
        mStorage=FirebaseStorage.getInstance();
        imagesin=findViewById(R.id.imagesin);
        imagesin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(property_details.this,Upload_images.class));
            }
        });
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
        String id = mAuth.getUid();
//
//        StorageReference reference=mStorage.getReference().child("property_images").child(mAuth.getUid());
//        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//          uri1 =uri;
//          uri2=uri;
//          uri3=uri;
//          uri4=uri;
//            }
//        });





        user User=new user(dls,adrs,charge,phn_no);
       DatabaseUsers.child("Property Details").child(id).setValue(User).addOnCompleteListener(new OnCompleteListener<Void>() {
           @Override
           public void onComplete(@NonNull Task<Void> task) {
               if (task.isSuccessful()){
                   Toast.makeText(property_details.this, "Propety Added", Toast.LENGTH_SHORT).show();
               }
           }
       });

    }


}