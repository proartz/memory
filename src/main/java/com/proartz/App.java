package com.proartz;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import java.util.List;

public class App extends JPanel
                 implements ItemListener {

    protected List<JToggleButton> buttons;
    int numbersOfButtons = 50;
    int selectionCounter;

    public App() {
        buttons = new LinkedList<JToggleButton>();

        for(int i = 0; i < numbersOfButtons; i++) {
            JToggleButton button = new JToggleButton();
            button.setIcon(new ImageIcon("images/ufo-flying.png"));
            button.setSelectedIcon(new ImageIcon("images/ufo.png"));
            buttons.add(button);
        }

        for(JToggleButton button : buttons) {
            button.addItemListener(this);
            add(button);
        }

        selectionCounter = 0;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (selectionCounter < 2) {
                selectionCounter++;
            } else {
                for(JToggleButton button : buttons) {
                    button.setSelected(false);
                }

                selectionCounter = 0;
            }
        }
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Testing Buttons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        App newContentPane = new App();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}