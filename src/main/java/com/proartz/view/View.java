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
    private JToggleButton[] selectedButton;
    private Controller controller;
    private ActionListener actionListener;

    public View() {

    }

    public void initializeView(){
        initializeButtons();
        initializeContentPane();
        initializeActionListener();
        initializeMenu();
        initializeFrame();
    }

    private void initializeButtons() {
        buttons = new ArrayList<>();
        selectedButton = new JToggleButton[2];
    }

    private void initializeContentPane() {
        contentPane = new JPanel();
        int numberOfRows, numberOfColumns;
        numberOfRows = numberOfColumns = (int) Math.ceil(Math.sqrt(numberOfTiles));
        contentPane.setLayout(new GridLayout(numberOfRows, numberOfColumns));
        contentPane.setOpaque(true);
    }

    public void initializeActionListener() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("restart".equals(e.getActionCommand())) {
                    //restart the game
                    controller.restartGame();
                } else if ("save".equals(e.getActionCommand())) {
                    //save the game
                } else if ("end".equals(e.getActionCommand())) {
                    controller.endGame();
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

    public void restartView() {
        
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

    public void saveSelectedButton(int numberOfButton, Object source) {
        selectedButton[numberOfButton - 1] = (JToggleButton) source;
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

    public int getIndexOfButton(Object source) {
        return buttons.indexOf(source);
    }

    public JToggleButton getSelectedButton(int index) {
        return selectedButton[index - 1];
    }

    public void setNumberOfTiles(int numberOfTiles) {
        this.numberOfTiles = numberOfTiles;
    }

    public void setFrameTitle(String frameTitle) {
        this.frameTitle = frameTitle;
    }
}
