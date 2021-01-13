package com.snake.basic;
import java.awt.*;

// pop up one window
public class test {
    public static void main(String[] args) {
        Frame frame = new Frame("First GUI");

        // set it visible
        frame.setVisible(true);

        // set window size
        frame.setSize(400,400);

        // set background color
        frame.setBackground(new Color(100,200,50));

        // set windows's original pop up location
        frame.setLocation(800,800);

        // set it to unresizeable (cannot adjust the window size)
        frame.setResizable(false);


    }
}
