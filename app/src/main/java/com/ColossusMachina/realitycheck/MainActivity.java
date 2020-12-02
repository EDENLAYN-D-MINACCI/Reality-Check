package com.ColossusMachina.realitycheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    TextView QuoteTextView;
    CheckBox first;
    CheckBox second;
    CheckBox third;
    CheckBox fourth;
    ConstraintLayout introConstraintLayout;
    LinearLayout checkboxesLinearLayout;

    NotificationCompat.Builder notificationBuilder;
    Random random = new Random();
    List<String> realityChecks  = new ArrayList<>();
    List<String> someIntroductionWords  = new ArrayList<>();
    int remainingTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init
        introConstraintLayout = findViewById(R.id.IntroConstraintLayout);
        checkboxesLinearLayout = findViewById(R.id.checkboxesLinearLayout);
        QuoteTextView = findViewById(R.id.QuoteTextView);
        first = findViewById(R.id.FirstCheckBox);
        second = findViewById(R.id.SecondCheckBox);
        third = findViewById(R.id.ThirdCheckBox);
        fourth = findViewById(R.id.FourthCheckBox);


        realityChecks.add("Five Fingers?");
        realityChecks.add("Look at the details of your hands");
        realityChecks.add("Just now, was it Daytime?");
        realityChecks.add("Is matter tangible?");
        realityChecks.add("Deserted Place?");
        realityChecks.add("Who are the people around you?");
        realityChecks.add("How did you get there?");
        realityChecks.add("What was you doing 10 minutes ago?");
        realityChecks.add("Have a look in the mirror");


        someIntroductionWords.add("Be aware.");
        someIntroductionWords.add("Look around.");
        someIntroductionWords.add("Hey, can you see those details?");
        someIntroductionWords.add("Present?");
        someIntroductionWords.add("And where are you.. now?");
        someIntroductionWords.add("can you fly?");



        //hiding main activity and checkboxes
        QuoteTextView.setTranslationY(-10f);
        QuoteTextView.setAlpha(0f);
        checkboxesLinearLayout.setAlpha(0f);
        checkboxesLinearLayout.setVisibility(View.GONE);
        /*
        first.setAlpha(0f);
        second.setAlpha(0f);
        third.setAlpha(0f);
        fourth.setAlpha(0f);
        */

        SetRandomIntroductionText();
        SetCheckboxesTitle();

    }


    public void SetRandomIntroductionText(){

        //init
        int seconds = 5; // intro will last 5s
        int delayOfPrecision = 900; // delay due to precision of the device (set subjectively to 900ms)


        //select random quote
        int randomIndex = random.nextInt(someIntroductionWords.size());
        String randomQuote = someIntroductionWords.get(randomIndex);


        //showing quote
        QuoteTextView.animate().alpha(1f).setDuration(300);
        QuoteTextView.animate().translationYBy(10f).setDuration(1000);
        QuoteTextView.setText(randomQuote);

        //show quote during 5s
        new CountDownTimer(seconds * 1000 + delayOfPrecision, 1000) {
            @Override
            public void onTick(long l) {
                Log.i("INFO - INTRO", "onTick: " + l);

                if(l < 2000){
                    QuoteTextView.animate().alpha(0f).setDuration(200);
                }
            }

            @Override
            public void onFinish() {
                Log.i("INFO - INTRO", "onFinish: Intro Ended");

                //hide quote
                introConstraintLayout.animate().alpha(0f).setDuration(200); //1s fade out

                //display main activity
                checkboxesLinearLayout.setVisibility(View.VISIBLE);
                checkboxesLinearLayout.animate().alpha(1f).setDuration(200); //200ms fade in
            }
        }.start();
    }


    public String PickUpRandomRealityChecks(){
        int randomIndex = random.nextInt(realityChecks.size());
        return realityChecks.get(randomIndex);
    }

    public void SetCheckboxesTitle(){

        List<String> listOfChecks = new ArrayList<>(4);
        String selectedCheck;
        int occurrences;
        int totalIteration = 4;

        // clear the list for next call
        listOfChecks.clear();

        //select 4 random reality check
        for (int i = 0; totalIteration != i; i++) {

            //pick up random item in list of realityChecks, and store it
            selectedCheck = PickUpRandomRealityChecks();

            //Check number of occurence in the list of selected reality check
            occurrences = Collections.frequency(listOfChecks, selectedCheck);

            // if the selected check is already in the list, choose another one
            if(occurrences < 1){

                //add stored check into temporary list
                listOfChecks.add(selectedCheck);

                Log.i("DEBUG - CHECKBOX TITLE", "occurrences: " + occurrences);
                Log.i("DEBUG - CHECKBOX TITLE", "selected check: " + selectedCheck);
                Log.i("DEBUG - CHECKBOX TITLE", "i: " + i);
            }
            else{
                totalIteration += 1;
            }


        }

        //change title of the checkbox with selected reality checks
        first.setText(listOfChecks.get(0));
        second.setText(listOfChecks.get(1));
        third.setText(listOfChecks.get(2));
        fourth.setText(listOfChecks.get(3));


        //fade in checkboxes
        /*
        first.animate().alpha(1f).setDuration(200);
        second.animate().alpha(1f).setDuration(200);
        third.animate().alpha(1f).setDuration(200);
        fourth.animate().alpha(1f).setDuration(200);
        */

    }

    public void DisableCheckbox(TextView checkBox){
        checkBox.setPaintFlags(checkBox.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        checkBox.setTextColor(Color.parseColor("#FF535353"));
        checkBox.setEnabled(false);
    }

    public void OnCheckboxesChecked(View v){

        //on click, disable checkbox
        if(first.isChecked()){
            DisableCheckbox(first);
        }
        if(second.isChecked()){
            DisableCheckbox(second);
        }
        if(third.isChecked()){
            DisableCheckbox(third);
        }
        if(fourth.isChecked()){
            DisableCheckbox(fourth);
        }
        if(
        first.isChecked() &&
        second.isChecked() &&
        third.isChecked() &&
        fourth.isChecked()){

            try{
                //set alarm notification
                int delay = 1000 * 60 * 60; // 1h
                remainingTime = (int) (System.currentTimeMillis() - delay / 20);

                //set notification
                scheduleNotification(getNotification( "Are you dreaming..?", "Stay alert, it's time for a reality check!"), delay);
                Toast.makeText(this, "Next check in one hour.", Toast.LENGTH_SHORT).show();

                //start tips activity
                Intent intent = new Intent(this, TipsActivity.class);
                startActivity(intent);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    private void scheduleNotification(Notification notification, int delay) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
        Log.i("TAG - DEBUG", "scheduleNotification: " + futureInMillis/1000);

    }

    private Notification getNotification(String textTitle, String textContent) {

        //redirect to main activity when notification is tapped
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        //build notification

        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle(textTitle);
        builder.setContentText(textContent);
        builder.setSmallIcon(R.drawable.icon_a);
        builder.setVibrate(new long[] { 0, 500, 100, 1000, 100 }); //Vibration  { delay, vibrate, sleep, vibrate, sleep }
        builder.setContentIntent(pendingIntent); // Set the intent that will fire when the user taps the notification
        builder.setAutoCancel(true); //setAutoCancel(), will removes the notification when the user taps it.

        return builder.build();
    }
}