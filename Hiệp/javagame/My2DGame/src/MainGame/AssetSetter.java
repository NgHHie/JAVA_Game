package MainGame;

import Objects.Key;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new Key();
		gp.obj[0].worldX = 5 * gp.tileSize;
		gp.obj[0].worldY = 5 * gp.tileSize;
		
		
	}

}
