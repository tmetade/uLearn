package com.example.tmetade.ulearn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity implements View.OnClickListener
{

    private static final String TAG = "Login";

    private EditText mFirstNameField;
    private EditText mLastNameField;
    private EditText mUsernameField;
    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mConfirmPasswordField;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFirstNameField = (EditText) findViewById(R.id.field_firstName);
        mLastNameField = (EditText) findViewById(R.id.field_lastName);
        mUsernameField = (EditText) findViewById(R.id.field_username);
        mEmailField = (EditText) findViewById(R.id.field_email);
        mPasswordField = (EditText) findViewById(R.id.field_password);
        mConfirmPasswordField = (EditText) findViewById(R.id.field_confirmPassword);

        findViewById(R.id.create_account_button).setOnClickListener(this);
        findViewById(R.id.back_button).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void writeNewUser(String userId, String firstName, String lastName, String username)
    {
        User user = new User(firstName, lastName, username);

        mDatabase.child("users").child(userId).setValue(user);
    }

    private boolean validateForm()
    {
        boolean valid = true;

        String firstName = mFirstNameField.getText().toString();
        if (TextUtils.isEmpty(firstName))
        {
            mFirstNameField.setError("Required.");
            valid = false;
        }
        else
        {
            mFirstNameField.setError(null);
        }

        String lastName = mLastNameField.getText().toString();
        if (TextUtils.isEmpty(lastName))
        {
            mLastNameField.setError("Required.");
            valid = false;
        }
        else
        {
            mFirstNameField.setError(null);
        }

        String username = mUsernameField.getText().toString();
        if (TextUtils.isEmpty(username))
        {
            mUsernameField.setError("Required.");
            valid = false;
        }
        else
        {
            mUsernameField.setError(null);
        }

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email))
        {
            mEmailField.setError("Required.");
            valid = false;
        }
        else
        {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password))
        {
            mPasswordField.setError("Required.");
            valid = false;
        }
        else
        {
            mPasswordField.setError(null);
        }

        if (!mPasswordField.getText().toString().equals(mConfirmPasswordField.getText().toString()))
        {
            Toast.makeText(SignUp.this, "Passwords do not match",
                    Toast.LENGTH_SHORT).show();
            valid = false;
        }
        return valid;
    }

    private void createAccount(String email, String password)
    {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm())
        {
            return;
        }

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");

                            sendEmailVerification();

                            writeNewUser(mAuth.getUid(), mFirstNameField.getText().toString(), mLastNameField.getText().toString(), mUsernameField.getText().toString());

                            Intent intent = new Intent(getBaseContext(),Main.class);
                            startActivity(intent);
                        }
                        else
                        {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
                        //hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }

    private void sendEmailVerification()
    {
        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        // [START_EXCLUDE]

                        if (task.isSuccessful())
                        {
                            Toast.makeText(SignUp.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(SignUp.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }

    @Override
    public void onClick(View v)
    {
        int buttonId = v.getId();
        if (buttonId == R.id.create_account_button)
        {
            createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
        }
        else if (buttonId == R.id.back_button)
        {
            Intent intent = new Intent(getBaseContext(), Login.class);
            startActivity(intent);
        }
    }
}