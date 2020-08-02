package com.example.e_exam;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Student_RegistrationActivity extends AppCompatActivity {
    EditText txt_name,txt_email,txt_password,txt_phone;
    Spinner spinner_level;
    Button btn_register;
   TextView txt_student_login;
    FirebaseAuth mAuthn;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__registration);
        mAuthn = FirebaseAuth.getInstance();
        txt_name=findViewById(R.id.edit_name);
        txt_email=findViewById(R.id.edit_email);
        txt_password=findViewById(R.id.edit_password);
       txt_phone=findViewById(R.id.edit_phone);
        spinner_level=findViewById(R.id.level);
        txt_student_login=findViewById(R.id.txt_student_login);
        btn_register=findViewById(R.id.btn_sign);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txt_name.getText().toString().trim();
                String email = txt_email.getText().toString().trim();
                String level = spinner_level.getSelectedItem().toString();
                String phone = txt_phone.getText().toString().trim();
                String password = txt_password.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    txt_name.setError("name is required");
                }
                else  if(TextUtils.isEmpty(email)){
                    txt_email.setError("email is required");
                }
                else  if(TextUtils.isEmpty(level)){
                    Toast.makeText(Student_RegistrationActivity.this, "level is required", Toast.LENGTH_SHORT).show();
                }
                else  if(TextUtils.isEmpty(phone)){
                    txt_phone.setError("phone is required");
                }
                else  if(TextUtils.isEmpty(password)){
                    txt_password.setError("email is required");
                }
           else{
             submit(name,email,level,phone,password);
                }

            }
        });
          txt_student_login.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  //Go to activity of student login
                  startActivity(new Intent(Student_RegistrationActivity.this,StudentLoginActivity.class));
              }
          });
    }

    private void submit(final String name, final String email, final String level, final String phone, final String password) {
             mAuthn.createUserWithEmailAndPassword(email,password)
                     .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull final Task<AuthResult> task) {
                     if(task.isSuccessful()){
                         FirebaseUser currentUser = mAuthn.getCurrentUser();
                         final String userUid = currentUser.getUid();
                         reference= FirebaseDatabase.getInstance().getReference("Users").child("student").child(userUid);
                         HashMap<String,Object> map=new HashMap<>();
                         map.put("userId",userUid);
                         map.put("name",name);
                         map.put("email",email);
                         map.put("level",level);
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

                                     Toast.makeText(Student_RegistrationActivity.this, "Your Account created successfully", Toast.LENGTH_SHORT).show();

                                     startActivity(new Intent(Student_RegistrationActivity.this,Student_ProfileActivity.class));
                                 }
                             }
                         });
                     }
                     else {
                         Toast.makeText(Student_RegistrationActivity.this, "Error occured"+task.getException(), Toast.LENGTH_SHORT).show();
                     }
                         }
                     });
    }
}
