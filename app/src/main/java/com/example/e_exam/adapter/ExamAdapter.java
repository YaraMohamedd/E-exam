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
import com.example.e_exam.model.Question;

import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamViewHolder> {
    Context context;
    List<Question>questions;
    public  ExamAdapter(Context context,List<Question>questions){
        this.context=context;
        this.questions=questions;
    }
    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.exam_item, parent, false);
        return new ExamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamViewHolder holder, int position) {
        Question question = questions.get(position);
        holder.txt_content.setText(question.getQuestionContent());
        holder.radio_valid_answer.setText(question.getValidAnswer());
        holder.radio_wronganswer1.setText(question.getWrongAnswer1());
        holder.radio_wronganswer2.setText(question.getWrongAnswer2());
        holder.radio_wronganswer3.setText(question.getWrongAnswer3());
        String input;
        String result = null;
        Double dbl;
            holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId){
                        case  R.id.radio_valid_answer:
                            Toast.makeText(context, "This is the correect answer", Toast.LENGTH_SHORT).show();
                            break;
                        case  R.id.radio_wrong_answer1:
                            Toast.makeText(context, "This is wrong answer", Toast.LENGTH_SHORT).show();
                            break;
                        case  R.id.radio_wrong_answer2:
                            Toast.makeText(context, "This is wrong answer", Toast.LENGTH_SHORT).show();
                            break;
                        case  R.id.radio_wrong_answer3:
                            Toast.makeText(context, "This is wrong answer", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public  class ExamViewHolder extends  RecyclerView.ViewHolder{
        TextView txt_content;
        RadioButton radio_valid_answer,radio_wronganswer1,radio_wronganswer2,radio_wronganswer3;
      RadioGroup radioGroup;


        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_content=itemView.findViewById(R.id.txt_content);
            radio_valid_answer=itemView.findViewById(R.id.radio_valid_answer);
            radio_wronganswer1=itemView.findViewById(R.id.radio_wrong_answer1);
            radio_wronganswer2=itemView.findViewById(R.id.radio_wrong_answer2);
            radio_wronganswer3=itemView.findViewById(R.id.radio_wrong_answer3);
            radioGroup=itemView.findViewById(R.id.radio_questions);
        }
    }
}
