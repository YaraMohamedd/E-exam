package fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.e_exam.Exam_TestActivity;
import com.example.e_exam.R;
import com.example.e_exam.adapter.ExamAdapter;
import com.example.e_exam.model.Content;
import com.example.e_exam.model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FirstFragment extends Fragment {
    List<Question>questions;
    RecyclerView first_recycler;
    DatabaseReference mcq_data;
   ListView list;
     RadioGroup radioGroup;
     RadioButton radioButton1,radioButton2,radioButton3,radioButton4;
     Button btn_get_result;

    public FirstFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        /*
        btn_get_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input;
                String result = null;
                Double dbl;
                Intent i = new Intent(getActivity(), Exam_TestActivity.class);

                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.radio_valid_answer:
                        input=radioButton1.getText().toString();
                        dbl = Double.parseDouble(input);
                        result = String.valueOf(dbl);
                        i.putExtra("score", result);
                        break;
                    case R.id.radio_wrong_answer1:
                        i.putExtra("score",0);
                        break;
                    case R.id.radio_wrong_answer2:
                        i.putExtra("score",25);
                        break;
                    case R.id.radio_wrong_answer3:
                        i.putExtra("score",0);
                        break;
                }
                startActivity(i);

            }
        });

         */
        questions=new ArrayList<>();
        mcq_data= FirebaseDatabase.getInstance().getReference("Questions").child("Level One").child("Arabic").child("A").child("mcq");
        mcq_data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    Question question = ds.getValue(Question.class);
                    questions.add(question);
                }
                ExamAdapter adapter=new ExamAdapter(getActivity(),questions);
                first_recycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Error:"+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        initialize(view);
        return  view;
    }

    private void initialize(View view) {
        first_recycler=view.findViewById(R.id.first_recycler);
        first_recycler.setHasFixedSize(true);
        first_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


}
