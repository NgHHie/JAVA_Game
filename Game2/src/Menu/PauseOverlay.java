package Menu;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import MainGame.GamePanel;


public class PauseOverlay {
	private GamePanel gp;
	private BufferedImage backgroundImg, selectImg;
	private LevelButton[] level_button;
	
	private PauseButton info_button, story_button, sound_button_on, sound_button_off;
	private Graphics2D g2;
	private boolean check[] = new boolean[10];

	public PauseOverlay(GamePanel gp) {
		this.gp = gp;
		this.level_button = new LevelButton[10];
		loadBackground();
		createUrmButtons();
	}



	private void createUrmButtons() {
		
		int x1 = 350;
		int y1 = 400;
		for(int i = 0; i< 5; i++) {
			level_button[i] = new LevelButton(x1 + (i % 5) * 100 , y1, 50, 50, i, gp);
		}
		
		for(int i = 5; i< 10; i++) {
			level_button[i] = new LevelButton(x1 + (i % 5)  * 100 , y1 + 75, 50, 50, i, gp);
		}
		
		//250
		story_button = new PauseButton(x1  - 25, y1 + 240, 100, 50, "/ui/story_btn.png");
		info_button = new PauseButton(x1 + 162, y1 + 240, 131, 50, "/ui/about_us.png");
		
		sound_button_on = new PauseButton(x1 + 380, y1 + 240, 100, 50, "/ui/mute.png");
		sound_button_off = new PauseButton(x1 + 380, y1 + 240, 100, 50, "/ui/mute_x.png");

	}

	private void loadBackground() {
		backgroundImg = GetSpriteAtlas("/ui/final menu.png");
//		selectImg = GetSpriteAtlas("/level_button/back_text.png");
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
		for(int i = 0; i< 10; i++) {
			level_button[i].update();
		}
		info_button.update();
		story_button.update();
		sound_button_off.update();
		sound_button_on.update();

	}

	public void draw(Graphics g) {
		// Background
		int x1 = (gp.screenWidth - gp.tileSize * 6) / 2 ;
		int y1 = (gp.screenHeight - gp.tileSize * 6) / 2 ;

		g.drawImage(backgroundImg, 0, -1, gp.screenWidth + 5, gp.screenHeight, null);
		
		//5 Level Buttons
		for(int i = 0; i< 10; i++) {
			
			level_button[i].draw(g);
		}
		
		//pauseButton
		info_button.draw(g);
		story_button.draw(g);
		
		//sound
		if(gp.soundOn) {
			sound_button_on.draw(g);
		}
		else {
			sound_button_off.draw(g);
		}
		


	}

	public void mousePressed(MouseEvent e) {
		
		
		//level
		for(int i = 0; i< 10; i++) {
			if (isIn(e, level_button[i]) && !gp.infoShow && gp.dataLevel[i] == 1 && !gp.shadingOn && !gp.storyShow) {
				gp.level = i;
				gp.shadingOn = true;
				
//				gp.setupGame(i);

			}
		}

		//info
		if (isIn(e, info_button) && !gp.shadingOn && !gp.storyShow) {
			gp.infoShow = true;
			
		}
		
		
		//sound
		if (isIn(e, sound_button_on) && gp.soundOn && !gp.infoShow && !gp.shadingOn && !gp.storyShow) {
			gp.soundOn = false;
			gp.switchMusic();
		}
		else if (isIn(e, sound_button_off) && !gp.soundOn && !gp.infoShow && !gp.shadingOn && !gp.storyShow) {
			gp.soundOn = true;
			gp.switchMusic();
		}

		//quit
		if (isIn(e, story_button) && !gp.infoShow) {
			
			gp.storyShow = true;
	
		}
		
	}


	public void mouseMoved(MouseEvent e) {
		
		boolean switchMouseType = true;
		for(int i = 0; i< 10; i++) {
			
			if (isIn(e, level_button[i]) && !gp.infoShow) {
				switchMouseType = false;
				gp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			}	
			else {
		
			}
		}
		
		if (isIn(e, info_button) || isIn(e, story_button) 
				|| isIn(e, sound_button_on) || isIn(e, sound_button_off)) {
			switchMouseType = false;
			gp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		
		

		if (switchMouseType) {
			gp.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

		}
	}
	

	private boolean isIn(MouseEvent e, Button b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}

}