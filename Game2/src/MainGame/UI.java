package MainGame;

import java.awt.Color;
import java.io.IOException;
import javax.imageio.ImageIO;

import Menu.IntroOverlay;
import Menu.PauseOverlay;
import Menu.PlayOverlay;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.text.DecimalFormat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.Cursor;


public class UI {
	public PauseOverlay po ;
	public PlayOverlay playO;
	public IntroOverlay introO;
	public boolean isWinning = false;
	public boolean gameFinished = false;

	private GamePanel gp;
	private Graphics2D g2;
	private Font pixelFont_20, boldFont_20, pixelFont_14, boldFont_14, boldFontBig, pixelFontBig;
	private BufferedImage  infoImage, thankYouImg;
	private boolean textVisiable = true;
	private int timeSwitch = 0;


	public BufferedImage GetSpriteAtlas(String path) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResourceAsStream(path));;

		} catch(IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	public UI(GamePanel gp) {
		this.gp = gp;
		this.po = new PauseOverlay(gp);
		this.playO = new PlayOverlay(gp);
		this.introO = new IntroOverlay(gp);
		setTextFont();
		
		loadImg();
	}
	
	private void loadImg() {
		 thankYouImg = GetSpriteAtlas("/ui/winning.png");
	}
	

	private void setTextFont() {
		pixelFont_20 = new Font("Monospaced", Font.PLAIN, 20);
		pixelFont_14 = new Font("Monospaced", Font.PLAIN, 14);
		pixelFontBig = new Font("Monospaced", Font.PLAIN, 26);
		boldFont_20 = pixelFont_20.deriveFont(Font.BOLD); 
		boldFont_14 = pixelFont_14.deriveFont(Font.BOLD); 
		boldFontBig = pixelFontBig.deriveFont(Font.BOLD);
		
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		g2.setFont(boldFont_20);
		g2.setColor(Color.white);
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen(g2);
			
			if(gp.infoShow) {
				drawInfoShow(g2);
			}
		}
		else if(gp.gameState == gp.playState) {
			drawPlayScreen(g2);
		}
		else if(gp.gameState == gp.introState) {
			drawIntro(g2);
		}
		
		if(gp.shadingOn) {
			drawSwitchSceen(g2);
		}
		
		else if(gp.popUp) {
			drawInstruction(g2);
		}
		else if(gp.storyShow) {
			drawStoryShow(g2);
		}
		
		if(gp.nextLevelEffect == true) {
			drawNextLevelEffect();
		}
		
		if(gp.isPlayerAlive == false && textVisiable) {
			drawDieText(g2);
		}
		
		if(gp.isWinningEffect) {
			drawWinning(g2);
		}

	}

	private void drawSwitchSceen(Graphics2D g) {
		BufferedImage image = GetSpriteAtlas("/ui/blackbackground.png") ;
		if(timeSwitch == 0) {
			if(gp.alpha >= 0.95f) {
	        	timeSwitch ++;
	        	if(gp.winAllLevel) {
//	        		System.out.println("ok");
	        		gp.isWinningEffect = true;
	        	}
	        	else if(gp.isPlayerAlive == false) {
	        		gp.gameState = gp.pauseState;

//	        		gp.setupGame(gp.level);
	        	}
	        	else {
	        		gp.setupGame(gp.level);
	        		gp.gameState = gp.playState;
	        	}


	        }
	        else {
	        	gp.alpha += 0.05f;
	        }
		}

		if(timeSwitch == 1) {

			if(gp.alpha <= 0.05f) {
	        	gp.shadingOn = false;
	        	gp.alpha = 0f;
	        	timeSwitch = 0;
	        	gp.isPlayerAlive = true;


	        }
	        else {
	        	gp.alpha -= 0.05f;
	        }
		}

		// Tạo một AlphaComposite với độ trong suốt đã cho
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, gp.alpha);

        // Lưu trạng thái hiện tại của Graphics2D để khôi phục sau khi vẽ hình ảnh
        Composite originalComposite = g.getComposite();

        // Đặt AlphaComposite mới cho Graphics2D
        g.setComposite(alphaComposite);

        // Vẽ hình ảnh
        g.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight, null);

        // Khôi phục trạng thái ban đầu của Graphics2D
        g.setComposite(originalComposite);
        
	}
	
	
	private void drawStoryShow(Graphics2D g) {
		BufferedImage storyImg = GetSpriteAtlas("/ui/story_image.png") ;
		g.drawImage(storyImg, -1, 0, gp.screenWidth - 0, gp.screenHeight - 0, null);
		
	}

	private void drawDieText(Graphics2D g) {
		BufferedImage dieImg = GetSpriteAtlas("/ui/die.png") ;

		if(gp.indexForDieText < 90) {
			gp.indexForDieText +=2;
        	g.drawImage(dieImg, gp.tileSize * 8, 2 * gp.indexForDieText, 500, 65, null);
        }
		else if (gp.indexForDieText >= 90 && gp.indexForDieText < 150){
			gp.indexForDieText +=2;
			g.drawImage(dieImg, gp.tileSize * 8, 2 * 90, 500, 65, null);
//			gp.setupGame(gp.level);
			
		}
		else {
			gp.shadingOn = true;
		}
	}

	public void drawNextLevelEffect() {
		BufferedImage img1 = GetSpriteAtlas("/ui/blackbackground.png") ;

		
		if(timeSwitch == 0) {
			if(gp.xNext1 <= 0 || gp.xNext2 <= 0) {
				timeSwitch ++;
				gp.gameState = gp.pauseState;
				gp.setupGame((gp.level + 1) % 10);
			}
			else {
				gp.xNext1-= 15;
				gp.xNext2 -= 15;
			}
		}
		else {
			if(gp.xNext1 >= gp.screenWidth/2 ) {
				gp.nextLevelEffect = false;
				gp.xNext1 = gp.screenWidth/2;
				gp.xNext2 = gp.screenWidth;
				timeSwitch = 0;
	
			}
			else {
				gp.xNext1 += 15;
				gp.xNext2 += 15;
				
			}
		}
		
		
		this.g2.drawImage(img1, 0, 0, gp.screenWidth/2 - gp.xNext1, gp.screenHeight, null);
		this.g2.drawImage(img1, gp.xNext2, 0, gp.screenWidth, gp.screenHeight, null);
	}
	
	private void drawWinning(Graphics2D g) {
		g.drawImage(thankYouImg ,0, 0, gp.screenWidth, gp.screenHeight, null);
	}


	private void drawIntro(Graphics2D g2) {
		g2.setFont(boldFont_20);
		g2.setColor(Color.BLACK);
		introO.draw(g2);
		
	}

	private void drawInfoShow(Graphics g) {
		infoImage = GetSpriteAtlas("/ui/about_us_img.png");
		g.drawImage(infoImage, -1, 0, gp.screenWidth - 0, gp.screenHeight - 0, null);
	}

	private void drawInstruction(Graphics2D g) {
		//doing something
		BufferedImage img = GetSpriteAtlas("/ui/huong_dan_text.png");
		if(gp.gameState == gp.playState) {
			g.drawImage(img, gp.tileSize * 20 + 7, gp.tileSize * 2 - 20, 193, 120, null);
		}
		
		
	}

	private void drawPlayScreen(Graphics2D g2) {
	
		//home_button
		g2.setFont(boldFontBig);
		g2.setColor(Color.WHITE);
		playO.draw(g2);
		
		//img
		BufferedImage img = GetSpriteAtlas("/ui/level_status_img.png");
		g2.drawImage(img, gp.tileSize * 2 , gp.tileSize * 1 - 40 , 190*2/3, 126*2/3, null);

		
		//text_status
		String text1 = String.format("%d", gp.level);
		String text2 = "x" + String.format("%d", 5 - gp.quantityClone );
	
		
		g2.drawString(text1, (gp.tileSize * 5 - 50), (gp.tileSize * 2 - 38));
		g2.drawString(text2, (gp.tileSize * 5 - 66), (gp.tileSize * 2 - 18));
		
	}
	

	private void drawPauseScreen(Graphics2D g2) {
		
		po.draw(g2);	
		
	}

	

			
}
