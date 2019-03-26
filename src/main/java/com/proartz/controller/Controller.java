package com.proartz.controller;

import com.proartz.model.Model;
import com.proartz.model.Tile;
import com.proartz.view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Controller {
    private static final String IMAGES_DIR = "images/";
    private static final String FINAL_ICON = "006-outer-space-alien.png";
    private static final String COVER_ICON = "037-ufo-flying.png";
    private Model model;
    int numberOfTiles;
    private View view;
    private ArrayList<String> icons;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void createAndShowGUI() {
        numberOfTiles = model.getNumberOfTiles();

        view.setNumberOfTiles(numberOfTiles);
        view.setFrameTitle("Memory");
        view.setController(this);
        view.initializeView();

        icons = loadIcons(numberOfTiles / 2);

        createTiles();

        view.showFrame();
    }

    public void createTiles() {
        for(int i = 0; i < numberOfTiles; i++) {
            model.addTile(i, icons.get(i));
            view.addTile(IMAGES_DIR + icons.get(i),
                    IMAGES_DIR + COVER_ICON);
        }
    }

    public void tilePressed(Object source) {
        int indexOfTile = view.getIndexOfButton(source);
        Tile selectedTile = model.getTile(indexOfTile);
        model.selectTile(indexOfTile);
        model.incrementClickCounter();

        if (model.getSelectionCounter() == 0) {

            //if it is a first and only tile pressed
            model.saveSelectedTile(1, selectedTile);
            model.incrementSelectionCounter();

            view.saveSelectedButton(1, source);
            view.disableSelectedButtons();

        } else if (model.getSelectionCounter() == 1) {
            //if it's the second tile pressed and not the same one as first

            model.saveSelectedTile(2, selectedTile);
            model.incrementSelectionCounter();
            view.saveSelectedButton(2, source);
            view.disableSelectedButtons();

            if (model.compareSelectedTiles()) {

                //if the same picture has been uncovered
                model.setSelectedTilesToGuessed();
                model.decrementToGuess();

                if (model.getToGuess() == 0) {
                    showFinalDialog();
                }

                model.setSelectedTilesToGuessed();
                model.resetSelectedTiles();

                model.resetSelectionCounter();

                view.resetSelectedButtons();
            }
        } else if (model.getSelectionCounter() == 2) {
            //not guessed
            view.enableSelectedTiles();
            model.deselectSelectedTiles();
            model.resetSelectedTiles();

            view.deselectSelectedButtons();
            view.resetSelectedButtons();

            model.setSelectionCounter(1);
            view.saveSelectedButton(1, source);
            view.disableSelectedButtons();
            model.saveSelectedTile(1, selectedTile);
        }
    }

    private void showFinalDialog() {
        String message = "Yeah! Ukończyłeś grę w " +
                model.getClickCounter() +
                " kliknięć.";
        String title = "Koniec Gry";
        String image = IMAGES_DIR + FINAL_ICON;
        
        view.showMessage(message, title, image);
    }

    private ArrayList<String> loadIcons(int numberOfIcons) {
        ArrayList<String> icons = new ArrayList<>();

        String dirName = IMAGES_DIR;

        File fileName = new File(dirName);

        if(!fileName.isDirectory()) {
            System.err.format("Error: %s is not a directory%n", dirName);
            System.exit(-1);
        }

        String[] fileList = fileName.list();

        //convert an array to an ArrayList
        ArrayList<String> temp = new ArrayList<>(Arrays.asList(fileList));
        Collections.sort(temp);

        //remove the last/cover icon
        temp.remove(temp.size() - 1);

        Collections.shuffle(temp);

        //add "numberOfIcons" needed to an icons list
        icons.addAll(temp.subList(0, numberOfIcons));

        //duplicate all elements
        icons.addAll((ArrayList<String>)icons.clone());
        Collections.shuffle(icons);

        return icons;
    }

    public void endGame() {
        System.exit(1);
    }

    public void restartGame() {
        model.restartModel();
        view.restartView();
    }

    
}
