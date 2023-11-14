package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import MainGame.GamePanel;


public class Clone extends Entity{
	GamePanel gp;
	int st = 0;
	public boolean exist = true;
//	int timeExist = 600;
	
	public final float screenX, screenY;
	

	public Clone(GamePanel gp) {
		this.gp = gp;
		
		screenX = gp.screenWidth / 2 - gp.tileSize / 2;
		screenY = gp.screenHeight / 2 - gp.tileSize / 2;
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImages();
	}
	public void setDefaultValues() {
		worldX = 100;
		worldY = 100;
		speed = 5;
		direction = "";
		directionY = "";
		st = 0;
//		timeExist = 600;
	}
	public void getPlayerImages() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/clone_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/clone_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/clone_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/clone_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/clone_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/clone_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/clone_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/clone_right_2.png"));

		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
//		timeExist --;
//		if(timeExist == 0) exist = false;
		if(st < 600 && gp.movXClone[gp.stt][st] != null) {
			direction = gp.movXClone[gp.stt][st];
			directionY = gp.movYClone[gp.stt][st];
			st ++;
			if(direction == "" && directionY == "") {
				return;
			}
		}
		else {
			direction = "";
			directionY = "";
			return;
		}
		
		//CHECK TILE COLLISION
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, true);
		
		if(collisionOn == false) {
			switch(direction) {
			case "left":
				worldX -= (int)speed;
				break;
			case "right":
				worldX += speed;
				break;
			}
			switch(directionY) {
			case "up":
				worldY -= (int)speed;
				break;
			case "down":
				worldY += speed;
				break;
			}
		}
	
		if(collisionOn2[0] == true || collisionOn2[1] == true || collisionOn2[2] == true || 
				collisionOn2[3] == true || collisionOn2[4] == true) {
			if(speed < 4) speed += 0.5;
		}
		else speed = 5;
//		System.out.println(direction);

//		System.out.println(speed);
		
		spriteCounter ++;
		if(spriteCounter > 8) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}

	
	BufferedImage lastimage = down1;

	public void draw(Graphics2D g2) {  
		BufferedImage image = null;
//		BufferedImage lastimage = null;
		if(direction == "left") lastimage = left1;
		else lastimage = down1;
		
		switch(direction) {
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		case "":
			image = lastimage;
			break;
		}
		
		switch(directionY) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		}
		if(direction == "" && directionY == "") image = lastimage;
		
		g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
	}
	
	
	
}
