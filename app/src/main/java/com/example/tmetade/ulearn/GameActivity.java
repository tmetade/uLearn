package com.example.tmetade.ulearn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.tmetade.ulearn.games.CardMatching;

public class GameActivity extends AppCompatActivity implements Runnable
{

    private static final String TAG = "GameActivity";


    private GameView gameView;
    private Thread gameThread = null;
    volatile boolean playing;

    int iTimeElapsed = 0;
    int iTimer = 3000;

    private TextView mTextTimer;
    Intent intentExtras;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        intentExtras = getIntent();
        Bundle extrasBundle = intentExtras.getExtras();

        if(!extrasBundle.isEmpty())
        {
            if(extrasBundle.containsKey("game"))
            {
                String game = extrasBundle.getString("game");
                switch (game){
                    case "cardMatching":
                        CardMatching card = new CardMatching();
                }
            }
        }
        mTextTimer = (TextView) findViewById(R.id.text_time_left);
    }

    //pausing the game when activity is paused
    @Override
    protected void onPause()
    {
        super.onPause();
        pause();
    }

    //running the game when activity is resumed
    @Override
    protected void onResume()
    {
        super.onResume();
        resume();
    }

    @Override
    public void run()
    {
        while (playing) {
            //to update the frame
            update();

            //to draw the frame
            draw();

            //to control
            control();
        }
    }

    private void update()
    {
        iTimeElapsed ++;
        final int iTimeRemaining = iTimer - iTimeElapsed;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextTimer.setText(" " + iTimeRemaining / 100);
            }
        });
        if (iTimeRemaining < 0)
        {
            startActivity(new Intent(getBaseContext(), GameFinish.class));
        }
    }

    private void draw()
    {

    }

    private void control()
    {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        //when the game is paused
        //setting the variable to false
        playing = false;
        try {
            //stopping the thread
            gameThread.join();
        } catch (InterruptedException e) {
        }
    }

    public void resume() {
        //when the game is resumed
        //starting the thread again
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
}