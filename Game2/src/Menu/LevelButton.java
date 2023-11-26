package Menu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import MainGame.GamePanel;

import java.util.Random;


public class LevelButton extends Button {
	
	private GamePanel gp;
	public BufferedImage lvl_on[] = new BufferedImage[10] ;
	public BufferedImage lvl_off[] = new BufferedImage[10] ;
	public BufferedImage img1 ;
	public BufferedImage img2;
	public int rowIndex, index;
	public boolean mouseOver , mousePressed  ;

	public LevelButton(int x, int y, int width, int height, int rowIndex, GamePanel gp) {
		super(x, y, width, height);
		this.mouseOver = false;
		this.mousePressed = false;
		this.rowIndex = rowIndex;
		this.gp = gp;
		loadImgs();
		
	}

	private void loadImgs() {
		String path1 = "/level_button/bt" + 	String.format("%d", rowIndex) + ".png";
		String path2 = "/level_button/bt_x.png";
		
		img1 = GetSpriteAtlas(path1);
		img2 = GetSpriteAtlas(path2);
		

	}
	
	public BufferedImage GetSpriteAtlas(String path) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResourceAsStream(path));;

		} catch(IOException e) {
			e.printStackTrace();
		}
		return img;
	}

	public void update() {
			//nothing
	}

	
	public void draw(Graphics g) {
		
		if(gp.dataLevel[rowIndex] == 1) {
			g.drawImage(img1, x, y, 57, 55, null);
		}
		else g.drawImage(img2, x, y, 57, 54, null);
		
	}
	
	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}



}
