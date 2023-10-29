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
		direction = "down";
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
		if(keyH.upPressed == false && keyH.downPressed == false &&
				keyH.leftPressed == false && keyH.rightPressed == false) {
			if(direction == "right" || direction == "left") {
				spriteNum = 1;
			}
			direction = "";
			return;
		}
		
		if(keyH.upPressed == true) {
			direction = "up";
		}
		else if(keyH.downPressed == true) {
			direction = "down";
		}
		else if(keyH.leftPressed == true) {
			direction = "left";		
		}
		else if(keyH.rightPressed == true) {
			direction = "right";	
		}	
		
		//CHECK TILE COLLISION
		collisionOn = false;
		gp.cChecker.checkTile(this);
		
		//CHECK OBJECT COLLISION
//		int objIndex = gp.cChecker.checkObject(this, true);
//		pickUpObject(objIndex);
//		collisionOn2 = false;
		boolean check = false;
		if(collisionOn2 == false){
			gp.cChecker.checkObject(this, true);
			if(collisionOn2 == true) check = true;
		}
		else {
			gp.cChecker.checkObject(this, true);
		}
		
//		if(collisionOn2 == true) {
//			speed = 1;
//		}
//		else speed = 4;
		//IF COLLISION IS FALSE, PLAYER CAN MOVE
		if(collisionOn == false) {
			switch(direction) {
			case "up":
				worldY -= speed;
				break;
			case "down":
				worldY += speed;
				break;
			case "left":
				worldX -= (int)speed;
				break;
			case "right":
				worldX += speed;
				break;
			}
		}
//		if(check == true) speed = 0;
		if(collisionOn2 == true) {
			if(speed < 4) speed += 0.5;
		}
		else speed = 5;

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
	
//	public void pickUpObject(int i) {
//		if(i != 999) {
//			gp.obj[i] = null;
//		}
//	}
//	
//	BufferedImage image = null;
	BufferedImage lastimage = down1;

	public void draw(Graphics2D g2) {
	//	g2.setColor(Color.white);	
	//	g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		BufferedImage image = null;
//		BufferedImage lastimage = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
				lastimage = image;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
				lastimage = image;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
				lastimage = image;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
				lastimage = image;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		case "":
			image = lastimage;
		}
		g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
	}
	
}
