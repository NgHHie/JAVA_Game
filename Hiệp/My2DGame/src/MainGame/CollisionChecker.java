package MainGame;

import Objects.Key;
import Objects.SuperObject;
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
		int tmpLeftCol = entityLeftCol;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int tmpRightCol = entityRightCol;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;
		
		int tileNum1, tileNum2;
		switch(entity.direction) {
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
		switch(entity.directionY) {
		case "up":
			entityTopRow = (int) ((entityTopWorldY + entity.speedY) / gp.tileSize);
			tileNum1 = gp.tileM.mapTileNum[tmpLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[tmpRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOnY = true;
				entity.onGround = false;
//				entity.speedY = 0;
			}
			break;
		case "down":
			entityBottomRow = (int) ((entityBottomWorldY + entity.speedY) / gp.tileSize);
			tileNum1 = gp.tileM.mapTileNum[tmpLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[tmpRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOnY = true;
				entity.speedY = 1;
				entity.inAir = false;
				entity.onGround = true;
			}
			break;
		}
	}
	
	public void checkObject(Entity entity, boolean player) {
		for(int i=0; i<5; i++) {
			if(gp.obj[i] != null) {
				 entity.solidArea.x += entity.worldX;
				 entity.solidArea.y += entity.worldY;
				 gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				 gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				 
				 switch(entity.direction) {
				 case "left":
					 entity.solidArea.x -= (int)entity.speed;
					 if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						 gp.obj[i].collision = true;
						 if(entity.collisionOn2[i] == false) {
							 gp.obj[i].pastdirection = entity.direction;
							 gp.obj[i].slideOn = true;
							 entity.solidArea.x += (int)entity.speed;
							 entity.speed = 1;
						 }
						 entity.collisionOn2[i] = true;
						 gp.obj[i].speedobj = entity.speed;		 
					 }
					 break;
				 case "right":
					 entity.solidArea.x += (int)entity.speed;
				//	 System.out.println((gp.player.solidArea.x + 40) + " " + gp.obj[i].solidArea.x);
					 if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						 gp.obj[i].collision = true;

						 if(entity.collisionOn2[i] == false) {
							 gp.obj[i].pastdirection = entity.direction;
							 gp.obj[i].slideOn = true;
							 entity.solidArea.x -= (int)entity.speed;
							 entity.speed = 1;
							 
						 } 
						 entity.collisionOn2[i] = true;
						 gp.obj[i].speedobj = entity.speed;
					 }
					 break;
				 }
				 
				 switch(entity.directionY) {
				 case "down":
					 entity.solidArea.y += entity.speedY;
					 if(entity.solidArea.intersects(gp.obj[i].solidArea) && entity.worldY <= gp.obj[i].worldY - 48) {
						 entity.worldY = gp.obj[i].worldY - 48;
						 entity.speedY = 1;
						 entity.collisionOnY = true;
						 entity.inAir = false;
						 entity.onGround = true;
					 }
					 break;
				 }
			
				 
				 if(entity.direction == "" || 
						 gp.obj[i].worldY - entity.worldY > 48 ||
						 entity.worldY - gp.obj[i].worldY > 48 ) {
					 gp.obj[i].collision = false;
					 entity.collisionOn2[i] = false;
					 gp.moveobj[i] = false;
					 gp.obj[i].slideOn = false;
					 gp.obj[i].direction = "";
					 gp.obj[i].speedobj = 0;
					 entity.moveObj = false;
				 }
				 if(entity.collisionOn2[i] == true) {
					 gp.moveobj[i] = true;
					 gp.sttobj = i;
					 entity.moveObj = true;
					 gp.obj[i].direction = entity.direction;
					 gp.obj[i].update(gp);
					 gp.upobj[i] = true;
				 }
				 
				 entity.solidArea.x = entity.solidAreaDefaultX;
				 entity.solidArea.y = entity.solidAreaDefaultY;
				 gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				 gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY; 
				
			}
		}
	}
	
	public void checkTileObject(SuperObject box) {
		int entityLeftWorldX = box.worldX;
		int entityRightWorldX = box.worldX + gp.tileSize;
		int entityTopWorldY = box.worldY;
		int entityBottomWorldY = box.worldY + gp.tileSize;
	
		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int tmpLeftCol = entityLeftCol;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int tmpRightCol = entityRightCol;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(box.direction) {
		case "left":
			entityLeftCol = (int) ((entityLeftWorldX - box.speedobj) / gp.tileSize);
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				box.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (int) ((entityRightWorldX + box.speedobj) / gp.tileSize);
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				box.collisionOn = true;
			}
			break;
		}
		switch(box.directionY) {
		case "up":
			entityTopRow = (int) ((entityTopWorldY + box.speedY) / gp.tileSize);
			tileNum1 = gp.tileM.mapTileNum[tmpLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[tmpRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				box.collisionOnY = true;
				box.onGround = false;
//				entity.speedY = 0;
			}
			break;
		case "down":
			entityBottomRow = (int) ((entityBottomWorldY + box.speedY) / gp.tileSize);
			tileNum1 = gp.tileM.mapTileNum[tmpLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[tmpRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				box.collisionOnY = true;
				box.speedY = 1;
				box.inAir = false;
				box.onGround = true;
			}
			break;
		}
	}
	
}
