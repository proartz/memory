package com.proartz;

import javax.swing.*;

public class App {
    private static View view;

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Testing Buttons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        view = new View();
        view.setOpaque(true);
        frame.setContentPane(view);

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