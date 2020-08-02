package fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_exam.R;
import com.example.e_exam.adapter.TrueFalseAdapter;
import com.example.e_exam.model.Subject;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class TrueFalseFragment extends Fragment {

 RecyclerView recycler_truefalse;
 List<Subject>subjects;
 DatabaseReference data_truefalse;
    public TrueFalseFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_true_false, container, false);
        subjects=new ArrayList<>();
        data_truefalse= FirebaseDatabase.getInstance().getReference("Questions").child("Level One").child("Arabic").child("A").child("trueFalse");
        data_truefalse.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    Subject subject = ds.getValue(Subject.class);
                    subjects.add(subject);
                }
                TrueFalseAdapter adapter=new TrueFalseAdapter(getActivity(),subjects);
                recycler_truefalse.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        initializerecycler(view);
        return  view;
    }

    private void initializerecycler(View view) {
        recycler_truefalse=view.findViewById(R.id.recycler_truefalse);
        recycler_truefalse.setHasFixedSize(true);
        recycler_truefalse.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
