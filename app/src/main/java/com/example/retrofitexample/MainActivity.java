package com.example.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson)) // converts the json to Object using GSon
                .build();

        Api api = retrofit.create(Api.class);
        Call call = api.getHeroes();
        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                List<Hero> heroes = response.body();
                for (Hero h : heroes) {
                    Log.d("Name :::", h.getName());
                    Log.d("ImageUrl :::", h.getImageurl());
                    Log.d("Bio :::", h.getBio());
                    Log.d("Firstappearance :::", h.getFirstappearance());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("Failure", t.getMessage());
            }
        });
    }
}
