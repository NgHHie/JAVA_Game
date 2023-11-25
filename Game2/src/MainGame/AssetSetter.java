package MainGame;



import Objects.Button;
import Objects.Door;
import Objects.Elevator;
import Objects.Key;
import Objects.SuperObject;
import Objects.Thorn;

public class AssetSetter {
	private GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void set() {
		switch (gp.level) {
		case 0:
			setObject(gp.level);
			setEntity(200, 200);
			break;
		case 1:
			setObject(gp.level);
			setEntity(gp.tileSize * 12, 200);
			break;
		case 2:
			setObject(gp.level);
			setEntity(300, 300);
			break;
		case 3: 
			setObject(3);
			setEntity(300, 300);
			break;
		case 4:
			setObject(gp.level);
			setEntity(200, 200);
			break;
		case 5:
			setObject(gp.level);
			setEntity(200, 200);
			break;
		case 6:
			setObject(gp.level);
			setEntity(200, 200);
			break;
		case 7:
			setObject(gp.level);
			setEntity(200, 200);
			break;
		case 8:
			setObject(gp.level);
			setEntity(200, 200);
			break;
		case 9:
			setObject(gp.level);
			setEntity(200, 200);
			break;
		
		}
	}
	
	
	//0-4: key, 5-7: button, 8-10: elevator (ver; dọc, hor:ngang), 11...:thorn
	public void setObject(int level) {

		switch (level) {
		case 0:
//			setEntity(x1, y1);
			for(SuperObject x:gp.obj) x = null; 
			gp.obj[0] = new Button(gp);
			gp.obj[0].worldX = 7 * gp.tileSize + 24;
			gp.obj[0].worldY = 12 * gp.tileSize - 2;
			
			gp.obj[1] = new Button(gp);
			gp.obj[1].worldX = 12 * gp.tileSize + 24;
			gp.obj[1].worldY = 12 * gp.tileSize - 2;
			
			gp.obj[2] = new Button(gp);
			gp.obj[2].worldX = 17 * gp.tileSize + 24;
			gp.obj[2].worldY = 12 * gp.tileSize  - 2;

			gp.obj[3] = new Elevator(gp, (Button)gp.obj[0]);
			gp.obj[3].worldX = 7 * gp.tileSize + 1;
			gp.obj[3].worldY = 8 * gp.tileSize - 1;
			gp.obj[3].defaultWorldX = gp.obj[3].worldX;
			gp.obj[3].defaultWorldY = gp.obj[3].worldY;
			gp.obj[3].setElevator((Elevator)gp.obj[3], "ver");
			
			gp.obj[4] = new Thorn(gp);
			gp.obj[4].worldX = 10 * gp.tileSize;
			gp.obj[4].worldY = 12 * gp.tileSize;
			
			gp.obj[5] = new Door(gp);
			gp.obj[5].worldX = 21 * gp.tileSize;
			gp.obj[5].worldY = 2 * gp.tileSize;
			
			gp.obj[6] = new Elevator(gp, (Button)gp.obj[1]);
			gp.obj[6].worldX = 12 * gp.tileSize + 1;
			gp.obj[6].worldY = 8 * gp.tileSize - 1;
			gp.obj[6].defaultWorldX = gp.obj[6].worldX;
			gp.obj[6].defaultWorldY = gp.obj[6].worldY;
			gp.obj[6].setElevator((Elevator)gp.obj[6], "ver");
			
			gp.obj[7] = new Elevator(gp, (Button)gp.obj[2]);
			gp.obj[7].worldX = 17 * gp.tileSize + 1;
			gp.obj[7].worldY = 8 * gp.tileSize - 1;
			gp.obj[7].defaultWorldX = gp.obj[7].worldX;
			gp.obj[7].defaultWorldY = gp.obj[7].worldY;
			gp.obj[7].setElevator((Elevator)gp.obj[7], "ver");
			
			gp.obj[8] = new Thorn(gp);
			gp.obj[8].worldX = 6 * gp.tileSize;
			gp.obj[8].worldY = 7 * gp.tileSize;
			
			gp.obj[9] = new Thorn(gp);
			gp.obj[9].worldX = 9 * gp.tileSize;
			gp.obj[9].worldY = 7 * gp.tileSize;
			
			gp.obj[10] = new Thorn(gp);
			gp.obj[10].worldX = 10 * gp.tileSize;
			gp.obj[10].worldY = 7 * gp.tileSize;
			
			gp.obj[11] = new Thorn(gp);
			gp.obj[11].worldX = 11 * gp.tileSize;
			gp.obj[11].worldY = 7 * gp.tileSize;
			
			gp.obj[12] = new Thorn(gp);
			gp.obj[12].worldX = 14 * gp.tileSize;
			gp.obj[12].worldY = 7 * gp.tileSize ;
			
			gp.obj[13] = new Thorn(gp);
			gp.obj[13].worldX = 15 * gp.tileSize;
			gp.obj[13].worldY = 7 * gp.tileSize;
			
			gp.obj[14] = new Thorn(gp);
			gp.obj[14].worldX = 16 * gp.tileSize;
			gp.obj[14].worldY = 7 * gp.tileSize;
			
			break;
		case 1:
//			setEntity(x1, y1);
			for(int i = 0; i< 20 ; i++) {
				gp.obj[i] = null;
			}

			gp.obj[0] = new Key(gp);
			gp.obj[0].worldX = 9 * gp.tileSize;
			gp.obj[0].worldY = 2 * gp.tileSize;
			
			gp.obj[1] = new Key(gp);
			gp.obj[1].worldX = 3 * gp.tileSize;
			gp.obj[1].worldY = 2 * gp.tileSize;
			
			gp.obj[2] = new Button(gp);
			gp.obj[2].worldX = 6 * gp.tileSize;
			gp.obj[2].worldY = 12 * gp.tileSize;

			gp.obj[3] = new Elevator(gp, (Button)gp.obj[2]);
			gp.obj[3].worldX = 13 * gp.tileSize + 1;
			gp.obj[3].worldY = 10 * gp.tileSize - 1;
			gp.obj[3].defaultWorldX = gp.obj[3].worldX;
			gp.obj[3].defaultWorldY = gp.obj[3].worldY;
			gp.obj[3].setElevator((Elevator)gp.obj[3], "ver");
			
			gp.obj[4] = new Thorn(gp);
			gp.obj[4].worldX = 12 * gp.tileSize;
			gp.obj[4].worldY = 12 * gp.tileSize;
			
			gp.obj[5] = new Door(gp);
			gp.obj[5].worldX = 19 * gp.tileSize;
			gp.obj[5].worldY = 11 * gp.tileSize;
			
			break;
		case 2:
			for(int i = 0; i < 20 ; i++) {
				gp.obj[i] = null;
			} 
//			gp.obj[0] = new Key(gp);
//			gp.obj[0].worldX = 9 * gp.tileSize;
//			gp.obj[0].worldY = 2 * gp.tileSize;
			
			gp.obj[5] = new Button(gp);
			gp.obj[5].worldX = 9 * gp.tileSize;
			gp.obj[5].worldY = 12 * gp.tileSize;
			
			gp.obj[6] = new Button(gp);
			gp.obj[6].worldX = 21 * gp.tileSize;
			gp.obj[6].worldY = 8 * gp.tileSize;
			
			gp.obj[7] = new Button(gp);
			gp.obj[7].worldX = 9 * gp.tileSize;
			gp.obj[7].worldY = 5 * gp.tileSize;

			gp.obj[8] = new Elevator(gp, (Button)gp.obj[5]);
			gp.obj[8].worldX = 13 * gp.tileSize + 1;
			gp.obj[8].worldY = 11 * gp.tileSize - 1;
			gp.obj[8].defaultWorldX = gp.obj[8].worldX;
			gp.obj[8].defaultWorldY = gp.obj[8].worldY;
			gp.obj[8].setElevator((Elevator)gp.obj[8], "hor");
			
			gp.obj[9] = new Elevator(gp, (Button)gp.obj[7]);
			gp.obj[9].worldX = 15  * gp.tileSize + 1;
			gp.obj[9].worldY = 4 * gp.tileSize - 1;
			gp.obj[9].defaultWorldX = gp.obj[9].worldX;
			gp.obj[9].defaultWorldY = gp.obj[9].worldY;
			gp.obj[9].setElevator((Elevator)gp.obj[9], "hor");
			
			gp.obj[10] = new Elevator(gp, (Button)gp.obj[6]);
			gp.obj[10].worldX = 16 * gp.tileSize + 1;
			gp.obj[10].worldY = 8 * gp.tileSize - 1;
			gp.obj[10].defaultWorldX = gp.obj[10].worldX;
			gp.obj[10].defaultWorldY = gp.obj[10].worldY;
			gp.obj[10].setElevator((Elevator)gp.obj[10], "ver");
			
			gp.obj[11] = new Thorn(gp);
			gp.obj[11].worldX = 19 * gp.tileSize;
			gp.obj[11].worldY = 3 * gp.tileSize;
			
			gp.obj[12] = new Thorn(gp);
			gp.obj[12].worldX = 20 * gp.tileSize;
			gp.obj[12].worldY = 3 * gp.tileSize;
			
			gp.obj[13] = new Door(gp);
			gp.obj[13].worldX = 21 * gp.tileSize;
			gp.obj[13].worldY = 2 * gp.tileSize;
			
			break;
		case 3:
			for(int i = 0; i < 20 ; i++) {
				gp.obj[i] = null;
			} 
			
			gp.obj[0] = new Button(gp);
			gp.obj[0].worldX = 6 * gp.tileSize;
			gp.obj[0].worldY = 12 * gp.tileSize;

			gp.obj[1] = new Elevator(gp, (Button)gp.obj[0]);
			gp.obj[1].worldX = 16 * gp.tileSize + 1;
			gp.obj[1].worldY = 11 * gp.tileSize - 1;
			gp.obj[1].defaultWorldX = gp.obj[1].worldX;
			gp.obj[1].defaultWorldY = gp.obj[1].worldY;
			gp.obj[1].setElevator((Elevator)gp.obj[1], "ver");
			
			gp.obj[2] = new Door(gp);
			gp.obj[2].worldX = 1 * gp.tileSize;
			gp.obj[2].worldY = 10 * gp.tileSize;
			
			break;
		
		case 4:
			for(SuperObject x:gp.obj) x = null; 
			gp.obj[0] = new Key(gp);
			gp.obj[0].worldX = 9 * gp.tileSize;
			gp.obj[0].worldY = 2 * gp.tileSize;
			
			gp.obj[1] = new Key(gp);
			gp.obj[1].worldX = 3 * gp.tileSize;
			gp.obj[1].worldY = 2 * gp.tileSize;
			
			gp.obj[2] = new Button(gp);
			gp.obj[2].worldX = 6 * gp.tileSize;
			gp.obj[2].worldY = 12 * gp.tileSize;

			gp.obj[3] = new Elevator(gp, (Button)gp.obj[2]);
			gp.obj[3].worldX = 13 * gp.tileSize + 1;
			gp.obj[3].worldY = 10 * gp.tileSize - 1;
			gp.obj[3].defaultWorldX = gp.obj[3].worldX;
			gp.obj[3].defaultWorldY = gp.obj[3].worldY;
			gp.obj[3].setElevator((Elevator)gp.obj[3], "hor");
			
			gp.obj[4] = new Thorn(gp);
			gp.obj[4].worldX = 10 * gp.tileSize;
			gp.obj[4].worldY = 12 * gp.tileSize;
			
			gp.obj[5] = new Door(gp);
			gp.obj[5].worldX = 19 * gp.tileSize;
			gp.obj[5].worldY = 11 * gp.tileSize;
			
			break;
		case 5:
			for(SuperObject x:gp.obj) x = null; 
			gp.obj[0] = new Key(gp);
			gp.obj[0].worldX = 9 * gp.tileSize;
			gp.obj[0].worldY = 2 * gp.tileSize;
			
			gp.obj[1] = new Key(gp);
			gp.obj[1].worldX = 3 * gp.tileSize;
			gp.obj[1].worldY = 2 * gp.tileSize;
			
			gp.obj[2] = new Button(gp);
			gp.obj[2].worldX = 6 * gp.tileSize;
			gp.obj[2].worldY = 12 * gp.tileSize;

			gp.obj[3] = new Elevator(gp, (Button)gp.obj[2]);
			gp.obj[3].worldX = 13 * gp.tileSize + 1;
			gp.obj[3].worldY = 10 * gp.tileSize - 1;
			gp.obj[3].defaultWorldX = gp.obj[3].worldX;
			gp.obj[3].defaultWorldY = gp.obj[3].worldY;
			gp.obj[3].setElevator((Elevator)gp.obj[3], "hor");
			
			gp.obj[4] = new Thorn(gp);
			gp.obj[4].worldX = 10 * gp.tileSize;
			gp.obj[4].worldY = 12 * gp.tileSize;
			
			gp.obj[5] = new Door(gp);
			gp.obj[5].worldX = 19 * gp.tileSize;
			gp.obj[5].worldY = 11 * gp.tileSize;
			
			break;
		case 6:
			for(SuperObject x:gp.obj) x = null; 
			gp.obj[0] = new Key(gp);
			gp.obj[0].worldX = 9 * gp.tileSize;
			gp.obj[0].worldY = 2 * gp.tileSize;
			
			gp.obj[1] = new Key(gp);
			gp.obj[1].worldX = 3 * gp.tileSize;
			gp.obj[1].worldY = 2 * gp.tileSize;
			
			gp.obj[2] = new Button(gp);
			gp.obj[2].worldX = 6 * gp.tileSize;
			gp.obj[2].worldY = 12 * gp.tileSize;

			gp.obj[3] = new Elevator(gp, (Button)gp.obj[2]);
			gp.obj[3].worldX = 13 * gp.tileSize + 1;
			gp.obj[3].worldY = 10 * gp.tileSize - 1;
			gp.obj[3].defaultWorldX = gp.obj[3].worldX;
			gp.obj[3].defaultWorldY = gp.obj[3].worldY;
			gp.obj[3].setElevator((Elevator)gp.obj[3], "hor");
			
			gp.obj[4] = new Thorn(gp);
			gp.obj[4].worldX = 10 * gp.tileSize;
			gp.obj[4].worldY = 12 * gp.tileSize;
			
			gp.obj[5] = new Door(gp);
			gp.obj[5].worldX = 19 * gp.tileSize;
			gp.obj[5].worldY = 11 * gp.tileSize;
			
			break;
		case 7:
			for(SuperObject x:gp.obj) x = null; 
			gp.obj[0] = new Key(gp);
			gp.obj[0].worldX = 9 * gp.tileSize;
			gp.obj[0].worldY = 2 * gp.tileSize;
			
			gp.obj[1] = new Key(gp);
			gp.obj[1].worldX = 3 * gp.tileSize;
			gp.obj[1].worldY = 2 * gp.tileSize;
			
			gp.obj[2] = new Button(gp);
			gp.obj[2].worldX = 6 * gp.tileSize;
			gp.obj[2].worldY = 12 * gp.tileSize;

			gp.obj[3] = new Elevator(gp, (Button)gp.obj[2]);
			gp.obj[3].worldX = 13 * gp.tileSize + 1;
			gp.obj[3].worldY = 10 * gp.tileSize - 1;
			gp.obj[3].defaultWorldX = gp.obj[3].worldX;
			gp.obj[3].defaultWorldY = gp.obj[3].worldY;
			gp.obj[3].setElevator((Elevator)gp.obj[3], "hor");
			
			gp.obj[4] = new Thorn(gp);
			gp.obj[4].worldX = 10 * gp.tileSize;
			gp.obj[4].worldY = 12 * gp.tileSize;
			
			gp.obj[5] = new Door(gp);
			gp.obj[5].worldX = 19 * gp.tileSize;
			gp.obj[5].worldY = 11 * gp.tileSize;
			
			break;
		case 8:
			for(SuperObject x:gp.obj) x = null; 
			gp.obj[0] = new Key(gp);
			gp.obj[0].worldX = 9 * gp.tileSize;
			gp.obj[0].worldY = 2 * gp.tileSize;
			
			gp.obj[1] = new Key(gp);
			gp.obj[1].worldX = 3 * gp.tileSize;
			gp.obj[1].worldY = 2 * gp.tileSize;
			
			gp.obj[2] = new Button(gp);
			gp.obj[2].worldX = 6 * gp.tileSize;
			gp.obj[2].worldY = 12 * gp.tileSize;

			gp.obj[3] = new Elevator(gp, (Button)gp.obj[2]);
			gp.obj[3].worldX = 13 * gp.tileSize + 1;
			gp.obj[3].worldY = 10 * gp.tileSize - 1;
			gp.obj[3].defaultWorldX = gp.obj[3].worldX;
			gp.obj[3].defaultWorldY = gp.obj[3].worldY;
			gp.obj[3].setElevator((Elevator)gp.obj[3], "hor");
			
			gp.obj[4] = new Thorn(gp);
			gp.obj[4].worldX = 10 * gp.tileSize;
			gp.obj[4].worldY = 12 * gp.tileSize;
			
			gp.obj[5] = new Door(gp);
			gp.obj[5].worldX = 19 * gp.tileSize;
			gp.obj[5].worldY = 11 * gp.tileSize;
			
			break;
		case 9:
			for(SuperObject x:gp.obj) x = null; 
			gp.obj[0] = new Key(gp);
			gp.obj[0].worldX = 9 * gp.tileSize;
			gp.obj[0].worldY = 2 * gp.tileSize;
			
			gp.obj[1] = new Key(gp);
			gp.obj[1].worldX = 3 * gp.tileSize;
			gp.obj[1].worldY = 2 * gp.tileSize;
			
			gp.obj[2] = new Button(gp);
			gp.obj[2].worldX = 6 * gp.tileSize;
			gp.obj[2].worldY = 12 * gp.tileSize;

			gp.obj[3] = new Elevator(gp, (Button)gp.obj[2]);
			gp.obj[3].worldX = 13 * gp.tileSize + 1;
			gp.obj[3].worldY = 10 * gp.tileSize - 1;
			gp.obj[3].defaultWorldX = gp.obj[3].worldX;
			gp.obj[3].defaultWorldY = gp.obj[3].worldY;
			gp.obj[3].setElevator((Elevator)gp.obj[3], "hor");
			
			gp.obj[4] = new Thorn(gp);
			gp.obj[4].worldX = 10 * gp.tileSize;
			gp.obj[4].worldY = 12 * gp.tileSize;
			
			gp.obj[5] = new Door(gp);
			
			gp.obj[5].worldX = 19 * gp.tileSize;
			gp.obj[5].worldY = 11 * gp.tileSize;
			
			break;
		}
			
	}
	
	
	
	private void setEntity(int x, int y) {
		gp.player.worldDefaultX = x;
		gp.player.worldDefaultY = y;
		gp.player.worldX = x;
		gp.player.worldY = y;		
	}

}
