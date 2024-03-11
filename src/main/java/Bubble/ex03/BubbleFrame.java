package Bubble.ex03;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BubbleFrame extends JFrame {
    private JLabel backgroundMap;
    private Player player;
    public BubbleFrame(){
        initObject();
        initSetting();
        initListener();
        setVisible(true);
    }

    private void initListener() {
        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
                switch (e.getKeyCode()){
                    case KeyEvent.VK_LEFT:
                        player.left();
                        break;
                    case KeyEvent.VK_RIGHT:
                        player.right();
                        break;
                    case KeyEvent.VK_UP:
                        player.up();
                        break;
                }

            }
        });

    }

    private void initObject(){
        backgroundMap = new JLabel(new ImageIcon("images/backgroundMap.png"));
        //backgroundMap.setSize(100, 100);
        //backgroundMap.setLocation(300,300);
        //backgroundMap.setSize(1000,600);
        setContentPane(backgroundMap);
        //add(backgroundMap);
        player = new Player();
        add(player);

    }
    private void initSetting(){
        setSize(1000, 640);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args){
        new BubbleFrame();
    }
}
