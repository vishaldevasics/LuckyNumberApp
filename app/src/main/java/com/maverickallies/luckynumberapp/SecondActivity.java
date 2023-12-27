package com.maverickallies.luckynumberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.badge.BadgeUtils;

import org.w3c.dom.Text;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    TextView welcomeText,luckyNumberText;
    Button shareBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        luckyNumberText = findViewById(R.id.luckyNumTVId);
        shareBtn = findViewById(R.id.sharBtnId);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("name");

        int generatedNum = generateRandomNumber();

        luckyNumberText.setText(""+generatedNum);

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shareData(userName,generatedNum);

            }
        });

    }

    public int generateRandomNumber(){
        Random random = new Random();
        int upper_limit = 100;
        int randomNumberGenerated = random.nextInt(upper_limit);
        return randomNumberGenerated;
    }
    public void shareData(String userName,int randomNum){
        //Implicit Intents.

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        //Additional Info
        intent.putExtra(Intent.EXTRA_SUBJECT,userName + " got lucky today.");
        intent.putExtra(Intent.EXTRA_TEXT,"His lucky number is "+randomNum);

        //createChooser class is a utility provided by Android to select the app via which you want to share data.
        startActivity(Intent.createChooser(intent,"choose a Platform"));

    }

}