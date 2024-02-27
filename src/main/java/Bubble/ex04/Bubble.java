package Bubble.ex04;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
@Getter @Setter
public class Bubble extends JLabel implements Moveable {
    private BubbleFrame mContext;
    private Player player;
    private Enemy enemy;

    private BackgroundBubbleService backgroundBubbleService;

    private int x;
    private int y;

    // if it hits enemy
    private int state; // 0 = bubble 1 = bubbled

    private ImageIcon bubble;
    private ImageIcon bubbled;
    private ImageIcon bomb;

    private boolean left;
    private boolean right;
    private boolean up;

    public Bubble(BubbleFrame mContext){
        this.mContext = mContext;
        this.player = mContext.getPlayer();
        this.enemy = mContext.getEnemy();
        initObject();
        initSetting();
//        initThread();
    }

//    private void initThread() {
//        new Thread(() -> {
//            if(player.getPlayerDirection() == PlayerDirection.LEFT) left();
//            else right();
//        }).start();
//    }

    private void initObject() {
        bubble = new ImageIcon("images/bubble.png");
        bubbled = new ImageIcon("images/bubbled.png");
        bomb = new ImageIcon("images/bomb.png");

        backgroundBubbleService = new BackgroundBubbleService(this);
    }

    private void initSetting(){
        left = false;
        right = false;
        up = false;
        x = player.getX();
        y = player.getY();

        setIcon(bubble);
        setSize(50,50);
        state = 0;
    }


    @Override
    public void left() {
        for(int i =0; i< 300; i++){
            x--;
            setLocation(x,y);
            if(backgroundBubbleService.leftWall()){
                left = false;
                break;
            }

            if(Math.abs(x-enemy.getX()) > 40 && Math.abs(x-enemy.getX()) <60
               && Math.abs(y-enemy.getY()) > 0 && Math.abs(y-enemy.getY()) < 50){
                if(enemy.getState() == 0){
                    for(int j=0; j < 50;j++) x--;
                    attack();
                    break;
                }
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        up();
    }

    @Override
    public void attack() {
       state = 1;
       enemy.setState(1);
       setIcon(bubbled);
       mContext.remove(enemy);
       mContext.repaint();
    }

    @Override
    public void right() {
        for(int i =0; i< 300; i++){
            x++;
            setLocation(x,y);
            if(backgroundBubbleService.rightWall()){
                right = false;
                break;
            }
            if(Math.abs(x-enemy.getX()) > 40 && Math.abs(x-enemy.getX()) <60
                    && Math.abs(y-enemy.getY()) > 0 && Math.abs(y-enemy.getY()) < 50 ){
                if(enemy.getState() == 0){
                    for(int j=0; j <25; j++) x++;
                    attack();
                    break;
                }
            }
            try {Thread.sleep(1);}
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        up();
    }

    @Override
    public void up() {
        up = true;
        while(up){
            for(int i=0; i <300; i++){
                y--;
                setLocation(x,y);
                if(backgroundBubbleService.topWall()) {
                    up = false;
                    break;
                }
                try{
                    if(state ==0){
                        Thread.sleep(1);
                    }
                    else{ // when enemy is hit
                        Thread.sleep(10);
                    }
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        if(state == 0) clearBubble();
    }

    private void clearBubble() {
        try {
            Thread.sleep(2000);
            setIcon(bomb);
            Thread.sleep(300);
            mContext.remove(this);
            mContext.repaint();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
