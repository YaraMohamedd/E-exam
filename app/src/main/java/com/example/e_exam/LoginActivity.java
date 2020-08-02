package com.example.e_exam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
  EditText txt_email,txt_password;
  TextView txt_login;
  Button btn_login;
  FirebaseAuth mAuthn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
           mAuthn=FirebaseAuth.getInstance();
        txt_email=findViewById(R.id.email);
        txt_password=findViewById(R.id.password);
        btn_login=findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txt_email.getText().toString();
                String password = txt_password.getText().toString();

                if(TextUtils.isEmpty(email)){
                    txt_email.setError("This field is required");
                }
                else if(TextUtils.isEmpty(password)){
                    txt_email.setError("This field is required");
                }else {
                    mAuthn.signInWithEmailAndPassword(email,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    Toast.makeText(LoginActivity.this, "You have logged in", Toast.LENGTH_SHORT).show();
                                      startActivity(new Intent(LoginActivity.this,Professor_Subject.class));
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                }
            }

        });

    }
}
