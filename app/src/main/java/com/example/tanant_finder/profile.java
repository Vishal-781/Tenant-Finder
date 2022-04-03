package com.example.tanant_finder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class profile extends AppCompatActivity {
    Button btnlogout;
    ImageButton previous;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnlogout=findViewById(R.id.btnlogout);
        previous=findViewById(R.id.previous);
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


}