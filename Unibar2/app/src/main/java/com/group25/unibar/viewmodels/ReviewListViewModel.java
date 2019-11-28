package com.group25.unibar.viewmodels;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.group25.unibar.models.Review;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ReviewListViewModel extends ViewModel {
    // Url to our own api

    public String url= "https://unibarapi.azurewebsites.net/api/barreviews";

    ArrayList<Review> reviewList = new ArrayList<>();

    public ArrayList<Review> getAllReviews() throws ExecutionException, InterruptedException {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            reviewList = okHttpHandler.execute().get();

        return reviewList;
    }


    // Use of okHttp found in this tutorial: https://www.journaldev.com/13629/okhttp-android-example-tutorial
    public class OkHttpHandler extends AsyncTask<String, Void, ArrayList<Review>> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected ArrayList<Review> doInBackground(String...params) {
            Log.d("ReviewListViewModel", "Trying to fetch reviews from the db!");
            Request.Builder builder = new Request.Builder();
            Gson gson = new Gson();

            builder.url(url);
            Request request = builder.build();

            try {
                Response response = client.newCall(request).execute();
                Type eventType = new TypeToken<ArrayList<Review>>() {}.getType();

                ArrayList<Review> reviews = gson.fromJson(response.body().string(), eventType);

                Log.d("ReviewListViewModel", "Returning the reviews:" + reviews.toString());

                return reviews;
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

    }


}

