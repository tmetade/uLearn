package com.example.tmetade.ulearn.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User
{
    public String firstName;
    public String lastName;
    public String username;
    public String email;

    public User()
    {
        // Default constructor required for calls to DataSnapshot.getValue(com.example.tmetade.ulearn.models.User.class)
    }

    public User( String firstName, String lastName, String username, String email)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
    }
}