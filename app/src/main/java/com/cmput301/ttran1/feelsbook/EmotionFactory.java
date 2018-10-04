package com.cmput301.ttran1.feelsbook;

public class EmotionFactory {

    public EmotionFactory() {
    }

    public static Emotion makeEmotion(String emotion) throws Exception {
        if (emotion.equalsIgnoreCase("love")) {
            return new Love();
        }
        else if (emotion.equalsIgnoreCase("joy")) {
            return new Joy();
        }
        else if (emotion.equalsIgnoreCase("surprise")) {
            return new Surprise();
        }
        else if (emotion.equalsIgnoreCase("anger")) {
            return new Anger();
        }
        else if (emotion.equalsIgnoreCase("sadness")) {
            return new Sadness();
        }
        else if (emotion.equalsIgnoreCase("fear")) {
            return new Fear();
        }
        else {
            throw new NotAnEmotionException(emotion + "is not a valid emotion.");
        }
    }
}
