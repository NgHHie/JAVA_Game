package MainGame;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.GraphicAttribute;

import javax.swing.JPanel;

import Objects.SuperObject;
import entity.Clone;
import entity.Entity;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	//SCREEN SETTING
	final int originalTileSize = 16; //16x16 tile
	final int scale = 3; //tăng kích cỡ 3 lần
	
	public final int tileSize = originalTileSize * scale;
	public int maxScreenCol = 16; //có 16 cột
	public int maxScreenRow = 12; //có 12 hàng
	public final int screenWidth = tileSize * maxScreenCol; //768 pixels
	public final int screenHeight = tileSize * maxScreenRow;//576 pixels
	public String[][] movXClone = new String[5][600];
	public String[][] movYClone = new String[5][600];
	public int stt;
	public boolean[] moveobj = new boolean[5];
	public boolean[] upobj = new boolean[5];
	public int sttobj;
	public int quantityClone = 0;
	
	//FPS
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	CheckDead checkDead = new CheckDead(this);
	public Player player = new Player(this, keyH);
	public Clone[] clone = new Clone[5];
	public SuperObject obj[] = new SuperObject[10];

	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		aSetter.setObject();
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {
			// 1 UPDATE: update in4 such as character positions
			update();
			// 2 UPDATE: draw screen with the updated in4
			repaint();
				
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime /= 1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void setSolid(Entity entity, SuperObject obj) {
		entity.solidArea.x += entity.worldX;
		entity.solidArea.y += entity.worldY;
		obj.solidArea.x = obj.worldX + obj.solidArea.x;
		obj.solidArea.y = obj.worldY + obj.solidArea.y;
	}
	public void setDefaultSolid(Entity entity, SuperObject obj) {
		 entity.solidArea.x = entity.solidAreaDefaultX;
		 entity.solidArea.y = entity.solidAreaDefaultY;
		 obj.solidArea.x = obj.solidAreaDefaultX;
		 obj.solidArea.y = obj.solidAreaDefaultY; 
	}
	
	public void update() {
		for(int i=0; i<5; i++) upobj[i] = false;
		player = (Player)checkDead.checkDeadPlayer(player);
		if(player != null) {
			player.update();
		}

		for(int i=0; i<5; i++) {
			clone[i] = (Clone)checkDead.checkDeadPlayer(clone[i]);
			if(clone[i] != null) {
				stt = i;
				clone[i].update();
				
			}
		}
		for(int i=0; i<5; i++) {
			if(obj[i] != null && upobj[i] == false) {
				sttobj = i;
				obj[i].update();
			}
		}
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;

		tileM.draw(g2);
		for(int i=0; i<obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		
		for(int i=0; i<5; i++) {
			if(clone[i] != null && clone[i].exist == true) {
				clone[i].draw(g2);
			}
		}
		if(player != null) player.draw(g2);
		
		g2.dispose();
	}
	

}
