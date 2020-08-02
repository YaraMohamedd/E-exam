package com.example.e_exam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class UpdateQuestion extends AppCompatActivity {
Button btn_update,btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_question);
        btn_update=findViewById(R.id.btn_update);

    }
}
