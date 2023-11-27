package Objects;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import MainGame.GamePanel;
import entity.Entity;

public class Thorn extends SuperObject {
	private boolean put;

	public Thorn(GamePanel gp) {
		super(gp);
		this.name = "Thorn";
		try {
			this.image = ImageIO.read(getClass().getResourceAsStream("/object/Spike.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		this.solidArea = new Rectangle(0, 20, 48, 28);
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
	}

	public void update() {

	}

	
	public boolean checkCollisionPlayer(Entity player) {
		if (player != null) {
			this.collision = checkCollision(player);
			if (this.collision == true) {
				player.dead = true;
				return player.dead;
			}
		}

		this.collision = false;
		return false;
	}

	private boolean checkCollision(Entity entity) {
		gp.setSolid(entity, this);
		entity.solidArea.y += 1;

		if (entity.solidArea.intersects(this.solidArea)) {
			this.collision = true;
		} else
			this.collision = false;

		gp.setDefaultSolid(entity, this);

		return this.collision;
	}
}