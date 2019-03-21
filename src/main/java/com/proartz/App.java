package com.proartz;

public class App {
    private static Controller controller;

    public static void main(String[] args) {
        controller = new Controller(new Model(), new View());

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                controller.createAndShowGUI();
            }
        });
    }
}