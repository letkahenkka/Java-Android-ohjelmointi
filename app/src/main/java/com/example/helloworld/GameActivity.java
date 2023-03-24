package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    public static final String TAG="GameActivity";
    private ImageButton imagebutton1;
    private ImageButton imagebutton2;
    private ImageButton imagebutton3;
    private ImageButton imagebutton4;

    private FloatingActionButton refreshbutton;

    private static final String KEY_HS = "HighestScore";

    private int highestSuccessCount;

    private int laskuri = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Random random = new Random();

        int rand_int = random.nextInt(3);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.roundanimation);

        SharedPreferences myPreferences;
        myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.putInt(KEY_HS, 3);
        myEditor.commit();
        highestSuccessCount = myPreferences.getInt(KEY_HS, 0);



        imagebutton1 = findViewById(R.id.imageButton1);
        imagebutton2 = findViewById(R.id.imageButton2);
        imagebutton3 = findViewById(R.id.imageButton3);
        imagebutton4 = findViewById(R.id.imageButton4);

        refreshbutton = findViewById(R.id.refreshButton);

        imagebutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagebutton1.startAnimation(animation);
                if(rand_int == 0){
                    imagebutton1.setImageResource(R.mipmap.ic_launcher);
                }
                else{
                    imagebutton1.setVisibility(View.INVISIBLE);
                }
            }
        });

        imagebutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagebutton2.startAnimation(animation);
                if(rand_int == 1){
                    imagebutton2.setImageResource(R.mipmap.ic_launcher);
                }
                else{
                    imagebutton2.setVisibility(View.INVISIBLE);
                }
            }
        });

        imagebutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagebutton3.startAnimation(animation);
                if(rand_int == 2){
                    imagebutton3.setImageResource(R.mipmap.ic_launcher);
                }
                else{
                    imagebutton3.setVisibility(View.INVISIBLE);
                }
            }
        });

        imagebutton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagebutton4.startAnimation(animation);
                if(rand_int == 3){
                    imagebutton4.setImageResource(R.mipmap.ic_launcher);
                }
                else{
                    imagebutton4.setVisibility(View.INVISIBLE);
                }
            }
        });

        refreshbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());
            }
        });
    }
}