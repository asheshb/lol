package com.bitwindow.lol;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragment = (MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMain);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
            mFragment.setJokeData(result);
            mFragment.displayJoke();
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
