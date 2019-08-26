package com.hanjang.object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.hanjang.graphics.Animation;
import com.hanjang.graphics.ImageLoader;
import com.hanjang.graphics.SpriteSheet;

public class Player extends GameObject{
	private int velX, velY = 0;
	private int gravity = 1;
	private int isFacing = 1; //1 right
	
	private SpriteSheet defaultRHeroSs = new SpriteSheet(new ImageLoader().getImage("/defaultRHero.png"));
	private SpriteSheet defaultLHeroSs = new SpriteSheet(new ImageLoader().getImage("/defaultLHero.png"));
	private SpriteSheet movingRHeroSs = new SpriteSheet(new ImageLoader().getImage("/movingRHero.png"));
	private SpriteSheet movingLHeroSs = new SpriteSheet(new ImageLoader().getImage("/movingLHero.png"));

	private SpriteSheet jumpRHeroSs = new SpriteSheet(new ImageLoader().getImage("/jumpRHero.png"));
	private SpriteSheet crouchRHeroSs = new SpriteSheet(new ImageLoader().getImage("/crouchRHero.png"));
	private SpriteSheet attackRHeroSs = new SpriteSheet(new ImageLoader().getImage("/attackRHero.png"));
	private SpriteSheet jumpLHeroSs = new SpriteSheet(new ImageLoader().getImage("/jumpLHero.png"));
	private SpriteSheet crouchLHeroSs = new SpriteSheet(new ImageLoader().getImage("/crouchLHero.png"));
	private SpriteSheet attackLHeroSs = new SpriteSheet(new ImageLoader().getImage("/attackLHero.png"));
	
	private BufferedImage defaultRImages[];
	private BufferedImage defaultLImages[];
	private BufferedImage movingRImages[];
	private BufferedImage movingLImages[];
	
	private BufferedImage jumpRImages[];
	private BufferedImage crouchRImages[];
	private BufferedImage attackRImages[];
	private BufferedImage jumpLImages[];
	private BufferedImage crouchLImages[];
	private BufferedImage attackLImages[];			
	
	private State state = State.Standing;
	
	private Animation defaultRAnimation;
	private Animation defaultLAnimation;
	private Animation movingLAnimation;
	private Animation movingRAnimation;
	private Animation jumpRAnimation;
	private Animation jumpLAnimation;
	private Animation crouchRAnimation;
	private Animation crouchLAnimation;
	private Animation attackRAnimation;
	private Animation attackLAnimation;
	
	public Player(int x, int y, int width, int height) {
		super(x, y, width, height); //may need modification for jumping / crouching
		
		defaultRImages = defaultRHeroSs.grabImageSet(0, 38, 48, 4, 1);
		defaultLImages = defaultLHeroSs.grabImageSet(114, 38, 48, 4, -1); //starting drawing x coord, image width, height, numOfImage, direction
		movingRImages = movingRHeroSs.grabImageSet(0, 66, 48, 12, 1);
		movingLImages = movingLHeroSs.grabImageSet(726, 66, 48, 12, -1);
		
		jumpRImages = jumpRHeroSs.grabImageSet(0, 61, 77, 5, 1);
		crouchRImages = crouchRHeroSs.grabImageSet(0, 48, 48, 3, 1);
		attackRImages = attackRHeroSs.grabImageSet(0, 96, 48, 6, 1);
		jumpLImages = jumpLHeroSs.grabImageSet(244, 61, 77, 5, -1);
		crouchLImages = crouchLHeroSs.grabImageSet(96, 48, 48, 3, -1);
		attackLImages = attackLHeroSs.grabImageSet(480, 96, 48, 6, -1);	
		
		defaultRAnimation = new Animation(7, defaultRImages, this);
		defaultLAnimation = new Animation(7, defaultLImages, this);
		movingRAnimation = new Animation(4, movingRImages, this);
		movingLAnimation = new Animation(4, movingLImages, this);
		
		jumpRAnimation = new Animation(5, jumpRImages, this);
		jumpLAnimation = new Animation(5, jumpLImages, this);
		crouchRAnimation = new Animation(5, crouchRImages, this); 
		crouchLAnimation = new Animation(5, crouchLImages, this);
		attackRAnimation = new Animation(5, attackRImages, this);
		attackLAnimation = new Animation(5, attackLImages, this);
	}
	
