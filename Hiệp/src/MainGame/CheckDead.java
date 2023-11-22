package MainGame;

import entity.Entity;

public class CheckDead {
	private GamePanel gp;
	
	public CheckDead(GamePanel gp) {
		this.gp = gp;
	}
	
	public Entity checkDeadPlayer(Entity player) {
		if(player == null) return null;
		for(int i=0; i<=1; i++) {
			for(int j=0; j<=1; j++) {
				if(gp.obj[i] != null && gp.obj[j] != null)
				if(player.worldY > gp.obj[i].worldY - 48
						&& player.worldY < gp.obj[i].worldY + 32
						&& player.worldY > gp.obj[j].worldY - 48
						&& player.worldY < gp.obj[j].worldY + 32) {
					if(gp.obj[i].worldX < gp.obj[j].worldX
							&& gp.obj[j].worldX - gp.obj[i].worldX < 32 + 48
							&& player.worldX > gp.obj[i].worldX
							&& player.worldX < gp.obj[j].worldX) {
						return null;
					}
				}
			}
		}
		return player;
	}
	
}
