package com.snake.jFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class test2 {
    public static void main(String[] args) {
        new MyFrame2().init();
    }
}

class MyFrame2 extends JFrame
{
    public void init()
    {
        this.setBounds(400,400,500,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton button = new JButton("Click to pop up a window");
        button.setBounds(30,30,200,50);

        Container container = this.getContentPane();
        container.setLayout(null);
        container.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Dialog();
            }
        });
    }
}

class Dialog extends JDialog
{
    public Dialog()
    {
        this.setBounds(500,500,200,200);
        this.setVisible(true);

        Container container = this.getContentPane();
        container.setLayout(null);

        Label label = new Label("Hello from the other side~");
        label.setBounds(20,30,160,40);
        container.add(label);
    }
}
