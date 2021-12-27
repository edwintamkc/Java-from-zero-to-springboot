package com.prepare.event;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class test3_cal {
    public static void main(String[] args) {
        Calculator cal = new Calculator();
        cal.close();
    }
}

class Calculator extends Frame
{
    private TextField t1,t2,t3;
    public Calculator()
    {
        // 3 textfields
        t1 = new TextField(10);
        t2 = new TextField(10);
        t3 = new TextField(20);

        // euqal button
        Button equalButton = new Button("=");
        equalButton.addActionListener(new MyListener());

        // add label
        Label addLabel = new Label("+");

        // set layout, use flowlayout to auto adjust
        setLayout(new FlowLayout());

        add(t1);
        add(addLabel);
        add(t2);
        add(equalButton);
        add(t3);

        setLocation(700,450);
        pack();
        setVisible(true);
    }

    private class MyListener implements ActionListener
    {
        // get two number from two text field
        // output result to textfield 3
        @Override
        public void actionPerformed(ActionEvent e) {
            int a = Integer.parseInt(t1.getText());
            int b = Integer.parseInt(t2.getText());
            t3.setText(""+(a+b));
        }
    }

    public void close()
    {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}

