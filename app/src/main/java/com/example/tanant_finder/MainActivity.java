package com.example.tanant_finder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity  {


   private FirebaseAuth mAuth;
    private TextView no_account;
    private EditText username, password;
    private Button button1;
    TextView fb;
    CallbackManager callbackManager;




        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        no_account = findViewById(R.id.no_acccount);
       username = findViewById(R.id.username);
        password= findViewById(R.id.password);
        button1= findViewById(R.id.button1);
        fb=findViewById(R.id.fb);
        callbackManager = CallbackManager.Factory.create();
            callbackManager = CallbackManager.Factory.create();

            LoginManager.getInstance().registerCallback(callbackManager,
                    new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                           startActivity(new Intent(MainActivity.this,welcome.class));
                           finish();
                        }

                        @Override
                        public void onCancel() {
                            // App code
                        }

                        @Override
                        public void onError( FacebookException exception) {
                            // App code
                        }
                    });
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("public_profile"));

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();

            }
        });
        no_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,signup.class));
            }
        });


}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }



     public void login() {
         String user = username.getText().toString().trim();
         String pass = password.getText().toString().trim();
         if (user.isEmpty()) {
             username.setError("email is required");
         }
         if (pass.isEmpty()) {
             password.setError("password is required");
         } else {
             mAuth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if (task.isSuccessful()) {
                         Toast.makeText(MainActivity.this, "login successful", Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(MainActivity.this, Dashboard.class));
                     } else {
                         Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                     }
                 }
             });

         }


     }
  @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(MainActivity.this, welcome.class));
        }


    }

}