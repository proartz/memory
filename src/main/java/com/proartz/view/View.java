package com.proartz.view;

import com.proartz.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class View {

    private int numberOfTiles;
    private String frameTitle;
    private JFrame frame;
    private JPanel contentPane;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem newGame, saveGame, endGame;
    private ArrayList<JToggleButton> buttons;
    private JToggleButton[] selectedButtons;
    private Controller controller;
    private ActionListener actionListener;

    public View(int numberOfTiles, String frameTitle, Controller controller) {
        this.numberOfTiles = numberOfTiles;
        this.frameTitle = frameTitle;
        this.controller = controller;

        initializeView();
    }

    public void initializeView(){
        initializeContentPane();
        initializeButtons();
        initializeActionListener();
        initializeMenu();
        initializeFrame();
    }

    private void initializeContentPane() {
        contentPane = new JPanel();
        int numberOfRows, numberOfColumns;
        numberOfRows = numberOfColumns = (int) Math.ceil(Math.sqrt(numberOfTiles));
        contentPane.setLayout(new GridLayout(numberOfRows, numberOfColumns));
        contentPane.setOpaque(true);
    }

    private void initializeButtons() {
        buttons = new ArrayList<>();
        for(int i = 0; i < numberOfTiles; i++) {
            // set-uo the button
            JToggleButton button = new JToggleButton();

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
        selectedButtons = new JToggleButton[2];
    }

    public void initializeActionListener() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("restart".equals(e.getActionCommand())) {
                    //restart the game
                    controller.actions("restart");
                } else if ("save".equals(e.getActionCommand())) {
                    //save the game
                    controller.actions("save");
                } else if ("end".equals(e.getActionCommand())) {
                    controller.actions("end");
                }
            }
        };
    }
    private void initializeMenu() {
        menuBar = new JMenuBar();

        menu = new JMenu("Gra");
        menu.setMnemonic(KeyEvent.VK_G);
        menuBar.add(menu);

        newGame = new JMenuItem("Nowa Gra",
                KeyEvent.VK_N);
        newGame.setActionCommand("restart");
        newGame.addActionListener(actionListener);
        menu.add(newGame);

        saveGame = new JMenuItem("Zapisz grę",
                KeyEvent.VK_S);
        saveGame.setActionCommand("save");
        saveGame.addActionListener(actionListener);
        menu.add(saveGame);
        menu.addSeparator();

        endGame = new JMenuItem("Zakończ",
                KeyEvent.VK_Z);
        endGame.setActionCommand("end");
        endGame.addActionListener(actionListener);
        menu.add(endGame);
    }

    private void initializeFrame() {
        frame = new JFrame("Testing Buttons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(contentPane);
        frame.setJMenuBar(menuBar);
    }

    public void setTileIcon(int tileNumber, String image, String coverImage) {
        JToggleButton button = buttons.get(tileNumber);

        button.setIcon(new ImageIcon(coverImage));
        button.setPressedIcon(new ImageIcon(image));
        button.setDisabledIcon(new ImageIcon(image));
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

    public void saveSelectedButton(int numberOfButton, int tileNumber) {
        selectedButtons[numberOfButton - 1] = buttons.get(tileNumber);
    }

    public void clearSelectedButtons() {
        selectedButtons[0] = selectedButtons[1] = null;
    }

    public void disableSelectedButtons() {
        selectedButtons[0].setEnabled(false);
        if(selectedButtons[1] != null) {
            selectedButtons[1].setEnabled(false);
        }
    }

    public void enableSelectedTiles() {
        selectedButtons[0].setEnabled(true);
        selectedButtons[1].setEnabled(true);
    }
    public void deselectSelectedButtons() {
        selectedButtons[0].setSelected(false);
        selectedButtons[1].setSelected(false);
    }

    public int getIndexOfButton(Object source) {
        return buttons.indexOf(source);
    }

    public JToggleButton getSelectedButton(int index) {
        return selectedButtons[index - 1];
    }

    public void setTileState(int tileNumber, boolean selected) {
        JToggleButton button = buttons.get(tileNumber);

        button.setEnabled(!selected);
        button.setSelected(selected);

    }

    public void setSelectedButton(int tileNumber, boolean selected) {
        selectedButtons[tileNumber - 1].setEnabled(!selected);
        selectedButtons[tileNumber - 1].setSelected(selected);
    }

    public void clearSelectedButton(int tileNumber) {
        selectedButtons[tileNumber - 1] = null;
    }

    public void showSelectedButtons(){
        System.out.println("view.selectedButtons:");
        for(JToggleButton button : selectedButtons) {
            System.out.println(button);
        }
    }
}
