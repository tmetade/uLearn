package com.example.tmetade.ulearn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tmetade.ulearn.games.CardMatching;

public class Games extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_games, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(getResources().getText(R.string.games));

        // Buttons
        view.findViewById(R.id.game1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        int buttonId = v.getId();
        if (buttonId == R.id.game1)
        {
            Intent intent = new Intent(getActivity().getBaseContext(),CardMatching.class);
            startActivity(intent);
        }
    }
}