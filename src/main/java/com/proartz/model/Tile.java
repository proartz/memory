package com.proartz.model;

public class Tile {
    private boolean guessed;
    private boolean selected;
    private String value;

    public Tile(String value) {
        guessed = false;
        selected = false;
        this.value = value;
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
}
