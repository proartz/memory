package com.proartz.controller;

import com.proartz.model.Model;
import com.proartz.view.View;

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