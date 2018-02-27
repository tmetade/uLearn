package com.example.tmetade.ulearn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Main extends AppCompatActivity  implements View.OnClickListener
{
    private static final String TAG = "Main";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.sign_out_button).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    private void signOut()
    {
        mAuth.signOut();

        Toast.makeText(Main.this, "Successfully Signed Out",
                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getBaseContext(), Login.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v)
    {
        int buttonId = v.getId();
        if (buttonId == R.id.sign_out_button)
        {
            signOut();
        }
    }
}
