package com.example.e_exam.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_exam.R;
import com.example.e_exam.TestActivity;
import com.example.e_exam.UpdateQuestion;
import com.example.e_exam.model.Question;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {
    Context context;
    List<Question> questions;
    private  OnItemClickListener mListener;
    public  interface OnItemClickListener{
        public  void OnItemClick(int position);

    }
    public  void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }
    public  QuestionAdapter(Context context,List<Question>questions){
        this.context=context;
        this.questions=questions;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup  parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_layout, parent, false);

        return new QuestionViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        Question question = questions.get(position);
        holder.txt_difficulity.setText(question.getDifficulty());
        holder.txt_type.setText(question.getType());
        holder.txt_content.setText(question.getQuestionContent());
        holder.radio_valid_answer.setText(question.getValidAnswer());
        holder.radio_wrong_answer1.setText(question.getWrongAnswer1());
        holder.radio_wrong_answer2.setText(question.getWrongAnswer2());
        holder.radio_wrong_answer3.setText(question.getWrongAnswer3());
      /*
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, UpdateQuestion.class);
                context.startActivity(intent);
            }
        });


       */

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public  class  QuestionViewHolder extends  RecyclerView.ViewHolder{
        TextView txt_difficulity,txt_type;
        TextView txt_content;
        RadioButton radio_valid_answer,radio_wrong_answer1,radio_wrong_answer2,radio_wrong_answer3;
        LinearLayout layout;


        public QuestionViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            txt_difficulity=itemView.findViewById(R.id.txt_difficulity);
            txt_type=itemView.findViewById(R.id.txt_type);
            txt_content=itemView.findViewById(R.id.txtquestion_content);
            radio_valid_answer=itemView.findViewById(R.id.valid_answer);
            radio_wrong_answer1=itemView.findViewById(R.id.wrong_answer1);
            radio_wrong_answer2=itemView.findViewById(R.id.wrong_answer2);
            radio_wrong_answer3=itemView.findViewById(R.id.wrong_answer3);
            layout=itemView.findViewById(R.id.linear);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
        if(listener!=null){
            int position = getAdapterPosition();
            if(position!=RecyclerView.NO_POSITION){
                listener.OnItemClick(position);
            }
        }
                }
            });
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Question question = questions.get(getAdapterPosition());
                    showupdatedialogoue(question.getQuestionId());
                }
            });


        }
    }

    private void showupdatedialogoue(final String questionId) {

        final AlertDialog.Builder alertdialog=new AlertDialog.Builder(context);
        final   View view = LayoutInflater.from(context).inflate(R.layout.layout_update, null);

        alertdialog.setView(view);
        final Spinner spinner_type=view.findViewById(R.id.type);
        final Spinner spinner_difficulity=view.findViewById(R.id.difficulity);
        final EditText edit_content=view.findViewById(R.id.content);
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
                    Toast.makeText(context, "this field is required", Toast.LENGTH_SHORT).show();
                }
                else    if(TextUtils.isEmpty(Difficulity)){
                    Toast.makeText(context, "this field is required", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(Content)){
                    Toast.makeText(context, "this field is required", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(Valid_answer)){
                    Toast.makeText(context, "this field is required", Toast.LENGTH_SHORT).show();
                }
                else   if(TextUtils.isEmpty(Wrong_answer1)){
                    Toast.makeText(context, "this field is required", Toast.LENGTH_SHORT).show();
                }
                else   if(TextUtils.isEmpty(Wrong_answer2)){
                    Toast.makeText(context, "this field is required", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(Wrong_answer3)){
                    Toast.makeText(context, "this field is required", Toast.LENGTH_SHORT).show();
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

    private boolean updat(String questionId, String difficulity, String content, String type, String valid_answer, String wrong_answer1, String wrong_answer2, String wrong_answer3) {
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Questions").child("Level One").child("Arabic").child("A").child("mcq").child(questionId);

        Question question=new Question(questionId,difficulity,content,type,valid_answer,wrong_answer1,wrong_answer2,wrong_answer3);
        databaseReference.setValue(question);
        return  true;
    }
    private void delet_question(String questionId) {
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Questions").child("Level One").child("Arabic").child("A").child("mcq").child(questionId);
        databaseReference.removeValue();
        Toast.makeText(context, "Question deleted successfullu", Toast.LENGTH_SHORT).show();
    }
}
