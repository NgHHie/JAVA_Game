package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import MainGame.GamePanel;

public class Entity {
	protected GamePanel gp;
	public String name;
	public int worldX, worldY;
	public float speed;
	public float speedY;
	public boolean inAir;
	public boolean moveObj;
	public boolean onGround;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	public String directionY;
	
	protected int spriteCounter = 0;
	protected int spriteNum = 1;
	public Rectangle solidArea;
	public boolean collisionOn = false;
	public boolean collisionOnY = false;
	public boolean[] collisionOn2 = new boolean[5];
	
	
	public int solidAreaDefaultX, solidAreaDefaultY;
	public int worldDefaultX;
	public int worldDefaultY;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
		
		solidArea = new Rectangle(8, 16, 32, 32);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		setDefaultValues();
	}
	
	protected void setDefaultValues() {
//		worldDefaultX = ;
//		worldDefaultY;
		worldX = worldDefaultX;
		worldY = worldDefaultY;
		speed = 5;
		speedY = 1;
		inAir = true;
		onGround = false;
		direction = "";
		directionY = "";
	}
}
