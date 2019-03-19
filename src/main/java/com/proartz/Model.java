package com.proartz;

public class Model {
    private int selectionCounter;
    private int numberOfButtons;

    public Model() {
        selectionCounter = 0;
        numberOfButtons = 50;
    }

    public void incrementCounter() {
        selectionCounter++;
    }

    public void resetCounter() {
        selectionCounter = 0;
    }

    public int getSelectionCounter() {
        return selectionCounter;
    }

    public void setSelectionCounter(int selectionCounter) {
        this.selectionCounter = selectionCounter;
    }

    public int getNumberOfButtons() {
        return numberOfButtons;
    }

    public void setNumberOfButtons(int numberOfButtons) {
        this.numberOfButtons = numberOfButtons;
    }
}
