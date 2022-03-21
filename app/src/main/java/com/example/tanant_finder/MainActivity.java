package com.example.tanant_finder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      findViewById(R.id.no_acccount).setOnClickListener(this );


    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.no_acccount:

                startActivity(new Intent(this,signup.class));


                break;
        }

    }
}