package com.qzzo.android.requests.networkClient;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.qzzo.android.executors.AppExecutors;
import com.qzzo.android.models.Movie;
import com.qzzo.android.models.Trailer;
import com.qzzo.android.requests.ServiceGenerator;
import com.qzzo.android.requests.response.MovieListResponse;
import com.qzzo.android.requests.response.TrailerListResponse;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import retrofit2.Call;
import retrofit2.Response;

public class MovieDetailClient {

    private static MovieDetailClient mInstance;
    private final MutableLiveData<List<Trailer>> trailerList;
    private TrailerListRunnable trailerListRunnable;
    private Future requestHandler;

    public static MovieDetailClient getInstance(){
        if(mInstance == null) mInstance = new MovieDetailClient();
        else mInstance.trailerList.setValue(null);
        return mInstance;
    }

    private MovieDetailClient(){
        trailerList = new MutableLiveData<>();
    }

    public LiveData<List<Trailer>> getTrailers(){
        return trailerList;
    }

    public void cancelRequest(boolean isCancel){
        if(isCancel){
            if(requestHandler != null) requestHandler.cancel(true);
        }
    }

    public void getTrailerList(String movieID){
        trailerList.postValue(null);
        if(trailerListRunnable != null) trailerListRunnable = null;
        trailerListRunnable = new TrailerListRunnable(movieID);
        ScheduledThreadPoolExecutor poolExecutor = AppExecutors.getInstance().getExecutor();
        requestHandler = poolExecutor.submit(trailerListRunnable);
    }

    private class TrailerListRunnable implements Runnable {

        private final String movieID;

        public TrailerListRunnable(String movieID){
            this.movieID = movieID;
        }

        @Override
        public void run() {
            Call trailerListResponseCall = ServiceGenerator.getMovieDbAPI().getTrailerList(movieID);
            try {
                Response response = trailerListResponseCall.execute();
                if(response.code() == 200){
                    TrailerListResponse trailerListResponse = (TrailerListResponse) response.body();
                    if(trailerListResponse != null) {
                        List<Trailer> mTrailerListResponse = trailerListResponse.getTrailerList();
                        if(mTrailerListResponse ==null || mTrailerListResponse.size()==0){
                            trailerList.setValue(null);
                        }
                        else{
                            trailerList.postValue(mTrailerListResponse);
                        }
                        System.out.println(mTrailerListResponse.toString());
                    }
                }
                System.out.println("Response : "+response);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Request Completed");
        }
    }

}
