package Menu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PauseButton extends Button {
	private BufferedImage img;
	private String path;

	public PauseButton(int x, int y, int width, int height, String path) {
		super(x, y, width, height);
		this.path = path;
		loadImgs();
	}

	private void loadImgs() {
		this.img = GetSpriteAtlas(this.path);
	}
	
	public BufferedImage GetSpriteAtlas(String path) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResourceAsStream(path));;

		} catch(IOException e) {
			e.printStackTrace();
		}
		return img;
	}

	public void update() {		
		//nothing to do
	}
	
	public void draw(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}


}