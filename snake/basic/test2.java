package com.snake.test1;
import java.awt.*;

// pop up 10 windows
public class test2 {
    public static void main(String[] args) {
        for(int i = 0; i < 10; i++)
        {
            new Myframe(100,100,200,200,Color.black);
        }
    }

}

class Myframe extends Frame{
    static int cnt = 0;

    public Myframe(int x, int y, int w, int h, Color color)
    {
        super("Frame"+(++cnt));
        setBounds(x,y,w,h);
        setBackground(color);
        setVisible(true);
    }
}
