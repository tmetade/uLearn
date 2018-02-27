package com.example.tmetade.ulearn;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LanguageList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_list);
    }

    public void onClick(View view){
        Intent i = new Intent(getBaseContext(),Main.class);
        startActivity(i);
    }
}
