package Bubble.ex04;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class Enemy extends JLabel implements Moveable {
    private int x;
    private int y;
    private BubbleFrame mContext;
    private EnemyDirection enemyDirection;

    private int state;

    private final int SPEED = 3;
    private final int JUMPSPEED = 1;

    private boolean leftWallCrash;
    private boolean rightWallCrash;

    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    private ImageIcon EnemyR, EnemyL;
    public Enemy(BubbleFrame mContext){
        this.mContext = mContext;
        initObject();
        initSetting();
        initBackgroundEnemyService();
        right();
    }


    private void initBackgroundEnemyService() {
        new Thread(new BackgroundEnemyService(this)).start();
    }

    public void initObject(){
        EnemyR = new ImageIcon("images/EnemyR.png");
        EnemyL = new ImageIcon("images/EnemyL.png");
    }
    public void initSetting(){
        x = 480;
        y = 178;
        left = false;
        right = false;
        up = false;
        down = false;
        leftWallCrash = false;
        rightWallCrash = false;
        state = 0;

        enemyDirection = EnemyDirection.RIGHT;
        setIcon(EnemyR);
        setSize(50, 50);
        setLocation(x, y);
    }

    @Override
    public void left() {
        enemyDirection = EnemyDirection.LEFT;
        left = true;
        new Thread(() -> {
            while(left){
                setIcon(EnemyL);
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
        enemyDirection = EnemyDirection.RIGHT;
        right = true;
        new Thread(() -> {
            while (right) {
                setIcon(EnemyR);
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

