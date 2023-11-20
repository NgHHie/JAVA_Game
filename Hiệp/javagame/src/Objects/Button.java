package Objects;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import MainGame.GamePanel;

public class Button extends SuperObject{
	GamePanel gp;
	public boolean put;
	
	public Button(GamePanel gp) {
		this.gp = gp;
		this.put = false;
		this.name = "Button";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/button.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		solidArea = new Rectangle(0, 0, 48, 48);
	}
	public void update() {
		this.put = checkPlayer();
//		checkBox();
	}
	
	private boolean checkPlayer() {
		if(gp.player != null) {
			 gp.player.solidArea.x += gp.player.worldX;
			 gp.player.solidArea.y += gp.player.worldY;
			 this.solidArea.x = this.worldX + this.solidArea.x;
			 this.solidArea.y = this.worldY + this.solidArea.y;
			 
			 if(gp.player.solidArea.intersects(this.solidArea)) {
				 this.collision = true;
			 }
			 else this.collision = false;
			 
			 gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			 gp.player.solidArea.y = gp.player.solidAreaDefaultY;
			 this.solidArea.x = this.solidAreaDefaultX;
			 this.solidArea.y = this.solidAreaDefaultY; 
			 
			 if(this.collision == true) return true;
			
		}
		for(int i=0; i<5; i++) {
			if(gp.clone[i] != null) {
				 gp.clone[i].solidArea.x += gp.clone[i].worldX;
				 gp.clone[i].solidArea.y += gp.clone[i].worldY;
				 this.solidArea.x = this.worldX + this.solidArea.x;
				 this.solidArea.y = this.worldY + this.solidArea.y;
				 
				 if(gp.clone[i].solidArea.intersects(this.solidArea)) {
					 this.collision = true;
				 }
				 else this.collision = false;
				 
				 gp.clone[i].solidArea.x = gp.clone[i].solidAreaDefaultX;
				 gp.clone[i].solidArea.y = gp.clone[i].solidAreaDefaultY;
				 this.solidArea.x = this.solidAreaDefaultX;
				 this.solidArea.y = this.solidAreaDefaultY; 
				 
				 if(this.collision == true) return true;
			}
		}
		this.collision = false;
		return false;
	}
}
