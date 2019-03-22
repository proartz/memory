package com.proartz;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Controller {
    private Model model;
    int numberOfTiles;
    private View view;
    private ArrayList<Integer> tiles;
    ArrayList<String> icons;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        numberOfTiles = model.getNumberOfTiles();
        view.setNumberOfTiles(numberOfTiles);
        view.setController(this);
        view.initializeView();
        tiles = new ArrayList<>();
        icons = loadIcons(numberOfTiles / 2);
    }

    public void createAndShowGUI() {
        int numberOfTiles = model.getNumberOfTiles();

        createTiles();
        view.showFrame();
    }

    public void createTiles() {
        for(int i = 0; i < numberOfTiles; i++) {
            model.addTile(icons.get(i));
            view.addTile(icons.get(i));
        }
    }

    public void tilePressed(Object source) {
        int indexOfTile = view.getIndexOfButton(source);
        Tile selectedTile = model.getTile(indexOfTile);
        model.selectTile(indexOfTile);

        if (model.getSelectionCounter() == 0) {
            model.saveSelectedTile(1, selectedTile);
            model.incrementSelectionCounter();

            view.saveSelectedButton(1, source);
        } else if (model.getSelectionCounter() == 1) {
            model.saveSelectedTile(2, selectedTile);
            model.incrementSelectionCounter();
            view.saveSelectedButton(2, source);

            if (model.compareSelectedTiles()) {
                model.decrementToGuess();

                if (model.getToGuess() == 0) {
                    System.out.println("Gratuluję!!! Wygrałeś!!!");
                }

                //disable buttons
                model.setSelectedTilesToGuessed();
                model.resetSelectedTiles();

                view.disableSelectedButtons();

                model.resetSelectionCounter();

                view.resetSelectedButtons();
            }
        } else if (model.getSelectionCounter() == 2) {
            //not guessed
            model.deselectSelectedTiles();
            model.resetSelectedTiles();

            view.deselectSelectedButtons();
            view.resetSelectedButtons();

            model.setSelectionCounter(1);
            view.saveSelectedButton(1, source);
            model.saveSelectedTile(1, selectedTile);
        }
    }

    private ArrayList<String> loadIcons(int numberOfIcons) {
        ArrayList<String> icons = new ArrayList<>();

        String dirName = "images/";

        File fileName = new File(dirName);
        File[] fileList = fileName.listFiles();

        for (int i = 0; i < numberOfIcons; i++) {

            //add icon twice
            icons.add(fileList[i].toString());
            icons.add(fileList[i].toString());
        }
        Collections.shuffle(icons);

        return icons;
    }
}
