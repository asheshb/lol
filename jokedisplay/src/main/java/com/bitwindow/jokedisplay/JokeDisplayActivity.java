package com.bitwindow.jokedisplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {
    private static final String JOKE_MSG = "JOKE_MSG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);
        Intent intent = getIntent();
        String message = intent.getStringExtra(JOKE_MSG);
        TextView txtView = (TextView) findViewById(R.id.tvJoke);
        txtView.setMovementMethod(new ScrollingMovementMethod());
        txtView.setText(message);
    }

}
