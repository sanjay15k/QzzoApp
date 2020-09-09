package com.qzzo.android.requests.networkClient;

import com.qzzo.android.executors.AppExecutors;
import com.qzzo.android.models.Movie;
import com.qzzo.android.requests.ServiceGenerator;
import com.qzzo.android.requests.response.MovieListResponse;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Response;

public class MovieListClient {

    private static MovieListClient mInstance;
    private final MutableLiveData<List<Movie>> movieList;
    private MovieListRunnable movieListRunnable;
    private Future requestHandler;

    public static MovieListClient getInstance(){
        if(mInstance == null) mInstance = new MovieListClient();
        else mInstance.movieList.setValue(null);
        return mInstance;
    }

    private MovieListClient(){
        movieList = new MutableLiveData<>();
    }

    public LiveData<List<Movie>> getMovies(){
        return movieList;
    }

    public void cancelRequest(boolean isCancel){
        if(isCancel){
            if(requestHandler != null) requestHandler.cancel(true);
        }
    }

    public void getMovieList(){
        movieList.postValue(null);
        if(movieListRunnable != null) movieListRunnable = null;
        movieListRunnable = new MovieListRunnable();
        ScheduledThreadPoolExecutor poolExecutor = AppExecutors.getInstance().getExecutor();
        requestHandler = poolExecutor.submit(movieListRunnable);
    }

    private class MovieListRunnable implements Runnable {
        @Override
        public void run() {
            Call movieListResponseCall = ServiceGenerator.getMovieDbAPI().getMovieList();
            try {
                Response response = movieListResponseCall.execute();
                if(response.code() == 200){
                    MovieListResponse trailerListResponse = (MovieListResponse) response.body();
                    if(trailerListResponse != null) {
                        List<Movie> mTrailerListResponse = trailerListResponse.getMovieList();
                        if(mTrailerListResponse ==null || mTrailerListResponse.size()==0){
                            movieList.setValue(null);
                        }
                        else{
                            movieList.postValue(mTrailerListResponse);
                        }
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
