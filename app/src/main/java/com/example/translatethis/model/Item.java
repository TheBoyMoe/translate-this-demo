package com.example.translatethis.model;


public class Item {

    private String mFromText;
    private String mToString;
    private String mAudioFile;

    public Item() {}

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
