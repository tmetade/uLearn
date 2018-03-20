package com.example.tmetade.ulearn.games;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.example.tmetade.ulearn.R;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class CardMatching extends Fragment implements View.OnClickListener
{
    private static final String TAG = "CardMatching";

    private ImageView mCardView;
    JSONObject mJson;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        try
        {
            mJson = new JSONObject(readJSONFromAsset());
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return inflater.inflate(R.layout.fragment_card_matching, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // buttons
        view.findViewById(R.id.img_card1).setOnClickListener(this);
        view.findViewById(R.id.img_card2).setOnClickListener(this);
        view.findViewById(R.id.img_card3).setOnClickListener(this);
        view.findViewById(R.id.img_card4).setOnClickListener(this);
        view.findViewById(R.id.img_card5).setOnClickListener(this);
        view.findViewById(R.id.img_card6).setOnClickListener(this);
        view.findViewById(R.id.img_card7).setOnClickListener(this);
        view.findViewById(R.id.img_card8).setOnClickListener(this);
        view.findViewById(R.id.img_card9).setOnClickListener(this);
    }

    public void flip(int id)
    {
        mCardView = (ImageView) getView().findViewById(id);
        final ObjectAnimator oa1 = ObjectAnimator.ofFloat(mCardView, "scaleX", 1f, 0f);
        final ObjectAnimator oa2 = ObjectAnimator.ofFloat(mCardView, "scaleX", 0f, 1f);
        oa1.setInterpolator(new DecelerateInterpolator());
        oa2.setInterpolator(new AccelerateDecelerateInterpolator());
        oa1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mCardView.setImageResource(R.drawable.card_front);
                oa2.start();
            }
        });
        oa1.start();
    }

    @Override
    public void onClick(View v)
    {
        flip(v.getId());
    }


    public String readJSONFromAsset()
    {
        String json = null;
        try
        {
            AssetManager assetManager = getActivity().getBaseContext().getAssets();
            InputStream is = assetManager.open("card_game.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex)
        {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}