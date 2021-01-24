package com.prepare.jFrame;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class test3 {
    public static void main(String[] args) {
        new Icon();
    }
}

class Icon extends JFrame
{
    public Icon()
    {
        // get resource under the same file of ImageIcon class
        URL url = Icon.class.getResource("icon.jpg");

        // create label, and set its Icon to be the url
        JLabel myLabel = new JLabel("icon");
        myLabel.setIcon(new ImageIcon(url)); // we need to use ImageIcon to accommodate it
        myLabel.setHorizontalAlignment(SwingConstants.CENTER);

        Container container = getContentPane();
        container.add(myLabel);

        setVisible(true);
    }


}
