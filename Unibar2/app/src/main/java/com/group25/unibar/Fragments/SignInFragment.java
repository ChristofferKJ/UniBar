package com.group25.unibar.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.group25.unibar.R;
import com.group25.unibar.activities.MainActivity;

public class SignInFragment extends Fragment implements View.OnClickListener {


    EditText email;
    EditText password;
    Button sign_in;
    TextView sign_up;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_sign_in, container, false);

        email = v.findViewById(R.id.editTextUsername);
        password = v.findViewById(R.id.editTextPassword);
        sign_in = v.findViewById(R.id.buttonSignIn);
        sign_up = v.findViewById(R.id.textViewSignUp);


        sign_in.setOnClickListener(this);
        sign_up.setOnClickListener(this);



        return v;
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.buttonSignIn:
                Log.d("SignInFragment", "Sign In Pressed");

                // TODO: Make login logic here

                Intent myIntent = new Intent(getActivity(), MainActivity.class);

                startActivityForResult(myIntent, 1);

                break;

            case R.id.textViewSignUp:
                Log.d("SignInFragment", "Sign Up Pressed");
                //NavDirections action =
                //       SpecifyAmountFragmentDirections.actionSignInActivityToSignUpActivity();
                //Fragment fragmentById = getSupportFragmentManager().findFragmentById(R.id.login_nav_host);
                //SignInFragment.findNavController(fragmentById).navigate(action);
                Navigation.findNavController(view).navigate(R.id.signUpFragment);
                //Navigation.findNavController(view).navigate(action);

                break;

        }
    }
}
