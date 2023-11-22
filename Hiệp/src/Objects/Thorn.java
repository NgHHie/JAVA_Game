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
			this.image = ImageIO.read(getClass().getResourceAsStream("/object/thorn.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		this.solidArea = new Rectangle(0, 0, 48, 36);
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
	}

	public void update() {
		checkCollisionPlayer();
	}

	private void checkCollisionPlayer() {
		if (gp.player != null) {
			this.collision = checkCollision(gp.player);

			if (this.collision == true) {
				gp.player = null;
				return;
			}
		}
		for (int i = 0; i < 5; i++) {
			if (gp.clone[i] != null) {
				this.collision = checkCollision(gp.clone[i]);

				if (this.collision == true) {
					gp.clone[i] = null;
					return;
				}
			}
		}
		this.collision = false;
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
