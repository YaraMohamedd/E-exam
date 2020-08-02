package com.example.e_exam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Subject_List extends AppCompatActivity {
    Button btn_add,btn_show,btn_delete,btn_update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject__list);
        btn_add=findViewById(R.id.btn_add_question);
        btn_show=findViewById(R.id.btn_show_question);
        btn_update=findViewById(R.id.btn_update);
        //btn_delete=findViewById(R.id.btn_delete_question);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(Subject_List.this,Add_Question.class));
            }
        });
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Subject_List.this,TestActivity.class));
            }
        });
        /*
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Subject_List.this,TestActivity.class));
            }
        });

         */

}}
