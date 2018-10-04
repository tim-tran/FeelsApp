package com.cmput301.ttran1.feelsbook;

import java.util.ArrayList;

public class EmotionsHistory {

    private static ArrayList<Emotion> emotions = new ArrayList<>();

    public EmotionsHistory() {}

    public static ArrayList<Emotion> getEmotions() {
        return emotions;
    }

    public static void addEmotion(Emotion emotion) {
        emotions.add(emotion);
    }

    public static int size() {
        return emotions.size();
    }
}
