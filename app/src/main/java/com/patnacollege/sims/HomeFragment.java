package com.patnacollege.sims;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private String registrationNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ImageButton notificationBtn = view.findViewById(R.id.navigation_btn);
        notificationBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), NotificaionActivity.class);
            startActivity(intent);
        });

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        registrationNumber = sharedPreferences.getString("key", "0000");

        getFirebaseData(view);

        return view;
    }

    public void getFirebaseData(View view) {
        TextView nameTextView = view.findViewById(R.id.name);
        TextView attendanceTextView = view.findViewById(R.id.attendance);
        TextView gradeTextView = view.findViewById(R.id.grade);
        TextView regNoTextView = view.findViewById(R.id.registration_no);
        TextView semTextView = view.findViewById(R.id.semester_text_view);


        //        TextView totalAssignmentTextView = view.findViewById(R.id.total_assignment);
//        TextView closestDueTextView = view.findViewById(R.id.closest_due);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Registration Number");
        databaseReference.child(registrationNumber).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Retrieve the values from the dataSnapshot
                String name = dataSnapshot.child("Name").getValue(String.class);
                String attendance = dataSnapshot.child("Attendance").getValue(String.class);
                String grade = dataSnapshot.child("Grade").getValue(String.class);
                String sem = dataSnapshot.child("Semester").getValue(String.class);


                // Populate the TextViews
                nameTextView.setText(name);
                attendanceTextView.setText(String.valueOf(attendance));
                gradeTextView.setText(grade);
                regNoTextView.setText(registrationNumber);
                semTextView.setText(sem);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                HelperFunctions.makeToast(view.getContext(), "Something went wrong");
            }
        });


    }
}