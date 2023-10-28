package MainGame;

import entity.Entity;

public class CollisionChecker {
	GamePanel gp;
	
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
		boolean change = false;
		for(int i=0; i<gp.obj.length; i++) {
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
							 if(entity.collisionOn2 == false) {
								 entity.solidArea.y += entity.speed-1;
								 entity.collisionOn2 = true;
								 change = true;
								 entity.speed = 1;
								 gp.obj[i].worldY -= entity.speed;
							 } else {
								 entity.collisionOn2 = true;
								 change = true;
								 gp.obj[i].worldY -= entity.speed;
							 }
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
							 if(entity.collisionOn2 == false) {
								 entity.solidArea.y -= entity.speed-1;
								 entity.collisionOn2 = true;
								 change = true;
								 entity.speed = 1;
								 gp.obj[i].worldY += entity.speed;
							 } else {
								 entity.collisionOn2 = true;
								 change = true;
								 gp.obj[i].worldY += entity.speed;
							 }
						 }
						 if(player == true) {
							 index = i;
						 }
						 
					 }
					 break;
				 case "left":
					 entity.solidArea.x -= entity.speed;
					 if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						 if(gp.obj[i].collision == true) {
							 if(entity.collisionOn2 == false) {
								 entity.solidArea.x += entity.speed-1;
								 entity.collisionOn2 = true;
								 change = true;
								 entity.speed = 1;
								 gp.obj[i].worldX -= entity.speed;
							 } else {
								 entity.collisionOn2 = true;
								 change = true;
								 gp.obj[i].worldX -= entity.speed;
							 }
						 }
						 if(player == true) {
							 index = i;
						 }
						 
					 }
					 break;
				 case "right":
					 entity.solidArea.x += entity.speed;
					 if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						 if(gp.obj[i].collision == true) {
							 if(entity.collisionOn2 == false) {
								 entity.solidArea.x -= entity.speed-1;
								 entity.collisionOn2 = true;
								 change = true;
								 entity.speed = 1;
								 gp.obj[i].worldX += entity.speed;
							 } else {
								 entity.collisionOn2 = true;
								 change = true;
								 gp.obj[i].worldX += entity.speed;
							 }
						 }
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
			}
		}
		if(change == false) entity.collisionOn2 = false;
//		return index;
	}
	
}
