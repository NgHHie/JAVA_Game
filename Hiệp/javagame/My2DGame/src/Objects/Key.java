package Objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Key extends SuperObject{
	
	public Key() {
		inAir = true;
		onGround = false;
		speedY = 1;
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/key.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		collision = false;
	}
	

}
