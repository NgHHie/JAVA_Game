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

			
		int x, y;
		switch (gp.level) 
		{
		case 0:
			setObject(gp.level);
			setEntity(120, 150);
			break;
		
		case 1:
			setObject(gp.level);
			
			x = 8 * gp.tileSize + 1;
			y = 12 * gp.tileSize - 1;
			setEntity(x, y);
			break;
		case 2:
			setObject(gp.level);
			
			int x2 = 13 * gp.tileSize + 1;
			int y2 = 3 * gp.tileSize - 1;
			setEntity(x2, y2);
			break;
		case 3: 
			setObject(gp.level);
			setEntity(300, 300);
			break;
		case 4: 
			setObject(gp.level);
			 x = 12 * gp.tileSize + 1;
			 y = 7 * gp.tileSize - 1;
			setEntity(x, y);
			break;
		case 5:
			setObject(gp.level);
			
			x = 22 * gp.tileSize;
			y = 5 * gp.tileSize - 1;
			setEntity(x, y);
			break;
		case 6:
			setObject(gp.level);
			setEntity(100, 200);
			break;
		case 7:
			setObject(gp.level);
			setEntity(200, 200);
			break;
		case 8:
			setObject(gp.level);
			
			 x = 15 * gp.tileSize + 1;
			 y = 3 * gp.tileSize - 1;
			setEntity(x, y);
			break;
		
		case 9:
			setObject(gp.level);
			x = 2 * gp.tileSize + 1;
			y = 2 * gp.tileSize - 1;
			setEntity(x, y);
			
			break;
		}    
            
	}
	
	private void setObject(int level) {
		switch (level) {
		case 0:
			for(int i = 0; i < 40 ; i++) {
				gp.obj[i] = null;
			}	
			
			gp.obj[0] = new Door(gp);
			gp.obj[0].worldX = 22 * gp.tileSize;
			gp.obj[0].worldY = 2 * gp.tileSize;
			
			break;
		case 1:
			for(int i=0; i<40; i++)
				gp.obj[i] = null;
			// hop 1 and 2
			gp.obj[0] = new Key(gp);
			gp.obj[0].worldX = 9 * gp.tileSize;
			gp.obj[0].worldY = 2 * gp.tileSize;
			gp.obj[1] = new Key(gp);
			gp.obj[1].worldX = 3 * gp.tileSize;
			gp.obj[1].worldY = 2 * gp.tileSize;
			
			//nut bam thang may
			gp.obj[2] = new Button(gp);
			gp.obj[2].worldX = 6 * gp.tileSize;
			gp.obj[2].worldY = 12 * gp.tileSize;
			
			
			//elavator 
			
			gp.obj[3] = new Elevator(gp, (Button)gp.obj[2]);
			gp.obj[3].worldX = 13 * gp.tileSize + 1;
			gp.obj[3].worldY = 10 * gp.tileSize - 1;
			gp.obj[3].defaultWorldX = gp.obj[3].worldX;
			gp.obj[3].defaultWorldY = gp.obj[3].worldY;
			gp.obj[3].setElevator((Elevator)gp.obj[3], "ver", 3);
	
			// thorn 
			
			gp.obj[4] = new Thorn(gp);
			gp.obj[4].worldX = 15 * gp.tileSize;
			gp.obj[4].worldY = 12 * gp.tileSize;
			
			// door 
			gp.obj[5] = new Door(gp);
			gp.obj[5].worldX = 18 * gp.tileSize;
			gp.obj[5].worldY = 3 * gp.tileSize;
			
			
			//da xong khong chinh sua
			break;		

		case 2:
			for(int i=0; i<40; i++)
					gp.obj[i] = null;
			// nut bam  1 
			gp.obj[0] = new Button(gp);
			gp.obj[0].worldX = 14 * gp.tileSize;
			gp.obj[0].worldY = 13 * gp.tileSize;
			//  thang may 1
			gp.obj[1] = new Elevator(gp, (Button)gp.obj[0]);
			gp.obj[1].worldX = 20 * gp.tileSize + 1;
			gp.obj[1].worldY = 12 * gp.tileSize - 1;
			gp.obj[1].defaultWorldX = gp.obj[1].worldX;
			gp.obj[1].defaultWorldY = gp.obj[1].worldY;
			gp.obj[1].setElevator((Elevator)gp.obj[1], "ver", 5);
			
			// nut bam  2
			gp.obj[2] = new Button(gp);
			gp.obj[2].worldX = 24 * gp.tileSize;
			gp.obj[2].worldY = 4 * gp.tileSize;
			//  thang may 2
			gp.obj[3] = new Elevator(gp, (Button)gp.obj[2]);
			gp.obj[3].worldX = 4 * gp.tileSize + 1;
			gp.obj[3].worldY = 12 * gp.tileSize - 1;
			gp.obj[3].defaultWorldX = gp.obj[3].worldX;
			gp.obj[3].defaultWorldY = gp.obj[3].worldY;
			gp.obj[3].setElevator((Elevator)gp.obj[3], "ver", 8);
			
			// door 
			gp.obj[4] = new Door(gp);
			gp.obj[4].worldX = 0 * gp.tileSize;
			gp.obj[4].worldY = 3 * gp.tileSize;
						
			// hoan thanh khong chinh sua
			break;
		case 3:
			for(int i = 0; i < 40 ; i++) {
				gp.obj[i] = null;
			} 

			
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
			gp.obj[8].setElevator((Elevator)gp.obj[8], "hor", 3);
			
			gp.obj[9] = new Elevator(gp, (Button)gp.obj[7]);
			gp.obj[9].worldX = 15  * gp.tileSize + 1;
			gp.obj[9].worldY = 4 * gp.tileSize - 1;
			gp.obj[9].defaultWorldX = gp.obj[9].worldX;
			gp.obj[9].defaultWorldY = gp.obj[9].worldY;
			gp.obj[9].setElevator((Elevator)gp.obj[9], "hor", 3);
			
			gp.obj[10] = new Elevator(gp, (Button)gp.obj[6]);
			gp.obj[10].worldX = 16 * gp.tileSize + 1;
			gp.obj[10].worldY = 8 * gp.tileSize - 1;
			gp.obj[10].defaultWorldX = gp.obj[10].worldX;
			gp.obj[10].defaultWorldY = gp.obj[10].worldY;
			gp.obj[10].setElevator((Elevator)gp.obj[10], "ver", 3);
			
			gp.obj[11] = new Thorn(gp);
			gp.obj[11].worldX = 19 * gp.tileSize;
			gp.obj[11].worldY = 3 * gp.tileSize;
			
//			gp.obj[12] = new Thorn(gp);
//			gp.obj[12].worldX = 20 * gp.tileSize;
//			gp.obj[12].worldY = 3 * gp.tileSize;
			
			gp.obj[13] = new Door(gp);
			gp.obj[13].worldX = 21 * gp.tileSize;
			gp.obj[13].worldY = 2 * gp.tileSize;
			
			break;
			
			// da xong khong chinh sua
		case 4:
			for(int i=0; i<40; i++)
				gp.obj[i] = null;
			// nut bam 1
			gp.obj[0] = new Button(gp);
			gp.obj[0].worldX = 2 * gp.tileSize;
			gp.obj[0].worldY = 13 * gp.tileSize;
			
			//evalator
			gp.obj[1] = new Elevator(gp, (Button)gp.obj[0]);
			gp.obj[1].worldX = 1 * gp.tileSize + 1;
			gp.obj[1].worldY = 6 * gp.tileSize - 1;
			gp.obj[1].defaultWorldX = gp.obj[1].worldX;
			gp.obj[1].defaultWorldY = gp.obj[1].worldY;
			gp.obj[1].setElevator((Elevator)gp.obj[1], "hor", 6);
			
			
			// nut bam 2
			gp.obj[2] = new Button(gp);
			gp.obj[2].worldX = 22 * gp.tileSize;
			gp.obj[2].worldY = 13 * gp.tileSize;
			
			// evalator
			gp.obj[3] = new Elevator(gp, (Button)gp.obj[2]);
			gp.obj[3].worldX = 11 * gp.tileSize + 1;
			gp.obj[3].worldY = 5 * gp.tileSize - 1;
			gp.obj[3].defaultWorldX = gp.obj[3].worldX;
			gp.obj[3].defaultWorldY = gp.obj[3].worldY;
			gp.obj[3].setElevator((Elevator)gp.obj[3], "ver", 2);
			
			
			//door 
			gp.obj[4] = new Door(gp);
			gp.obj[4].worldX = 14 * gp.tileSize + 10;
			gp.obj[4].worldY = 0 * gp.tileSize;
			//hoan thanh ko can chinh sua
			
			break;
		case 5: 
			for(int i = 0; i < 40 ; i++) {
				gp.obj[i] = null;
			} 

			
			gp.obj[0] = new Key(gp);
			gp.obj[0].worldX = 21 * gp.tileSize;
			gp.obj[0].worldY = 7 * gp.tileSize;
                        
            gp.obj[1] = new Key(gp);
			gp.obj[1].worldX = 21 * gp.tileSize;
			gp.obj[1].worldY = 6 * gp.tileSize;
            gp.obj[2] = new Button(gp);
			gp.obj[2].worldX = 6 * gp.tileSize;
			gp.obj[2].worldY = 12 * gp.tileSize;

            gp.obj[3] = new Elevator(gp, (Button)gp.obj[2]);
			gp.obj[3].worldX = 13 * gp.tileSize + 1;
			gp.obj[3].worldY = 7 * gp.tileSize - 1;
			gp.obj[3].defaultWorldX = gp.obj[3].worldX;// + 7 * gp.tileSize;
			gp.obj[3].defaultWorldY = gp.obj[3].worldY;
			gp.obj[3].setElevator((Elevator)gp.obj[3], "hor", 3);
                        
			gp.obj[4] = new Door(gp);
			gp.obj[4].worldX = 1 * gp.tileSize;
			gp.obj[4].worldY = 4 * gp.tileSize;
                        
            gp.obj[5] = new Thorn(gp);
            gp.obj[5].worldX = 11 * gp.tileSize + 1;
            gp.obj[5].worldY = 6 * gp.tileSize - 1;
			
//            gp.obj[6] = new Thorn(gp);
//            gp.obj[6].worldX = 12 * gp.tileSize + 1;
//            gp.obj[6].worldY = 6 * gp.tileSize - 1;
			
            gp.obj[7] = new Thorn(gp);
            gp.obj[7].worldX = 10 * gp.tileSize + 1;
            gp.obj[7].worldY = 6 * gp.tileSize - 1;
                        
            gp.obj[8] = new Thorn(gp);
            gp.obj[8].worldX = 5 * gp.tileSize + 1;
            gp.obj[8].worldY = 6 * gp.tileSize - 1;
			
            gp.obj[9] = new Thorn(gp);
            gp.obj[9].worldX = 6 * gp.tileSize + 1;
            gp.obj[9].worldY = 6 * gp.tileSize - 1;
			
            gp.obj[10] = new Thorn(gp);
            gp.obj[10].worldX = 4 * gp.tileSize + 1;
            gp.obj[10].worldY = 6 * gp.tileSize - 1;
                        
            gp.obj[11] = new Thorn(gp);
            gp.obj[11].worldX = 8 * gp.tileSize + 1;
            gp.obj[11].worldY = 10 * gp.tileSize - 1;
            break;
			// hoan thanh khong chinh sua
		case 6:
			for(int i = 0; i < 40 ; i++) {
				gp.obj[i] = null;
			}	
			
	
			//button cho thang may
			gp.obj[5] = new Button(gp);
			gp.obj[5].worldX = 22 * gp.tileSize;
			gp.obj[5].worldY = 9 * gp.tileSize;

			//thang cho button 3
			gp.obj[8] = new Elevator(gp, (Button)gp.obj[5]);
			gp.obj[8].worldX = 6 * gp.tileSize + 1;
			gp.obj[8].worldY = 5 * gp.tileSize - 1;
			gp.obj[8].defaultWorldX = gp.obj[8].worldX;
			gp.obj[8].defaultWorldY = gp.obj[8].worldY;
			gp.obj[8].setElevator((Elevator)gp.obj[8], "ver", 2);
			
			gp.obj[9] = new Thorn(gp);
			gp.obj[9].worldX = 3 * gp.tileSize;
			gp.obj[9].worldY = 12 * gp.tileSize;
			
			gp.obj[11] = new Thorn(gp);
			gp.obj[11].worldX = 4 * gp.tileSize;
			gp.obj[11].worldY = 12 * gp.tileSize;
			
			gp.obj[12] = new Thorn(gp);
			gp.obj[12].worldX = 5 * gp.tileSize;
			gp.obj[12].worldY = 12 * gp.tileSize;
			
			gp.obj[13] = new Thorn(gp);
			gp.obj[13].worldX = 6 * gp.tileSize;
			gp.obj[13].worldY = 12 * gp.tileSize;
			
			gp.obj[14] = new Thorn(gp);
			gp.obj[14].worldX = 7 * gp.tileSize;
			gp.obj[14].worldY = 12 * gp.tileSize;
			
			gp.obj[15] = new Thorn(gp);
			gp.obj[15].worldX = 8 * gp.tileSize;
			gp.obj[15].worldY = 12 * gp.tileSize;
			
			gp.obj[16] = new Thorn(gp);
			gp.obj[16].worldX = 9 * gp.tileSize;
			gp.obj[16].worldY = 12 * gp.tileSize;
			
			gp.obj[17] = new Thorn(gp);
			gp.obj[17].worldX = 10 * gp.tileSize;
			gp.obj[17].worldY = 12 * gp.tileSize;
			
			gp.obj[18] = new Thorn(gp);
			gp.obj[18].worldX = 11 * gp.tileSize;
			gp.obj[18].worldY = 12 * gp.tileSize;
			
			gp.obj[21] = new Thorn(gp);
			gp.obj[21].worldX = 12 * gp.tileSize;
			gp.obj[21].worldY = 12 * gp.tileSize;
			
			gp.obj[22] = new Thorn(gp);
			gp.obj[22].worldX = 13 * gp.tileSize;
			gp.obj[22].worldY = 12 * gp.tileSize;
			
			gp.obj[23] = new Thorn(gp);
			gp.obj[23].worldX = 14 * gp.tileSize;
			gp.obj[23].worldY = 12 * gp.tileSize;
			
			gp.obj[24] = new Thorn(gp);
			gp.obj[24].worldX = 15 * gp.tileSize;
			gp.obj[24].worldY = 12 * gp.tileSize;
			
			gp.obj[25] = new Thorn(gp);
			gp.obj[25].worldX = 16 * gp.tileSize;
			gp.obj[25].worldY = 12 * gp.tileSize;
			
			gp.obj[26] = new Thorn(gp);
			gp.obj[26].worldX = 17 * gp.tileSize;
			gp.obj[26].worldY = 12 * gp.tileSize;
			
			gp.obj[27] = new Thorn(gp);
			gp.obj[27].worldX = 18 * gp.tileSize;
			gp.obj[27].worldY = 12 * gp.tileSize;

			gp.obj[28] = new Thorn(gp);
			gp.obj[28].worldX = 19 * gp.tileSize;
			gp.obj[28].worldY = 12 * gp.tileSize;

			gp.obj[29] = new Thorn(gp);
			gp.obj[29].worldX = 20 * gp.tileSize;
			gp.obj[29].worldY = 12 * gp.tileSize;
			
			gp.obj[30] = new Thorn(gp);
			gp.obj[30].worldX = 21 * gp.tileSize;
			gp.obj[30].worldY = 12 * gp.tileSize;
			
			gp.obj[31] = new Thorn(gp);
			gp.obj[31].worldX = 22 * gp.tileSize;
			gp.obj[31].worldY = 12 * gp.tileSize;
			
			gp.obj[32] = new Thorn(gp);
			gp.obj[32].worldX = 23 * gp.tileSize;
			gp.obj[32].worldY = 12 * gp.tileSize;
			
			
			gp.obj[19] = new Door(gp);
			gp.obj[19].worldX = 1 * gp.tileSize;
			gp.obj[19].worldY = 1 * gp.tileSize;
			
			break;
		case 7:
			for (int i=0; i<40; i++)
				gp.obj[i] = null;
		 
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
			gp.obj[3].setElevator((Elevator)gp.obj[3], "ver", 3);
		
			gp.obj[4] = new Thorn(gp);
			gp.obj[4].worldX = 10 * gp.tileSize;
			gp.obj[4].worldY = 12 * gp.tileSize ;
		
			gp.obj[5] = new Door(gp);
			gp.obj[5].worldX = 22 * gp.tileSize;
			gp.obj[5].worldY = 2 * gp.tileSize;
		
			gp.obj[6] = new Elevator(gp, (Button)gp.obj[1]);
			gp.obj[6].worldX = 12 * gp.tileSize + 1;
			gp.obj[6].worldY = 8 * gp.tileSize - 1;
			gp.obj[6].defaultWorldX = gp.obj[6].worldX;
			gp.obj[6].defaultWorldY = gp.obj[6].worldY;
			gp.obj[6].setElevator((Elevator)gp.obj[6], "ver", 3);
		
			gp.obj[7] = new Elevator(gp, (Button)gp.obj[2]);
			gp.obj[7].worldX = 17 * gp.tileSize + 1;
			gp.obj[7].worldY = 8 * gp.tileSize - 1;
			gp.obj[7].defaultWorldX = gp.obj[7].worldX;
			gp.obj[7].defaultWorldY = gp.obj[7].worldY;
			gp.obj[7].setElevator((Elevator)gp.obj[7], "ver", 3);
		
			gp.obj[8] = new Thorn(gp);
			gp.obj[8].worldX = 6 * gp.tileSize ;
			gp.obj[8].worldY = 7 * gp.tileSize ;
		
			gp.obj[9] = new Thorn(gp);
			gp.obj[9].worldX = 9 * gp.tileSize;
			gp.obj[9].worldY = 7 * gp.tileSize ;
		
			gp.obj[10] = new Thorn(gp);
			gp.obj[10].worldX = 10 * gp.tileSize;
			gp.obj[10].worldY = 7 * gp.tileSize ;
		
			gp.obj[11] = new Thorn(gp);
			gp.obj[11].worldX = 11 * gp.tileSize;
			gp.obj[11].worldY = 7 * gp.tileSize;
		
			gp.obj[12] = new Thorn(gp);
			gp.obj[12].worldX = 14 * gp.tileSize;
			gp.obj[12].worldY = 7 * gp.tileSize ;
		
			gp.obj[13] = new Thorn(gp);
			gp.obj[13].worldX = 15 * gp.tileSize;
			gp.obj[13].worldY = 7 * gp.tileSize ;
		
			gp.obj[14] = new Thorn(gp);
			gp.obj[14].worldX = 16 * gp.tileSize;
			gp.obj[14].worldY = 7 * gp.tileSize ;
		
			break;
		case 8:
			for(int i=0; i<40; i++)
				gp.obj[i] = null;
			//key 
			gp.obj[0] = new Key(gp);
			gp.obj[0].worldX = 3 * gp.tileSize + 5;
			gp.obj[0].worldY = 13 * gp.tileSize - 1;
			//thorn 1 ,2 ,3 
			gp.obj[5] = new Thorn(gp);
			gp.obj[5].worldX = 10 * gp.tileSize;
			gp.obj[5].worldY = 9 * gp.tileSize;
			gp.obj[1] = new Thorn(gp);
			gp.obj[1].worldX = 11 * gp.tileSize;
			gp.obj[1].worldY = 8  * gp.tileSize;
			gp.obj[2] = new Thorn(gp);
			gp.obj[2].worldX = 11 * gp.tileSize;
			gp.obj[2].worldY = 9  * gp.tileSize;
			
			
			
			// button 
			gp.obj[3] = new Button(gp);
			gp.obj[3].worldX = 17 * gp.tileSize;
			gp.obj[3].worldY = 13 * gp.tileSize;
			//evalator
			gp.obj[4] = new Elevator(gp, (Button)gp.obj[3]);
			gp.obj[4].worldX = 10 * gp.tileSize + 1;
			gp.obj[4].worldY = 11 * gp.tileSize - 1;
			gp.obj[4].defaultWorldX = gp.obj[4].worldX;
			gp.obj[4].defaultWorldY = gp.obj[4].worldY;
			gp.obj[4].setElevator((Elevator)gp.obj[4], "ver", 3);
			///door 
			gp.obj[11] = new Door(gp);
			gp.obj[11].worldX = 22 * gp.tileSize;
			gp.obj[11].worldY =  12* gp.tileSize;
			// hoan thanh khong chinh sua 
			break;
		case 9:
			for(int i=0; i<40; i++)
				gp.obj[i] = null;
			//key 
			gp.obj[0] = new Key(gp);
			gp.obj[0].worldX = 3 * gp.tileSize;
			gp.obj[0].worldY = 1 * gp.tileSize;
			
			gp.obj[1] = new Key(gp);
			gp.obj[1].worldX = 20 * gp.tileSize;
			gp.obj[1].worldY = 12 * gp.tileSize;
			
			// thorn 
			gp.obj[11] = new Thorn(gp);
			gp.obj[11].worldX = 13 * gp.tileSize;
			gp.obj[11].worldY = 14 * gp.tileSize;
			gp.obj[12] = new Thorn(gp);
			gp.obj[12].worldX = 15 * gp.tileSize;
			gp.obj[12].worldY = 12 * gp.tileSize;
			gp.obj[13] = new Thorn(gp);
			gp.obj[13].worldX = 20 * gp.tileSize;
			gp.obj[13].worldY = 10 * gp.tileSize;
			gp.obj[14] = new Thorn(gp);
			gp.obj[14].worldX = 23 * gp.tileSize;
			gp.obj[14].worldY = 13 * gp.tileSize;
			gp.obj[15] = new Thorn(gp);
			gp.obj[15].worldX = 21 * gp.tileSize;
			gp.obj[15].worldY = 13 * gp.tileSize;
			// door
			
			gp.obj[5] = new Door(gp);
			gp.obj[5].worldX = 24 * gp.tileSize;
			gp.obj[5].worldY =  9* gp.tileSize;
			// 
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
