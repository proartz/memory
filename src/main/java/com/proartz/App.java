package com.proartz;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class App extends Frame{

    private static Frame frame;
    private static int tilesInRow = 8;
    private static int numbersOfRows = tilesInRow;
    private static int height;
    private static int width;

    public App(){
        super();
    }

//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//        Point location = frame.getLocation();
//        height = frame.getHeight() - (insets().top + insets().bottom);
//        width = frame.getWidth() - (insets().left + insets().right);
//        System.out.format("Location of the frame: %s%n", location);
//        System.out.format("Height of the frame: %d%n", height);
//        System.out.format("Width of the frame: %d%n", width);
//
//        g.setColor(Color.RED);
//        int x = 0;
//        int y = insets().top;
//        int rectWidth = (width / tilesInRow) - 1;
//        int rectHeight = (height / numbersOfRows) - 1;
//        for(int i = 0; i < numbersOfRows; i++) {
//            x = 0;
//            for(int j = 0; j < tilesInRow; j++) {
//                g.drawRect(x, y, rectWidth, rectHeight);
//                x += rectWidth + 1;
//            }
//            y += rectHeight + 1;
//        }
//    }

    public void paint(Graphics g) {
        // Dynamically calculate size information
        // Dimension
        size = getSize();
        // diameter
        int d = Math.min(size.width, size.height);
        int x = (size.width - d)/2;
        int y = (size.height - d)/2;
        // draw circle (color already set to foreground)
        g.fillOval(x, y, d, d);
        g.setColor(Color.black);
        g.drawOval(x, y, d, d);
    }

    public static void main(String[] args) {
        frame = new App();
        frame.setSize(400, 400);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}