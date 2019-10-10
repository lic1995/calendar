package com.example.comp90018;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class SetUpTab extends Fragment {

    private Button btnSetDate,btnSetTime;
    private TextView ShowDate,ShowTime;
    private FirebaseAuth mAuth;

    public SetUpTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_set_up_tab, container, false);
        btnSetDate=view.findViewById(R.id.btnSetDate);
        ShowDate=view.findViewById(R.id.txtShowDate);
        btnSetTime=view.findViewById(R.id.btnSetTime);
        ShowTime=view.findViewById(R.id.txtShowTime);
        mAuth= FirebaseAuth.getInstance();


        btnSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
//                ShowTime.setText(TimeUtils);
                TimePickerDialog timePickerDialog=new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        calendar.set(Calendar.HOUR_OF_DAY, i);
                        calendar.set(Calendar.MINUTE, i1);

                        ShowTime.setText(DateUtils.formatDateTime(getContext(), calendar.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME));

                        FirebaseDatabase.getInstance().getReference().child("my_users").child(mAuth.getCurrentUser().getUid()).child("SetTime")
                                .setValue(DateUtils.formatDateTime(getContext(), calendar.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME));
                    }
                },calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        false);
                timePickerDialog.show();
            }
        });
        btnSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
//                ShowDate.setText(DateUtils.formatDateTime(MainPageActivity.this, calendar.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_SHOW_YEAR));
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                                LogUtils.d(TAG, "onDateSet: year: " + year + ", month: " + month + ", dayOfMonth: " + dayOfMonth);

                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, month);
                                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                                ShowDate.setText(DateUtils.formatDateTime(getContext(), calendar.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_SHOW_YEAR));

                                FirebaseDatabase.getInstance().getReference().child("my_users").child(mAuth.getCurrentUser().getUid()).child("SetDate")
                                        .setValue(DateUtils.formatDateTime(getContext(), calendar.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_SHOW_YEAR));
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

            }
        });

        return view;
    }

}
