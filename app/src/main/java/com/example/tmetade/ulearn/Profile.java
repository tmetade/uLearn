package com.example.tmetade.ulearn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tmetade.ulearn.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends Fragment
{
    private static final String TAG = "Profile";

    private DatabaseReference mDatabase;
    private String uid;

    private TextView mTextUsername;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        mTextUsername = view.findViewById(R.id.text_username);



        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(getResources().getText(R.string.profile));
        populateMenu();
    }

    private void populateMenu()
    {

        mDatabase.child("users").child(uid).addListenerForSingleValueEvent(
                new ValueEventListener()
                {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        // Get user value
                        User user = dataSnapshot.getValue(User.class);
                        Log.e(TAG, "WTF2");
                        // [START_EXCLUDE]
                        if (user == null)
                        {
                            // User is null, error out
                            Log.e(TAG, "User " + uid + " is unexpectedly null");
                        } else
                        {
                            mTextUsername.setText(user.username);

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError)
                    {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                    }
                });
    }
}