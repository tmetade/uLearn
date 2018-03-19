package com.example.tmetade.ulearn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        view.findViewById(R.id.img_verbs).setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        int buttonId = v.getId();
        Intent intent;
        Bundle bundle = new Bundle();
        if (buttonId == R.id.img_verbs)
        {
            bundle.putString("game","cardMatching");
            intent = new Intent(getActivity().getBaseContext(),GameActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if (buttonId == R.id.img_directions)
        {
            bundle.putString("game","cardMatching");
            intent = new Intent(getActivity().getBaseContext(),GameActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if (buttonId == R.id.img_sports)
        {
            bundle.putString("game","cardMatching");
            intent = new Intent(getActivity().getBaseContext(),GameActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if (buttonId == R.id.img_order_up)
        {
            bundle.putString("game","cardMatching");
            intent = new Intent(getActivity().getBaseContext(),GameActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}