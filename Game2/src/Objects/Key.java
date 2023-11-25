package Objects;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import MainGame.GamePanel;

public class Key extends SuperObject {

	public Key(GamePanel gp) {
		super(gp);
		this.inAir = true;
		this.onGround = false;
		this.speedY = 1;
		this.name = "Key";
		try {
			this.image = ImageIO.read(getClass().getResourceAsStream("/ui/scibox.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		this.collision = false;
		this.solidArea = new Rectangle(0, 0, 48, 48);
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
	}

	public void update() {
		if (this.speedY < 0) {
			this.directionY = "up";
		} else if (this.speedY > 0)
			this.directionY = "down";
		this.collisionOn = false;
		this.collisionOnY = false;
		gp.cChecker.checkTileObject(this);
		gp.cChecker.checkObwOb(this, gp.sttobj);

		// IF COLLISION IS FALSE, PLAYER CAN MOVE
		if (this.collisionOn == false) {
			switch (this.direction) {
			case "left":
				this.worldX -= (int) this.speedobj;
				break;
			case "right":
				this.worldX += this.speedobj;
				break;
			}
		}
		if (this.collisionOnY == false) {
			this.inAir = true;
			switch (this.directionY) {
			case "up":
				this.worldY += (int) this.speedY;
				break;
			case "down":
				this.worldY += (int) this.speedY;
				break;
			}
		}

		if (this.inAir) {
			this.speedY += 1;
		}

		if (gp.player != null) {
			if (this.inAir == true) {
				if (gp.player.worldY >= this.worldY && gp.player.worldY <= this.worldY + 16) {
					if (gp.player.worldX >= this.worldX - 40 && gp.player.worldX <= this.worldX + 40) {
						gp.player.worldY = this.worldY + 33;
						this.speedY = 3;
						gp.player.speedY = this.speedY;
						gp.player.directionY = "down";
						if (gp.player.onGround == true) {
							gp.player = null;
						}
					}
				}
			}
		}
		if (gp.player != null) {
			if (gp.player.worldY >= this.worldY && gp.player.worldY <= this.worldY + 16) {
				if (gp.player.worldX >= this.worldX && gp.player.worldX <= this.worldX + 40
						&& (gp.player.direction.compareTo("left") == 0 || gp.player.direction == ""))
					gp.player.worldX = this.worldX + 40;
				else if (gp.player.worldX <= this.worldX && gp.player.worldX >= this.worldX - 40
						&& (gp.player.direction.compareTo("right") == 0 || gp.player.direction == ""))
					gp.player.worldX = this.worldX - 40;
			}
		}

		for (int i = 0; i < 5; i++) {
			if (gp.clone[i] != null) {
				if (this.inAir == true) {
					if (gp.clone[i].worldY >= this.worldY && gp.clone[i].worldY <= this.worldY + 16) {
						if (gp.clone[i].worldX >= this.worldX - 40 && gp.clone[i].worldX <= this.worldX + 40) {
							gp.clone[i].worldY = this.worldY + 33;
							gp.clone[i].speedY = this.speedY;
							gp.clone[i].directionY = "down";
							System.out.println(gp.clone[i].onGround);
							if (gp.clone[i].onGround == true) {
								gp.clone[i] = null;
							}
						}
					}
				}
			}
			if (gp.clone[i] != null) {
				if (gp.clone[i].worldY >= this.worldY && gp.clone[i].worldY <= this.worldY + 16) {
					if (gp.clone[i].worldX >= this.worldX && gp.clone[i].worldX <= this.worldX + 40
							&& (gp.clone[i].direction.compareTo("left") == 0 || gp.clone[i].direction == "")) {
						gp.clone[i].worldX = this.worldX + 41;
					} else if (gp.clone[i].worldX <= this.worldX && gp.clone[i].worldX >= this.worldX - 40
							&& (gp.clone[i].direction.compareTo("right") == 0 || gp.clone[i].direction == "")) {
						gp.clone[i].worldX = this.worldX - 41;
					}
				}
			}
		}

		for (int i = 0; i <= 1; i++) {
			if (gp.obj[i] != null && i != gp.sttobj && this.inAir == false) {
				if (gp.obj[i].worldY >= this.worldY - 40 && gp.obj[i].worldY <= this.worldY + 40) {
//				if(gp.obj[i].worldY == gp.obj[k].worldY ) {
					if (gp.obj[i].worldX >= this.worldX && gp.obj[i].worldX <= this.worldX + 48) {
						gp.obj[i].worldX = this.worldX + 48;
					} else if (gp.obj[i].worldX <= this.worldX && gp.obj[i].worldX >= this.worldX - 48) {
						gp.obj[i].worldX = this.worldX - 48;
					}
				}
			}
		}
	}

}
