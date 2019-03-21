package com.proartz;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Controller {
    private Model model;
    private View view;

    public Controller() {
        view = new View();
        model = new Model();
    }

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void createAndShowGUI() {
        createButtons(model.getNumberOfTiles());
        view.showFrame();
    }

    public void createButtons(int numberOfButtons) {
        List<JToggleButton> buttons = view.getButtons();
        JPanel contentPane = view.getContentPane();

        ArrayList<String> icons = loadIcons();

        if((icons.size() * 2) < numberOfButtons) {
            System.err.println("Error: We don't have enough images :(");
            System.exit(-1);
        }

        for(int i = 0; i < numberOfButtons; i++) {
            JToggleButton button = new JToggleButton();
            button.setIcon(new ImageIcon("images/037-ufo-flying.png"));
            button.setSelectedIcon(new ImageIcon(icons.get(i)));

            button.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    Object source = e.getItemSelectable();
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        if (model.getSelectionCounter() < 2) {
                            model.incrementCounter();
                        } else {
                            for(JToggleButton button : view.getButtons()) {
                                button.setSelected(false);
                            }
                            model.resetCounter();
                        }
                    }
                }
            });

            buttons.add(button);
            contentPane.add(button);
        }
    }

    private ArrayList<String> loadIcons() {
        ArrayList<String> icons = new ArrayList<String>();

        String dirName = "images/";

        File fileName = new File(dirName);
        File[] fileList = fileName.listFiles();

        for (File file: fileList) {
            System.out.println(file);

            //add icon twice
            icons.add(file.toString());
            icons.add(file.toString());
        }
        Collections.shuffle(icons);

        return icons;
    }
}
