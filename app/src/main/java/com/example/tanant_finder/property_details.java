package com.example.tanant_finder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class property_details extends AppCompatActivity {
  Button submit,preview;
  final int PICK_IMAGE_REQUEST=11;
  EditText details,address,rent,contact_no,imagename;
  DatabaseReference DatabaseUsers;
  FirebaseStorage mStorage;
  FirebaseDatabase database;
  FirebaseAuth mAuth;
  ProgressBar progressBar1;
  String dls,id;
 Uri imguri;

 String imageUri;
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
        imagename=findViewById(R.id.image_name);
        progressBar1=findViewById(R.id.progressbar1);
        mAuth=FirebaseAuth.getInstance();
        mStorage=FirebaseStorage.getInstance();
        database=FirebaseDatabase.getInstance();
        imagesin=findViewById(R.id.imagesin);
        imagesin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
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


    public void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
   private String  getFileExtension(Uri uri){
       ContentResolver cR=getContentResolver();
       MimeTypeMap  mime=MimeTypeMap.getSingleton();
       return mime.getExtensionFromMimeType(cR.getType(uri));
   }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imguri = data.getData();
            imagesin.setImageURI(imguri);
            id=System.currentTimeMillis()+"."+getFileExtension(imguri);
           final StorageReference reference = mStorage.getReference().child(id);


            reference.putFile(imguri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(property_details.this, "image uploaded", Toast.LENGTH_SHORT).show();
                }
            });
//            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                @Override
//                public void onSuccess(Uri uri) {
//                    imageUri=uri !=null ? uri.toString():null;
////                    database.getReference().child("Property Details").child(dls).setValue(uri.toString());
//                }
//            });


        }



//        DatabaseUsers.child("Property Details").child(mAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    user User = snapshot.getValue(user.class);
//                    assert User !=null;
//                    Picasso.get()
//                            .load(User.getImageV())
//                            .placeholder(R.drawable.icon)
//                            .into(imagesin);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }
    private void insertData() {
        dls= details.getText().toString()  !=null ? details.getText().toString():imageUri;
        String adrs=address.getText().toString();
        String charge=rent.getText().toString();
        String phn_no =contact_no.getText().toString();
        user User=new user(dls,adrs,charge,phn_no,imguri.toString());
        DatabaseUsers.child("Property Details").child(adrs).setValue(User).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(property_details.this, "Propety Added", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }




}








