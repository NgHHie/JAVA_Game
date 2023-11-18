package MainGame;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("2D Hiep");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack(); //màn hình được chỉnh kích thước giống với khởi tạo trong GamePanel
		
		
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.setupGame();
		gamePanel.startGameThread();
	}

}
