package com.example.comp90018;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by acer-pc on 2016/4/17.
 */
public class SetRePlayActivity extends AppCompatActivity implements View.OnClickListener {

    private CheckBox no_replay,everyDay_replay,everyWeek_replay,everyMonth_replay,everyYear_replay;

    @OnClick(R.id.left_back) void finishClose(){
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_set_replay);

        ButterKnife.bind(this);

        no_replay = (CheckBox) findViewById(R.id.no_replay);
        everyWeek_replay = (CheckBox) findViewById(R.id.everyWeek_replay);
        everyMonth_replay = (CheckBox) findViewById(R.id.everyMonth_replay);
        everyDay_replay = (CheckBox) findViewById(R.id.everyDay_replay);
        everyYear_replay = (CheckBox) findViewById(R.id.everyYear_replay);

        no_replay.setChecked(true);

        no_replay.setOnClickListener(this);
        everyWeek_replay.setOnClickListener(this);
        everyMonth_replay.setOnClickListener(this);
        everyDay_replay.setOnClickListener(this);
        everyYear_replay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.no_replay:
                no_replay.setChecked(true);
                everyWeek_replay.setChecked(false);
                everyMonth_replay.setChecked(false);
                everyDay_replay.setChecked(false);
                everyYear_replay.setChecked(false);
                intent.putExtra("replay", "no repeat");
                setResult(0, intent);
                finish();
                break;
            case R.id.everyWeek_replay:
                everyWeek_replay.setChecked(true);
                no_replay.setChecked(false);
                everyMonth_replay.setChecked(false);
                everyDay_replay.setChecked(false);
                everyYear_replay.setChecked(false);
                intent.putExtra("replay", "every week");
                setResult(0, intent);
                finish();
                break;
            case R.id.everyMonth_replay:
                everyMonth_replay.setChecked(true);
                no_replay.setChecked(false);
                everyWeek_replay.setChecked(false);
                everyDay_replay.setChecked(false);
                everyYear_replay.setChecked(false);
                intent.putExtra("replay", "every month");
                setResult(0, intent);
                finish();
                break;
            case R.id.everyDay_replay:
                everyDay_replay.setChecked(true);
                no_replay.setChecked(false);
                everyWeek_replay.setChecked(false);
                everyMonth_replay.setChecked(false);
                everyYear_replay.setChecked(false);
                intent.putExtra("replay", "every day");
                setResult(0, intent);
                finish();
                break;
            case R.id.everyYear_replay:
                everyYear_replay.setChecked(true);
                no_replay.setChecked(false);
                everyWeek_replay.setChecked(false);
                everyMonth_replay.setChecked(false);
                everyDay_replay.setChecked(false);
                intent.putExtra("replay", "every year");
                setResult(0, intent);
                finish();
                break;
        }
    }
}
