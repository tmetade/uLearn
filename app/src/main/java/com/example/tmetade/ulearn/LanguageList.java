package com.example.tmetade.ulearn;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LanguageList extends AppCompatActivity implements View.OnClickListener
{

    private static final String TAG = "LanguageList";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_list);

        findViewById(R.id.btn_french).setOnClickListener(this);
    }

    public void onClick(View view)
    {
        int buttonId = view.getId();
        if (buttonId == R.id.btn_french)
        {
            startActivity(new Intent(getBaseContext(), Main.class));
        }
    }
}
