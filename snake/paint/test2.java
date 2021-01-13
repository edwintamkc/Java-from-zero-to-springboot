package com.snake.paint;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class test2 {
    public static void main(String[] args) {
        MyFrame frame = new MyFrame("Paint");
        frame.close();
    }
}

class MyFrame extends Frame
{
    private ArrayList<Point> allPoints;
    public MyFrame(String title)
    {
        super(title);
        setBounds(200,200,400,300);
        setVisible(true);
        this.allPoints = new ArrayList<>();

        // listen to mouse
        this.addMouseListener(new MyMouseListener());
    }

    private void addPoint(Point p) { this.allPoints.add(p); }

    @Override
    public void paint(Graphics g) {
        // draw all recorded point from arrayList
        for(Point p : allPoints)
        {
            g.setColor(Color.BLACK);
            g.fillOval(p.x,p.y,10,10);
        }

    }

    private class MyMouseListener extends MouseAdapter
    {       // do not want to rewrite press and click event, so use function from MouseAdapter
        // mouse pressed
        @Override
        public void mousePressed(MouseEvent e) {
            MyFrame frame = (MyFrame) e.getSource();

            // record the coordinate of current mouse and add it to arraylist
            addPoint(e.getPoint());

            frame.repaint();
        }

    }

    public void close() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

}