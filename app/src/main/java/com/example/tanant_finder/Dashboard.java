package com.example.tanant_finder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tanant_finder.adapter.MyAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {
   private FloatingActionButton addprop;
   RecyclerView recyclerView;
   ArrayList<user> list = new ArrayList<>();
  private DatabaseReference databaseReference;
  private     MyAdapter adapter;
   ImageButton profilebutton;





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
        databaseReference= FirebaseDatabase.getInstance().getReference("Property Details");
        list= new ArrayList<>();
        adapter=new MyAdapter(this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        profilebutton=findViewById(R.id.profilebutton);

        profilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,profile.class));
            }
        });


        databaseReference.addValueEventListener(new ValueEventListener() {
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
                Toast.makeText(Dashboard.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Log.i("printed",String.valueOf(list.size()));

        addprop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,property_details.class));
            }
        });


    }



}