import java.awt.Dimension;
import java.awt.Toolkit;

import com.hanjang.object.Game;
import com.hanjang.window.Window;

public class Main {
	public static double WIDTH;
	public static double HEIGHT;
	
	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		WIDTH = screenSize.getWidth();
		HEIGHT = screenSize.getHeight();
		new Window(WIDTH, HEIGHT, new Game());
	}
}
