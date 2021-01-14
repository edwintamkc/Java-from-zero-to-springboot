package com.prepare.panel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class test4 {
    public static void main(String[] args) {
        new JButtonDemo2();
    }
}

class JButtonDemo2 extends JFrame
{
    public JButtonDemo2()
    {
        Container container = getContentPane();

        // 1. get url and change it to icon
        URL iconUrl = JButtonDemo.class.getResource("icon.jpg");
        Icon imgIcon = new ImageIcon(iconUrl);

        // 2. create button and button group
        JRadioButton button1 = new JRadioButton("button1");
        JRadioButton button2 = new JRadioButton("button2");
        JRadioButton button3 = new JRadioButton("button3");

        ButtonGroup group = new ButtonGroup();
        group.add(button1);
        group.add(button2);
        group.add(button3);

        // 3. add it to pane
        container.add(button1);
        container.add(button2);
        container.add(button3);
        container.setLayout(new FlowLayout());
        this.setVisible(true);
        this.setBounds(400,400,200,200);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}