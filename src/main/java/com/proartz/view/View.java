package com.proartz.view;

import com.proartz.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class View {

    int numberOfTiles;
    private JFrame frame;
    private JPanel contentPane;
    private ArrayList<JToggleButton> buttons;
    private JToggleButton[] selectedButton;
    private Controller controller;

    public View() {

    }

    public void initializeView(){
        frame = new JFrame("Testing Buttons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        int numberOfRows, numberOfColumns;
        numberOfRows = numberOfColumns = (int) Math.ceil(Math.sqrt(numberOfTiles));
        contentPane.setLayout(new GridLayout(numberOfRows, numberOfColumns));
        contentPane.setOpaque(true);

        frame.setContentPane(contentPane);

        buttons = new ArrayList<>();

        selectedButton = new JToggleButton[2];
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void addTile(String image, String coverImage) {
        // set-uo the button
        JToggleButton button = new JToggleButton();
        button.setIcon(new ImageIcon(coverImage));
        button.setSelectedIcon(new ImageIcon(image));
        button.setDisabledSelectedIcon(new ImageIcon(image));
        button.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Object source = e.getItemSelectable();
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    controller.tilePressed(source);
                }
            }});

        buttons.add(button);
        contentPane.add(button);
    }

    public void showFrame() {
        frame.pack();
        frame.setVisible(true);
    }

    public void showMessage(String message, String title, String image) {
        JOptionPane.showMessageDialog(frame, message, title,
                JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon(image));
    }

    public int getIndexOfButton(Object source) {
        return buttons.indexOf(source);
    }

    public void saveSelectedButton(int numberOfButton, Object source) {
        selectedButton[numberOfButton - 1] = (JToggleButton) source;
    }

    public JToggleButton getSelectedButton(int index) {
        return selectedButton[index - 1];
    }

    public void resetSelectedButtons() {
        selectedButton[0] = selectedButton[1] = null;
    }

    public void disableSelectedButtons() {
        selectedButton[0].setEnabled(false);
        if(selectedButton[1] != null) {
            selectedButton[1].setEnabled(false);
        }
    }

    public void enableSelectedTiles() {
        selectedButton[0].setEnabled(true);
        selectedButton[1].setEnabled(true);
    }
    public void deselectSelectedButtons() {
        selectedButton[0].setSelected(false);
        selectedButton[1].setSelected(false);
    }

    public void setNumberOfTiles(int numberOfTiles) {
        this.numberOfTiles = numberOfTiles;
    }
}
