package Menu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.jar.Attributes.Name;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import MainGame.GamePanel;

public class IntroOverlay {
    private GamePanel gp;
    private Timer timer;
    private BufferedImage introImage, nameImg;
    private int index = -35;
 
    private boolean isTextVisible = true;

    public IntroOverlay(GamePanel gp) {
        this.gp = gp;

        loadImage();
        createButtons();

        // Khởi tạo Timer
        timer = new Timer(600, (e)-> {
            isTextVisible = !isTextVisible;
        });
        timer.start();
    }

    private void createButtons() {
        
    }

    private void loadImage() {
        introImage = GetSpriteAtlas("/ui/white_background.jpg");
//        nameImg = GetSpriteAtlas("/ui/name4.png");
       
    }

    public BufferedImage GetSpriteAtlas(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }


    public void draw(Graphics g) {
        String introText = "Nhấn phím bất kỳ để bắt đầu...";

        g.drawImage(introImage, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g.drawImage(nameImg, 2 * index, 180, 500, 65, null);
        if(index < 170) {
        	index +=3;
        }
        if (isTextVisible) {
            g.drawString(introText, gp.tileSize * 9, gp.screenHeight - 100);
        }
    }

    public void anyKeyPressed() {
        gp.gameState = gp.pauseState;
        timer.stop(); // Dừng Timer khi có bất kỳ phím nào được nhấn
    }


}
