package com.example.e_exam;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_exam.adapter.StudentAdapter;
import com.example.e_exam.model.Professor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Student_SubjectActivity extends AppCompatActivity {

    List<Professor> professors;
    RecyclerView subject_recycler;
    DatabaseReference student_subject;
    FirebaseAuth mAuthn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__subject);
        subject_recycler=findViewById(R.id.student_recycler);
        subject_recycler.setHasFixedSize(true);
        subject_recycler.setLayoutManager(new LinearLayoutManager(this));
        professors=new ArrayList<>();
        student_subject= FirebaseDatabase.getInstance().getReference("LevelSubjects").child("Level One");
        student_subject.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot studentdata:dataSnapshot.getChildren()){
                    Professor professor = studentdata.getValue(Professor.class);
                    professors.add(professor);
                }
                StudentAdapter adapter=new StudentAdapter(Student_SubjectActivity.this,professors);
                subject_recycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
