package com.example.myapplication10;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication10.databinding.ActivityMainBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.nio.channels.Channel;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MaterialTimePicker picker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        createrNotficationChannel();


        binding.time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                showTimePicker();

            }
        });

        binding.alarm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setAlarm();

            }
        });

        binding.cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cancelAlarm();

            }
        });
    }

    private void cancelAlarm() {

        Intent intent = new Intent(this,AlarmManager.class);

        pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        if (alarmManager == null ){

            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        }

        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Cancelled", Toast.LENGTH_SHORT).show();
    }

    private void setAlarm() {

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this,AlarmManager.class);

        pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,pendingIntent;

        Toast.makeText(this,"Alarm set Successfully", Toast.LENGTH_SHORT).show();



    }

    private void showTimePicker() {
        picker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Alarm time")
                .build();

        picker.show(getSupportFragmentManager(),"foxandoird";

        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (picker.getHour() > 12){

                    binding.selecttime.setText(
                            String.format("%02d",(picker.getHour()-12)+":"+String.format(("%02d",picker.getMinute())+"PM")
                    );

                }else {

                    binding.selecttime.setText(picker.getHour() + ":" + picker.getHour() + "AM");

                }

                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,picker.getHour());
                calendar.set(Calendar.MINUTE,picker.getMinute());
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND,0);


            }
        })


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createrNotficationChannel() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES,0){
            CharSequence name = "foxandroidReminderChanel";
            String descriptoin = "Channel For Alarm Manger";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("foxandroid",name,importance);
            channel.setDescription(descriptoin);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);



        }
    }


}


