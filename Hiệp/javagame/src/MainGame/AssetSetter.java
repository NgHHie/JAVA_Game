package MainGame;



import Objects.Button;
import Objects.Elevator;
import Objects.Key;
import Objects.Thorn;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new Key(gp);
		gp.obj[0].worldX = 9 * gp.tileSize;
		gp.obj[0].worldY = 2 * gp.tileSize;
		
//		gp.obj[1] = new Key(gp);
//		gp.obj[1].worldX = 3 * gp.tileSize;
//		gp.obj[1].worldY = 2 * gp.tileSize;
		
		gp.obj[2] = new Button(gp);
		gp.obj[2].worldX = 6 * gp.tileSize;
		gp.obj[2].worldY = 9 * gp.tileSize;

		gp.obj[3] = new Elevator(gp);
		gp.obj[3].worldX = 13 * gp.tileSize + 1;
		gp.obj[3].worldY = 8 * gp.tileSize - 1;
		gp.obj[3].defaultWorldX = gp.obj[3].worldX;
		gp.obj[3].defaultWorldY = gp.obj[3].worldY;
		gp.obj[3].setElevator((Elevator)gp.obj[3], "ver");
		
//		gp.obj[4] = new Thorn(gp);
//		gp.obj[4].worldX = 13 * gp.tileSize;
//		gp.obj[4].worldY = 10 * gp.tileSize;
		
	}

}
