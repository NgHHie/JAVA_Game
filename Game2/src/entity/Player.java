package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import MainGame.GamePanel;
import MainGame.KeyHandler;

public class Player extends Entity {
	private KeyHandler keyH;
	private int admit;
	private int[] st = new int[5];
	private int cnt;

	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.name = "Player";
		this.keyH = keyH;
		this.admit = 0;
		this.cnt = 0;
		this.dead = false;
		this.reborn = true;

		getPlayerImages();
	}

	private void getPlayerImages() {
		try {
			imageidle = ImageIO.read(getClass().getResourceAsStream("/player/Idle.png"));
			imageleft = ImageIO.read(getClass().getResourceAsStream("/player/RunLeft.png"));
			imageright = ImageIO.read(getClass().getResourceAsStream("/player/Run.png"));
			imagedead = ImageIO.read(getClass().getResourceAsStream("/player/Dead.png"));
			imageborn = ImageIO.read(getClass().getResourceAsStream("/player/ReBorn.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		animation = new BufferedImage[6][10];
		
		imgidle = new BufferedImage[5];
		for(int i=0; i<imgidle.length; i++) {
			imgidle[i] = imageidle.getSubimage(i*128 + 28, 128 - 70, 70, 70);
			animation[0][i] = imgidle[i];
		}
		
		imgleft = new BufferedImage[8]; 
		for(int i=0; i<imgleft.length; i++) {
			imgleft[i] = imageleft.getSubimage(i*128 + 28, 128 - 70, 70, 70);
			animation[1][i] = imgleft[i];
		}
		
		imgright = new BufferedImage[8]; 
		for(int i=0; i<imgright.length; i++) {
			imgright[i] = imageright.getSubimage(i*128 + 28, 128 - 70, 70, 70);
			animation[2][i] = imgright[i];
		}
		
		imgdead = new BufferedImage[10];
		for(int i=0; i<imgidle.length; i++) {
			imgdead[i] = imagedead.getSubimage(i*128 + 28, 128 - 70, 70, 70);
			animation[3][i] = imgdead[i];
		}
		
		imgborn = new BufferedImage[8]; 
		for(int i=0; i<imgleft.length; i++) {
			imgborn[i] = imageborn.getSubimage(i*128 + 28, 128 - 70, 70, 70);
			animation[4][i] = imgborn[i];
		}
	}

	public void update() {
		if(reborn == true) return;
		if(dead == true) {
			Arrays.fill(collisionOn2, false);
			direction = "";
			keyH = null;
//			return;
		}
		if(keyH != null) {
			if (admit == 0) {
				if (keyH.callClone == true && cnt < 5) {
					cnt++;
					collisionOn = false;
					Arrays.fill(collisionOn2, false);
					callClone();
					st[cnt - 1] = 600000;
					admit = 30;
				}
			} else if (admit > 0)
				admit--;
	
			if (keyH.leftPressed == false && keyH.rightPressed == false) {
				direction = "";
			} 
	
			if (direction == null)
				direction = "";
			if (directionY == null)
				directionY = "";
	
			if (keyH.upPressed == true) {
				jump();
			}
			if (speedY < 0) {
				directionY = "up";
			} else if (speedY > 0)
				directionY = "down";
	
			if (keyH.leftPressed == true) {
				direction = "left";
			} else if (keyH.rightPressed == true) {
				direction = "right";
			}
	
			if (cnt < 5 && st[cnt] < 600000) {
				gp.movXClone[cnt][st[cnt]] = direction;
				gp.movYClone[cnt][st[cnt]] = directionY;
				st[cnt]++;
			}
		}
		// CHECK TILE COLLISION
		collisionOn = false;
		collisionOnY = false;

		// CHECK OBJECT COLLISIOn
		gp.cChecker.checkObject(this);
		gp.cChecker.checkTile(this);

		// IF COLLISION IS FALSE, PLAYER CAN MOVE
		if (collisionOn == false) {
			switch (direction) {
			case "left":
				worldX -= (int) speed;
				break;
			case "right":
				worldX += speed;
				break;
			}
		}
		if (collisionOnY == false) {
			inAir = true;
			switch (directionY) {
			case "up":
				worldY += (int) speedY;
				break;
			case "down":
				worldY += (int) speedY;
				break;
			}
		}
		if (collisionOn2[0] == true || collisionOn2[1] == true 
				|| collisionOn2[2] == true || collisionOn2[3] == true
				|| collisionOn2[4] == true) {
			if (speed < 4)
				speed += 0.5;
		} else
			speed = 5;

		if (inAir) {
			speedY += 0.75;
		}
	}

	private void jump() {
		if (inAir == true) return;
		if(gp.soundOn) gp.playSE(1, 2);
		
		inAir = true;
		onGround = false;
		speedY = -13;
	}

	private void callClone() {
		reborn = true;
		aniIndex = 0;
		aniTick = 0;
		aniSpeed = 5;
		if (gp.quantityClone < 5) {
			gp.quantityClone++;
			gp.clone[gp.quantityClone - 1] = new Clone(gp);
		}
		setDefaultValues();
		gp.aSetter.set();
		for (int i = 0; i < 5; i++) {
			if (gp.clone[i] != null && gp.clone[i].dead == false) {
				gp.clone[i].setDefaultValues();
			}
		}
		for (int i = 0; i < 5; i++) {
			gp.moveobj[i] = false;
		}
	}
	


}




