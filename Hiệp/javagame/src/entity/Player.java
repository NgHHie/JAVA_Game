package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import MainGame.GamePanel;
import MainGame.KeyHandler;

public class Player extends Entity {
	GamePanel gp;
	public KeyHandler keyH;
	int admit = 0;
	int cnt = 0;
	int[] st = new int[5];

	public final float screenX, screenY;

	public Player(GamePanel gp, KeyHandler keyH) {
		this.name = "Player";
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
		worldDefaultX = 8 * gp.tileSize;
		worldDefaultY = 4 * gp.tileSize - 1;
		worldX = worldDefaultX;
		worldY = worldDefaultY;
		speed = 5;
		speedY = 1;
		inAir = true;
		onGround = false;
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

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		if (admit == 0) {
			if (keyH.callClone == true && cnt < 5) {
				cnt++;
				collisionOn = false;
				Arrays.fill(collisionOn2, false);
				callClone();
				st[cnt - 1] = 600;
				admit = 30;
			}
		} else if (admit > 0)
			admit--;

		if (keyH.leftPressed == false && keyH.rightPressed == false) {
			direction = "";
		} else {
			if (keyH.leftPressed == false && keyH.rightPressed == false) {
				if (direction == "right" || direction == "left") {
					spriteNum = 1;
				}
				direction = "";
			}
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

		if (cnt < 5 && st[cnt] < 600) {
			gp.movXClone[cnt][st[cnt]] = direction;
			gp.movYClone[cnt][st[cnt]] = directionY;
			st[cnt]++;
		}

		// CHECK TILE COLLISION
		collisionOn = false;
		collisionOnY = false;
		gp.cChecker.checkTile(this);
//		System.out.println(collisionOn);

		// CHECK OBJECT COLLISIOn
		gp.cChecker.checkObject(this);

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
		if (collisionOn2[0] == true || collisionOn2[1] == true || collisionOn2[2] == true || collisionOn2[3] == true
				|| collisionOn2[4] == true) {
			if (speed < 4)
				speed += 0.5;
		} else
			speed = 5;

		spriteCounter++;
		if (spriteCounter > 8) {
			if (spriteNum == 1) {
				spriteNum = 2;
			} else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}

		if (inAir) {
			speedY += 0.75;
		}
	}

	private void jump() {
		if (inAir == true) return;
		
		inAir = true;
		onGround = false;
		speedY = -13;
	}

	public void callClone() {
		if (gp.quantityClone < 5) {
			gp.quantityClone++;
			gp.clone[gp.quantityClone - 1] = new Clone(gp);
		}
		setDefaultValues();
		gp.aSetter.setObject();
		for (int i = 0; i < 5; i++) {
			if (gp.clone[i] != null) {
				gp.clone[i].setDefaultValues();
			}
		}
		for (int i = 0; i < 5; i++) {
			gp.moveobj[i] = false;
		}
	}

	BufferedImage lastimage = down1;

	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		lastimage = down1;

		if (direction.compareTo("left") == 0 && directionY.compareTo("up") == 0) {
			if (spriteNum == 1) {
				image = down2;
			}
			if (spriteNum == 2) {
				image = down2;
			}
		} else if (direction.compareTo("right") == 0 && directionY.compareTo("up") == 0) {
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down1;
			}
		} else if (direction.compareTo("left") == 0 && directionY.compareTo("down") == 0 && onGround == false) {
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down1;
			}
		} else if (direction.compareTo("right") == 0 && directionY.compareTo("down") == 0 && onGround == false) {
			if (spriteNum == 1) {
				image = down2;
			}
			if (spriteNum == 2) {
				image = down2;
			}
		} else if (direction.compareTo("left") == 0 && directionY.compareTo("down") == 0 && onGround == true) {
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
		} else if (direction.compareTo("right") == 0 && directionY.compareTo("down") == 0 && onGround == true) {
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
		} else if (direction.compareTo("") == 0 && directionY.compareTo("up") == 0) {
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down1;
			}
		} else if (direction.compareTo("") == 0 && directionY.compareTo("down") == 0) {
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down1;
			}
		} else
			image = lastimage;

		g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
	}

}
