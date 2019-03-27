package com.proartz.controller;

import com.proartz.model.Model;
import com.proartz.model.Tile;
import com.proartz.view.View;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Controller {
    private static final int DEFAULT_NUMBER_OF_TILES = 16;
    private static final String IMAGES_DIR = "images/";
    private static final String FINAL_ICON = "006-outer-space-alien.png";
    private static final String COVER_ICON = "037-ufo-flying.png";
    private static final String DATA_FILE = "save.dat";
    private static final String GAME_TITLE = "Memory";
    private Model model;
    int numberOfTiles;
    private View view;
    private ArrayList<String> icons;
    boolean loadedGame;
    private boolean disableTilePress = false;

    public Controller() {

        //check do we have saved game
        Path dataFile = Paths.get(DATA_FILE);

        if(Files.exists(dataFile)) {
            loadedGame = true;
            model = loadGame(DATA_FILE);

        } else {
            loadedGame = false;
            model = new Model(DEFAULT_NUMBER_OF_TILES);
        }
        numberOfTiles = model.getNumberOfTiles();
        initializeIcons();

        view = new View(numberOfTiles, GAME_TITLE, this);
    }

    public void startGame() {
        if(!loadedGame) {
            createTiles();
        }
        model.showModel();
        disableTilePress = true;
        loadModelToView();
        disableTilePress = false;

        view.showFrame();
    }

    public void initializeIcons() {
        icons = loadIcons(numberOfTiles / 2);
    }

    public void createTiles() {
        for(int i = 0; i < numberOfTiles; i++) {
            model.addTile(i, icons.get(i));
        }
    }

    public void loadModelToView() {
        for(int i = 0; i < numberOfTiles; i++) {
            Tile tile = model.getTile(i);
            String image = tile.getValue();
            view.setTileIcon(i, IMAGES_DIR + image, IMAGES_DIR + COVER_ICON);
            view.setTileState(i, tile.isSelected());
        }
        for(int i = 0; i < 2; i++){
            Tile tile = model.getSelectedTile(1);
            if(tile == null) {
                view.clearSelectedButton(i + 1);
            } else {
                view.saveSelectedButton(i + 1, tile.getIndex());
                view.setSelectedButton(i + 1, tile.isSelected());
            }
        }
    }

    public void tilePressed(Object source) {
        if(disableTilePress) {
            return;
        }
        int indexOfTile = view.getIndexOfButton(source);
        Tile selectedTile = model.getTile(indexOfTile);
        model.selectTile(indexOfTile);
        model.incrementClickCounter();

        if (model.getSelectionCounter() == 0) {

            //if it is a first and only tile pressed
            model.saveSelectedTile(1, selectedTile);
            model.incrementSelectionCounter();

            view.saveSelectedButton(1, indexOfTile);
            view.disableSelectedButtons();

        } else if (model.getSelectionCounter() == 1) {
            //if it's the second tile pressed and not the same one as first

            model.saveSelectedTile(2, selectedTile);
            model.incrementSelectionCounter();
            view.saveSelectedButton(2, indexOfTile);
            view.disableSelectedButtons();

            if (model.compareSelectedTiles()) {

                //if the same picture has been uncovered
                model.decrementToGuess();

                if (model.getToGuess() == 0) {
                    showFinalDialog();
                }

                model.clearSelectedTiles();

                model.resetSelectionCounter();

                view.clearSelectedButtons();
            }
        } else if (model.getSelectionCounter() == 2) {
            //not guessed
            view.enableSelectedTiles();
            model.deselectSelectedTiles();
            model.clearSelectedTiles();

            view.deselectSelectedButtons();
            view.clearSelectedButtons();

            model.setSelectionCounter(1);
            view.saveSelectedButton(1, indexOfTile);
            view.disableSelectedButtons();
            model.saveSelectedTile(1, selectedTile);
        }
        model.showModel();
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
        if(model.getToGuess() != 0) {
            saveGame(DATA_FILE);
        }
        System.exit(1);
    }

    public void restartGame() {
        initializeIcons();
        model.restartModel();
        loadValuesToModel();
        loadModelToView();
    }

    public void loadValuesToModel() {
        for(int i = 0; i < numberOfTiles; i++) {
            Tile tile = model.getTile(i);
            String value = icons.get(i);
            tile.setValue(value);
        }
    }
    public void saveGame(String dataFile) {
        try (ObjectOutputStream out = new ObjectOutputStream(new
                    BufferedOutputStream(new FileOutputStream(dataFile)))) {

            out.writeObject(model);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Model loadGame(String dataFile) {
        Model savedGame = null;

        try (ObjectInputStream in = new ObjectInputStream(new
                    BufferedInputStream(new FileInputStream(dataFile)))) {

            savedGame = (Model)in.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return savedGame;
    }

    public void actions(String action) {
        if(action.equals("restart")) {
            restartGame();
        } else if(action.equals("save")) {
            saveGame(DATA_FILE);
        } else if (action.equals("end")) {
            endGame();
        }
    }
}
