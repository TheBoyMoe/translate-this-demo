package com.example.translatethis.common;


import com.example.translatethis.BuildConfig;

public class Constants {

    public static final String LOG_TAG = "LOG";

    // MS Speech-To-Text Services // TODO
    public static final String PRIMARY_SUBSCRIPTION_KEY = BuildConfig.MS_COGNITIVE_SERVIES_PRIMARY_SUBSCRIPTION_KEY;
    public static final String SECONDARY_SUBSCRIPTION_KEY = BuildConfig.MS_COGNITIVE_SERVIES_SECONDARY_SUBSCRIPTION_KEY;

    // MS Language Translation Services //TODO
    public static final String CLIENT_ID_VALUE = BuildConfig.MS_COGNITIVE_SERVIES_CLIENT_ID_VALUE;
    public static final String CLIENT_SECRET_VALUE = BuildConfig.MS_COGNITIVE_SERVIES_CLIENT_SECRET_VALUE;

    public static final String SPEECH_TO_TEXT_PREFERENCES = "speech_to_text_preferences";
    public static final String SPEECH_MODE_INDEX = "speech_mode_index";
    public static final String BASE_LANGUAGE_INDEX = "base_language_index";
    public static final String CONVERT_LANGUAGE_INDEX = "convert_language_index";

    // language codes for language spinner
    public static final String[] LANGUAGE_CODES = {
            "en-gb", "fr-fr", "de-de", "it-it", "es-es"
    };

    // languages available for translation - order must match string array order
//    public static final Language[] LANGUAGES = {
//            Language.ENGLISH,
//            Language.FRENCH,
//            Language.GERMAN,
//            Language.ITALIAN,
//            Language.SPANISH
//    };

}
