package Bubble.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class BackgroundEnemyService implements Runnable{
    private BufferedImage image;
    private Enemy enemy;

    public BackgroundEnemyService(Enemy enemy){
        this.enemy= enemy;
        try{
            image = ImageIO.read(new File("images/backgroundMapService.png"));
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        while(enemy.getState()==0){

            // wall crash check
            Color leftColor = new Color(image.getRGB(enemy.getX() - 10, enemy.getY() + 25));
            Color rightColor = new Color(image.getRGB(enemy.getX() + 50 +15, enemy.getY() + 25));
            int bottomColor = image.getRGB(enemy.getX()+ 10, enemy.getY() + 50+5) +
                                 image.getRGB(enemy.getX()+ 50 - 10, enemy.getY() + 50+5);

            //bottom Crash
            if(bottomColor != -2){
                //System.out.println("Bottom Color: " + bottomColor);
                //System.out.println("Bottom Crash");
                enemy.setDown(false);
            }
            else {
                if(!enemy.isUp() && !enemy.isDown()) enemy.down();
            }


            // Wall Crash
            if(leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0){
                //System.out.println("Left wall crash");
                //enemy.setLeftWallCrash(true);
                enemy.setLeft(false);
                if(!enemy.isRight()) enemy.right();
            }
            else if(rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0){
                enemy.setRight(false);
                if(!enemy.isLeft()) enemy.left();

                //enemy.setRightWallCrash(true);
            } else {
                enemy.setLeftWallCrash(false);
                enemy.setRightWallCrash(false);
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
