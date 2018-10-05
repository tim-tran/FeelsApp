package com.cmput301.ttran1.feelsbook;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;

public class EmotionsHistory {

    private static int loveCount = 0;
    private static int joyCount = 0;
    private static int surpriseCount = 0;
    private static int angerCount = 0;
    private static int sadnessCount = 0;
    private static int fearCount = 0;

    private static final String FILENAME = "SavedEmotions.sav";

    private static ArrayList<Emotion> emotions = new ArrayList<>();

    public EmotionsHistory() {}

    public static ArrayList<Emotion> getEmotions() {
        return emotions;
    }

    public static Emotion getEmotionByIndex(int ind) {
        return emotions.get(ind);
    }

    public static void setEmotionByIndex(int ind, Emotion emotion) {
        emotions.set(ind, emotion);
    }

    public static void addEmotion(Emotion emotion) {
        emotions.add(emotion);
    }

    public static int size() {
        return emotions.size();
    }

    // TODO: improve this so you don't have to count every time you add a new emotion
    public static void count() {
        loveCount = 0;
        joyCount = 0;
        surpriseCount = 0;
        angerCount = 0;
        sadnessCount = 0;
        fearCount = 0;
        for(Emotion emotion : emotions){
            if(emotion instanceof Love){
                loveCount++;
            }else if(emotion instanceof Joy){
                joyCount++;
            }else if(emotion instanceof Surprise) {
                surpriseCount++;
            }else if(emotion instanceof Anger) {
                angerCount++;
            }else if(emotion instanceof Sadness) {
                sadnessCount++;
            }else if(emotion instanceof Fear) {
                fearCount++;
            }
        }
    }

    public static int getLoveCount() {
        return loveCount;
    }

    public static int getJoyCount() {
        return joyCount;
    }

    public static int getSurpriseCount() {
        return surpriseCount;
    }

    public static int getAngerCount() {
        return angerCount;
    }

    public static int getSadnessCount() {
        return sadnessCount;
    }

    public static int getFearCount() {
        return fearCount;
    }

    public static void getSavedEmotions(Context context) throws IOException, ClassNotFoundException {
        emotions = (ArrayList<Emotion>) InternalStorage.readObject(context, FILENAME);
    }
}
