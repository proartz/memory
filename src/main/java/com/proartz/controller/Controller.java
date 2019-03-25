package com.proartz.controller;

import com.proartz.model.Model;
import com.proartz.model.Tile;
import com.proartz.view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Controller {
    private Model model;
    int numberOfTiles;
    private View view;
    private ArrayList<String> icons;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        numberOfTiles = model.getNumberOfTiles();
        view.setNumberOfTiles(numberOfTiles);
        view.setController(this);
        view.initializeView();
        icons = loadIcons(numberOfTiles / 2);
    }

    public void createAndShowGUI() {
        int numberOfTiles = model.getNumberOfTiles();

        createTiles();
        view.showFrame();
    }

    public void createTiles() {
        for(int i = 0; i < numberOfTiles; i++) {
            model.addTile(i, icons.get(i));
            view.addTile("images/" + icons.get(i));
        }
    }

    public void tilePressed(Object source) {
        int indexOfTile = view.getIndexOfButton(source);
        Tile selectedTile = model.getTile(indexOfTile);
        model.selectTile(indexOfTile);

        if (model.getSelectionCounter() == 0) {

            //if it is a first and only tile pressed
            model.saveSelectedTile(1, selectedTile);
            model.incrementSelectionCounter();

            view.saveSelectedButton(1, source);
            view.disableSelectedButtons();

        } else if ((model.getSelectionCounter() == 1) &&
                   (model.getSelectedTileIndex(1) != indexOfTile)){

            //if it's the second tile pressed and not the same one as first

            model.saveSelectedTile(2, selectedTile);
            model.incrementSelectionCounter();
            view.saveSelectedButton(2, source);
            view.disableSelectedButtons();

            if (model.compareSelectedTiles()) {

                //if the same picture has been uncovered
                model.decrementToGuess();

                if (model.getToGuess() == 0) {
                    System.out.println("Gratuluję!!! Wygrałeś!!!");
                }

                //disable buttons
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

    private ArrayList<String> loadIcons(int numberOfIcons) {
        ArrayList<String> icons = new ArrayList<>();

        String dirName = "images/";

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
}
