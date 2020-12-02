package com.ColossusMachina.realitycheck;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class TipsActivity extends Activity {

    List<String> listOfTips = new ArrayList<>();
    TextView tipsTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        tipsTextView = findViewById(R.id.TipsTextView);
        tipsTextView.setAlpha(0f);
        tipsTextView.setTranslationY(-10f);

        try {
            final Typeface typeface = ResourcesCompat.getFont(TipsActivity.this, R.font.special_elite_regular);
            tipsTextView.setTypeface(typeface);
        } catch (Exception e){
            e.printStackTrace();
        }

        listOfTips.add("do a check each time you eat.");

        listOfTips.add(
                "Reality testing, or reality checking, is a form of mental training." +
                "It increases metacognition by training your mind to notice your own awareness."
                + "    \n" +
                "According to Cognitive NeuropsychiatryTrusted Source," +
                " your level of metacognition is similar in your waking and dreaming states." +
                "So, higher metacognition when you’re awake could lead to higher metacognition when you’re dreaming.\n"
                + "    \n" +
                "To enhance your metacognition, you can do reality tests while you’re awake.");

        listOfTips.add("This is a small problem some people have when they reality check," +
                " specially when they do it a lot and never change their reality check." +
                "Sometimes you can get used to a certain type of reality check," +
                " so that when you do it in the dream it has no effect and doesn’t actually do anything." +
                "    \n" +
                "This is a problem and can be very annoying when you’re trying to induce lucidity." +
                "To combat this just try and change your reality checks about once a month," +
                " so you’re not always doing the same one. It keeps your mind guessing and keeps you on track.");

        listOfTips.add(
                "The most important thing to remember when doing reality checks is that," +
                " whatever the ‘action’ is, you must really expect it to happen." +
                " If you’re doing the finger-palm push, REALLY expect your finger to go through your palm.");

        listOfTips.add(
                "If you want a physical reality check, " +
                "try looking at your personal \"totem\" and using it as your key to your dream world.");

        listOfTips.add("Get some small stickers and write ‘are you dreaming’ on them and stick them around your house!\n");

        listOfTips.add(
                "When you doing a reality check, you have to be aware of the environment around you," +
                " you have to be in a state of total awareness." +
                "There are many mindfulness practices such as, " +
                "Direct your attention to your breath, or your body sensations for example." +
                " Whenever you notice that your attention has wandered away from this, gently bring it back.");

        listOfTips.add(
                "Mindfulness is like a muscle that need to be trained to be efficient." +
                " practice in stillness (at least 10 minutes, on a chair or cushion)" +
                "practice in motion (e.g. during exercise, walking or eating)");


        try {
            DisplayTips();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public  void DisplayTips(){


        String randomTip;
        Random random = new Random();

        int tipIndex = random.nextInt(listOfTips.size());
        randomTip = listOfTips.get(tipIndex);

        tipsTextView.setText(randomTip);
        tipsTextView.animate().translationYBy(10f).setDuration(3000);
        tipsTextView.animate().alpha(1f).setDuration(1000);
    }


    //Reality testing, or reality checking, is a form of mental training.
    // It increases metacognition by training your mind to notice your own awareness.
    //
    //According to Cognitive NeuropsychiatryTrusted Source,
    // your level of metacognition is similar in your waking and dreaming states.
    // So, higher metacognition when you’re awake could lead to higher metacognition when you’re dreaming.
    //
    //This may be related to the brain’s prefrontal cortex,
    // which plays a role in both reality testing and lucid dreaming.
    // To enhance your metacognition, you can do reality tests while you’re awake.
    //https://www.healthline.com/health/healthy-sleep/how-to-lucid-dream#how-to-lucid-dream

    //This is a small problem some people have when they reality check,
    // specially when they do it a lot and never change their reality check.
    // Sometimes you can get used to a certain type of reality check,
    // so that when you do it in the dream it has no effect and doesn’t actually do anything.
    //
    //This is a problem and can be very annoying when you’re trying to induce lucidity.
    // To combat this just try and change your reality checks about once a month,
    // so you’re not always doing the same one. It keeps your mind guessing and keeps you on track.


    //The most important thing to remember when doing reality checks is that,
    // whatever the ‘action’ is you must really expect it to happen.
    // If you’re doing the finger/palm push, REALLY expect your finger to go through your palm.

    //If you want a physical reality check, try looking at your personal "totem" and using it as your key to your dream world.

    //Get some small stickers and write ‘are you dreaming’ on them and stick them around your house!
    //https://howtolucid.com/perfect-times-to-do-a-reality-check/

    //When you doing a reality check, you have to be aware of the environment around you,
    //you have to be in a state of total awareness.
    //There are many mindfulness practices such as,
    // Direct your attention to your breath, your body sensations or whatever..
    // Whenever you notice that your attention has wandered away from this, gently bring it back.
    //https://megsalter.com/mindfulness-reality-check-eight-tips-one-warning-and-one-booster/

    // Mindfulness is like a muscle that need to be trained to be efficient.
    //practice in stillness (at least 10 minutes, on a chair or cushion)
    //practice in motion (e.g. during exercise, walking or eating)
    //https://megsalter.com/mindfulness-reality-check-eight-tips-one-warning-and-one-booster/
}
