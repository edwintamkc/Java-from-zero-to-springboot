package com.prepare.panel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class test3 {
    public static void main(String[] args) {
        new JButtonDemo();
    }
}

class JButtonDemo extends JFrame
{
    public JButtonDemo()
    {
        Container container = getContentPane();

        // 1. get url and change it to icon
        URL iconUrl = JButtonDemo.class.getResource("icon.jpg");
        Icon imgIcon = new ImageIcon(iconUrl);

        // 2. set icon as button
        JButton button = new JButton(imgIcon);
        button.setToolTipText("Button");

        // 3. add it to pane
        container.add(button);

        this.setVisible(true);
        this.setBounds(400,400,200,200);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}