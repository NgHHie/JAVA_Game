package MainGame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Menu.PauseOverlay;
import Menu.PlayOverlay;

public class MouseHandler implements MouseListener, MouseMotionListener{
	
	private GamePanel gp;
	private PauseOverlay op ;
	private PlayOverlay playO;
	
	
	public MouseHandler(GamePanel gp) {
		this.gp = gp;
		this.op = new PauseOverlay(gp);
		this.playO = new PlayOverlay(gp);
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		if(gp.gameState == gp.pauseState) {
			op.mouseMoved(e);
			
		}
		else if(gp.gameState == gp.playState){
			playO.mouseMoved(e);
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(gp.soundOn) gp.playSE(0, 1);
		
		if(gp.gameState == gp.pauseState) {
//			System.out.print("click");
			op.mousePressed(e);
			
		}
		else if(gp.gameState == gp.playState){
			playO.mousePressed(e);
		}
	}
	
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(gp.gameState == gp.pauseState) {
//			op.mouseReleased(e);
			
		}
		else if(gp.gameState == gp.playState){
			playO.mousePressed(e);
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
	
		
	}
}