	public void tick() {
		setX(getX() + velX);
		setY(getY() + velY);
		
		if(isFacing == 1) {
			if(state == State.Standing) {
				defaultRAnimation.runAnimation();
			}
			else if(state == State.Moving) {
				movingRAnimation.runAnimation();	
			}
			//점프 고쳐야한다. 지속된 입력이 가능한것부터 고쳐라 누르고 있으면 어떻게 작동되는지도 확인하고
			else if(state == State.Jumping) {
				velY += gravity;
				if(getY() > 800)
					velY = 0;
					
				jumpRAnimation.runAnimation();	
			}
			else if(state == State.Crouching) { 
				if(crouchRAnimation.getCurrentFrameIndex() < crouchRAnimation.getLastFrameIndex()) {
					crouchRAnimation.runAnimation();
				}
				else if(crouchRAnimation.getCurrentFrameIndex() >= crouchRAnimation.getLastFrameIndex()) {
					crouchRAnimation.pauseAnimation(crouchRAnimation.getLastFrameIndex());
				}
			}
			else if(state == State.Attacking) {
				if(attackRAnimation.getCurrentFrameIndex() < attackRAnimation.getLastFrameIndex()) {
					attackRAnimation.runAnimation();
				}
				else if(attackRAnimation.getCurrentFrameIndex() >= attackRAnimation.getLastFrameIndex()) {
					state = State.Standing;
					attackRAnimation.setCurrentFrameIndex(0);
				}
			}
		}
		else if(isFacing == -1) {
			if(state == State.Standing) {
				defaultLAnimation.runAnimation();
			}
			else if(state == State.Moving) {
				movingLAnimation.runAnimation();	
			}
			else if(state == State.Jumping) {
				jumpLAnimation.runAnimation();
			}
			else if(state == State.Crouching) {
				if(crouchLAnimation.getCurrentFrameIndex() < crouchLAnimation.getLastFrameIndex()) {
					crouchLAnimation.runAnimation();
				}
				else if(crouchLAnimation.getCurrentFrameIndex() >= crouchLAnimation.getLastFrameIndex()) {
					crouchLAnimation.pauseAnimation(crouchLAnimation.getLastFrameIndex());
				}
			}
			else if(state == State.Attacking) {
				if(attackLAnimation.getCurrentFrameIndex() < attackLAnimation.getLastFrameIndex()) {
					attackLAnimation.runAnimation();
				}
				else if(attackLAnimation.getCurrentFrameIndex() >= attackLAnimation.getLastFrameIndex()) {
					state = State.Standing;
					attackLAnimation.setCurrentFrameIndex(0);
				}
			}			
		}
	}
	
	public State getState() {
		return state;
	}
	
	public void crouchingReset() {
		crouchRAnimation.setCurrentFrameIndex(0);
		crouchLAnimation.setCurrentFrameIndex(0);
	}
	
	public int getIsFacing() {
		return isFacing;
	}

	public void setIsFacing(int isFacing) {
		this.isFacing = isFacing;
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
		if(isFacing == 1) {
			if(state == State.Standing) {
				defaultRAnimation.render(g, 3, 3);
			}
			else if(state == State.Moving) {
				movingRAnimation.render(g, 4, 3);			
			}
			else if(state == State.Jumping) {
				jumpRAnimation.render(g, 5, 4);			
			}
			else if(state == State.Crouching) {
				crouchRAnimation.render(g, 3, 3);
			}
			else if(state == State.Attacking) {
				attackRAnimation.render(g, 5, 3);
			}
		}
		else if(isFacing == -1) {
			if(state == State.Standing) {
				defaultLAnimation.render(g, 3, 3);		
			}
			else if(state == State.Moving) {
				movingLAnimation.render(g, 4, 3);		
			}
			else if(state == State.Jumping) {
				jumpLAnimation.render(g, 5, 4);	
			}
			else if(state == State.Crouching) {
				crouchLAnimation.render(g, 3, 3);
			}
			else if(state == State.Attacking) {
				attackLAnimation.render(g, 5, 3);
			}			
		}
	}
}
