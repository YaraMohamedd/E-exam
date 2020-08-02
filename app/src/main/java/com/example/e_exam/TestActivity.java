package com.example.e_exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.ActionBar;
import android.os.Bundle;

import fragment.MCQFragment;
import fragment.TrueFalseFragment;

public class TestActivity extends AppCompatActivity {
    public  static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        fragmentManager=getSupportFragmentManager();

        if(findViewById(R.id.contatiner1)!=null){
            if(savedInstanceState!=null){
                return;
            }
            fragmentManager.beginTransaction().add(R.id.contatiner1,new MCQFragment()).commit();
            fragmentManager.beginTransaction().add(R.id.contatiner2,new TrueFalseFragment()).commit();
        }


    }
}
