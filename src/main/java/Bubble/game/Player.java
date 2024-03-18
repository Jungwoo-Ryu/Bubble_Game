package Bubble.game;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Player extends JLabel implements Moveable {
    private int x;
    private int y;
    private BubbleFrame mContext;
    private List<Bubble> bubbleList;

    private PlayerDirection playerDirection;

    private final int SPEED = 4;
    private final int JUMPSPEED = 2;

    private boolean leftWallCrash;
    private boolean rightWallCrash;

    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    private ImageIcon playerR, playerL;
    public Player(BubbleFrame mContext){
        this.mContext = mContext;
        initObject();
        initSetting();
        initBackgroundPlayerService();
    }

    @Override
    public void attack() {
        new Thread(()->{
            Bubble bubble = new Bubble(mContext);
            mContext.add(bubble);
            bubbleList.add(bubble);
            if(playerDirection == PlayerDirection.LEFT){
                bubble.left();
            }
            else {
                bubble.right();
            }
        }).start();

    }

    private void initBackgroundPlayerService() {
        new Thread(new BackgroundPlayerService(this)).start();
    }

    public void initObject(){
        playerR = new ImageIcon("images/playerR.png");
        playerL = new ImageIcon("images/playerL.png");
        bubbleList = new ArrayList<>();
    }
    public void initSetting(){
        x = 85;
        y = 535;
        left = false;
        right = false;
        up = false;
        down = false;
        leftWallCrash = false;
        rightWallCrash = false;

        playerDirection = PlayerDirection.RIGHT;
        setIcon(playerR);
        setSize(50, 50);
        setLocation(x, y);
    }

    @Override
    public void left() {
        playerDirection = PlayerDirection.LEFT;
        left = true;
        new Thread(() -> {
            while(left){
                setIcon(playerL);
                x -= SPEED;
                setLocation(x,y);
                try{
                    Thread.sleep(10);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        ).start();
    }

    @Override
    public void right() {
        playerDirection = PlayerDirection.RIGHT;
        right = true;
        new Thread(() -> {
            while (right) {
                setIcon(playerR);
                x += SPEED;
                setLocation(x, y);
                try{
                    Thread.sleep(10);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void up() {
        System.out.println("UP");
        up = true;
        new Thread(() -> {
            for(int i =0; i < 130/JUMPSPEED; i++){
                y -= JUMPSPEED;
                setLocation(x,y);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
//                    throw new runtimeexception(e);
                }
            }
            up = false;
            down();
        }

        ).start();
    }

    @Override
    public void down() {
        System.out.println("DOWN");
        down = true;
        new Thread(() -> {
            while(down){
                y += JUMPSPEED;
                setLocation(x,y);
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
//                    throw new runtimeexception(e);
                }
            }
            down = false;
        }

        ).start();
    }


}

