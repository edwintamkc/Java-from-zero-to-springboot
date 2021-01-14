package com.prepare.basic;
import java.awt.*;

public class test6 {
    public static void main(String[] args) {
        Frame myFrame = new Frame();

        myFrame.setVisible(true);
        myFrame.setBounds(400,400,500,500);

        Button button1 = new Button("button1");
        Button button2 = new Button("button2");
        Button button3 = new Button("button3");
        Button button4 = new Button("button4");
        Button button5 = new Button("button5");
        Button button6 = new Button("button6");

        // Use setLayout again (used in test4)
        myFrame.setLayout(new GridLayout(3,2));

        myFrame.add(button1);
        myFrame.add(button2);
        myFrame.add(button3);
        myFrame.add(button4);
        myFrame.add(button5);
        myFrame.add(button6);

    }
}
