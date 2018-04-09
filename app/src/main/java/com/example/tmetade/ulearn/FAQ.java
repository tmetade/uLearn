package com.example.tmetade.ulearn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FAQ extends Fragment implements View.OnClickListener
{

    TextView a1;
    TextView a2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_faq, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(getResources().getText(R.string.faq));

        view.findViewById(R.id.q1).setOnClickListener(this);
        view.findViewById(R.id.q2).setOnClickListener(this);

        a1 = view.findViewById(R.id.a1);
        a2 = view.findViewById(R.id.a2);
    }

    @Override
    public void onClick(View v)
    {
        int buttonId = v.getId();
        if (buttonId == R.id.q1)
        {
            a1.setVisibility(View.VISIBLE);
        }
        else if (buttonId == R.id.q2)
        {
            a2.setVisibility(View.VISIBLE);
        }
    }
}