package com.example.comp90018;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class SetAlarmTimeActivity extends AppCompatActivity implements View.OnClickListener {

    private CheckBox no_remind,min10_remind,hour1_remind,day1_remind;

    @OnClick(R.id.left_alarm_back) void finishClose(){
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm_time);
        ButterKnife.bind(this);
        no_remind = (CheckBox) findViewById(R.id.no_remind);
        min10_remind = (CheckBox) findViewById(R.id.min10_remind);
        hour1_remind = (CheckBox) findViewById(R.id.hour1_remind);
        day1_remind = (CheckBox) findViewById(R.id.day1_remind);

        no_remind.setChecked(true);

        no_remind.setOnClickListener(this);
        min10_remind.setOnClickListener(this);
        hour1_remind.setOnClickListener(this);
        day1_remind.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.no_remind:
                no_remind.setChecked(true);
                min10_remind.setChecked(false);
                hour1_remind.setChecked(false);
                day1_remind.setChecked(false);
                intent.putExtra("remind", "None");
                setResult(1, intent);
                finish();
                break;
            case R.id.min10_remind:
                min10_remind.setChecked(true);
                no_remind.setChecked(false);
                hour1_remind.setChecked(false);
                day1_remind.setChecked(false);
                intent.putExtra("remind", "ten minutes early");
                setResult(1, intent);
                finish();
                break;
            case R.id.hour1_remind:
                hour1_remind.setChecked(true);
                no_remind.setChecked(false);
                min10_remind.setChecked(false);
                day1_remind.setChecked(false);
                intent.putExtra("remind", "one hour early");
                setResult(1, intent);
                finish();
                break;
            case R.id.day1_remind:
                day1_remind.setChecked(true);
                no_remind.setChecked(false);
                min10_remind.setChecked(false);
                hour1_remind.setChecked(false);
                intent.putExtra("remind", "one day early");
                setResult(1, intent);
                finish();
                break;
        }
    }
}
