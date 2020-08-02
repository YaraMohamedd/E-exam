package fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.telephony.mbms.MbmsErrors;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.e_exam.R;
import com.example.e_exam.adapter.TestAdapter;
import com.example.e_exam.model.Content;
import com.example.e_exam.model.Subject;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class SecondFragment extends Fragment {
    RecyclerView second_recycler;
    List<Subject>subjects;
    DatabaseReference truefalse_data;
    ListView list;
    public SecondFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_second, container, false);
        second_recycler=view.findViewById(R.id.second_recycler);
        initializerecycler(view);
     //   list=view.findViewById(R.id.list);
        subjects=new ArrayList<>();
        truefalse_data= FirebaseDatabase.getInstance().getReference("Questions").child("Level One").child("Arabic").child("A").child("trueFalse");
        truefalse_data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    Subject subject = ds.getValue(Subject.class);
                    subjects.add(subject);
                }
                TestAdapter adapter=new TestAdapter(getActivity(),subjects);
                second_recycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Error:"+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return  view;
    }

    private void initializerecycler(View view) {
       // second_recycler=view.findViewById(R.id.second_recycler);
        second_recycler.setHasFixedSize(true);
        second_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
