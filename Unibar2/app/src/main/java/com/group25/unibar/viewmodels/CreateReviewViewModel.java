package com.group25.unibar.viewmodels;

import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.group25.unibar.models.Review;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CreateReviewViewModel extends ViewModel {

    public String url= "https://unibarapi.azurewebsites.net/api/barreviews";

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    // Inspiration til postReview fundet her: https://medium.com/@taman.neupane/basic-example-of-livedata-and-viewmodel-14d5af922d0
    public void postReview(Review review) throws IOException {

        Log.d("CreateReviewViewModel", "Trying to post");

        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();


        String jsonParsedReview = gson.toJson(review);

        Log.d("CreateReviewViewModel", "Parsed the object to json: " + jsonParsedReview);

        RequestBody body = RequestBody.create(JSON, jsonParsedReview);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("CreateReviewViewModel",response.body().string());
            }
        });
    }
}
