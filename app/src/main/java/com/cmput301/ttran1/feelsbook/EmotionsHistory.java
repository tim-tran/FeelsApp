package com.cmput301.ttran1.feelsbook;

import java.util.ArrayList;

public class EmotionsHistory {

    private static ArrayList<Emotion> emotions;

    public EmotionsHistory() {
        emotions = new ArrayList<>();
    }

    public ArrayList<Emotion> getEmotions() {
        return emotions;
    }

    public void addEmotion(Emotion emotion) {
        emotions.add(emotion);
    }
}
