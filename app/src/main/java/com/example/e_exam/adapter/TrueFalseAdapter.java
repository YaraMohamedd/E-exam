package com.example.e_exam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_exam.R;
import com.example.e_exam.model.Subject;

import java.util.List;

public class TrueFalseAdapter extends RecyclerView.Adapter<TrueFalseAdapter.TrueFalseViewHolder> {
    Context context;
    List<Subject> subjects;

    public  TrueFalseAdapter(Context context,List<Subject>subjects){
        this.context=context;
        this.subjects=subjects;
    }

    @NonNull
    @Override
    public TrueFalseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.true_false_list, parent, false);
        return new TrueFalseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrueFalseViewHolder holder, int position) {
        Subject subject = subjects.get(position);
        holder.txt_difficulity.setText(subject.getDifficulty());
        holder.txt_type.setText(subject.getType());
        holder.txt_question_content.setText(subject.getQuestionContent());
        holder.radio_valid_answer.setText(subject.getValidAnswer());

        //holder.radio_wrong_answer.setText(subject.get);
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public class TrueFalseViewHolder extends  RecyclerView.ViewHolder{
        TextView txt_difficulity,txt_type,txt_question_content;
        RadioButton radio_valid_answer,radio_wrong_answer;
        public TrueFalseViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_difficulity=itemView.findViewById(R.id.txt_difficulity);
            txt_type=itemView.findViewById(R.id.txt_type);
            txt_question_content=itemView.findViewById(R.id.txtquestion_content);
            radio_valid_answer=itemView.findViewById(R.id.valid_answer);

          //  radio_wrong_answer=itemView.findViewById(R.id.wrong_answer);


        }
    }
}
