package MainGame;

import entity.Entity;

public class CollisionChecker {
	GamePanel gp;
	public String pastdirection;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
	
		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;
		
		int tileNum1, tileNum2;
		switch(entity.direction) {
		case "up":
			entityTopRow = (int) ((entityTopWorldY - entity.speed) / gp.tileSize);
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (int) ((entityBottomWorldY + entity.speed) / gp.tileSize);
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (int) ((entityLeftWorldX - entity.speed) / gp.tileSize);
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (int) ((entityRightWorldX + entity.speed) / gp.tileSize);
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		}
	}
	
	public void checkObject(Entity entity, boolean player) {
		int index = 999;
//		entity.collisionOn2 = false;
		for(int i=0; i<5; i++) {
			boolean change = false;
			if(gp.obj[i] != null) {
				 entity.solidArea.x += entity.worldX;
				 entity.solidArea.y += entity.worldY;
				 gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				 gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				 
				 switch(entity.direction) {
				 case "up":
					 entity.solidArea.y -= entity.speed;
					 if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						 if(gp.obj[i].collision == true) {
							 if(entity.collisionOn2[i] == false) {
								 gp.obj[i].pastdirection = entity.direction;
								 entity.solidArea.y += entity.speed;
								 entity.collisionOn2[i] = true;
								 change = true;
								 entity.speed = 1;
//								 gp.obj[i].worldY -= entity.speed;
							 } else {
								 entity.collisionOn2[i] = true;
								 change = true;
								 gp.obj[i].worldY -= entity.speed;
							 }
//							 gp.obj[0].slideOn = true;
							 gp.obj[i].speedobj = entity.speed;
						 }
						 if(player == true) {
							 index = i;
						 }
						 
					 }
					 break;
				 case "down":
					 entity.solidArea.y += entity.speed;
					 if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						 if(gp.obj[i].collision == true) {
							 if(entity.collisionOn2[i] == false) {
								 gp.obj[i].pastdirection = entity.direction;
								 entity.solidArea.y -= entity.speed;
								 entity.collisionOn2[i] = true;
								 change = true;
								 entity.speed = 1;
//								 gp.obj[i].worldY += entity.speed;
							 } else {
								 entity.collisionOn2[i] = true;
								 change = true;
								 gp.obj[i].worldY += entity.speed;
							 }
//							 gp.obj[0].slideOn = true;
							 gp.obj[i].speedobj = entity.speed;

						 }
						 if(player == true) {
							 index = i;
						 }
						 
					 }
					 break;
				 case "left":
					 entity.solidArea.x -= entity.speed;
					 if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						 gp.obj[i].collision = true;
//						 if(gp.obj[i].collision == true) {
							 if(entity.collisionOn2[i] == false) {
								 gp.obj[i].pastdirection = entity.direction;
								 gp.obj[i].slideOn = true;
								 entity.solidArea.x += entity.speed;
//								 entity.solidArea.x = gp.obj[i].worldX + 48;
								 entity.collisionOn2[i] = true;
								 change = true;
								 entity.speed = 1;
//								 gp.obj[i].worldX -= (int)entity.speed;
							 } else {
								 entity.collisionOn2[i] = true;
								 change = true;
								 gp.obj[i].worldX -= (int)entity.speed;
//								 entity.solidArea.x = gp.obj[i].worldX + 48;
//							 }
//							 gp.obj[0].slideOn = true;
							 gp.obj[i].speedobj = entity.speed;

						 }
						 if(player == true) {
							 index = i;
						 }
						 
					 }
					 break;
				 case "right":
					 entity.solidArea.x += entity.speed;
					 if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						 gp.obj[i].collision = true;
//						 if(gp.obj[i].collision == true) {
							 if(entity.collisionOn2[i] == false) {
								 gp.obj[i].pastdirection = entity.direction;
								 gp.obj[i].slideOn = true;
								 entity.solidArea.x -= entity.speed;
//								 entity.solidArea.x = gp.obj[i].worldX - 32;
								 entity.collisionOn2[i] = true;
								 change = true;
								 entity.speed = 1;
								 gp.obj[i].worldX += entity.speed;
							 } else {
								 entity.collisionOn2[i] = true;
								 change = true;
								 gp.obj[i].worldX += entity.speed;
//								 entity.solidArea.x = gp.obj[i].worldX - 32;
							 }
//							 gp.obj[0].slideOn = true;
							 gp.obj[i].speedobj = entity.speed;

//						 }
						 if(player == true) {
							 index = i;
						 }
						 
					 }
					 break;
				 }

				 entity.solidArea.x = entity.solidAreaDefaultX;
				 entity.solidArea.y = entity.solidAreaDefaultY;
				 gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				 gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY; 
				 
				 if(entity.collisionOn2[i] == true) gp.moveobj[i] = true;
				 System.out.println(entity.direction + " " + change);
//				 if(change == false && entity.collisionOn2[i] == true) {
				 if(entity.direction == "") {
						gp.obj[i].collision = false;
						entity.collisionOn2[i] = false;
						gp.moveobj[i] = false;
						gp.obj[i].slideOn = false;
				}
			}
//			if(change == false && entity.collisionOn2 == true) {
//				gp.obj[i].collision = false;
//				entity.collisionOn2 = false;
//				gp.obj[i].slideOn = false;
//			}
		}

//		return index;
	}
	
}
