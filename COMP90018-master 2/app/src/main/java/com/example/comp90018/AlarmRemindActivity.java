package com.example.comp90018;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by acer-pc on 2016/4/21.
 */
public class AlarmRemindActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getIntent().getExtras();
        AlarmBean alarmBean = (AlarmBean) bundle.getSerializable("alarm");

        Toast.makeText(this,"Ringing！"+alarmBean.getTitle(), Toast.LENGTH_SHORT).show();
    }

}
