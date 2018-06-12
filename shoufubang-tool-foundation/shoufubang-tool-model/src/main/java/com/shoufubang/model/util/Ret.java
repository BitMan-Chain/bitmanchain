package com.shoufubang.model.util;

public class Ret{
    private String prob;
    private String word;
    private Rect rect;

    public String getProb() {
        return prob;
    }

    public void setProb(String prob) {
        this.prob = prob;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    @Override
    public String toString() {
        return word + ",";
    }
}