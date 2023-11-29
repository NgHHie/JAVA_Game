package MainGame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame window = new JFrame();
		GamePanel gamePanel = new GamePanel();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	            // Thực hiện lệnh khi cửa sổ đang đóng
	           gamePanel.updateAndCloseFile();
	        }
	    });
		
		window.setResizable(false);
		window.setTitle("CLONELY HOMO 1");	
		window.add(gamePanel);
		window.pack(); 
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		gamePanel.setupGameStart();
		gamePanel.startGameThread();
	}
}

