package com.snake.paint;
import java.awt.*;

public class test {
    public static void main(String[] args) {
        new MyPaint();
    }
}

class MyPaint extends Frame
{
    public MyPaint()
    {
        setBounds(200,200,600,500);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        // Give color to the pen, draw a circle
        g.setColor(Color.CYAN);
        g.fillOval(100,100,100,100);

        // Give color to the pen, draw a square
        g.setColor(Color.GREEN);
        g.fillRect(250,250,100,100);

    }
}

