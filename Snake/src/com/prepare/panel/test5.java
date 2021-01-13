package com.prepare.panel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class test5 {
    public static void main(String[] args) {
        new JButtonDemo3();
    }
}

class JButtonDemo3 extends JFrame
{
    public JButtonDemo3()
    {
        Container container = getContentPane();

        // 1. get url and change it to icon
        URL iconUrl = JButtonDemo.class.getResource("icon.jpg");
        Icon imgIcon = new ImageIcon(iconUrl);

        // 2. create button
        JCheckBox checkbox1 = new JCheckBox("checkbox1");
        JCheckBox checkbox2 = new JCheckBox("checkbox2");

        // 3. add it to pane
        container.add(checkbox1);
        container.add(checkbox2);

        container.setLayout(new FlowLayout());
        this.setVisible(true);
        this.setBounds(400,400,200,200);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}