package com.prepare.box;

import javax.swing.*;
import java.awt.*;

public class test3 {
    public static void main(String[] args) {
        new TextDemo();
    }
}

class TextDemo extends JFrame
{
    public TextDemo()
    {
        Container container = getContentPane();

        JTextField textField1 = new JTextField("default msg1");
        JTextField textField2 = new JTextField("default msg2");

        container.add(textField1,BorderLayout.WEST);
        container.add(textField2, BorderLayout.EAST);


        this.setVisible(true);
        this.setBounds(300,300,200,100);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
