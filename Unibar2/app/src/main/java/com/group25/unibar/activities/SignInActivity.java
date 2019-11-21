package com.group25.unibar.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.group25.unibar.R;

public class SignInActivity extends Activity implements View.OnClickListener {

    EditText email;
    EditText password;
    Button sign_in;
    TextView sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        sign_in = findViewById(R.id.buttonSignIn);
        sign_up = findViewById(R.id.textViewSignUp);


        sign_in.setOnClickListener(this);
        sign_up.setOnClickListener(this);
    }


    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.buttonSignIn:
                Log.d("SignInActivity", "Sign In Pressed");
                break;

            case R.id.textViewSignUp:
                Log.d("SignInActivity", "Sign Up Pressed");
                break;

        }
    }
}
