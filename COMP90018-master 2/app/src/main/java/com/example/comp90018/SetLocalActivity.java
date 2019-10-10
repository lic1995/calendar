package com.example.comp90018;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SetLocalActivity extends AppCompatActivity {

    @BindView(R.id.ed_local)
    EditText ed_local;
    @OnClick(R.id.tv_save) void saveAndClose(){
        Intent intent=new Intent();
        if(ed_local.getText().toString().equals("")){
            intent.putExtra("local", "None");
            setResult(2, intent);
            finish();
        }else {
            intent.putExtra("local", ed_local.getText().toString());
            setResult(2, intent);
            finish();
        }

    }

    @OnClick(R.id.left_local_back) void finishClose(){
        finish();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_local);
        ButterKnife.bind(this);


    }
}
