package com.proartz;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class View {

    private JFrame frame;
    private JPanel contentPane;
    private List<JToggleButton> buttons;

    public View() {
        frame = new JFrame("Testing Buttons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setOpaque(true);

        frame.setContentPane(contentPane);

        buttons = new ArrayList<JToggleButton>();
    }

    public void showFrame() {
        frame.pack();
        frame.setVisible(true);
    }

    public List<JToggleButton> getButtons() {
        return buttons;
    }

    public void setButtons(List<JToggleButton> buttons) {
        this.buttons = buttons;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public void setContentPane(JPanel contentPane) {
        this.contentPane = contentPane;
    }
}
