package MainGame;

public class CheckDead {
	GamePanel gp;
	
	public CheckDead(GamePanel gp) {
		this.gp = gp;
	}
	
	void checkDeadPlayer() {
		if(gp.player.worldY > gp.obj[0].worldY - 48
				&& gp.player.worldY < gp.obj[0].worldY + 32
				&& gp.player.worldY > gp.obj[1].worldY - 48
				&& gp.player.worldY < gp.obj[1].worldY + 32) {
			if(gp.obj[0].worldX < gp.obj[1].worldX
					&& gp.obj[1].worldX - gp.obj[0].worldX < 32 + 48
					&& gp.player.worldX > gp.obj[0].worldX
					&& gp.player.worldX < gp.obj[1].worldX) {
				gp.player = null;
			}
		}
		if(gp.player.worldY > gp.obj[0].worldY - 48
				&& gp.player.worldY < gp.obj[0].worldY + 32
				&& gp.player.worldY > gp.obj[1].worldY - 48
				&& gp.player.worldY < gp.obj[1].worldY + 32) {
			if(gp.obj[1].worldX < gp.obj[0].worldX
					&& gp.obj[0].worldX - gp.obj[1].worldX < 32 + 48
					&& gp.player.worldX > gp.obj[1].worldX
					&& gp.player.worldX < gp.obj[0].worldX) {
				gp.player = null;
			}
		}	
	}
	
	void checkDeadClone(int i) {
		if(gp.clone[i] == null) return;
		if(gp.clone[i].worldY > gp.obj[0].worldY - 48
				&& gp.clone[i].worldY < gp.obj[0].worldY + 32
				&& gp.clone[i].worldY > gp.obj[1].worldY - 48
				&& gp.clone[i].worldY < gp.obj[1].worldY + 32) {
			if(gp.obj[0].worldX < gp.obj[1].worldX
					&& gp.obj[1].worldX - gp.obj[0].worldX < 32 + 48
					&& gp.clone[i].worldX > gp.obj[0].worldX
					&& gp.clone[i].worldX < gp.obj[1].worldX) {
				gp.clone[i] = null;
			}
		}
		if(gp.clone[i].worldY > gp.obj[0].worldY - 48
				&& gp.clone[i].worldY < gp.obj[0].worldY + 32
				&& gp.clone[i].worldY > gp.obj[1].worldY - 48
				&& gp.clone[i].worldY < gp.obj[1].worldY + 32) {
			if(gp.obj[1].worldX < gp.obj[0].worldX
					&& gp.obj[0].worldX - gp.obj[1].worldX < 32 + 48
					&& gp.clone[i].worldX > gp.obj[1].worldX
					&& gp.clone[i].worldX < gp.obj[0].worldX) {
				gp.clone[i] = null;
			}
		}	
	}

}
