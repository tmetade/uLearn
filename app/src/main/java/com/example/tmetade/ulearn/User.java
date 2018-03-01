package com.example.tmetade.ulearn;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User
{
    public String firstName;
    public String lastName;
    public String username;

    public User()
    {
        // Default constructor required for calls to DataSnapshot.getValue(com.example.tmetade.ulearn.User.class)
    }

    public User( String firstName, String lastName, String username)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }
}
