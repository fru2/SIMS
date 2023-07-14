package com.patnacollege.sims;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.patnacollege.sims.dataAdapter.AssignmentAdapter;
import com.patnacollege.sims.dataModel.AssignmentDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AssignmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AssignmentFragment extends Fragment {


    private RecyclerView recyclerView;
    private AssignmentAdapter adapter;
    private DatabaseReference databaseReference;
    private List<AssignmentDataModel> dataList;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AssignmentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AssignmentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AssignmentFragment newInstance(String param1, String param2) {
        AssignmentFragment fragment = new AssignmentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_assignment, container, false);

        firebaseDataFetch(view);

        // Inflate the layout for this fragment
        return view;
    }

    public void firebaseDataFetch(View view){
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        dataList = new ArrayList<>();
        adapter = new AssignmentAdapter(dataList);
        recyclerView.setAdapter(adapter);

        // Initialize Firebase Realtime Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Assignment");

        // Fetch data from Firebase Realtime Database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataList.clear();
                for (DataSnapshot outerSnapshot : dataSnapshot.getChildren()) {
                    String title = outerSnapshot.child("Title").getValue(String.class);
                    String description = outerSnapshot.child("Sub_title").getValue(String.class);
                    String dueDate = outerSnapshot.child("Due_date").getValue(String.class);
                    String subject = outerSnapshot.child("Subject").getValue(String.class);
                    AssignmentDataModel data = new AssignmentDataModel(title, description, dueDate, subject);
                    dataList.add(data);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                HelperFunctions.makeToast(view.getContext(), "Failed to get data from Firebase");
            }
        });
    }
}