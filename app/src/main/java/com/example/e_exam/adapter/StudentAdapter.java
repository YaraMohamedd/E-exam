package com.example.e_exam.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_exam.Exam_ListActivity;
import com.example.e_exam.Exam_TestActivity;
import com.example.e_exam.R;
import com.example.e_exam.model.Professor;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder>{
    Context context;
    List<Professor> professors;
    public  StudentAdapter(Context context,List<Professor>professors){
        this.context=context;
        this.professors=professors;
    }
    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.test_layout, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Professor professor = professors.get(position);
        holder.txt_name.setText(professor.getName());
        holder.txt_name_arbic.setText(professor.getName_arabic());
        holder.btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Exam_TestActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return professors.size();
    }

    public  class StudentViewHolder extends  RecyclerView.ViewHolder{
        TextView txt_name_arbic;
        TextView txt_name;
        CardView cardView;
        Button btn_test;
        public StudentViewHolder(@NonNull View itemView) {

            super(itemView);
            txt_name=itemView.findViewById(R.id.txt_name);
            txt_name_arbic=itemView.findViewById(R.id.txt_name_arabic);
            cardView=itemView.findViewById(R.id.card_subject);
            btn_test=itemView.findViewById(R.id.test);

        }
    }
}
