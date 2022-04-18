package com.example.tanant_finder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Edit_UserDetails extends AppCompatActivity {
    EditText setusername, setemail, changepassword;
    Button savebtn;
    ImageView changeprofile;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_details);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        setusername = findViewById(R.id.setusername);
        setemail = findViewById(R.id.setemail);
        changepassword = findViewById(R.id.changepassword);
        savebtn = findViewById(R.id.savebtn);
        storage=FirebaseStorage.getInstance();
        changeprofile=findViewById(R.id.changeprofilepic);
        changeprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecting();
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    public void register() {
        String email = setemail.getText().toString().trim();
        String pass = changepassword.getText().toString().trim();
        String usernme = setusername.getText().toString().trim();
        user_details User_details=new user_details(usernme,email,pass);
        Toast.makeText(Edit_UserDetails.this, "Changes saved", Toast.LENGTH_SHORT).show();
        String id=mAuth.getUid();
        database.getReference().child("Users").child(id).setValue(User_details);
        startActivity(new Intent(Edit_UserDetails.this, profile.class));
    }
    private void selecting(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);}


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData() !=null){
          Uri  dpUri =data.getData();
            Picasso.get().load(dpUri).placeholder(R.drawable.icon).into(changeprofile);
            final StorageReference reference=storage.getReference().child("profile_dp").child(mAuth.getUid());
            reference.putFile(dpUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(Edit_UserDetails.this, "image uploaded", Toast.LENGTH_SHORT).show();
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
