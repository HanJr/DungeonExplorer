package com.hanjang.object;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.hanjang.graphics.ImageLoader;
import com.hanjang.listener.MouseInput;
import com.hanjang.listener.MouseMotionInput;


public class Game extends Canvas implements Runnable{
	
	private Thread thread;
	private boolean isRunning = false;
	private ImageLoader loader;
	
	private Button playButton;
	private Button helpButton;
	private Button exitButton;
	
	private ArrayList<Button> buttonList = new ArrayList<Button>();
	private ArrayList<BufferedImage> bgImages = new ArrayList<BufferedImage>();
	
	private Background bg;
	private BufferedImage title;
	
	public void init() {
		loader = new ImageLoader();
		
		bgImages.add(loader.getImage("/lobby_background_1.png"));
		bgImages.add(loader.getImage("/lobby_background_2.png"));
		bgImages.add(loader.getImage("/lobby_background_3.png"));
		
		bg = new Background(bgImages);

		title = loader.getImage("/title.png");
		
		playButton = new Button(this.getWidth() / 2 - 100, this.getHeight() / 2, 200, 100, loader.getImage("/b_4.png"), loader.getImage("/b_5.png"), "Play");
		helpButton = new Button(this.getWidth() / 2 - 100, this.getHeight() / 2 + 100, 200 , 100, loader.getImage("/b_4.png"), loader.getImage("/b_5.png"), "Help");
		exitButton = new Button(this.getWidth() / 2 - 100, this.getHeight() / 2 + 200, 200, 100, loader.getImage("/b_4.png"), loader.getImage("/b_5.png"), "Exit");
	
		buttonList.add(playButton);
		buttonList.add(helpButton);
		buttonList.add(exitButton);
	
		addMouseListener(new MouseInput(buttonList));
		
		addMouseMotionListener(new MouseMotionInput(playButton)); //how can I make the Button class to be able to listen the mouse Event? challenge in this class that inherits Canvas
		addMouseMotionListener(new MouseMotionInput(helpButton));
		addMouseMotionListener(new MouseMotionInput(exitButton));


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
		bg.tick();
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
		
		g2d.translate(bg.getX(), 0);
		
		bg.render(g);
	
		g2d.translate(-bg.getX(), -0);		
		
		g.drawImage(title, this.getWidth() / 2 - title.getWidth() / 2, this.getHeight() / 2 - 250, null);
		for(int i = 0; i < buttonList.size(); i++) {
			buttonList.get(i).render(g);
		}
		
		g.dispose();
		bs.show();
		
	}	
	
	
}