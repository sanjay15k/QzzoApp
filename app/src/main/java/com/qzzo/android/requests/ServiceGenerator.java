package com.qzzo.android.requests;

import com.qzzo.android.utils.Constants;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static OkHttpClient getClient(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            HttpUrl originalHttpUrl = original.url();

            HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", Constants.API_KEY)
                    .addQueryParameter("language", Constants.LANG)
                    .build();

            // Request customization: add request headers
            Request.Builder requestBuilder = original.newBuilder().url(url);

            Request request = requestBuilder.build();
            return chain.proceed(request);
        });
        return httpClient.build();
    }

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.API_BASE_URL)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static MovieDbAPI movieDbAPI = retrofit.create(MovieDbAPI.class);

    public static MovieDbAPI getMovieDbAPI(){
        return movieDbAPI;
    }
}

