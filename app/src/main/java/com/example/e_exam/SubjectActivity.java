package com.example.e_exam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_exam.adapter.ProfessorAdapter;
import com.example.e_exam.model.Professor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SubjectActivity extends AppCompatActivity  {
    DatabaseReference dataRefs;
    List<Professor> professors;
    RecyclerView professor_recycler;
    FirebaseAuth mAuthn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        mAuthn=FirebaseAuth.getInstance();
        String uid = mAuthn.getUid();
        professor_recycler=findViewById(R.id.recycler_adapter);
        professor_recycler.setHasFixedSize(true);
        professor_recycler.setLayoutManager(new LinearLayoutManager(this));
        professors=new ArrayList<>();

        dataRefs= FirebaseDatabase.getInstance().getReference("LevelSubjects").child("Level One");
        dataRefs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   for(DataSnapshot subject_data:dataSnapshot.getChildren()){
                       Professor professor = subject_data.getValue(Professor.class);
                       professors.add(professor);
                   }
                ProfessorAdapter adapter=new ProfessorAdapter(SubjectActivity.this,professors);
                   professor_recycler.setAdapter(adapter);

            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }



}
