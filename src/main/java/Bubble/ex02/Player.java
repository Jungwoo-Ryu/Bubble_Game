package Bubble.ex02;

import javax.swing.*;

public class Player extends JLabel {
    private int x;
    private int y;
    private ImageIcon playerR, playerL;
    public Player(){
        initObject();
        initSetting();
    }

    public void initObject(){
        playerR = new ImageIcon("images/playerR.png");
        playerL = new ImageIcon("images/playerL.png");
    }
    public void initSetting(){
        x = 55;
        y = 535;

        setIcon(playerR);
        setSize(50, 50);
        setLocation(x, y);
    }
}
