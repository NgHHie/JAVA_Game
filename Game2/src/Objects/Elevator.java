package Objects;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import MainGame.GamePanel;

public class Elevator extends SuperObject {
	private boolean on;
	private int maxHeight;
	private int minHeight;
	private int maxWidth;
	private int minWidth;
	private int speed;
	protected boolean horizontal; // ngang
	protected boolean vertical; // doc
	
	private Button bt;
	private int sizeofelevator;
	
	public void setSizeofelevator(int x) {
		this.sizeofelevator = x;
	}

	public Elevator(GamePanel gp, Button bt) {
		super(gp);
		this.bt = bt;
		this.name = "Elevator";
		this.gp = gp;
		this.on = false;
		this.speed = 1;
		try {
			this.image = ImageIO.read(getClass().getResourceAsStream("/object/thangmay.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		this.solidArea = new Rectangle(0, 0, 96, 24);
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
	}

	public void update() {
		this.minHeight = this.defaultWorldY;
		this.maxHeight = this.defaultWorldY - this.sizeofelevator * gp.tileSize;
		this.minWidth = this.defaultWorldX;
		this.maxWidth = this.defaultWorldX + this.sizeofelevator * gp.tileSize;
		Button tmp = bt;
		if (tmp.put == true) {
						
			if (this.vertical == true && this.worldY > this.maxHeight) {
				
				this.worldY -= this.speed;

				if (gp.player != null) {
					gp.setSolid(gp.player, this);
					if (gp.player.solidArea.intersects(this.solidArea)) {
						gp.player.worldY -= this.speed;
					}
					gp.setDefaultSolid(gp.player, this);
				}
				for (int i = 0; i < 5; i++) {
					if (gp.clone[i] != null) {
						gp.setSolid(gp.clone[i], this);
						if (gp.clone[i].solidArea.intersects(this.solidArea)) {
							gp.clone[i].worldY -= this.speed;
						}
						gp.setDefaultSolid(gp.clone[i], this);
					}
				}
			}
			if (this.horizontal == true && this.worldX < this.maxWidth) {
				this.worldX += this.speed;

				if (gp.player != null) {
					gp.setSolid(gp.player, this);
					gp.player.solidArea.y += 2;

					if (gp.player.solidArea.intersects(this.solidArea)) {
						gp.player.worldX += this.speed;
					}
					gp.setDefaultSolid(gp.player, this);
				}
				for (int i = 0; i < 5; i++) {
					if (gp.clone[i] != null) {
						gp.setSolid(gp.clone[i], this);
						gp.clone[i].solidArea.y += 2;

						if (gp.clone[i].solidArea.intersects(this.solidArea)) {
							gp.clone[i].worldX += this.speed;
						}
						gp.setDefaultSolid(gp.clone[i], this);
					}
				}
			}
		} else {
			
//			gp.soundEffect.get(4).stop();
			if (this.vertical == true && this.worldY < this.minHeight)
				this.worldY += this.speed;
			else if (this.horizontal == true && this.worldX > this.minWidth) {
				this.worldX -= this.speed;
				if (gp.player != null) {
					gp.setSolid(gp.player, this);
					gp.player.solidArea.y += 5;

					if (gp.player.solidArea.intersects(this.solidArea)) {
						gp.player.worldX -= this.speed;
					}
					gp.setDefaultSolid(gp.player, this);
				}
				for (int i = 0; i < 5; i++) {
					if (gp.clone[i] != null) {
						gp.setSolid(gp.clone[i], this);
						gp.clone[i].solidArea.y += 5;

						if (gp.clone[i].solidArea.intersects(this.solidArea)) {
							gp.clone[i].worldX -= this.speed;
						}
						gp.setDefaultSolid(gp.clone[i], this);
					}
				}
			}
		}
	}
	
	

}
