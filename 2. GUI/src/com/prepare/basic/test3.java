package com.prepare.basic;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class test3 {
    public static void main(String[] args) {
        Frame myframe = new Frame();
        // Panel is like a box on Frame
        Panel myPanel = new Panel();

        // 1. setlayout
        myframe.setLayout(null);

        // 2. coordinate and color of Frame (the window)
        myframe.setBounds(300,300,500,500);
        myframe.setBackground(new Color(50,100,200));

        // 3. coordinate and color of Panel (box on the window)
        myPanel.setBounds(0,100,500,400); // relative coordinate, relative to frame
        myPanel.setBackground(new Color(37, 213, 55));

        // 4. add panel to the frame, and set frame visible
        myframe.add(myPanel);
        myframe.setVisible(true);

        // 5. add close listenner
        myframe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
