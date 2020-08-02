package com.example.e_exam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.e_exam.R;
import com.example.e_exam.model.Professor;

import java.util.List;

public class ListAdapter extends ArrayAdapter<Professor> {
    Context context;
    List<Professor> professors;

    public  ListAdapter(Context context,List<Professor>professors){
        super(context, R.layout.row_layout,professors);
        this.context=context;
        this.professors=professors;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.subject_item_layout, null, true);
        TextView name=view.findViewById(R.id.txt_name);
        TextView name_arabic=view.findViewById(R.id.txt_name_arabic);
        Professor professor=professors.get(position);
        name.setText(professor.getName());
        name_arabic.setText(professor.getName_arabic());
        return view;
    }
}
