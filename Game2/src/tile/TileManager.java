package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;

import javax.imageio.ImageIO;

import MainGame.GamePanel;

public class TileManager {
	private GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];

	public TileManager(GamePanel gp) {
		this.gp = gp;
		this.tile = new Tile[10];
		this.mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

		getTileImage();
		loadMap("/maps/map01.txt");
	}
	
	
	//choose map
	public void chooseMap(String map) {
		loadMap(map);
	}

	private void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tile[1].collision = true;

			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			tile[2].collision = true;

			tile[3] = new Tile();
			tile[3].name = "Player";
			if (tile[3].name.compareTo("Player") == 0) {
				tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/noplayer.png"));
			} else {
				tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/noclone.png"));
			}

			tile[4] = new Tile();
			tile[4].name = "Clone";
			if (tile[4].name.compareTo("Player") == 0) {
				tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/noplayer.png"));
			} else {
				tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/noclone.png"));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;

			while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
				String line = br.readLine();
				String numbera[] = line.split(" ");
				while (col < gp.maxScreenCol) {
					int num = Integer.parseInt(numbera[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gp.maxScreenCol) {
					col = 0;
					row++;
				}
			}
			br.close();

		} catch (Exception e) {

		}
	}

	public void draw(Graphics2D g2) {
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;

		while (col < gp.maxScreenCol + 1 && row < gp.maxScreenRow) {
			int tileNum = mapTileNum[col][row];
			if(mapTileNum[col][row] != 0)
				g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
			col++;
			x += gp.tileSize;

			if (col == gp.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y += gp.tileSize;
			}
		}
	}
}
