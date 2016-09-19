package com.example.translatethis.common;


import com.microsoft.speech.tts.Voice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoicesUtils {

    private static final Map<String, List<Voice>> mVoices = new HashMap<>();

    private VoicesUtils() {
        throw new AssertionError();
    }

    // instantiated when class is loaded into memory
    static {
        addVoice(new Voice("en-gb", "Microsoft Server Speech Text to Speech Voice (en-GB, Susan, Apollo)", Voice.Gender.Female, true));
        addVoice(new Voice("en-gb", "Microsoft Server Speech Text to Speech Voice (en-GB, George, Apollo)", Voice.Gender.Male, true));
        addVoice(new Voice("fr-fr", "Microsoft Server Speech Text to Speech Voice (fr-FR, Julie, Apollo)", Voice.Gender.Female, true));
        addVoice(new Voice("fr-fr", "Microsoft Server Speech Text to Speech Voice (fr-FR, Paul, Apollo)", Voice.Gender.Male, true));
        addVoice(new Voice("de-de", "Microsoft Server Speech Text to Speech Voice (de-DE, Hedda)", Voice.Gender.Female, true));
        addVoice(new Voice("de-de", "Microsoft Server Speech Text to Speech Voice (de-DE, Stefan, Apollo)", Voice.Gender.Male, true));
        addVoice(new Voice("it-it", "Microsoft Server Speech Text to Speech Voice (it-IT, Cosimo, Apollo)", Voice.Gender.Male, true));
        addVoice(new Voice("es-es", "Microsoft Server Speech Text to Speech Voice (es-ES, Laura, Apollo)", Voice.Gender.Female, true));
        addVoice(new Voice("es-es", "Microsoft Server Speech Text to Speech Voice (es-ES, Pablo, Apollo)", Voice.Gender.Male, true));
    }

    private static void addVoice(Voice voice) {
        // retrieve the list of voices for that locale/lang
        List<Voice> list = mVoices.get(voice.lang);
        if (list == null) {
            // instantiate an empty array list and add
            // it to the map for that particular locale/lang (using lang as key)
            list = new ArrayList<>();
            mVoices.put(voice.lang, list);
        }
        // add the voice to the arraylist
        list.add(voice);
    }

    public static Voice getVoice(String lang, int index) {
        List<Voice> list = mVoices.get(lang);
        if (list != null && list.size() > index) {
            // retrieve the particular voice at that index
            return list.get(index);
        }
        // else
        return null;
    }

    public static int voiceCount(String lang) {
        List<Voice> list = mVoices.get(lang);
        if (list!= null) {
            return list.size();
        }
        // else
        return 0;
    }


}
