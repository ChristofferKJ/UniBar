package com.group25.unibar.Fragments;

import java.util.Arrays;
import java.util.Objects;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.group25.unibar.R;
import com.group25.unibar.activities.MainActivity;
import com.group25.unibar.models.User;
import com.group25.unibar.models.UserLocalStore;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.ContentFrameLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


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
//        if (new UserLocalStore(getContext()).getUserLoggedIn()){
//            Intent myIntent = new Intent(getActivity(), MainActivity.class);
//            startActivityForResult(myIntent, 1);
//
//            getActivity().finish();
//        }
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sign_in, container, false);
        tokenTracker.startTracking();
        email = v.findViewById(R.id.editTextUsername);
        password = v.findViewById(R.id.editTextPassword);
//        sign_in = v.findViewById(R.id.buttonSignIn);
//        sign_up = v.findViewById(R.id.textViewSignUp);
        fb_login = v.findViewById(R.id.buttonFacebookSignIn);

//        sign_in.setOnClickListener(this);
//        sign_up.setOnClickListener(this);
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

                String first_name = object.optString("first_name","");
                String last_name = object.optString("last_name","");
                String email = object.optString("email","");
                String id = object.optString("id","");
                String image_url = "https://graph.facebook.com/"+ id +"/picture?type=normal";

                //save user in local db
                User user = new User(first_name, last_name, email, image_url);
                new NullPointerFixer().execute(user);

                Log.d(TAG, "onCompleted: "+ image_url);
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
//            case R.id.buttonSignIn:
//                Log.d("SignInFragment", "Sign In Pressed");
//
//                 TODO: Make login logic here
//
//                Intent myIntent = new Intent(getActivity(), MainActivity.class);
//
//                startActivityForResult(myIntent, 1);
//                getActivity().finish();
//                break;

//            case R.id.textViewSignUp:
//                Log.d("SignInFragment", "Sign Up Pressed");
//                Navigation.findNavController(view).navigate(R.id.action_signInFragment_to_signUpActivity);
//                break;
            case  R.id.buttonFacebookSignIn:
                LoginManager.getInstance().logIn(this, Arrays.asList("public_profile"));



                break;

        }
    }

    private class NullPointerFixer extends AsyncTask<User, Void, Void>{
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent myIntent = new Intent(getActivity(), MainActivity.class);
            startActivityForResult(myIntent, 1);
            getActivity().finish();
        }

        @Override
        protected Void doInBackground(User... users) {
            User user = users[0];
            Context context = getContext();
            if (context == null) cancel(true);
            UserLocalStore localStore = new UserLocalStore(getContext());
            localStore.setUserLoggedIn(true);
            localStore.StoreUserData(user);

            return null;
        }
    }
}
