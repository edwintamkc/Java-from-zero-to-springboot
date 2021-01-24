package com.snake;

import javax.swing.*;

public class StartGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake~~~");

        frame.setBounds(100,100,900,720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        frame.add(new GamePanel());
        frame.setVisible(true);

    }
}
