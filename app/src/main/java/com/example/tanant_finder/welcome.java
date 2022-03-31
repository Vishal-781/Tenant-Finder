package com.example.tanant_finder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class welcome extends AppCompatActivity {

     Button next;
     FirebaseAuth mAuth;
     Button logoutbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        next= findViewById(R.id.next);
        mAuth=FirebaseAuth.getInstance();
        logoutbtn = findViewById(R.id.logoutbtn);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(welcome.this,Dashboard.class));
            }
        });
       logoutbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               logout();
           }
       });

    }
    public void logout(){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(welcome.this,MainActivity.class));
    }
}