package com.prepare.box;

import javax.swing.*;
import java.awt.*;

public class test2 {
    public static void main(String[] args) {
        new ComboBox2();
    }
}

class ComboBox2 extends JFrame
{
    public ComboBox2()
    {
        Container container = getContentPane();

        String[] strs = {"1","2","3"};

        JList myList = new JList(strs);

        container.add(myList);
        this.setVisible(true);
        this.setBounds(300,300,200,100);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
