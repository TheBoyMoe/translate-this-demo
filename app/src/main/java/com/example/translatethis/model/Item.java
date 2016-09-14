package com.example.translatethis.model;


import android.content.ContentValues;

public class Item {

    private int mId = -1;
    private String mFromText;
    private String mToString;
    private String mAudioFile;

    public Item() {}

    public Item(String fromText, String toString, String audioFile) {
        mFromText = fromText;
        mToString = toString;
        mAudioFile = audioFile;
    }

    public ContentValues getValues() {
        return null; // TODO
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getFromText() {
        return mFromText;
    }

    public void setFromText(String fromText) {
        mFromText = fromText;
    }

    public String getToString() {
        return mToString;
    }

    public void setToString(String toString) {
        mToString = toString;
    }

    public String getAudioFile() {
        return mAudioFile;
    }

    public void setAudioFile(String audioFile) {
        mAudioFile = audioFile;
    }


}
