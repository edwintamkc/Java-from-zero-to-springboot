package com.prepare.basic;
import java.awt.*;

public class test4 {
    public static void main(String[] args) {
        Frame myFrame = new Frame();

        myFrame.setVisible(true);
        myFrame.setBounds(400,400,500,500);

        // 1. create button
        Button button1 = new Button("button1"); // name
        Button button2 = new Button("button2");
        Button button3 = new Button("button3");

        // 2. set its place (flow, ~ css)
        myFrame.setLayout(new FlowLayout());

        // 3. add button on the frame
        myFrame.add(button1);
        myFrame.add(button2);
        myFrame.add(button3);

    }
}
