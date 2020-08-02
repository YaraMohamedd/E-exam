package fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.e_exam.Add_Question;
import com.example.e_exam.R;
import com.example.e_exam.TestActivity;
import com.example.e_exam.UpdateQuestion;
import com.example.e_exam.adapter.QuestionAdapter;
import com.example.e_exam.model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MCQFragment extends Fragment  {
    RecyclerView mcq_recycler;
    List<Question> questions;
    DatabaseReference mcq_data;
  Button  btn_gototruefalsefragment;
  Spinner spinner_type,spinner_difficulity;
  EditText edit_content,edit_valid_answer,wrong_answer1,wong_answer2,wrong_answer3;
    View view;
    public MCQFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view = inflater.inflate(R.layout.fragment_m_c_q, container, false);
      //  btn_gototruefalsefragment=view.findViewById(R.id.btn_truefalse);



        mcq_data= FirebaseDatabase.getInstance().getReference("Questions").child("Level One").child("Arabic").child("A").child("mcq");
        mcq_data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 for(DataSnapshot ds:dataSnapshot.getChildren()){
                     Question question = ds.getValue(Question.class);
                     questions.add(question);
                 }
                QuestionAdapter adapter=new QuestionAdapter(getActivity(),questions);
                 mcq_recycler.setAdapter(adapter);
                 adapter.setOnItemClickListener(new QuestionAdapter.OnItemClickListener() {
                     @Override
                     public void OnItemClick(int position) {
                         Question question = questions.get(position);
                         showupdateDialogue(question.getQuestionId());
                     }
                 });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        questions=new ArrayList<>();

        initialize(view);
        return  view;
    }

    private void showupdateDialogue(final String questionId) {
        final AlertDialog.Builder alertdialog=new AlertDialog.Builder(getActivity());
        final   View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_update, null);

        alertdialog.setView(view);
        final Spinner spinner_type=view.findViewById(R.id.type);
        final Spinner spinner_difficulity=view.findViewById(R.id.difficulity);
        final  EditText edit_content=view.findViewById(R.id.content);
        final  EditText edit_valid_answer =view.findViewById(R.id.validanswer);
        final  EditText wrong_answer1=view.findViewById(R.id.wrong_answer1);
        final  EditText wrong_answer2 =view.findViewById(R.id.wrong_answer2);
        final  EditText wrong_answer3 =view.findViewById(R.id.wrong_answer3);
        Button update=view.findViewById(R.id.btn_update);
        Button delete=view.findViewById(R.id.btn_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              delet_question(questionId);
            }
        });
              update.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      String Type = spinner_type.getSelectedItem().toString();
                      String Difficulity = spinner_difficulity.getSelectedItem().toString();
                      String Content = edit_content.getText().toString().trim();
                      String Valid_answer = edit_valid_answer.getText().toString().trim();
                      String Wrong_answer1 = wrong_answer1.getText().toString().trim();
                      String Wrong_answer2 = wrong_answer2.getText().toString().trim();
                      String Wrong_answer3 = wrong_answer3.getText().toString().trim();

                      if(TextUtils.isEmpty(Type)){
                          Toast.makeText(getActivity(), "this field is required", Toast.LENGTH_SHORT).show();
                      }
                      else    if(TextUtils.isEmpty(Difficulity)){
                          Toast.makeText(getActivity(), "this field is required", Toast.LENGTH_SHORT).show();
                      }
                      else if(TextUtils.isEmpty(Content)){
                          Toast.makeText(getActivity(), "this field is required", Toast.LENGTH_SHORT).show();
                      }
                      else if(TextUtils.isEmpty(Valid_answer)){
                          Toast.makeText(getActivity(), "this field is required", Toast.LENGTH_SHORT).show();
                      }
                      else   if(TextUtils.isEmpty(Wrong_answer1)){
                          Toast.makeText(getActivity(), "this field is required", Toast.LENGTH_SHORT).show();
                      }
                      else   if(TextUtils.isEmpty(Wrong_answer2)){
                          Toast.makeText(getActivity(), "this field is required", Toast.LENGTH_SHORT).show();
                      }
                      else if(TextUtils.isEmpty(Wrong_answer3)){
                          Toast.makeText(getActivity(), "this field is required", Toast.LENGTH_SHORT).show();
                      }
                      else {
                          updat(questionId,Difficulity,Content,Type,Valid_answer,Wrong_answer1,Wrong_answer2,Wrong_answer3);
                      }


                  }
              });
        alertdialog.setTitle("update dialog");
        AlertDialog alertDialog=alertdialog.create();
        alertDialog.show();
    }

    private void delet_question(String questionId) {
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Questions").child("Level One").child("Arabic").child("A").child("mcq").child(questionId);
databaseReference.removeValue();
        Toast.makeText(getContext(), "Question deleted successfullu", Toast.LENGTH_SHORT).show();
    }

    private boolean updat(String questionId, String difficulity, String content, String type, String valid_answer, String wrong_answer1, String wrong_answer2, String wrong_answer3) {
       DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Questions").child("Level One").child("Arabic").child("A").child("mcq").child(questionId);

        Question question=new Question(questionId,difficulity,content,type,valid_answer,wrong_answer1,wrong_answer2,wrong_answer3);
          databaseReference.setValue(question);
          return  true;
    }


    private void initialize(View view) {
        mcq_recycler=view.findViewById(R.id.mcq_recycler);
        mcq_recycler.setHasFixedSize(true);
        mcq_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));


    }



}
