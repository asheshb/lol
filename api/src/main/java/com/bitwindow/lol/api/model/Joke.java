package com.bitwindow.lol.api.model;

/** The object model for the data we are sending through endpoints */
public class Joke {

    private String myData;

    public Joke(String data){
        myData = data;
    }
    public String getData() {
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }
}