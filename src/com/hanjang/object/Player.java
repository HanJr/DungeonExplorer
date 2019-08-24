package com.hanjang.object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.hanjang.graphics.Animation;
import com.hanjang.graphics.ImageLoader;
import com.hanjang.graphics.SpriteSheet;

public class Player extends GameObject{
	private int velX, velY = 0;
	private int gravity;
	
	private SpriteSheet defaultRHeroSs = new SpriteSheet(new ImageLoader().getImage("/defaultRHero.png"));
	private SpriteSheet defaultLHeroSs = new SpriteSheet(new ImageLoader().getImage("/defaultLHero.png"));
	private SpriteSheet movingRHeroSs = new SpriteSheet(new ImageLoader().getImage("/movingRHero.png"));
	private SpriteSheet movingLHeroSs = new SpriteSheet(new ImageLoader().getImage("/movingLHero.png"));
	
	private BufferedImage defaultRImages[];
	private BufferedImage defaultLImages[];
	private BufferedImage movingRImages[];
	private BufferedImage movingLImages[];
			
	
	private State state = State.StandingR;
	
	private Animation defaultRAnimation;
	private Animation defaultLAnimation;
	private Animation movingLAnimation;
	private Animation movingRAnimation;
	
	public Player(int x, int y, int width, int height) {
		super(x, y, width, height); //may need modification for jumping / crouching
		
		defaultRImages = defaultRHeroSs.grabImageSet(0, 38, 48, 4, 1);
		defaultLImages = defaultLHeroSs.grabImageSet(114, 38, 48, 4, -1); //starting drawing x coord, image width, height, numOfImage, direction
		movingRImages = movingRHeroSs.grabImageSet(0, 66, 48, 12, 1);
		movingLImages = movingLHeroSs.grabImageSet(726, 66, 48, 12, -1);
		
		defaultRAnimation = new Animation(7, defaultRImages, this);
		defaultLAnimation = new Animation(7, defaultLImages, this);
		movingRAnimation = new Animation(4, movingRImages, this);
		movingLAnimation = new Animation(4, movingLImages, this);
	}
	
	public void tick() {
		setX(getX() + velX);
		if(state == State.StandingR) {
			defaultRAnimation.runAnimation();
		}
		else if(state == State.StandingL) {
			defaultLAnimation.runAnimation();
		}
		else if(state == State.MovingRight) {
			movingRAnimation.runAnimation();
		}
		else if(state == State.MovingLeft) {
			movingLAnimation.runAnimation();
		}
	
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public void render(Graphics g) {
		if(state == State.StandingR) {
			defaultRAnimation.render(g, 3, 3);
		}
		else if(state == State.StandingL) {
			defaultLAnimation.render(g, 3, 3);
		}
		else if(state == State.MovingRight) {
			movingRAnimation.render(g, 4, 3);
		}
		else if(state == State.MovingLeft) {
			movingLAnimation.render(g, 4, 3);
		}
	}
}
