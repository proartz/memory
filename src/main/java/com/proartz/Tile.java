package com.proartz;

public class Tile {
    private boolean guessed;
    private boolean selected;
    private Object reference;

    public Tile() {
        this(null);
    }

    public Tile(Object reference) {
        guessed = false;
        selected = false;
        this.reference = reference;
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

    public Object getReference() {
        return reference;
    }

    public void setReference(Object reference) {
        this.reference = reference;
    }
}
