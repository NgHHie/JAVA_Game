package Objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.LayoutStyle;
import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;

import MainGame.GamePanel;
import entity.Entity;
import entity.Player;

public class SuperObject {
	protected GamePanel gp;
	protected BufferedImage image;
	public String name;
	public boolean collision = false;
	public boolean slideOn = false;
	public int worldX, worldY, defaultWorldX, defaultWorldY;
	public Rectangle solidArea;
	public int solidAreaDefaultX;
	public int solidAreaDefaultY;
	public float speedobj;
	public String direction;
	public String directionY;
//	public String pastdirection = "";

	public boolean collisionOn, collisionOnY, onGround, inAir;
	public float speedY;
	
	public SuperObject(GamePanel gp) {
		this.gp = gp;
	}

	public void draw(Graphics2D g2, GamePanel gp) {
		int screenX = worldX;
		int screenY = worldY;

		g2.drawImage(image, screenX, screenY, this.solidArea.width, this.solidArea.height, null);
	}

	public void update() {
		// TODO Auto-generated method stub

	}
	
	

	public void setElevator(Elevator ele, String s) {
		if (s.compareTo("hor") == 0)
			ele.horizontal = true;
		else
			ele.vertical = true;
	}

}
