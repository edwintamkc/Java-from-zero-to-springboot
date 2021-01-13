package com.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.Random;

// Panel of the game
public class GamePanel extends JPanel implements KeyListener, ActionListener {
    private int length; // length of snake
    private int[] snakeX = new int[600]; // all body of snake, x coordinate
    private int[] snakeY = new int[500]; // y coordinate
    boolean isStart;
    boolean isFail;
    private String direction; // snake's direction
    int score;
    int prevKeyCode; // for deciding the snake's movement (current left, next cannot be right, etc)

    // timer
    Timer timer = new Timer(100,this);  // time in ms
    // coordinate of food
    int foodX, foodY;
    Random random = new Random();


    public GamePanel(){
        init();
        this.setFocusable(true); // get focus
        this.addKeyListener(this); // get keyboard listener
        timer.start();
    }

    public void init(){
        length = 3; // since each body is 25x25, we simply decrease it by 25
        snakeX[0] = 100; snakeY[0] = 100; // head
        snakeX[1] = 75; snakeY[1] = 100; // body
        snakeX[2] = 50; snakeY[2] = 100; // body
        isStart = false;
        isFail = false;
        direction = "R";
        score = 0;

        // random food occur places
        foodX = 25 + 25*random.nextInt(34);
        foodY = 75 + 25*random.nextInt(24);
    }

    // ------------------------------------paint panel----------------------------------
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // need this line otherwise we could see the flash
        this.setBackground(Color.WHITE);

        // draw the header and game panel
        Data.header.paintIcon(this,g,25,11);
        g.fillRect(25,75,850,600);

        // show the score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Bahnschrift",Font.BOLD,15));
        g.drawString("Length " + length, 750,35);
        g.drawString("Score   " + score, 750,50);

        // draw the snake and food
        Data.food.paintIcon(this,g,foodX,foodY);

        if(direction.equals("R")){
            Data.right.paintIcon(this,g,snakeX[0],snakeY[0]);
        } else if(direction.equals("L")) {
            Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if(direction.equals("U")){
            Data.up.paintIcon(this,g,snakeX[0],snakeY[0]);
        } else if(direction.equals("D")){
            Data.down.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        for(int i = 1; i < length; i++){
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }


        if(isStart == false){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Bahnschrift",Font.BOLD,40));
            g.drawString("Press sapce to start",250,350);
        }

        if(isFail == true){
            g.setColor(Color.RED);
            g.setFont(new Font("Bahnschrift",Font.BOLD,40));
            g.drawString("Press space to try again!!",250,350);
        }
    }
    // --------------------------------------event listener----------------------------
    // for keyboard
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        // game start or not
        if(keyCode == KeyEvent.VK_SPACE){
            if(isFail){
                isFail = false;
                init();
            }else {
                isStart = !isStart;
            }
            repaint();
        }

        // if current head is left, next could not be right
        // otherwise the head will smash the body
        if((prevKeyCode == KeyEvent.VK_RIGHT && keyCode == KeyEvent.VK_LEFT)
            || (prevKeyCode == KeyEvent.VK_LEFT && keyCode == KeyEvent.VK_RIGHT)
            || (prevKeyCode == KeyEvent.VK_UP && keyCode == KeyEvent.VK_DOWN)
            || (prevKeyCode == KeyEvent.VK_DOWN && keyCode == KeyEvent.VK_UP))
        {

        }
        else{
            // snake movement
            if(keyCode == KeyEvent.VK_UP){
                prevKeyCode = KeyEvent.VK_UP;
                direction = "U";
            } else if(keyCode == KeyEvent.VK_DOWN){
                prevKeyCode = KeyEvent.VK_DOWN;
                direction = "D";
            } else if(keyCode == KeyEvent.VK_LEFT){
                prevKeyCode = KeyEvent.VK_LEFT;
                direction = "L";
            } else if(keyCode == KeyEvent.VK_RIGHT){
                prevKeyCode = KeyEvent.VK_RIGHT;
                direction = "R";
            }
        }
    }

    // for timer
    @Override
    public void actionPerformed(ActionEvent e) {
        if(isStart && isFail == false){
            // eat the food
            if(snakeX[0] == foodX && snakeY[0] == foodY){
                // increase the length and score
                length++;
                score += 10;

                // gen a food randomly
                foodX = 25 + 25*random.nextInt(34);
                foodY = 75 + 25*random.nextInt(24);
            }

            // shift the snake (body)
            for(int i = length-1; i > 0; i--) {
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1];
            }
            // shift the snake (head)
            if(direction.equals("R")){
                snakeX[0] = snakeX[0] + 25;
                if(snakeX[0] > 850){ snakeX[0] = 25;} // boarder judge
            } else if(direction.equals("L")){
                snakeX[0] = snakeX[0] - 25;
                if(snakeX[0] < 25) { snakeX[0] = 850;}
            } else if(direction.equals("U")){
                snakeY[0] = snakeY[0] - 25;
                if(snakeY[0] < 75) { snakeY[0] = 650;}
            } else if(direction.equals("D")){
                snakeY[0] = snakeY[0] + 25;
                if(snakeY[0] > 650) { snakeY[0] = 75;}
            }

            // fail function (head's coordinate equals any body's coordinate)
            for(int i = 1; i < length; i++){
                if(snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]){
                        isFail = true;
                }
            }

            repaint();
        }
        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }

}
