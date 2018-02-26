package com.example.tmetade.ulearn;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Login extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextView mTextView = (TextView) findViewById(R.id.txtForgotPassword);
        mTextView.setPaintFlags(mTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    public void onLogin(View view)
    {
        Intent i = new Intent(getBaseContext(),LanguageList.class);
        startActivity(i);
    }

    void onSignUp(View view)
    {
        Intent i = new Intent(getBaseContext(),SignUp.class);
        startActivity(i);
    }
}