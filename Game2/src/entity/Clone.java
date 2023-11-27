package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import MainGame.GamePanel;


public class Clone extends Entity{
	private int st = 0;

	public Clone(GamePanel gp) {
		super(gp);
		this.name = "Clone";
		
		this.worldDefaultX = gp.player.worldDefaultX;
		this.worldDefaultY = gp.player.worldDefaultY;
		this.worldX = this.worldDefaultX;
		this.worldY = this.worldDefaultY;
		
		getPlayerImages();
	}
	
	protected void setDefaultValues() {
		super.setDefaultValues();
		st = 0;
	}

	private void getPlayerImages() {
		try {
			imageidle = ImageIO.read(getClass().getResourceAsStream("/player/clone_idle.png"));
			imageleft = ImageIO.read(getClass().getResourceAsStream("/player/clone_run_left.png"));
			imageright = ImageIO.read(getClass().getResourceAsStream("/player/clone_run.png"));
			imagedead = ImageIO.read(getClass().getResourceAsStream("/player/clone_dead.png"));

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
	}
	
	public void update() {
		if(dead == true) {
			Arrays.fill(collisionOn2, false);
			direction = "";
//			return;
		}
		else {
			if(st < 600000 && gp.movXClone[gp.stt][st] != null) {
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
				speedY = -13;
			}
			
			if(speedY < 0) {
				directionY = "up";
			}
			else if(speedY > 0) directionY = "down";
		}
		//CHECK TILE COLLISION
		collisionOn = false;
		collisionOnY = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this);
		
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
			if(speed < 4) speed += 0.5;
		}
		else speed = 5;
		

		if(inAir) {
			speedY += 0.75;
		}
	}

	
}
