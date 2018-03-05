package com.example.tmetade.ulearn.games;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tmetade.ulearn.Login;
import com.example.tmetade.ulearn.Main;
import com.example.tmetade.ulearn.R;

public class CardMatching extends AppCompatActivity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_matching);

        findViewById(R.id.back_button).setOnClickListener(this);
        startGame();
    }

    private void startGame()
    {

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
