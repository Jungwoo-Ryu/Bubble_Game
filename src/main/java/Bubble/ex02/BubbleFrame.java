package Bubble.ex02;

import javax.swing.*;

public class BubbleFrame extends JFrame {
    private JLabel backgroundMap;
    private Player player;
    public BubbleFrame(){
        initObject();
        initSetting();
        setVisible(true);
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
