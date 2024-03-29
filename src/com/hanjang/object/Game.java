package com.hanjang.object;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.hanjang.graphics.ImageLoader;
import com.hanjang.graphics.SpriteSheet;
import com.hanjang.listener.KeyboardInput;
import com.hanjang.listener.MouseInput;
import com.hanjang.listener.MouseMotionInput;


public class Game extends Canvas implements Runnable{
	
	private Thread thread;
	private boolean isRunning = false;
	private ImageLoader loader;
	private Camera cam;
	
	private Button playButton;
	private Button helpButton;
	private Button exitButton;
	
	private ArrayList<Button> buttonList = new ArrayList<Button>();
	private ArrayList<BufferedImage> bgImages = new ArrayList<BufferedImage>();
	
	private Background lobbyBg;
	private Background gameBg;
	private BufferedImage title;
	
	private Player player;
	
	private State gameState;
	
	public void init() {
		loader = new ImageLoader();
		
		bgImages.add(loader.getImage("/lobby_background_1.png"));
		bgImages.add(loader.getImage("/lobby_background_2.png"));
		bgImages.add(loader.getImage("/lobby_background_3.png"));
		
		lobbyBg = new Background(bgImages, 2, 1);
		gameBg = new Background(loader.getImage("/gameBg.png"), 4, 4);
		
		title = loader.getImage("/title.png");
		
		playButton = new Button(this.getWidth() / 2 - 100, this.getHeight() / 2, 200, 100, loader.getImage("/b_4.png"), loader.getImage("/b_5.png"), "Play");
		helpButton = new Button(this.getWidth() / 2 - 100, this.getHeight() / 2 + 100, 200 , 100, loader.getImage("/b_4.png"), loader.getImage("/b_5.png"), "Help");
		exitButton = new Button(this.getWidth() / 2 - 100, this.getHeight() / 2 + 200, 200, 100, loader.getImage("/b_4.png"), loader.getImage("/b_5.png"), "Exit");
	
		buttonList.add(playButton);
		buttonList.add(helpButton);
		buttonList.add(exitButton);
	
		gameState = State.MainLobby;
		
		addMouseListener(new MouseInput(buttonList, this));
		
		addMouseMotionListener(new MouseMotionInput(playButton)); //how can I make the Button class to be able to listen the mouse Event? challenge in this class that inherits Canvas
		addMouseMotionListener(new MouseMotionInput(helpButton));
		addMouseMotionListener(new MouseMotionInput(exitButton));
		
		player = new Player(0, 800, 38, 48);
		cam = new Camera(player, this);
		addKeyListener(new KeyboardInput(player));		
	}

	public synchronized void start() {
		if(isRunning)
			return;
		
		isRunning = true;
		
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void close() {
		if(!isRunning)
			return;
		
		isRunning = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	@Override
	public void run() {
		init();		
		
		long lastUpdate = System.nanoTime(); 
		double targetNumOfUpdate = 60;
		double ns = 1000000000 / targetNumOfUpdate;
		double delta = 0;
		
		int update = 0;
		int frame = 0;
		long timer = System.currentTimeMillis();
		
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastUpdate) / ns;
			lastUpdate = now;
			
			if(delta >= 1) {
				tick();
				delta--;
				update++;
			}
			
			render();
			frame++;
			
			if(timer + 1000 <= System.currentTimeMillis()) {
				timer += 1000;
				System.out.println("Update: " + update + " FPS: " + frame);
				update = 0;
				frame = 0;
			}
		}
		
	}
	
	public void tick() {
		lobbyBg.tick();
		player.tick();
		cam.tick();
	}

	//이부분 외우기만하고 정확히 모름. Graphics g 에 어떻게 넘어가고, 어떻게 3중 버퍼가 되는지
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		//bg flickering. fix it
		if(gameState == State.InGame) {
			//- (player.getWidth() * 3) determines the position of the character in camera's view, As it increases, the camera puts the hero more on the left side
			//The same value of the above formula must be used in the Camera class's tick method.
			if(player.getX() >= getWidth() / 2 - (player.getWidth() * 3))
				g2d.translate(cam.getX(), cam.getY());
			gameBg.render(g);
			player.render(g);
		}
		else if(gameState == State.MainLobby) {	
			g2d.translate(lobbyBg.getX(), 0);
			
	
				lobbyBg.render(g);
		
			g2d.translate(-lobbyBg.getX(), -0);		
			
			g.drawImage(title, this.getWidth() / 2 - title.getWidth() / 2, this.getHeight() / 2 - 250, null);
			for(int i = 0; i < buttonList.size(); i++) {
				buttonList.get(i).render(g);
			}
		}
			
		g.dispose();
		bs.show();
		
	}	
	
	public void setGameState(State gameState) {
		this.gameState = gameState;
	}
	
	public State getGameState() {
		return gameState;
	}
}