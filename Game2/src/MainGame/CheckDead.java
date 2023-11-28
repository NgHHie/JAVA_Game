package MainGame;

import entity.Entity;

public class CheckDead {
	private GamePanel gp;
	
	public CheckDead(GamePanel gp) {
		this.gp = gp;
	}
	
	public boolean checkDeadPlayer(Entity player) {
		if(player == null) return true;
		
		//check giữa 2 thùng
		for(int i=0; i<=1; i++) {
			for(int j=0; j<=1; j++) {
				if(gp.obj[i] != null && gp.obj[j] != null)
				if(player.worldY > gp.obj[i].worldY - 48
						&& player.worldY < gp.obj[i].worldY + 32
						&& player.worldY > gp.obj[j].worldY - 48
						&& player.worldY < gp.obj[j].worldY + 32) {
					if(gp.obj[i].worldX < gp.obj[j].worldX
							&& gp.obj[j].worldX - gp.obj[i].worldX < 32 + 32
							&& player.worldX > gp.obj[i].worldX
							&& player.worldX < gp.obj[j].worldX) {
						System.out.println("thung");
						return true;
						
					}
				}
			}
		}
		
		//check đâm vào gai
		for(int i=0; i<40; i++) {
			if(gp.obj[i] != null && gp.obj[i].name.compareTo("Thorn") == 0) {
				Objects.Thorn tmp = (Objects.Thorn)gp.obj[i];
				boolean check = tmp.checkCollisionPlayer(player);
				if(check == true) {
//					System.out.println("gai");
					return check;
				}
			}
		}
	
		//check giữa thùng và tile 
		int entityLeftWorldX = player.worldX + player.solidArea.x + player.solidArea.width / 2;
		int entityTopWorldY = player.worldY + player.solidArea.y;
		int entityBottomWorldY = player.worldY + player.solidArea.y + player.solidArea.height;

		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;
		
		if(entityLeftCol >=0 && entityTopRow >= 0 && entityBottomRow < gp.maxScreenRow) {
			int tileNum1, tileNum2;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true
					|| player.name.compareTo(gp.tileM.tile[tileNum1].name) == 0
					|| player.name.compareTo(gp.tileM.tile[tileNum2].name) == 0) {
				return true;
			}
		}
	
		return false;
	}
	
}
