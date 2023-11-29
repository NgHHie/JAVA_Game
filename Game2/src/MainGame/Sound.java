package MainGame;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	private Clip clip;
	private URL soundURL[] = new URL[30];
	private FloatControl fc;
	private float volumn ;
	private GamePanel gp;
	
	public Sound(GamePanel gp) {
		this.gp = gp;
		soundURL[0] = getClass().getResource("/sound/backsound.wav");
		soundURL[1] = getClass().getResource("/sound/click.wav");
		soundURL[2] = getClass().getResource("/sound/jump.wav");
		soundURL[3] = getClass().getResource("/sound/gameover.wav");
		soundURL[4] = getClass().getResource("/sound/win.wav");	
		soundURL[5] = getClass().getResource("/sound/cua_thang_may.wav");
	}
	
	public void setFile(int i) {
		try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (Exception ex) {
 //       	ex.printStackTrace();
            // Xử lý hoặc thông báo lỗi theo nhu cầu của bạn
        }
	}
	
	public void play() {
		if(gp.soundOn == true) clip.start();
    }
	
	public void loop() {
         clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
         clip.stop();

    }
	
    public void setVolumn(int volumnScale) {
        switch (volumnScale) {
            case 0:
                volumn = -80f;
                break;
            case 1:
                volumn = -5f;
                break;
            case 2:
                volumn = -20f;
                break;
        }


    }
	
	
}
