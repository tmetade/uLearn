package com.example.tmetade.ulearn.games;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.tmetade.ulearn.GameFinish;
import com.example.tmetade.ulearn.Main;
import com.example.tmetade.ulearn.R;

public class CardMatching extends AppCompatActivity implements View.OnClickListener
{

    private TextView mTextTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_matching);

        mTextTimer = (TextView) findViewById(R.id.text_time_left);

        findViewById(R.id.back_button).setOnClickListener(this);


        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                mTextTimer.setText("0:" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                startActivity(new Intent(getBaseContext(), GameFinish.class));
            }
        }.start();
    }

    @Override
    public void onClick(View view)
    {
        int buttonId = view.getId();
        if (buttonId == R.id.back_button)
        {
            startActivity(new Intent(getBaseContext(), Main.class));
        }
    }

}
