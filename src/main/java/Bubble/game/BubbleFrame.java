package Bubble.game;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Getter @Setter
public class BubbleFrame extends JFrame {
    private BubbleFrame mContext = this;
    private JLabel backgroundMap;
    private Player player;
    private Enemy enemy;
    public BubbleFrame() {
        initObject();
        initSetting();
        initListener();
        setVisible(true);
    }

    private void initListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (!player.isLeft() && !player.isLeftWallCrash()) player.left();
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (!player.isRight() && !player.isRightWallCrash()) player.right();
                        break;
                    case KeyEvent.VK_UP:
                        if (!player.isUp() && !player.isDown()) player.up();
                        break;
                    case KeyEvent.VK_SPACE:
                        //Bubble bubble = new Bubble(mContext);
                        //add(bubble);
                        player.attack();
                        break;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        player.setLeft(false);
                        break;
                    case KeyEvent.VK_RIGHT:
                        player.setRight(false);
                        break;
                }
            }
        });
    }


    private void initObject() {
        backgroundMap = new JLabel(new ImageIcon("images/backgroundMap.png"));
        //backgroundMap.setSize(100, 100);
        //backgroundMap.setLocation(300,300);
        //backgroundMap.setSize(1000,600);
        setContentPane(backgroundMap);
        //add(backgroundMap);
        player = new Player(this);
        add(player);
        enemy = new Enemy(this);
        add(enemy);
        new BGM();
    }

    private void initSetting() {
        setSize(1000, 640);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new BubbleFrame();
    }
}
