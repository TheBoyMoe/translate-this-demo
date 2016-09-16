package com.example.translatethis.common;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefsUtils {

    public SharedPrefsUtils() {
        throw new AssertionError();
    }

    // FIXME ? needed
    public static int getSpeechModeIndex(Context context) {
        SharedPreferences prefs = getPrefs(context);
        return prefs.getInt(Constants.SPEECH_MODE_INDEX, 0);
    }

    // FIXME ? needed
    // save the current speech mode selection
    public static void updateSpeechModeIndex(Context context, int speechModeIndex) {
        SharedPreferences prefs = getPrefs(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(Constants.SPEECH_MODE_INDEX, speechModeIndex).apply();
    }

    public static int getFromLanguage(Context context) {
        SharedPreferences prefs = getPrefs(context);
        return prefs.getInt(Constants.FROM_LANGUAGE_INDEX, 0);
    }

    // save the current language selection
    public static void updateFromLanguage(Context context, int languageIndex) {
        SharedPreferences prefs = getPrefs(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(Constants.FROM_LANGUAGE_INDEX, languageIndex).apply();
    }

    public static int getToLanguage(Context context) {
        SharedPreferences prefs = getPrefs(context);
        return prefs.getInt(Constants.TO_LANGUAGE_INDEX, 0);
    }

    public static void updateToLanguage(Context context, int languageIndex) {
        SharedPreferences prefs = getPrefs(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(Constants.TO_LANGUAGE_INDEX, languageIndex).apply();
    }

    // helper method
    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(Constants.SPEECH_TO_TEXT_PREFERENCES, Context.MODE_PRIVATE);
    }

}

