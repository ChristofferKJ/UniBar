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
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.group25.unibar.R;
import com.group25.unibar.activities.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class SignInFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "SignInFragment";

    CallbackManager callbackManager;
    EditText email;
    EditText password;
    Button sign_in;
    Button fb_login;
    TextView sign_up;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sign_in, container, false);
        tokenTracker.startTracking();
        email = v.findViewById(R.id.editTextUsername);
        password = v.findViewById(R.id.editTextPassword);
        sign_in = v.findViewById(R.id.buttonSignIn);
        sign_up = v.findViewById(R.id.textViewSignUp);
        fb_login = v.findViewById(R.id.buttonFacebookSignIn);

        sign_in.setOnClickListener(this);
        sign_up.setOnClickListener(this);
        fb_login.setOnClickListener(this);

        //Facebook login callback
        callbackManager = CallbackManager.Factory.create();


//            LoginManager.getInstance().registerCallback(callbackManager,
//                new FacebookCallback<LoginResult>() {
//                    @Override
//                    public void onSuccess(LoginResult loginResult) {
//                        // App code
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        // App code
//                    }
//
//                    @Override
//                    public void onError(FacebookException exception) {
//                        // App code
//                    }
//                });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken == null) {
                //User is logged out
                Log.d(TAG, "onCurrentAccessTokenChanged: null");
            } else {
                loadUserProfile(currentAccessToken);
                Log.d(TAG, "onCurrentAccessTokenChanged: "+ currentAccessToken.toString());
            }
        }
    };
    private void loadUserProfile(AccessToken CurrentToken) {
        GraphRequest request = GraphRequest.newMeRequest(CurrentToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String first_name = object.getString("first_name");
                    String last_name = object.getString("last_name");
                    String email = object.getString("email");
                    String id = object.getString("id");
                    String image_url = "https://graph.facebook.com/"+ id +"/picture?type=normal";
                    Log.d(TAG, "onCompleted: "+ image_url);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSignIn:
                Log.d("SignInFragment", "Sign In Pressed");

                // TODO: Make login logic here

                Intent myIntent = new Intent(getActivity(), MainActivity.class);

                startActivityForResult(myIntent, 1);

                break;

            case R.id.textViewSignUp:
                Log.d("SignInFragment", "Sign Up Pressed");
                Navigation.findNavController(view).navigate(R.id.signUpFragment);
                break;
            case  R.id.buttonFacebookSignIn:
                LoginManager.getInstance().logIn(this, Arrays.asList("public_profile"));
                break;

        }
    }
}
