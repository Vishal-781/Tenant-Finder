package com.example.tanant_finder;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class profile extends AppCompatActivity {
    Button btnlogout;
    ImageView profilepic;
    ImageButton previous;
    TextView textView1;
    TextView textView2;
    FirebaseAuth mAuth;
   private Uri imageUri;
    ActivityResultLauncher<String> launcher;
    FirebaseDatabase database;
   private FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnlogout=findViewById(R.id.btnlogout);
        previous=findViewById(R.id.previous);
        textView1=findViewById(R.id.usernamed);
        textView2=findViewById(R.id.textView2);
        profilepic=findViewById(R.id.addprofilepic);
        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
//        launcher=registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
//            @Override
//            public void onActivityResult(Uri uri) {
//                final StorageReference reference= storage.getReference()
//                        .child("Users").child(FirebaseAuth.getInstance().getUid()).child("Profile dp");
//                reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                            @Override
//                            public void onSuccess(Uri uri) {
//                                database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("Profile dp").setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void unused) {
//                                        Toast.makeText(profile.this, "image uploaded", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                            }
//                        });
//                    }
//                });
//            }
//        });

//
//        database.getReference().child("Users").child(mAuth.getUid()).child("profile dp")
//                  .addListenerForSingleValueEvent(new ValueEventListener() {
//              @Override
//              public void onDataChange(@NonNull DataSnapshot snapshot) {
//                  if (snapshot.exists()){
//                     user_details User_details= snapshot.getValue(user_details.class);
//                      Glide.with(profile.this).load(User_details.getProfile_dp()).circleCrop().placeholder(R.drawable.dp)
//                              .into(profilepic);
//                      textView1.setText(User_details.getUsernme() );
//                      textView2.setText(User_details.getEmailc() );
//
//                  }
//
//              }
//
//              @Override
//              public void onCancelled(@NonNull DatabaseError error) {
//                  Toast.makeText(profile.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//              }
//          });

//        database.getReference().child("profile_dp").child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String dp=snapshot.getValue(String.class);
//                Glide.with(profile.this)
//                        .load(dp)
//                        .fitCenter()
//                        .circleCrop()
//                        .into(profilepic);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                launcher.launch("images/*");

            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(profile.this,Dashboard.class));
            }
        });
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
            }
        });

    }
    public void Logout(){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(profile.this,MainActivity.class));
        }
//
//    private void selectimg(){
//        Intent intent=new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent,1);
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if ( requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData() !=null){
//            imageUri=data.getData();
//            Glide.with(this).load(imageUri).fitCenter().into(profilepic);
//            final StorageReference reference=storage.getReference().child("profile dp").child(FirebaseAuth.getInstance().getUid());
//            reference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    Toast.makeText(profile.this, "image uploaded", Toast.LENGTH_SHORT).show();
//                }
//            });
//             reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                 @Override
//                 public void onSuccess(Uri uri) {
//                database.getReference().child("Users").child(mAuth.getUid()).child("profile_dp").setValue(uri.toString());
//                 }
//             });
//        }
//    }



}