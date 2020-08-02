package com.example.e_exam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.e_exam.adapter.QuestionAdapter;
import com.example.e_exam.adapter.TrueFalseAdapter;
import com.example.e_exam.model.Question;
import com.example.e_exam.model.Subject;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuestionListActivity extends AppCompatActivity {
    RecyclerView question,subject;
    List<Question>questions;
    DatabaseReference question_data,question_data1;
    List<Subject>subjects;
  Button btn_add_toexam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);
        question=findViewById(R.id.question_recycler);
        question.setHasFixedSize(true);
        question.setLayoutManager(new LinearLayoutManager(this));
        subject=findViewById(R.id.question_recycler);
        subject.setHasFixedSize(true);
        subject.setLayoutManager(new LinearLayoutManager(this));
        questions=new ArrayList<>();
        subjects=new ArrayList<>();

        question_data= FirebaseDatabase.getInstance().getReference("Questions").child("Level One").child("Arabic").child("A").child("mcq");
       // question_data1= FirebaseDatabase.getInstance().getReference("Questions").child("Level One").child("Arabic").child("A").child("trueFalse");
        question_data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 for(DataSnapshot question_shot:dataSnapshot.getChildren()){
                     Question question = question_shot.getValue(Question.class);
                     questions.add(question);
                 }
                QuestionAdapter adapter=new QuestionAdapter(QuestionListActivity.this,questions);
                 question.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        /*
        question_data1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                     Subject subject = snapshot.getValue(Subject.class);
                     subjects.add(subject);
                 }
                TrueFalseAdapter adapter=new TrueFalseAdapter(QuestionListActivity.this,subjects);
                 subject.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

         */
     //   btn_add_toexam=findViewById(R.id.add_to_exam);

     }
}
