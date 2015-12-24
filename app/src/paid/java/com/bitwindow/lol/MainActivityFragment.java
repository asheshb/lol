package com.bitwindow.lol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitwindow.jokedisplay.JokeDisplayActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private static final String LOG_TAG = MainActivityFragment.class.getSimpleName();
    private static final boolean DEBUG = true; // Set this to false to disable logs.
    private static final String JOKE_MSG = "JOKE_MSG";
    private String mJoke;


    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate()");
    }

    public void setJokeData(String jokeData){
        mJoke = jokeData;
    }

    public String getJokeData(){
        return mJoke;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreateView");
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    public void launchJokeActivity(){
        Intent intent = new Intent(getActivity(), JokeDisplayActivity.class);
        intent.putExtra(JOKE_MSG, mJoke);
        startActivity(intent);

    }

    public void displayJoke(){
        launchJokeActivity();
    }
}
