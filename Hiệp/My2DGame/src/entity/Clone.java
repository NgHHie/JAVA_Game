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
		worldX = gp.player.worldDefaultX;
		worldY = gp.player.worldDefaultY;
		speed = 5;
		speedY = 1;
		inAir = true;
		onGround = false;
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
		if(st < 600 && gp.movXClone[gp.stt][st] != null) {
			direction = gp.movXClone[gp.stt][st];
			directionY = gp.movYClone[gp.stt][st];
			st ++;
		}
		else {
			direction = "";
			directionY = "down";
		}
		
		if(inAir == false && directionY.compareTo("up") == 0) {
			inAir = true;
			onGround = false;
			speedY = -15;
		}
		
		//CHECK TILE COLLISION
		collisionOn = false;
		collisionOnY = false;
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
		}
		if(collisionOnY == false) {
			inAir = true;
			switch(directionY) {
			case "up":
				worldY += (int)speedY;
				break;
			case "down":
				worldY += (int)speedY;
				break;
			}
		}
	
		if(collisionOn2[0] == true || collisionOn2[1] == true || collisionOn2[2] == true || 
				collisionOn2[3] == true || collisionOn2[4] == true) {
			if(speed < 4) speed += 0.25;
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
		if(inAir) {
			speedY += 1;
		}
	}

	
	BufferedImage lastimage = down1;

	public void draw(Graphics2D g2) {  
		BufferedImage image = null;
		lastimage = down1;
		

		if(direction.compareTo("left") == 0 && directionY.compareTo("up") == 0) {
			if(spriteNum == 1) {
				image = down2;
			}
			if(spriteNum == 2) {
				image = down2;
			}
		}
		else if(direction.compareTo("right") == 0 && directionY.compareTo("up") == 0) {
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down1;
			}
		}
		else if(direction.compareTo("left") == 0 && directionY.compareTo("down") == 0 && onGround == false) {
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down1;
			}
		}
		else if(direction.compareTo("right") == 0 && directionY.compareTo("down") == 0 && onGround == false) {
			if(spriteNum == 1) {
				image = down2;
			}
			if(spriteNum == 2) {
				image = down2;
			}
		}
		else if(direction.compareTo("left") == 0 && directionY.compareTo("down") == 0 && onGround == true) {
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
		}
		else if(direction.compareTo("right") == 0 && directionY.compareTo("down") == 0 && onGround == true) {
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
		}
		else if(direction.compareTo("") == 0 && directionY.compareTo("up") == 0) {
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down1;
			}
		}
		else if(direction.compareTo("") == 0 && directionY.compareTo("down") == 0) {
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down1;
			}
		}
		else image = lastimage;

		
		g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
	}
	
	
	
}
