package com.bitwindow.lol;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity  implements AsyncTaskCompleteListener<String> {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final boolean DEBUG = true; // Set this to false to disable logs.
    private EndpointsAsyncTask mEndpointsAsyncTask;
    private MainActivityFragment mFragment;
    private ProgressBar mProgressBar;
    //For connected testing
    private boolean mConnectedTestingAsync = false;
    public String mResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragment = (MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMain);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void setConnectedTestingAsync(boolean connectedTesting){
        this.mConnectedTestingAsync = connectedTesting;
    }

    public boolean getConnectedTestingAsync(){
        return this.mConnectedTestingAsync;
    }

    @Override
    public void onDestroy() {
        if (DEBUG)
            Log.d(LOG_TAG, "GOING TO DESTROY" );
        if(mEndpointsAsyncTask != null && !mEndpointsAsyncTask.getStatus().equals(AsyncTask.Status.FINISHED)) {
            Log.d(LOG_TAG, "CANCELLED TASK" );
            mEndpointsAsyncTask.cancel(true);
        }
        super.onDestroy();
    }

    @Override
    public void onTaskComplete(String result)
    {
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);
        if(result != null) {
            if(!mConnectedTestingAsync) {
                mFragment.setJokeData(result);
                mFragment.displayJoke();
            } else{
                mResult = result;
            }
            Log.d(LOG_TAG, "SHOWING JOKE");
            //Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();

        }
    }


    @Override
    public void onTaskBefore()
    {
        mProgressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void onAsyncExceptionRaised(Exception e) {
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);
        if(e instanceof SocketTimeoutException || e instanceof UnknownHostException){
            Toast.makeText(this, getString(R.string.exception_no_internet), Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }


    public void fetchJokeData(View view){
        if (mEndpointsAsyncTask == null || mEndpointsAsyncTask.getStatus().equals(AsyncTask.Status.FINISHED)) {
            mEndpointsAsyncTask = new EndpointsAsyncTask(this);
            mEndpointsAsyncTask.execute();
        }
    }
}
