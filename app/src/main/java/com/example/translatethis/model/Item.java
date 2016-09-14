package com.example.translatethis.model;


import android.content.ContentValues;

public class Item {

    private int mId = -1;
    private String mOriginalText;
    private String mTranslatedText;
    private String mAudioFile;

    public Item() {}

    public Item(String fromText, String toText, String audioFile) {
        mOriginalText = fromText;
        mTranslatedText = toText;
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

    public String getOriginalText() {
        return mOriginalText;
    }

    public void setOriginalText(String originalText) {
        mOriginalText = originalText;
    }

    public String getTranslatedText() {
        return mTranslatedText;
    }

    public void setTranslatedText(String translatedText) {
        mTranslatedText = translatedText;
    }

    public String getAudioFile() {
        return mAudioFile;
    }

    public void setAudioFile(String audioFile) {
        mAudioFile = audioFile;
    }


}
