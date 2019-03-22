package com.proartz;

public class Tile {
    private boolean guessed;
    private boolean selected;

    public Tile() {
        this(null);
    }

    public Tile(Object reference) {
        guessed = false;
        selected = false;
    }

    public boolean isGuessed() {
        return guessed;
    }

    public void setGuessed(boolean guessed) {
        this.guessed = guessed;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
