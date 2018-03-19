package com.example.tmetade.ulearn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GameFinish extends AppCompatActivity implements View.OnClickListener
{

    private static final String TAG = "GameFinish";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_finish);

        findViewById(R.id.retry_button).setOnClickListener(this);
        findViewById(R.id.games_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        int buttonId = v.getId();
        if (buttonId == R.id.retry_button)
        {

        }
        else if (buttonId == R.id.games_button)
        {
            startActivity(new Intent(getBaseContext(), Main.class));
        }
    }
}
