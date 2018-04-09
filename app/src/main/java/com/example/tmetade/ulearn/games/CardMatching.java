package com.example.tmetade.ulearn.games;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.AssetManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tmetade.ulearn.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class CardMatching extends Fragment implements View.OnClickListener
{
    private static final String TAG = "CardMatching";

    private TextView mUserScore;
    private ImageView mCardView;
    JSONObject mJson;

    private ImageView[] images = new ImageView[12];
    private Card[] pronouns = new Card[8];
    private Card[] verbs = new Card[8];
    private Card[] game = new Card[12];

    private int[] flipped = new int[2];
    private int cardsFlipped = 0;

    private int userScore;
    private int cardsMatched;

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

        if(mJson != null)
        {
            buildGame();
        }

        return inflater.inflate(R.layout.fragment_card_matching, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View myActivityView=(RelativeLayout)getActivity().findViewById(R.id.parentContainer);
        mUserScore = myActivityView.findViewById(R.id.text_user_score);

        images[0] = view.findViewById(R.id.img_card0);
        images[1] = view.findViewById(R.id.img_card1);
        images[2] = view.findViewById(R.id.img_card2);
        images[3] = view.findViewById(R.id.img_card3);
        images[4] = view.findViewById(R.id.img_card4);
        images[5] = view.findViewById(R.id.img_card5);
        images[6] = view.findViewById(R.id.img_card6);
        images[7] = view.findViewById(R.id.img_card7);
        images[8] = view.findViewById(R.id.img_card8);
        images[9] = view.findViewById(R.id.img_card9);
        images[10] = view.findViewById(R.id.img_card10);
        images[11] = view.findViewById(R.id.img_card11);

        for (int i = 0; i<images.length; i++)
        {
            images[i].setOnClickListener(this);
            images[i].setTag(R.drawable.card_back);
        }
    }

    private void buildGame()
    {
        try
        {
            JSONArray game = mJson.getJSONArray("avoir");
            JSONArray pronounsArray = game.getJSONObject(0).getJSONArray("pronouns");
           for (int i = 0; i<pronounsArray.length(); i++)
            {
                JSONObject pronoun = pronounsArray.getJSONObject(i);
                Card pronounCard = new Card(pronoun.getString("pronoun"), pronoun.getString("img"),  pronoun.getString("pair"));
                pronouns[i] = pronounCard;
            }
            JSONArray verbsArray = game.getJSONObject(1).getJSONArray("verbs");
            for (int i = 0; i<pronounsArray.length(); i++)
            {
                JSONObject verb = verbsArray.getJSONObject(i);
                Card verbCard = new Card(verb.getString("verb"), verb.getString("img"), verb.getString("pair"));
                verbs[i] = verbCard;
            }

            buildDeck();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void buildDeck()
    {
        Random randomGenerator = new Random();
        int randomInt1 = randomGenerator.nextInt(8);
        int randomInt2 = randomGenerator.nextInt(8);
        while (randomInt1 == randomInt2)
        {
            randomInt2 = randomGenerator.nextInt(8);
        }

        int count = 0;
        for (int i = 0; i<pronouns.length; i++)
        {
            if (i != randomInt1 && i != randomInt2)
            {
                game[count] = pronouns[i];
                game[count+1] = verbs[i];

                count = count + 2;
            }
        }

        game = RandomizeArray(game);
    }

    public static Card[] RandomizeArray(Card[] array){
        Random rgen = new Random();  // Random number generator

        for (int i=0; i<array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            Card temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
    }

    public void flipBack(final int id)
    {
        mCardView = (ImageView) getView().findViewById(id);
        final ObjectAnimator oa1 = ObjectAnimator.ofFloat(mCardView, "scaleX", 1f, 0f);
        final ObjectAnimator oa2 = ObjectAnimator.ofFloat(mCardView, "scaleX", 0f, 1f);
        oa1.setInterpolator(new DecelerateInterpolator());
        oa2.setInterpolator(new AccelerateDecelerateInterpolator());
        oa1.setDuration(100);
        oa2.setDuration(100);

        oa1.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Integer integer = (Integer) mCardView.getTag();

                mCardView.setImageResource(R.drawable.card_back);
                mCardView.setTag(R.drawable.card_back);
                oa2.start();
            }
        });
        oa1.start();

    }

    public void flipBack(final int id1, final int id2)
    {
        mCardView = (ImageView) getView().findViewById(id1);
        final ObjectAnimator oa1 = ObjectAnimator.ofFloat(mCardView, "scaleX", 1f, 0f);
        final ObjectAnimator oa2 = ObjectAnimator.ofFloat(mCardView, "scaleX", 0f, 1f);
        oa1.setInterpolator(new DecelerateInterpolator());
        oa2.setInterpolator(new AccelerateDecelerateInterpolator());
        oa1.setDuration(100);
        oa2.setDuration(100);

        oa1.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Integer integer = (Integer) mCardView.getTag();

                mCardView.setImageResource(R.drawable.card_back);
                mCardView.setTag(R.drawable.card_back);
                oa2.start();
            }
        });
        oa1.start();
        oa2.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                flipBack(id2);
            }
        });
    }

    public void flip(final int id)
    {
        mCardView = (ImageView) getView().findViewById(id);
        final ObjectAnimator oa1 = ObjectAnimator.ofFloat(mCardView, "scaleX", 1f, 0f);
        final ObjectAnimator oa2 = ObjectAnimator.ofFloat(mCardView, "scaleX", 0f, 1f);
        oa1.setInterpolator(new DecelerateInterpolator());
        oa2.setInterpolator(new AccelerateDecelerateInterpolator());
        oa1.setDuration(100);
        oa2.setDuration(100);

        oa1.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Integer integer = (Integer) mCardView.getTag();
               if (integer != R.drawable.card_back)
               {
                   mCardView.setImageResource(R.drawable.card_back);
                   mCardView.setTag(R.drawable.card_back);
               }
               else
               {
                   Context context = mCardView.getContext();
                   int cardId = 0;
                   switch (id)
                   {
                       case R.id.img_card0:
                           flipped[cardsFlipped] = 0;
                           cardId = context.getResources().getIdentifier(game[0].getImgCard(), "drawable", context.getPackageName());
                           mCardView.setImageResource(cardId);
                           mCardView.setTag(cardId);
                           break;
                       case R.id.img_card1:
                           flipped[cardsFlipped] = 1;
                           cardId = context.getResources().getIdentifier(game[1].getImgCard(), "drawable", context.getPackageName());
                           mCardView.setImageResource(cardId);
                           mCardView.setTag(cardId);
                           break;
                       case R.id.img_card2:
                           flipped[cardsFlipped] = 2;
                           cardId = context.getResources().getIdentifier(game[2].getImgCard(), "drawable", context.getPackageName());
                           mCardView.setImageResource(cardId);
                           mCardView.setTag(cardId);
                           break;
                       case R.id.img_card3:
                           flipped[cardsFlipped] = 3;
                           cardId = context.getResources().getIdentifier(game[3].getImgCard(), "drawable", context.getPackageName());
                           mCardView.setImageResource(cardId);
                           mCardView.setTag(cardId);
                           break;
                       case R.id.img_card4:
                           flipped[cardsFlipped] = 4;
                           cardId = context.getResources().getIdentifier(game[4].getImgCard(), "drawable", context.getPackageName());
                           mCardView.setImageResource(cardId);
                           mCardView.setTag(cardId);
                           break;
                       case R.id.img_card5:
                           flipped[cardsFlipped] = 5;
                           cardId = context.getResources().getIdentifier(game[5].getImgCard(), "drawable", context.getPackageName());
                           mCardView.setImageResource(cardId);
                           mCardView.setTag(cardId);
                           break;
                       case R.id.img_card6:
                           flipped[cardsFlipped] = 6;
                           cardId = context.getResources().getIdentifier(game[6].getImgCard(), "drawable", context.getPackageName());
                           mCardView.setImageResource(cardId);
                           mCardView.setTag(cardId);
                           break;
                       case R.id.img_card7:
                           flipped[cardsFlipped] = 7;
                           cardId = context.getResources().getIdentifier(game[7].getImgCard(), "drawable", context.getPackageName());
                           mCardView.setImageResource(cardId);
                           mCardView.setTag(cardId);
                           break;
                       case R.id.img_card8:
                           flipped[cardsFlipped] = 8;
                           cardId = context.getResources().getIdentifier(game[8].getImgCard(), "drawable", context.getPackageName());
                           mCardView.setImageResource(cardId);
                           mCardView.setTag(cardId);
                           break;
                       case R.id.img_card9:
                           flipped[cardsFlipped] = 9;
                           cardId = context.getResources().getIdentifier(game[9].getImgCard(), "drawable", context.getPackageName());
                           mCardView.setImageResource(cardId);
                           mCardView.setTag(cardId);
                           break;
                       case R.id.img_card10:
                           flipped[cardsFlipped] = 10;
                           cardId = context.getResources().getIdentifier(game[10].getImgCard(), "drawable", context.getPackageName());
                           mCardView.setImageResource(cardId);
                           mCardView.setTag(cardId);
                           break;
                       case R.id.img_card11:
                           flipped[cardsFlipped] = 11;
                           cardId = context.getResources().getIdentifier(game[11].getImgCard(), "drawable", context.getPackageName());
                           mCardView.setImageResource(cardId);
                           mCardView.setTag(cardId);
                           break;
                   }
                   cardsFlipped ++;
               }
                oa2.start();
            }
        });

        oa1.start();
        oa2.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                if (cardsFlipped == 2)
                {
                    cardsFlipped = 0;
                    checkMatch();
                    flipped[0] = -1;
                    flipped[1] = -1;
                }

            }
        });
    }

    private void checkMatch()
    {
        if (game[flipped[0]].getPair().equals( game[flipped[1]].getName()))
        {
            userScore += 10;
            cardsMatched ++;
            images[flipped[0]].setVisibility(View.INVISIBLE);
            images[flipped[1]].setVisibility(View.INVISIBLE);

            mUserScore.setText(String.valueOf(userScore));

            if(cardsMatched == 6)
            {
                cardsMatched = 0;
                userScore += 50;
                restartGame();
            }
        }
        else
        {
            flipBack(images[flipped[0]].getId(), images[flipped[1]].getId());
        }

    }

    private void restartGame()
    {
        buildDeck();

        for(int i = 0; i<images.length; i++)
        {
            images[i].setImageResource(R.drawable.card_back);
            images[i].setTag(R.drawable.card_back);
            images[i].setVisibility(View.VISIBLE);
        }
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