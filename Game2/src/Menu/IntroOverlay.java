package Menu;

import java.awt.Graphics;
import java.awt.Graphics2D;
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
    private int index = -50;
    private int effectSpeed = 5, effectTick, effectIndex;
 
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
        introImage = GetSpriteAtlas("/ui/back2.png");
        nameImg = GetSpriteAtlas("/ui/name.png");
       
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


    public void draw(Graphics2D g) {
    	BufferedImage img = GetSpriteAtlas("/ui/press_any_key.png");
        g.drawImage(introImage, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g.drawImage(nameImg,160 , 2 * index, 900, 255, null);
        if(index < 90) {
        	index +=3;
        }
        if(index > 60){
        	drawRain(g);
        }
        if (isTextVisible) {
        	g.drawImage(img, gp.tileSize * 8 - 40, gp.screenHeight - 150, 500, 40, null);
        }
    }
    
    

    private void drawRain(Graphics2D g2) {
    	effectTick++;
		if(effectTick >= effectSpeed) {
			effectTick = 0;
			effectIndex ++;
		}
		
		if(effectIndex >= 8) {
			effectIndex = 0;
		}
		
		g2.drawImage(gp.raineffect[effectIndex], 0, 0, gp.screenWidth, gp.screenHeight, null);
		
	}

	public void anyKeyPressed() {
        gp.gameState = gp.pauseState;
        timer.stop(); // Dừng Timer khi có bất kỳ phím nào được nhấn
    }


}