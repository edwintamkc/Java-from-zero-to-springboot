package com.snake.event;
import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class test2 {
    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        frame.close();
    }
}

class MyFrame extends Frame{
    public MyFrame(){
        setBounds(400,400,500,500);
        setVisible(true);

        TextField textField = new TextField();
        add(textField);


        // 1. listen to the textField
        MyActionListener2 actionlistener2 = new MyActionListener2();
        textField.addActionListener(actionlistener2);
    }

    public void close(){
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}

class MyActionListener2 implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        TextField text = (TextField) e.getSource(); // accept user's input and use change it to TextField
        System.out.println(text.getText()); // get the msg from text
    }
}
