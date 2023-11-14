package MainGame;

import Objects.Key;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new Key();
		gp.obj[0].worldX = 5 * gp.tileSize + 48 * 3;
		gp.obj[0].worldY = 5 * gp.tileSize;
		
		gp.obj[1] = new Key();
		gp.obj[1].worldX = 5 * gp.tileSize;
		gp.obj[1].worldY = 5 * gp.tileSize;
//		
//		gp.obj[2] = new Key();
//		gp.obj[2].worldX = 5 * gp.tileSize + 48 * 5;
//		gp.obj[2].worldY = 5 * gp.tileSize;
		
	}

}
