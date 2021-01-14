package com.prepare.box;

import javax.swing.*;
import java.awt.*;

public class test {
    public static void main(String[] args) {
        new ComboBox();
    }
}

class ComboBox extends JFrame
{
    public ComboBox()
    {
        Container container = getContentPane();

        JComboBox box = new JComboBox();
        box.addItem("first");
        box.addItem("second");
        box.addItem("third");

        container.add(box);
        this.setVisible(true);
        this.setBounds(300,300,200,100);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
