package com.proartz;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class View extends JPanel
        implements ItemListener {

    private List<JToggleButton> buttons;
    private int numberOfButtons = 50;
    private Model model;

    public View() {
        model = new Model();
        buttons = createButtons(numberOfButtons);

        for(JToggleButton button : buttons) {
            button.addItemListener(this);
            add(button);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (model.getSelectionCounter() < 2) {
                model.incrementCounter();
            } else {
                for(JToggleButton button : buttons) {
                    button.setSelected(false);
                }
                model.resetCounter();
            }
        }
    }

    public List<JToggleButton> createButtons(int numberOfButtons) {
        List<JToggleButton> buttons = new ArrayList<JToggleButton>();

        for(int i = 0; i < numberOfButtons; i++) {
            JToggleButton button = new JToggleButton();
            button.setIcon(new ImageIcon("images/ufo-flying.png"));
            button.setSelectedIcon(new ImageIcon("images/ufo.png"));
            buttons.add(button);
        }
        return buttons;
    }
}
