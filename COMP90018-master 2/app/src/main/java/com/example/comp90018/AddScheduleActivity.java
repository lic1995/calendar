package com.example.comp90018;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddScheduleActivity extends AppCompatActivity {

    private DatePickerDialog mDataPicker;
    private TimePickerDialog mStartTimePicker, mEndTimePicker;
    private boolean isAllDay = false;
    private boolean isVibrate = false;
    private AlarmBean alarmBean = new AlarmBean();
    private AlarmDBSupport support;
    private int id;

    @BindView(R.id.alarm_title)
    EditText alarm_title;
    @BindView(R.id.alarm_description)
    EditText alarm_description;
    @BindView(R.id.alarm_replay)
    TextView alarm_replay;
    @BindView((R.id.alarm_remind))
    TextView alarm_remind;
    @BindView(R.id.alarm_local)
    TextView alarm_local;
    @BindView(R.id.alarm_color)
    TextView alarm_color;
    @BindView(R.id.alarm_tone_Path)
    TextView alarm_tone_Path;
    @BindView(R.id.alarm_date)
    TextView alarm_date;
    @BindView(R.id.insert_update_title)
    TextView insert_update_title;
    @BindView(R.id.action_bar)
    LinearLayout action_bar;

    @OnClick(R.id.alarm_date)
    void openDatePicker() {
        getDatePickerDialog();
        mDataPicker.show();
    }

    @BindView(R.id.alarm_start_time)
    TextView alarm_start_time;

    @OnClick(R.id.alarm_start_time)
    void openStartTimePicker() {
        getStartTimePickerDialog();
        mStartTimePicker.show();
    }

    @BindView(R.id.alarm_end_time)
    TextView alarm_end_time;

    @OnClick(R.id.alarm_end_time)
    void openEndTimePicker() {
        getEndTimePickerDialog();
        mEndTimePicker.show();
    }

    @OnClick(R.id.left_clear)
    void clear() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @OnClick(R.id.layout_alarm_replay)
    void openSetReplayActivity() {
        startActivityForResult(new Intent(AddScheduleActivity.this, SetRePlayActivity.class), 0);
    }

    @OnClick(R.id.layout_alarm_remind)
    void openSetAlarmTimeActivity() {
        startActivityForResult(new Intent(AddScheduleActivity.this, SetAlarmTimeActivity.class), 1);
    }

    @OnClick(R.id.layout_alarm_local)
    void openSetLocalActivity() {
        startActivityForResult(new Intent(AddScheduleActivity.this, SetLocalActivity.class), 2);
    }

    @OnClick(R.id.layout_alarm_color)
    void openSetColorActivity() {
        startActivityForResult(new Intent(AddScheduleActivity.this, SetColorActivity.class), 3);
    }

    @OnClick(R.id.layout_alarm_tone_Path)
    void openSetAlarmToneActivity() {
        startActivityForResult(new Intent(AddScheduleActivity.this, SetAlarmToneActivity.class), 4);
    }

    @BindView(R.id.sw_all_day)
    Switch sw_all_day;

    @OnClick(R.id.sw_all_day)
    void allday() {
        if (!isAllDay) {
            alarm_start_time.setVisibility(View.GONE);
            alarm_end_time.setVisibility(View.GONE);
            isAllDay = true;
        } else {
            alarm_start_time.setVisibility(View.VISIBLE);
            alarm_end_time.setVisibility(View.VISIBLE);
            isAllDay = false;
        }
    }

    @BindView(R.id.sw_vibrate)
    Switch sw_vibrate;

    @OnClick(R.id.sw_vibrate)
    void is_Vibrate() {
        Vibrator vibrator;
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        if (!isVibrate) {
            alarmBean.setIsVibrate(1);
            vibrator.vibrate(500);
            isVibrate = true;
        } else {
            alarmBean.setIsVibrate(0);
            vibrator.cancel();
            isVibrate = false;
        }
    }

    @OnClick(R.id.tv_save)
    void saveAlarmBeanToDB() {

        if (alarm_title.getText().toString().equals("")) {
            alarmBean.setTitle("No title");
        } else {
            alarmBean.setTitle(alarm_title.getText().toString());
        }


        if (alarm_description.getText().toString().equals("")) {
            alarmBean.setDescription("No description");
        } else {
            alarmBean.setDescription(alarm_description.getText().toString());
        }


        if (alarm_local.getText().equals("Add location")) {
            alarmBean.setLocal("NO location");
        }


        if (isAllDay) {
            alarmBean.setIsAllday(1);
        } else {
            alarmBean.setIsAllday(0);
        }


        if (alarm_date.getText().toString().equals("Select event date")) {
            alarmBean.setYear(getToDay().get(Calendar.YEAR));
            alarmBean.setMonth(getToDay().get(Calendar.MONTH));
            alarmBean.setDay(getToDay().get(Calendar.DAY_OF_MONTH));
        }


        if (alarm_start_time.getText().toString().equals("Select start time")) {
            if(isAllDay){
                alarmBean.setStartTimeHour(0);
                alarmBean.setStartTimeMinute(0);
            }else {
                alarmBean.setStartTimeHour(getToDay().get(Calendar.HOUR_OF_DAY));
                alarmBean.setStartTimeMinute(getToDay().get(Calendar.MINUTE));
            }
        }


        if (alarm_end_time.getText().toString().equals("Select end time")) {
            if(isAllDay){
                alarmBean.setEndTimeHour(23);
                alarmBean.setEndTimeMinute(59);
            }else {
                alarmBean.setEndTimeHour(getToDay().get(Calendar.HOUR_OF_DAY) + 1);
                alarmBean.setEndTimeMinute(getToDay().get(Calendar.MINUTE));
            }
        }


        if (alarm_remind.getText().toString().equals("Select reminder time")) {
            alarmBean.setAlarmTime("Defalut");
        } else {
            alarmBean.setAlarmTime(alarm_remind.getText().toString());
        }


        if (alarm_replay.getText().toString().equals("No repeat")) {
            alarmBean.setReplay("No repeat");
        } else {
            alarmBean.setReplay(alarm_replay.getText().toString());
        }


        if (alarm_tone_Path.getText().toString().equals("Sound")) {
            Uri uri = RingtoneManager.getActualDefaultRingtoneUri(
                    AddScheduleActivity.this, RingtoneManager.TYPE_RINGTONE);
            alarmBean.setAlarmTonePath(uri.toString());
        }


        if (alarm_color.getText().toString().equals("moren")) {
            alarmBean.setAlarmColor("moren");
        } else {
            alarmBean.setAlarmColor(alarm_color.getText().toString());
        }


        if (alarm_local.getText().toString().equals("No location")) {
            alarmBean.setLocal("No location");
        } else {
            alarmBean.setLocal(alarm_local.getText().toString());
        }

        if (id == 0) {
            System.out.println("Saved data:" + alarmBean.toString());
            support.insertAlarmDate(alarmBean);

            SendAlarmBroadcast.startAlarmService(this);

            Toast.makeText(this, "Added successfully！", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            System.out.println("Updated date:" + alarmBean.toString());
            support.updateDataById(id, alarmBean);

            SendAlarmBroadcast.startAlarmService(this);

            Toast.makeText(this, "Updated successfully！", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        ButterKnife.bind(this);

        support = new AlarmDBSupport(getApplicationContext());
        if (getIntent().getStringExtra("type").equals("DetailToAdd")) {
            Intent intent = getIntent();
            AlarmBean bean = (AlarmBean) intent.getSerializableExtra("AlarmBean");
            id = bean.getId();
            alarm_title.setText(bean.getTitle());
            alarm_remind.setText(bean.getAlarmTime());
            alarm_color.setText(bean.getAlarmColor());
            alarm_description.setText(bean.getDescription());
            alarm_local.setText(bean.getLocal());
            alarm_replay.setText(bean.getReplay());
            insert_update_title.setText("Change event");

            int colorId = ColorUtils.getColorFromStr(bean.getAlarmColor());
            action_bar.setBackgroundColor(getResources().getColor(colorId));
            Window window = getWindow();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(getResources().getColor(colorId));
            }
        } else {
            insert_update_title.setText("Add event");
        }
    }


    private void getDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        mDataPicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat df = new SimpleDateFormat("DD/MM/YY DoW");
                alarm_date.setText(df.format(calendar.getTime()));


                alarmBean.setYear(year);
                alarmBean.setMonth(monthOfYear);
                alarmBean.setDay(dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }


    private void getStartTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        mStartTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                SimpleDateFormat df = new SimpleDateFormat("HH:mm");

                alarm_start_time.setText("Start time:  " + df.format(calendar.getTime()));


                alarmBean.setStartTimeHour(hourOfDay);
                alarmBean.setStartTimeMinute(minute);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
    }


    private void getEndTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        mEndTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                SimpleDateFormat df = new SimpleDateFormat("HH:mm");

                alarm_end_time.setText("End time:  " + df.format(calendar.getTime()));



                alarmBean.setEndTimeHour(hourOfDay);
                alarmBean.setEndTimeMinute(minute);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
    }

    private Calendar getToDay() {
        Calendar today = Calendar.getInstance();
        today.setTimeInMillis(System.currentTimeMillis());
        return today;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == 0) {
                if (data != null) {
                    alarm_replay.setText(data.getStringExtra("replay"));
                    alarmBean.setReplay(data.getStringExtra("replay"));
                }
            }
        } else if (requestCode == 1) {
            if (resultCode == 1) {
                if (data != null) {
                    alarm_remind.setText(data.getStringExtra("remind"));
                    alarmBean.setAlarmTime(data.getStringExtra("remind"));
                }
            }
        } else if (requestCode == 2) {
            if (resultCode == 2) {
                if (data != null) {
                    alarm_local.setText(data.getStringExtra("local"));
                    alarmBean.setLocal(data.getStringExtra("local"));
                }
            }
        } else if (requestCode == 3) {
            if (resultCode == 3) {
                if (data != null) {
                    alarm_color.setText(data.getStringExtra("color"));
                    alarmBean.setAlarmColor(data.getStringExtra("color"));
                    int colorId = ColorUtils.getColorFromStr(data.getStringExtra("color"));
                    action_bar.setBackgroundColor(getResources().getColor(colorId));
                    Window window = getWindow();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.setStatusBarColor(getResources().getColor(colorId));
                    }

                }
            }
        } else if (requestCode == 4) {
            if (resultCode == 4) {
                if (data != null) {
                    alarm_tone_Path.setText(data.getStringExtra("tone"));
                    alarmBean.setAlarmTonePath(data.getStringExtra("tonePath"));
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
