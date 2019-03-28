package com.proartz.model;

import java.io.Serializable;

public class Tile implements Serializable {
    private boolean selected;
    private int index;
    private String value;

    public Tile(int index, String value) {
        selected = false;
        this.value = value;
        this.index = index;
    }

    public void restartTile() {
        selected = false;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public boolean isSelected() {
        return selected;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "selected=" + selected +
                ", index=" + index +
                ", value='" + value + '\'' +
                '}';
    }
}
