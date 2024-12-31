package com.example.apnakissan;

import android.content.Intent;
import android.os.Bundle;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
 private FirebaseAuth auth;
 private EditText signupmail,signuppassword;
 private Button sihnupbutton;
 private TextView redirectsignin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();
        signupmail = findViewById(R.id.signup_email);
        signuppassword = findViewById(R.id.signup_password);
        sihnupbutton = findViewById(R.id.signup_button);
        redirectsignin = findViewById(R.id.redirectlogin);

        sihnupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = signupmail.getText().toString().trim();
                String pass = signuppassword.getText().toString().trim();

                if (user.isEmpty()){
                    signupmail.setError("Mail can not be empty");
                }
                if (pass.isEmpty()){
                    signuppassword.setError("Please Enter your password");
                }else{
                    auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Signup.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Signup.this,Login.class));
                            }else{
                                Toast.makeText(Signup.this, "Sign Up Failed"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        redirectsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this,Login.class));
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}