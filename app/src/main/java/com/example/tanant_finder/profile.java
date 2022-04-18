package com.example.tanant_finder;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tanant_finder.adapter.profileAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class profile extends AppCompatActivity {
    Button btnlogout;
    Button edit;

    ImageView profilepic;
    ImageButton previous;
    TextView textView1;
    TextView textView2;
    FirebaseAuth mAuth;
   private Uri dpUri;
    ArrayList<user> list = new ArrayList<>();
   private profileAdapter mAdapter;
    RecyclerView recyclerView2;
    private DatabaseReference mreference ;
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
        edit=findViewById(R.id.edit);
        storage =FirebaseStorage.getInstance();
        mreference=FirebaseDatabase.getInstance().getReference("Property Details");


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(profile.this,Edit_UserDetails.class));
            }
        });
        mAuth=FirebaseAuth.getInstance();
        list=new ArrayList<>();
        mAdapter=new profileAdapter(this,list);
        recyclerView2=findViewById(R.id.recyclerView2);
        database=FirebaseDatabase.getInstance();
        recyclerView2.setAdapter(mAdapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        mreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    user User=dataSnapshot.getValue(user.class);

                    list.add(User);
                }
                mAdapter.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        database.getReference().child("Users").child(mAuth.getUid())
                  .addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot snapshot) {
                  if (snapshot.exists()){
                     user_details User_details= snapshot.getValue(user_details.class);
                      Picasso.get().load(User_details.getProfile_dp()).placeholder(R.drawable.dp)
                              .into(profilepic);
                      textView1.setText(User_details.getUsernme() );
                      textView2.setText(User_details.getEmailc() );

                  }

              }

              @Override
              public void onCancelled(@NonNull DatabaseError error) {
                  Toast.makeText(profile.this, error.getMessage(), Toast.LENGTH_SHORT).show();
              }
          });




        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               selecting();

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

    private void selecting(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData() !=null){
            dpUri =data.getData();
         Picasso.get().load(dpUri).into(profilepic);
            final StorageReference reference=storage.getReference().child("profile_dp").child(FirebaseAuth.getInstance().getUid());
            reference.putFile(dpUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(profile.this, "image uploaded", Toast.LENGTH_SHORT).show();
                }
            });
             reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                 @Override
                 public void onSuccess(Uri uri) {
                database.getReference().child("Users").child(mAuth.getUid()).child("profile_dp").setValue(uri.toString());
                 }
             });
        }
    }



}