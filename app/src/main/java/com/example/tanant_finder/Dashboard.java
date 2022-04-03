package com.example.tanant_finder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
   private FloatingActionButton addprop;
   RecyclerView recyclerView;
   ArrayList<user> list;
   DatabaseReference databaseReference;
   MyAdapter adapter;
   ImageButton profilebutton;
   ImageButton homebutton;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Dashboard.this,property_details.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        addprop = findViewById(R.id.addprop);
        recyclerView= findViewById(R.id.recyclerView);
        databaseReference= FirebaseDatabase.getInstance().getReference("users");
        list= new ArrayList<>();
        adapter=new MyAdapter(this,list);
        recyclerView.setAdapter(adapter);
        profilebutton=findViewById(R.id.profilebutton);
        homebutton=findViewById(R.id.homebutton);
//        homebutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Dashboard.this,Dashboard.class));
//            }
//        });
//
        profilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,profile.class));
            }
        });


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                     user User=dataSnapshot.getValue(user.class);
                     list.add(User);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        addprop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,property_details.class));
            }
        });



    }
}