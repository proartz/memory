package com.proartz.model;

public class Tile {
    private boolean guessed;
    private boolean selected;
    private int index;
    private String value;

    public Tile(int index, String value) {
        guessed = false;
        selected = false;
        this.value = value;
        this.index = index;
    }

    public void setGuessed(boolean guessed) {
        this.guessed = guessed;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }
}
