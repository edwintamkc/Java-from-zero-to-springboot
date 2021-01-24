package com.prepare.box;

import javax.swing.*;
import java.awt.*;

public class test4 {
    public static void main(String[] args) {
        new TextDemo2();
    }
}

class TextDemo2 extends JFrame
{
    public TextDemo2()
    {
        Container container = getContentPane();

        JPasswordField pwFeild = new JPasswordField();
        pwFeild.setEchoChar('*'); // *****

        container.add(pwFeild);


        this.setVisible(true);
        this.setBounds(300,300,200,100);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
