package com.example.e_exam.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.e_exam.Exam_ListActivity;
import com.example.e_exam.R;
import com.example.e_exam.Subject_List;
import com.example.e_exam.model.Professor;

import java.util.List;

import okhttp3.Cache;


public class ProfessorAdapter extends RecyclerView.Adapter<ProfessorAdapter.ProfessorViewHolder> implements View.OnClickListener {
    Context context;
    List<Professor>professors;
   // TextView txt_name_arbic;
    //TextView txt_name;
    public ProfessorAdapter(Context context, List<Professor>professors){
        this.context=context;
        this.professors=professors;
    }
    @NonNull
    @Override
    public ProfessorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.subject_item_layout, parent, false);
        return new ProfessorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessorViewHolder holder, int position) {
        Professor professor = professors.get(position);
        holder.txt_name.setText(professor.getName());
        holder.txt_name_arbic.setText(professor.getName_arabic());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Subject_List.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return professors.size();
    }

    @Override
    public void onClick(View v) {

    }

    public  class  ProfessorViewHolder extends RecyclerView.ViewHolder{
        TextView txt_name_arbic;
        TextView txt_name;
        CardView cardView;

        public ProfessorViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name=itemView.findViewById(R.id.txt_name);
            txt_name_arbic=itemView.findViewById(R.id.txt_name_arabic);

            cardView=itemView.findViewById(R.id.card_subject);
           // itemView.setOnClickListener((View.OnClickListener) this);
        }
    }
}
