package com.qzzo.android.requests.networkClient;

import com.qzzo.android.executors.AppExecutors;
import com.qzzo.android.models.Movie;
import com.qzzo.android.requests.ServiceGenerator;
import com.qzzo.android.requests.response.MovieListResponse;
import com.qzzo.android.utils.Constants;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
        System.out.println("IN ****");
        if(movieListRunnable != null) movieListRunnable = null;
        movieListRunnable = new MovieListRunnable();
        ScheduledThreadPoolExecutor poolExecutor = AppExecutors.getInstance().getExecutor();
        requestHandler = poolExecutor.submit(movieListRunnable);
    }

    private class MovieListRunnable implements Runnable {

        @Override
        public void run() {
            Call movieListResponseCall = ServiceGenerator.getMovieDbAPI().getMovieList();
            System.out.println("In 2 ***");
            try {
                System.out.println("In 3 ***");
                Response response = movieListResponseCall.execute();
                System.out.println("In 4 ***"+response.body());
                System.out.println("response : "+response);
                if(response.code() == 200){
                    MovieListResponse recipeResponse = (MovieListResponse) response.body();
                    if(recipeResponse != null) {
                        List<Movie> recipeListResponse = recipeResponse.getMovieList();
                        if(recipeListResponse ==null || recipeListResponse.size()==0){
                            movieList.setValue(null);
                        }
                        else{
                            movieList.postValue(recipeListResponse);
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
