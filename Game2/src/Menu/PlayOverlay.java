package Menu;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.Timer;

import javax.imageio.ImageIO;

import MainGame.GamePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayOverlay {
	private GamePanel gp;
	private Timer timer;

	private MenuButton menu_button, reset_button , ins_button;


	public PlayOverlay(GamePanel gp) {
		this.gp = gp;
		
		loadBackground();
		createButtons();
		
		//khởi tạo Timer
		timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gp.popUp = false;
                timer.stop();
                
            }
        }); 
	}

	private void createButtons() {
		
		int x1 = (gp.screenWidth  - 90);
		int y1 = 20;
		
		menu_button = new MenuButton(x1 , y1, 70, 70, "/ui/home_button.png");
		reset_button = new MenuButton(x1 - 70, y1, 70, 70, "/ui/restart_button.png");
		ins_button = new MenuButton(x1 - 140, y1, 70, 70, "/ui/huong_dan.png");
		

	}

	private void loadBackground() {
		//nothing
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
	public void draw(Graphics g) {

		//menu_button
		menu_button.draw(g);
		
		//reset_button
		reset_button.draw(g);
		
		//instructions_button
		ins_button.draw(g);
	}

	public void mousePressed(MouseEvent e) {

		if (isIn(e, menu_button) && gp.gameState == gp.playState && !gp.shadingOn && gp.isPlayerAlive) {
			gp.gameState = gp.pauseState;
		}
		
		else if (isIn(e, reset_button) && gp.gameState == gp.playState && !gp.shadingOn && gp.isPlayerAlive) {

			gp.gameState = gp.pauseState;
			gp.setupGameWithoutPause();
		}
		
		else if (isIn(e, ins_button) && gp.gameState == gp.playState && !gp.shadingOn && gp.isPlayerAlive) {
			gp.popUp = true;

            // Bắt đầu timer khi flag được đặt thành true
            timer.start();
		
		}

		
		//chuyen trang thai thi dung
		if(gp.gameState == gp.pauseState) {
			gp.popUp = false;
		}
	}

	public void mouseMoved(MouseEvent e) {
		
			if ((isIn(e, menu_button ) || isIn(e, ins_button )
					|| isIn(e, reset_button ) ) && gp.gameState == gp.playState) {
				gp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				
			}
			
			if (!isIn(e, menu_button ) && gp.gameState == gp.playState && !isIn(e, reset_button )){
				gp.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			
		}

	private boolean isIn(MouseEvent e, Button b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}

}