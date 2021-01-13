package com.snake.jFrame;

import javax.swing.*;
import java.awt.*;

public class test {
    public static void main(String[] args) {
        new MyFrame().init();
    }
}

class MyFrame extends JFrame
{
    public void init()
    {
        setVisible(true);
        setBounds(400,400,500,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 1. set label, similar to before
        JLabel label = new JLabel("Welcome~!");
        this.add(label);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // 2. set bgcolor***
        Container container = this.getContentPane();
        container.setBackground(Color.yellow);
    }
}
