package com.example.e_exam;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_exam.adapter.ExamAdapter;
import com.example.e_exam.model.Question;
import com.example.e_exam.model.Subject;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Exam_ListActivity extends AppCompatActivity {
    RecyclerView recycler_exam;
    List<Question>questions;
    DatabaseReference exam_data,exam_data2;
   TextView txt_content;
   RadioButton radio_valid_answer,radio_wrong_answer1,radio_wrong_answer2,radio_wrong_answer3;
   FirebaseAuth auth;
    String uid;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam__list);
        auth=FirebaseAuth.getInstance();
        recycler_exam=findViewById(R.id.exam_recycler);
        recycler_exam.setHasFixedSize(true);
        recycler_exam.setLayoutManager(new LinearLayoutManager(this));
       questions=new ArrayList<>();
        txt_content=findViewById(R.id.txt_content);
        radio_valid_answer=findViewById(R.id.radio_valid_answer);
        radio_wrong_answer1=findViewById(R.id.radio_wrong_answer1);
        radio_wrong_answer2=findViewById(R.id.radio_wrong_answer2);
        radio_wrong_answer3=findViewById(R.id.radio_wrong_answer3);
        listView=findViewById(R.id.list);
        FirebaseUser currentUser = auth.getCurrentUser();
      //  String uid = auth.getUid();
         uid = currentUser.getUid();


        exam_data= FirebaseDatabase.getInstance().getReference("Questions").child("Level One").child("Arabic").child("A").child("mcq");
       // exam_data2= FirebaseDatabase.getInstance().getReference("Questions").child("Level One").child("Arabic").child("A").child("trueFalse");

        exam_data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot data_exam:dataSnapshot.getChildren()){
                    Question question = data_exam.getValue(Question.class);
                    questions.add(question);
                }
                ExamAdapter adapter=new ExamAdapter(Exam_ListActivity.this,questions);
                recycler_exam.setAdapter(adapter);
            }

             @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });

    }


    }
