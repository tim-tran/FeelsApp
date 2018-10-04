package com.cmput301.ttran1.feelsbook;

import java.util.Date;

public abstract class Emotion {

    protected Date timestamp;
    protected String comment;

    public Emotion() {
        this.timestamp = new Date();
    }

    public Emotion(String cmt) {
        this.timestamp = new Date();
        this.comment = cmt;
    }

    public void setComment(String cmt) {
        this.comment = cmt;
    }

    public String getComment() {
        return this.comment;
    }

    public abstract String getEmotion();

    public Date getTimestamp() {
        return this.timestamp;
    }

    public abstract String toString();
}
