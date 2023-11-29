package entity;

import java.awt.Graphics2D;
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
	
	protected BufferedImage[][] animation;
	protected BufferedImage[] imgidle, imgleft, imgright, imgup, imgdown, imgdead, imgborn;
	protected BufferedImage imageidle, imageleft, imageright, imageup, imagedown, imagedead, imageborn;
	public int anicondition, aniTick, aniIndex, aniSpeed = 5;
	
	public String direction;
	public String directionY;
	public Rectangle solidArea;
	public boolean collisionOn = false;
	public boolean collisionOnY = false;
	public boolean[] collisionOn2 = new boolean[20];
	
	public int solidAreaDefaultX, solidAreaDefaultY;
	public int worldDefaultX;
	public int worldDefaultY;
	public boolean dead;
	public boolean reborn;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
		this.dead = false;
		this.solidArea = new Rectangle(8, 16, 32, 32);
		this.solidAreaDefaultX = solidArea.x;
		this.solidAreaDefaultY = solidArea.y;
		
		setDefaultValues();
	}
	
	protected void setDefaultValues() {
		worldX = worldDefaultX;
		worldY = worldDefaultY;
		speed = 5;
		speedY = 1;
		inAir = true;
		onGround = false;
		direction = "";
		directionY = "";
		this.aniTick = 0;
		this.aniIndex = 0;
	}
	
	public int imageCount(int condition) {
		if(condition == 0) return 5;
		else if(condition == 1) return 8;
		else if(condition == 2) return 8;
		else if(condition == 3) return 10;
		else if(condition == 4) return 8;
		return 0;
	}
	
	public void updateAnimationTick() {
		aniTick ++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex ++;
		}
		if(aniIndex >= imageCount(anicondition) && anicondition != 3) {
			aniIndex = 0;
		}
		if(anicondition == 3 && aniIndex == 10) {
			aniIndex = 9;
		}
	}
	
	public void draw(Graphics2D g2) {
		if(reborn == true && aniIndex < 7) anicondition = 4;	
		else {
			reborn = false;
			aniSpeed = 12;
		}
		
		if(reborn == false) {
		if(direction.compareTo("") == 0 || direction == null) anicondition = 0;
		else if(direction.compareTo("left") == 0) anicondition = 1;
		else if(direction.compareTo("right") == 0) anicondition = 2;
		
		if(dead == true) anicondition = 3;
		}
		updateAnimationTick();
//		System.out.println(anicondition);

		g2.drawImage(animation[anicondition][aniIndex], worldX, worldY, gp.tileSize, gp.tileSize, null);
	}
}
