package com.example.e_exam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_exam.R;
import com.example.e_exam.model.Subject;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {
    Context context;
    List<Subject>subjects;
    public  TestAdapter (Context context,List<Subject>subjects){
        this.context=context;
        this.subjects=subjects;
    }
    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_test, parent, false);

        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        Subject subject = subjects.get(position);
        holder.txt_content.setText(subject.getQuestionContent());
        holder.valid_answer.setText("True");
        holder.wrong_answer.setText("False");
        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.valid_answer:
                        Toast.makeText(context, "Correct answer", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.wrong_answer1:
                        Toast.makeText(context, "Wrong answer", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public  class  TestViewHolder extends RecyclerView.ViewHolder{
          TextView txt_content;
          RadioButton valid_answer,wrong_answer;
          RadioGroup radioGroup;

        public TestViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_content=itemView.findViewById(R.id.txtquestion_content);
            valid_answer=itemView.findViewById(R.id.valid_answer);
            wrong_answer=itemView.findViewById(R.id.wrong_answer1);
            radioGroup=itemView.findViewById(R.id.radio_questions);
        }
    }
}
