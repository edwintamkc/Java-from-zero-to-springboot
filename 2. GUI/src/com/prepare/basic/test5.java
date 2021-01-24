package com.prepare.basic;
import java.awt.*;

public class test5 {
    public static void main(String[] args) {
        Frame myFrame = new Frame();

        myFrame.setBounds(400,400,500,500);
        myFrame.setVisible(true);

        // 1. create buttons
        Button button1 = new Button("Right");
        Button button2 = new Button("Left");
        Button button3 = new Button("Bottom");
        Button button4 = new Button("Top");
        Button button5 = new Button("Center");

        // 2. try flow
        myFrame.add(button1,BorderLayout.WEST);
        myFrame.add(button2,BorderLayout.EAST);
        myFrame.add(button3,BorderLayout.SOUTH);
        myFrame.add(button4,BorderLayout.NORTH);
        myFrame.add(button5,BorderLayout.CENTER);
    }
}
