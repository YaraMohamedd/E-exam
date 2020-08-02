package com.example.e_exam;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_exam.model.Question;
import com.example.e_exam.model.Subject;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Add_Question extends AppCompatActivity  {
    Spinner Type,Difficulity;
    EditText Content,Valid_answer,Wrong_answer1,Wrong_answer2,Wrong_answer3;
    Button btn_Add,Add ,Addtoexam,btn_add_to_exam_true,btn_Update;
    DatabaseReference questionRefs,questionRefs2;
    Spinner spinner_type,spinner_difficulity,spinner_valid;
    EditText edit_content;
    Context context;
    List<Question> questions;
    String key,id;
    String difficulity,type,questionContent,validAnswer,wrongAnswer1,wrongAnswer2,wrongAnswer3;
    String Type_question,Difficulity_question,Content_question,Valid_question;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__question);
        Type=findViewById(R.id.type);
        Difficulity=findViewById(R.id.difficulity);
        Content=findViewById(R.id.content);
        Valid_answer=findViewById(R.id.validanswer);
        Wrong_answer1=findViewById(R.id.wrong_answer1);
        Wrong_answer2=findViewById(R.id.wrong_answer2);
        Wrong_answer3=findViewById(R.id.wrong_answer3);
            spinner_type=findViewById(R.id.typ_truefalse);
            spinner_difficulity=findViewById(R.id.difficulity_true_false);
            spinner_valid=findViewById(R.id.valid);
        edit_content=findViewById(R.id.question_content);
            Add=findViewById(R.id.btn_Add);
        btn_Add=findViewById(R.id.btn_add_to);
           Addtoexam=findViewById(R.id.btn_add_to_exam);
        btn_add_to_exam_true=findViewById(R.id.btn_add_to_exam_true);
        btn_Update=findViewById(R.id.btn_update);
        questions=new ArrayList<>();
        questionRefs= FirebaseDatabase.getInstance().getReference("Questions").child("Level One").child("Arabic").child("A").child("mcq");
        questionRefs= FirebaseDatabase.getInstance().getReference("Questions").child("Level One").child("Arabic").child("A").child("trueFalse");
        questionRefs2=FirebaseDatabase.getInstance().getReference("Exam").child("Level One").child("Arabic");

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 type = Type.getSelectedItem().toString();
                 difficulity = Difficulity.getSelectedItem().toString();
                 questionContent = Content.getText().toString().trim();
                 validAnswer = Valid_answer.getText().toString().trim();
                 wrongAnswer1 = Wrong_answer1.getText().toString().trim();
                 wrongAnswer2 = Wrong_answer2.getText().toString().trim();
                wrongAnswer3 = Wrong_answer3.getText().toString().trim();
                if(TextUtils.isEmpty(type)){
                    Toast.makeText(Add_Question.this, "this field is required", Toast.LENGTH_SHORT).show();
                }
             else    if(TextUtils.isEmpty(difficulity)){
                    Toast.makeText(Add_Question.this, "this field is required", Toast.LENGTH_SHORT).show();
                }
               else if(TextUtils.isEmpty(questionContent)){
                    Toast.makeText(Add_Question.this, "this field is required", Toast.LENGTH_SHORT).show();
                }
               else if(TextUtils.isEmpty(validAnswer)){
                    Toast.makeText(Add_Question.this, "this field is required", Toast.LENGTH_SHORT).show();
                }
              else   if(TextUtils.isEmpty(wrongAnswer1)){
                    Toast.makeText(Add_Question.this, "this field is required", Toast.LENGTH_SHORT).show();
                }
              else   if(TextUtils.isEmpty(wrongAnswer2)){
                    Toast.makeText(Add_Question.this, "this field is required", Toast.LENGTH_SHORT).show();
                }
               else if(TextUtils.isEmpty(wrongAnswer3)){
                    Toast.makeText(Add_Question.this, "this field is required", Toast.LENGTH_SHORT).show();
                }
               else {
                     id = questionRefs.push().getKey();
                    Question question=new Question(difficulity,questionContent,id,type,validAnswer,wrongAnswer1,wrongAnswer2,wrongAnswer3);
                    questionRefs.child(id).setValue(question);
                    Toast.makeText(Add_Question.this, "Question added successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Add.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                  Type_question = spinner_type.getSelectedItem().toString();
                  Difficulity_question = spinner_difficulity.getSelectedItem().toString();
                  Content_question = edit_content.getText().toString().trim();
                  Valid_question = spinner_valid.getSelectedItem().toString();
                 if(TextUtils.isEmpty(Type_question)){
                     Toast.makeText(Add_Question.this, "this field is required", Toast.LENGTH_SHORT).show();
                 }
                 else    if(TextUtils.isEmpty(Difficulity_question)){
                     Toast.makeText(Add_Question.this, "this field is required", Toast.LENGTH_SHORT).show();
                 }
                 else if(TextUtils.isEmpty(Content_question)){
                     Toast.makeText(Add_Question.this, "this field is required", Toast.LENGTH_SHORT).show();
                 }
                 else if(TextUtils.isEmpty(Valid_question)){
                     Toast.makeText(Add_Question.this, "this field is required", Toast.LENGTH_SHORT).show();
                 }
                 else {
                      key = questionRefs.push().getKey();
                     Subject subject=new Subject(Difficulity_question,Content_question,key,Type_question,Valid_question);
                     questionRefs.child(key).setValue(subject);
                     Toast.makeText(Add_Question.this, "Question added successfully", Toast.LENGTH_SHORT).show();

                 }
             }
         });

        Addtoexam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question=new Question(difficulity,questionContent,id,type,validAnswer,wrongAnswer1,wrongAnswer2,wrongAnswer3);
                questionRefs2.child(id).setValue(question);
                Toast.makeText(Add_Question.this, "Question added successfully TO exam", Toast.LENGTH_SHORT).show();


            }
        });

        btn_add_to_exam_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Subject subject=new Subject(Difficulity_question,Content_question,key,Type_question,Valid_question);
                questionRefs2.child(key).setValue(subject);
                Toast.makeText(Add_Question.this, "Question added successfully TO exam", Toast.LENGTH_SHORT).show();

            }
        });
    }




}
