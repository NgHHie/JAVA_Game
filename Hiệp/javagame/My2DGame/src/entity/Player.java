package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import MainGame.GamePanel;
import MainGame.KeyHandler;

public class Player extends Entity{
	GamePanel gp;
	public KeyHandler keyH;
	int admit = 0;
	int cnt = 0;
	int st = 0;
	
	public final float screenX, screenY;

	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
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
	}
	public void getPlayerImages() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		if(admit == 0) {
			if(keyH.callClone == true) {
				callClone();
				st = 600;
				admit = 30;
			}
		}
		else if(admit > 0) admit --;
		
		if(keyH.upPressed == false && keyH.downPressed == false && 
				keyH.leftPressed == false && keyH.rightPressed == false) {
			direction = "";
			directionY = "";
			cnt = 0;
			if(st < 600) {
				gp.movXClone[st] = direction;
				gp.movYClone[st] = directionY;
				st ++;
			}
			return;
		}
		else {
			if(keyH.leftPressed == false && keyH.rightPressed == false) {
				if(direction == "right" || direction == "left") {
					spriteNum = 1;
				}
				direction = "";	
			}
			if(keyH.upPressed == false && keyH.downPressed == false ) {
				if(directionY == "up" || directionY == "down") {
					spriteNum = 1;
				}
				directionY = "";
		
			}
		}

		
		if(keyH.upPressed == true) {
			directionY = "up";
		}
		else if(keyH.downPressed == true) {
			directionY = "down";
		}
		if(keyH.leftPressed == true) {
			direction = "left";		
		}
		else if(keyH.rightPressed == true) {
			direction = "right";	
		}	
		
		if(st < 600) {
			gp.movXClone[st] = direction;
			gp.movYClone[st] = directionY;
			st ++;
		}
		
		//CHECK TILE COLLISION
		collisionOn = false;
		gp.cChecker.checkTile(this);
		
		//CHECK OBJECT COLLISIOn
		gp.cChecker.checkObject(this, true);

		//IF COLLISION IS FALSE, PLAYER CAN MOVE
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

		if(collisionOn2 == true) {
			if(speed < 4) speed += 0.5;
		}
		else speed = 5;
		
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
	
	public void callClone() {
		if(gp.quantityClone <= 5) {
			gp.quantityClone ++;
			gp.clone[gp.quantityClone-1] = new Clone(gp);
		}
	}

	BufferedImage lastimage = down1;

	public void draw(Graphics2D g2) {  
		BufferedImage image = null;
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
