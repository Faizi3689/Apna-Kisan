package com.example.apnakissan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
private FirebaseAuth auth;
private EditText signmail, signpassword;
private TextView redirectsignup;
private Button signbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        signmail = findViewById(R.id.login_email);
        signpassword = findViewById(R.id.login_password);
        redirectsignup = findViewById(R.id.redirectSignup);
        signbutton = findViewById(R.id.login_button);

       signbutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String email = signmail.getText().toString();
               String pass = signpassword.getText().toString();
               if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                   if (!pass.isEmpty()){
                       auth.signInWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                           @Override
                           public void onSuccess(AuthResult authResult) {
                               Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(Login.this, Dashboard.class));
                               finish();
                           }
                       }).addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                           }
                       });

                   }else{
                       signpassword.setError("Password can not be empty");
                   }
               } else if (email.isEmpty()){
                   signmail.setError("Mail cannot be empty");
               }else{

                   signmail.setError("Please enter valid Email");
               }
           }
       });

      redirectsignup.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(Login.this, Signup.class));
          }
      });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}