package com.example.tmetade.ulearn;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User
{
    public String username;
    public String firstName;

    public User()
    {
        // Default constructor required for calls to DataSnapshot.getValue(com.example.tmetade.ulearn.User.class)
    }

    public User(String username, String firstName)
    {
        this.username = username;
        this.firstName = firstName;
    }
}
