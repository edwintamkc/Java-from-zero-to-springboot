package com.snake.event;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class test {
    public static void main(String[] args) {
        Frame frame = new Frame();
        Button button = new Button("button");

        frame.add(button,BorderLayout.CENTER);
        frame.setBounds(400,400,500,500);
        frame.setVisible(true);

        // 1. create eventListener
        MyActionListener myActionListener = new MyActionListener();
        button.addActionListener(myActionListener);

        windowClose(frame);


    }

    // close window
    private static void windowClose(Frame frame){
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}

class MyActionListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.out.println("one click");
    }

}