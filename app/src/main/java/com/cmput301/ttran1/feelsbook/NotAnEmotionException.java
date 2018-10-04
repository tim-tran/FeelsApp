package com.cmput301.ttran1.feelsbook;

class NotAnEmotionException extends Exception {

    String msg;

    public NotAnEmotionException(String str) {
        msg = str;
    }

    @Override
    public String toString() {
        return this.msg;
    }
}
