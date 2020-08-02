package com.example.e_exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.TextView;

import fragment.FirstFragment;
import fragment.MCQFragment;
import fragment.SecondFragment;
import fragment.TrueFalseFragment;

public class Exam_TestActivity extends AppCompatActivity {
    public   FragmentManager fragmentManager;
    TextView txt_get_my_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam__test);
        fragmentManager=getSupportFragmentManager();
     //   txt_get_my_result=findViewById(R.id.get_result);

        if(findViewById(R.id.container3)!=null){
            if(savedInstanceState!=null){
                return;
            }
            fragmentManager.beginTransaction().add(R.id.container3,new FirstFragment()).commit();
            fragmentManager.beginTransaction().add(R.id.container4,new SecondFragment()).commit();
        }
        /*
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("score");
            txt_get_my_result.setText(value);
        }

         */
    }
}
