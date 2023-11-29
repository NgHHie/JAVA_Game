package MainGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Menu.IntroOverlay;
import entity.Clone;

public class KeyHandler implements KeyListener {
	public boolean upPressed, downPressed, leftPressed, rightPressed, callClone;
	private GamePanel gp;
	private IntroOverlay introO;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
		this.introO = new IntroOverlay(gp);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	//check keypressed
	@Override
	public void keyPressed(KeyEvent e) {
		if(gp.gameState == gp.introState) {
			introO.anyKeyPressed();
		}
		//Nhận đầu vào là khi nhấn nút
		int code = e.getKeyCode();
		
		
		if(code == KeyEvent.VK_W ) {
			upPressed = true;
		}
		
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		if(code == KeyEvent.VK_SPACE) {
			callClone = true;
		}
		
		if(code == KeyEvent.VK_ESCAPE) {
			gp.infoShow = false;
			gp.storyShow = false;
			
			if(gp.winAllLevel == true) {

				gp.isWinningEffect = false;
				gp.winAllLevel = false;
				gp.gameState = gp.pauseState;

			}
		}
		
		if(code == KeyEvent.VK_R) {
			if(!gp.shadingOn && !gp.nextLevelEffect && gp.isPlayerAlive) {
				gp.gameState = gp.pauseState;
				gp.setupGameWithoutPause();
			}
		}	
		
	}
	//check keyreleased
	@Override
	public void keyReleased(KeyEvent e) {
		//Khi nhả nút
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		if(code == KeyEvent.VK_SPACE) {
			callClone = false;
		}
	}
	

}
