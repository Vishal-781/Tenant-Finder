package com.example.tanant_finder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class  signup extends AppCompatActivity {

     private EditText email, password1;
    private Button button;
    private FirebaseAuth mAuth;
     private TextView login1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
       email = findViewById(R.id.email);
        password1 = findViewById(R.id.password1);
        mAuth = FirebaseAuth.getInstance();
        login1=findViewById(R.id.login1);
        button= findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(signup.this,MainActivity.class));
            }
        });


    }

      public  void register(){
          String user =email.getText().toString().trim();
          String pass = password1.getText().toString().trim();
          if (user.isEmpty()) {
           email.setError("email is required");
          }
          if (pass.isEmpty()) {
              password1.setError("password is required");
          } else {
              mAuth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if(task.isSuccessful()){
                          Toast.makeText(signup.this, "user registered successfully", Toast.LENGTH_SHORT).show();
                          startActivity(new Intent(signup.this,MainActivity.class));
                  }else{
                          Toast.makeText(signup.this, "Registration unsuccessful"+task.getException(), Toast.LENGTH_SHORT).show();

              }



    }});
          }}
 

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {

    }


}
