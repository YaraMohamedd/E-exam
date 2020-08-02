package com.example.e_exam;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Professor_RegistrationActivity extends AppCompatActivity {
    EditText txt_name,txt_email,txt_phone,txt_password;
    Button btn_register;
    TextView txt_login;
  FirebaseAuth mAuthn;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor__registration);
       // FirebaseApp.initializeApp(this);
        mAuthn = FirebaseAuth.getInstance();
        txt_name=findViewById(R.id.name);
        txt_email=findViewById(R.id.email);
        txt_phone=findViewById(R.id.phone);
        txt_password=findViewById(R.id.password);
        btn_register=findViewById(R.id.btn_register);
          txt_login=findViewById(R.id.btn_login);
          txt_login.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                    startActivity(new Intent(Professor_RegistrationActivity.this,LoginActivity.class));
              }
          });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  startActivity(new Intent(Professor_RegistrationActivity.this,Professor_ProfileActivity.class));
                String Name = txt_name.getText().toString();
                String Email = txt_email.getText().toString();
                String Phone = txt_phone.getText().toString();
                String Password = txt_password.getText().toString();
                if(TextUtils.isEmpty(Name)){
                    txt_name.setError("please enter your name");
                }
                else if(TextUtils.isEmpty(Email)){
                    txt_email.setError("please enter your email");
                }
               else if(TextUtils.isEmpty(Phone)){
                    txt_phone.setError("please enter your phone");
                }
               else if(TextUtils.isEmpty(Password)){
                    txt_password.setError("please enter your password");
                }
               else {
                    submit(Name, Email, Phone, Password);
                }
            }
        });
    }

    private void submit(final String name, final String email, final String phone, final String password) {
        mAuthn.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull final Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser currentUser = mAuthn.getCurrentUser();
                            final String userUid = currentUser.getUid();
                            reference= FirebaseDatabase.getInstance().getReference("Users").child("doctor").child(userUid);
                            HashMap<String,Object> map=new HashMap<>();
                            map.put("userId",userUid);
                            map.put("name",name);
                            map.put("email",email);
                            map.put("phone",phone);
                            map.put("password",password);
                            map.put("image","");
                            reference.setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    if(task.isSuccessful()){
                                        //Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                                        //  intent.putExtra(USER_ID,userUid);
                                        // startActivity(intent);

                                        Toast.makeText(Professor_RegistrationActivity.this, "Your Account created successfully", Toast.LENGTH_SHORT).show();

                                        startActivity(new Intent(Professor_RegistrationActivity.this,Professor_ProfileActivity.class));
                                    }
                                }
                            }
                            );
                        }else {
                            Toast.makeText(Professor_RegistrationActivity.this, "Error occured"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
