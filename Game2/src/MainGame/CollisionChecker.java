package MainGame;

import Objects.Key;
import Objects.SuperObject;
import entity.Entity;

public class CollisionChecker {
	private GamePanel gp;
//	public String pastdirection;

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
		
		if(entityLeftCol < 0 || entityRightCol >= gp.maxScreenCol ||
				entityTopRow < 0 || entityBottomRow >= gp.maxScreenRow) return;

		int tileNum1, tileNum2;
		switch (entity.direction) {
		case "left":
			entityLeftCol = (int) ((entityLeftWorldX - entity.speed) / gp.tileSize);
			if(entityLeftCol < 0) return;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true
					|| entity.name.compareTo(gp.tileM.tile[tileNum1].name) == 0
					|| entity.name.compareTo(gp.tileM.tile[tileNum2].name) == 0) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (int) ((entityRightWorldX + entity.speed) / gp.tileSize);
			if(entityRightCol >= gp.maxScreenCol) return;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true
					|| entity.name.compareTo(gp.tileM.tile[tileNum1].name) == 0
					|| entity.name.compareTo(gp.tileM.tile[tileNum2].name) == 0) {
				entity.collisionOn = true;
			}
			break;
		}
		switch (entity.directionY) {
		case "up":
			entityTopRow = (int) ((entityTopWorldY + entity.speedY) / gp.tileSize);
			if(entityTopRow < 0) return;
			tileNum1 = gp.tileM.mapTileNum[tmpLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[tmpRightCol][entityTopRow];
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true
					|| entity.name.compareTo(gp.tileM.tile[tileNum1].name) == 0
					|| entity.name.compareTo(gp.tileM.tile[tileNum2].name) == 0) {
				entity.collisionOnY = true;
				entity.onGround = false;
				entity.speedY = 1;
			}
			break;
		case "down":
			entityBottomRow = (int) ((entityBottomWorldY + entity.speedY) / gp.tileSize);
			if(entityBottomRow >= gp.maxScreenRow) return;
			tileNum1 = gp.tileM.mapTileNum[tmpLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[tmpRightCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true
					|| entity.name.compareTo(gp.tileM.tile[tileNum1].name) == 0
					|| entity.name.compareTo(gp.tileM.tile[tileNum2].name) == 0) {
				entity.collisionOnY = true;
				entity.speedY = 1;
				entity.inAir = false;
				entity.onGround = true;
			}
			break;
		}
	}

	public void checkObject(Entity entity) {
		for (int i = 0; i < 20; i++) {
			if (gp.obj[i] != null) {
				gp.setSolid(entity, gp.obj[i]);

				if (gp.obj[i].name.compareTo("Button") != 0) {
					switch (entity.direction) {
					case "left":
						entity.solidArea.x -= (int) entity.speed;
						break;
					case "right":
						entity.solidArea.x += (int) entity.speed;
						break;
					}
					if (entity.solidArea.intersects(gp.obj[i].solidArea) && entity.dead == false) {
						if (gp.obj[i].name.compareTo("Key") == 0) {
							gp.obj[i].collision = true;
							if (entity.speed == 5) {
								entity.speed = (float) 1.5;
							}
							entity.collisionOn2[i] = true;
							gp.obj[i].speedobj = entity.speed;
						} else if (gp.obj[i].name.compareTo("Elevator") == 0) {
							entity.collisionOn = true;
						}
					} else {
						entity.collisionOn2[i] = false;
					}

					switch (entity.directionY) {
					case "down":
						entity.solidArea.y += entity.speedY;
						if (entity.solidArea.intersects(gp.obj[i].solidArea)
								&& entity.worldY <= gp.obj[i].worldY - gp.obj[i].solidArea.height
								&& gp.obj[i].name.compareTo("Thorn") != 0) {
							entity.speedY = 1;
							entity.collisionOnY = true;
							entity.inAir = false;
							entity.onGround = true;
						}
						break;
					case "up":
						entity.solidArea.y += entity.speedY;
						if (entity.solidArea.intersects(gp.obj[i].solidArea)
								&& entity.worldY >= gp.obj[i].worldY + gp.obj[i].solidArea.height - 16) {
							entity.speedY = 0;
							entity.collisionOnY = true;
						}
						break;
					}

					if (entity.collisionOn2[i] == false ||
							(entity.worldX > gp.obj[i].worldX && entity.direction.compareTo("right") == 0)
							|| (gp.obj[i].worldX > entity.worldX && entity.direction.compareTo("left") == 0)) {
						gp.obj[i].collision = false;
						entity.collisionOn2[i] = false;
						gp.moveobj[i] = false;
						gp.obj[i].slideOn = false;
						gp.obj[i].direction = "";
						gp.obj[i].speedobj = 0;
						entity.moveObj = false;
					}
					if (entity.collisionOn2[i] == true) {
						gp.moveobj[i] = true;
						gp.sttobj = i;
						entity.moveObj = true;
						gp.obj[i].direction = entity.direction;
						gp.upobj[i] = true;
						gp.obj[i].update();
					}

				}

				gp.setDefaultSolid(entity, gp.obj[i]);
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
		
		if(entityLeftCol < 0 || entityRightCol >= gp.maxScreenCol ||
				entityTopRow < 0 || entityBottomRow >= gp.maxScreenRow) return;

		int tileNum1, tileNum2;

		switch (box.direction) {
		case "left":
			entityLeftCol = (int) ((entityLeftWorldX - box.speedobj) / gp.tileSize);
			if(entityLeftCol < 0 || entityRightCol >= gp.maxScreenCol ||
					entityTopRow < 0 || entityBottomRow >= gp.maxScreenRow) return;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				box.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (int) ((entityRightWorldX + box.speedobj) / gp.tileSize);
			if(entityLeftCol < 0 || entityRightCol >= gp.maxScreenCol ||
					entityTopRow < 0 || entityBottomRow >= gp.maxScreenRow) return;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				box.collisionOn = true;
			}
			break;
		}
		switch (box.directionY) {
		case "up":
			entityTopRow = (int) ((entityTopWorldY + box.speedY) / gp.tileSize);
			tileNum1 = gp.tileM.mapTileNum[tmpLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[tmpRightCol][entityTopRow];
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				box.collisionOnY = true;
				box.onGround = false;
//				entity.speedY = 0;
			}
			break;
		case "down":
			entityBottomRow = (int) ((entityBottomWorldY + box.speedY) / gp.tileSize);
			tileNum1 = gp.tileM.mapTileNum[tmpLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[tmpRightCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				box.collisionOnY = true;
				box.speedY = 1;
				box.inAir = false;
				box.onGround = true;
			}
			break;
		}
	}

	public void checkObwOb(SuperObject entity, int k) {
		for (int i = 0; i <= 1; i++) {
			if (gp.obj[i] != null && i != k 
					&& gp.upobj[i] == false
					) {
				int tmpx = entity.solidArea.x;
				int tmpy = entity.solidArea.y;
				entity.solidArea.x = entity.worldX;
				entity.solidArea.y = entity.worldY;
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				
				//
				switch (entity.direction) {
				case "left":
					entity.solidArea.x -= (int) entity.speedobj;
					break;
				case "right":
					entity.solidArea.x += (int) entity.speedobj;
					break;
				}
				if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
					if (gp.obj[i].name.compareTo("Key") == 0) {
						gp.obj[i].collision = true;
//						if (entity.speedobj == 5) {
//							entity.speedobj = (float) 1.5;
//						}
						entity.collisionOn2[i] = true;
						gp.obj[i].speedobj = entity.speedobj;
					} else if (gp.obj[i].name.compareTo("Elevator") == 0) {
						entity.collisionOn = true;
					}
				} else {
					entity.collisionOn2[i] = false;
				}
				//

				switch (entity.directionY) {
				case "down":
					entity.solidArea.y += entity.speedY;
					if (entity.solidArea.intersects(gp.obj[i].solidArea)
							&& entity.worldY <= gp.obj[i].worldY - 48
							) {
//						entity.worldY = gp.obj[i].worldY - 48;
						entity.speedY = 1;
						
						entity.collisionOnY = true;
						entity.inAir = false;
						entity.onGround = true;
						entity.collisionOn2[i] = false;
					}
					break;
				}
				
				if (entity.collisionOn2[i] == false ||
						(entity.worldX > gp.obj[i].worldX && entity.direction.compareTo("right") == 0)
						|| (gp.obj[i].worldX > entity.worldX && entity.direction.compareTo("left") == 0)) {
					gp.obj[i].collision = false;
					entity.collisionOn2[i] = false;
					gp.moveobj[i] = false;
					gp.obj[i].slideOn = false;
					gp.obj[i].direction = "";
					gp.obj[i].speedobj = 0;
				}
				if (entity.collisionOn2[i] == true) {
					gp.moveobj[i] = true;
					gp.sttobj = i;
					gp.obj[i].direction = entity.direction;
					gp.upobj[i] = true;
					gp.obj[i].update();
//					gp.upobj[i] = true;
				}

				entity.solidArea.x = tmpx;
				entity.solidArea.y = tmpy;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;

			}
			if (gp.obj[i] != null && i != k 
					&& gp.upobj[i] == true
					) {
				int tmpx = entity.solidArea.x;
				int tmpy = entity.solidArea.y;
				entity.solidArea.x = entity.worldX;
				entity.solidArea.y = entity.worldY;
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				
				//
				

				switch (entity.directionY) {
				case "down":
					entity.solidArea.y += entity.speedY;
					if (entity.solidArea.intersects(gp.obj[i].solidArea)
							&& entity.worldY <= gp.obj[i].worldY - 48
							) {
//						entity.worldY = gp.obj[i].worldY - 48;
						entity.speedY = 1;
						
						entity.collisionOnY = true;
						entity.inAir = false;
						entity.onGround = true;
						entity.collisionOn2[i] = false;
					}
					break;
				}
				
				

				entity.solidArea.x = tmpx;
				entity.solidArea.y = tmpy;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;

			}
		
		}
		
	}

}