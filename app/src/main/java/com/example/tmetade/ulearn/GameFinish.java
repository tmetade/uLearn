package com.example.tmetade.ulearn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameFinish extends AppCompatActivity implements View.OnClickListener
{

    private static final String TAG = "GameFinish";

    TextView mTextUserScore;

    Intent intentExtras;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_finish);

        mTextUserScore = findViewById(R.id.text_user_score);

        intentExtras = getIntent();
        Bundle extrasBundle = intentExtras.getExtras();

        if(!extrasBundle.isEmpty())
        {
            if(extrasBundle.containsKey("score"))
            {
                String score = extrasBundle.getString("score");
                mTextUserScore.setText(score);
            }
        }

        findViewById(R.id.retry_button).setOnClickListener(this);
        findViewById(R.id.games_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        int buttonId = v.getId();
        if (buttonId == R.id.retry_button)
        {
            Intent intent;
            Bundle bundle = new Bundle();
            bundle.putString("game","cardMatching");
            intent = new Intent(getBaseContext(),GameActivity.class);
            intent.putExtras(bundle);
            finish();
            startActivity(intent);
        }
        else if (buttonId == R.id.games_button)
        {
            startActivity(new Intent(getBaseContext(), Main.class));
        }
    }
}
