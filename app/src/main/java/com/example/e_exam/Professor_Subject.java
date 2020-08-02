package com.example.e_exam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_exam.adapter.ListAdapter;
import com.example.e_exam.adapter.ProfessorAdapter;
import com.example.e_exam.adapter.SubjectAdapter;
import com.example.e_exam.model.Professor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class Professor_Subject extends AppCompatActivity {

    ArrayList<Integer> images=new ArrayList<>();
       List<Professor>professors;
       RecyclerView recyclerView;
       DatabaseReference subjectdata;
       FirebaseAuth firebaseAuth;
    String uid;
   // private Object Professor;
   ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor__subject);
        listView=findViewById(R.id.subject_list);
        firebaseAuth=FirebaseAuth.getInstance();
       // setview();
       // recyclerView=findViewById(R.id.recycler);
     //   SubjectAdapter subjectAdapter=new SubjectAdapter(images,this);
       // recyclerView.setHasFixedSize(true);
      //  LinearLayoutManager layoutManager=new LinearLayoutManager(this);
       // recyclerView.setLayoutManager(layoutManager);
        professors= new ArrayList<>();
       // subjectdata= FirebaseDatabase.getInstance().getReference("Levels");
       // FirebaseUser currentUser = firebaseAuth.getCurrentUser();
       //uid = currentUser.getUid();
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Professor professor = professors.get(position);
               gotosubjectactivity(professor.getName(),professor.getName_arabic());
           }
       });

    }

    private void gotosubjectactivity(String name, String name_arabic) {
        startActivity(new Intent(Professor_Subject.this,SubjectActivity.class));
    }

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
            //Professor professor = dataSnapshot1.getValue(Professor.class);
           // professors.add(professor);


            Professor professor=new Professor();
            professor.setName(dataSnapshot1.child("Arabic").getValue(Professor.class).getName());
            professor.setName_arabic(dataSnapshot1.child("Arabic").getValue(Professor.class).getName_arabic());
            ArrayList<String>arrayList=new ArrayList<>();
            arrayList.add(professor.getName());
            arrayList.add(professor.getName_arabic());



        }
     //   ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,professors);
      //  listView.setAdapter(adapter);
    }

    private  void setview(){
        images.add(R.drawable.subject4);


        images.add(R.drawable.subject4);

        images.add(R.drawable.subject4);

        images.add(R.drawable.subject4);

        images.add(R.drawable.subject4);

        images.add(R.drawable.subject4);

      //  initialRecycler();
    }
    private  void  initialRecycler(){
     //   RecyclerView recyclerView=findViewById(R.id.recycle);
     //   SubjectAdapter subjectAdapter=new SubjectAdapter(images,this);
       // recyclerView.setHasFixedSize(true);
        //LinearLayoutManager layoutManager=new LinearLayoutManager(this);
       // recyclerView.setLayoutManager(layoutManager);
       // recyclerView.setAdapter(subjectAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //final TextView txt_name=findViewById(R.id.txt_name);
       // final TextView txt_name_arabic=findViewById(R.id.txt_name_arabic);
        subjectdata= FirebaseDatabase.getInstance().getReference("Levels");
            subjectdata.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Professor professor = dataSnapshot1.getValue(Professor.class);
                    professors.add(professor);
                }
                      ListAdapter adapter=new ListAdapter(Professor_Subject.this,professors);
                listView.setAdapter(adapter);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
    }
    }