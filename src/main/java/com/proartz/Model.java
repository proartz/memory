package com.proartz;

public class Model {
    private int selectionCounter;

    public Model() {
        this.selectionCounter = 0;
    }

    public void incrementCounter() {
        this.selectionCounter++;
    }

    public void resetCounter() {
        this.selectionCounter = 0;
    }

    public int getSelectionCounter() {
        return selectionCounter;
    }

    public void setSelectionCounter(int selectionCounter) {
        this.selectionCounter = selectionCounter;
    }
}
