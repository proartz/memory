package com.proartz.controller;

public class App {
    private static Controller controller;

    public static void main(String[] args) {
        controller = new Controller();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                controller.startGame();
            }
        });
    }
}