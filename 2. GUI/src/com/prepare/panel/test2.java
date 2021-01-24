package com.prepare.panel;

import javax.swing.*;
import java.awt.*;

public class test2 {
    public static void main(String[] args) {
        new JScroll();
    }
}

class JScroll extends JFrame
{
    public JScroll()
    {
        Container container = getContentPane();

        // 1. create text area
        JTextArea textArea = new JTextArea(20,50); // 50 words per line
        textArea.setText("Please type here"); // default msg

        JScrollPane pane = new JScrollPane(textArea);
        container.add(pane);

        this.setBounds(400,400,200,300);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}