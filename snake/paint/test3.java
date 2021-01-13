package com.snake.paint;
import java.awt.*;
import java.awt.event.*;

public class test3
{
    public static void main(String[] args) {
        new KeyFrame();
    }
}

class KeyFrame extends Frame
{
    public KeyFrame()
    {
        super("keyboard try");
        setBounds(400,400,500,500);
        setVisible(true);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keycode = e.getKeyCode();
                if(keycode == KeyEvent.VK_UP)
                    System.out.println("up!");
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}