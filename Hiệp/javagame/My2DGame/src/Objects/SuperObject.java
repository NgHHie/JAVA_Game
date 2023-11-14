package Objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.LayoutStyle;
import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;

import MainGame.GamePanel;
import entity.Entity;
import entity.Player;

public class SuperObject {
	
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public boolean slideOn = false;
	public int worldX, worldY;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	public float speedobj;
	public String direction;
	public String pastdirection = "";
	KeyHandler keyH;
	
	public void draw(Graphics2D g2, GamePanel gp) {
		int screenX = worldX ;
		int screenY = worldY ;
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
	
	public void update(GamePanel gp) {
//		int j = gp.objmoved;
		int k = gp.sttobj;
		if(gp.player != null)
		if(gp.player.worldY > gp.obj[k].worldY - 48
				&& gp.player.worldY < gp.obj[k].worldY + 32) {
			if(gp.player.worldX > gp.obj[k].worldX
					&& gp.player.worldX < gp.obj[k].worldX + 40
					&& (gp.player.direction.compareTo("left") == 0
					|| gp.player.direction == ""))
				gp.player.worldX = gp.obj[k].worldX + 40;
			else if(gp.player.worldX < gp.obj[k].worldX
					&& gp.player.worldX > gp.obj[k].worldX - 40
					&& (gp.player.direction.compareTo("right") == 0
					|| gp.player.direction == ""))
				gp.player.worldX = gp.obj[k].worldX - 40;	
		}

		for(int i=0; i<5; i++) {
			if(gp.clone[i] != null) {
				if(gp.clone[i].worldY > gp.obj[k].worldY - 48
						&& gp.clone[i].worldY < gp.obj[k].worldY + 32) {
					if(gp.clone[i].worldX > gp.obj[k].worldX
							&& gp.clone[i].worldX < gp.obj[k].worldX + 40
							&& (gp.clone[i].direction.compareTo("left") == 0
							|| gp.clone[i].direction == "")) {
						gp.clone[i].worldX = gp.obj[k].worldX + 40;
					}
					else if(gp.clone[i].worldX < gp.obj[k].worldX
							&& gp.clone[i].worldX > gp.obj[k].worldX - 40
							&& (gp.clone[i].direction.compareTo("right") == 0
							|| gp.clone[i].direction == "")) {
						gp.clone[i].worldX = gp.obj[k].worldX - 40;
					}		
				}
			}
		}
		
		//Sửa: phải duyệt những hộp ở cạnh hộp được đẩy trước, không thể duyệt lần lượt
		for(int i=0; i<5; i++) {
			if(gp.obj[i] != null && i != k) {
				if(gp.obj[i].worldY > gp.obj[k].worldY - 48
						&& gp.obj[i].worldY < gp.obj[k].worldY + 48) {
					if(gp.obj[i].worldX > gp.obj[k].worldX
							&& gp.obj[i].worldX < gp.obj[k].worldX + 48) {
						gp.obj[i].worldX = gp.obj[k].worldX + 48;
					}
					else if(gp.obj[i].worldX < gp.obj[k].worldX
							&& gp.obj[i].worldX > gp.obj[k].worldX - 48) {
						gp.obj[i].worldX = gp.obj[k].worldX - 48;
					}	
				}
			}
		}
		

		
	}

}
