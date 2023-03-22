package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private TextView helloText;
    private Button gameStart;

    public static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        helloText = findViewById(R.id.helloText);
        helloText.setVisibility(View.INVISIBLE);
        gameStart = findViewById(R.id.gameStart);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(helloText.getVisibility() == View.VISIBLE){
                    helloText.setVisibility(View.INVISIBLE);
                }
                else{
                    helloText.setVisibility(View.VISIBLE);
                }
            }
        });

        gameStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Game started");
                Intent i = new Intent(view.getContext(), GameActivity.class);
                startActivity(i);
            }
        });

        Log.i(TAG, "Toimii toimii");
    }
}