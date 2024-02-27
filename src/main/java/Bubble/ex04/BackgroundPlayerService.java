package Bubble.ex04;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class BackgroundPlayerService implements Runnable{
    private BufferedImage image;
    private Player player;
    public BackgroundPlayerService(Player player){
        this.player = player;
        try{
            image = ImageIO.read(new File("images/backgroundMapService.png"));
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        while(true){
            Color leftColor = new Color(image.getRGB(player.getX() - 10, player.getY() + 25));
            Color rightColor = new Color(image.getRGB(player.getX() + 50 +15, player.getY() + 25));
            int bottomColor = image.getRGB(player.getX()+ 10, player.getY() + 50+5) +
                                 image.getRGB(player.getX()+ 50 - 10, player.getY() + 50+5);

            //bottom Crash
            if(bottomColor != -2){
                //System.out.println("Bottom Color: " + bottomColor);
                //System.out.println("Bottom Crash");
                player.setDown(false);
            }
            else {
                if(!player.isUp() && !player.isDown()) player.down();
            }


            // Wall Crash
            if(leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0){
                System.out.println("Left wall crash");
                player.setLeftWallCrash(true);
                player.setLeft(false);
            }
            else if(rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0){
                System.out.println("Right wall crash");
                player.setRight(false);
                player.setRightWallCrash(true);
            } else {
                player.setLeftWallCrash(false);
                player.setRightWallCrash(false);
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
