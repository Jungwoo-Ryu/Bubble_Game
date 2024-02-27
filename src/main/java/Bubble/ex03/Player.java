package Bubble.ex03;

import javax.swing.*;

public class Player extends JLabel implements Moveable {
    private int x;
    private int y;

    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

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
        left = false;
        right = false;
        up = false;
        down = false;

        setIcon(playerR);
        setSize(50, 50);
        setLocation(x, y);
    }

    @Override
    public void left() {
        setIcon(playerL);
        x -= 20;
        setLocation(x,y);
    }

    @Override
    public void right() {
        setIcon(playerR);
        x +=20;
        setLocation(x,y);
    }

    @Override
    public void up() {

    }

    @Override
    public void down() {

    }
}
