package com.hanjang.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.hanjang.object.GameObject;

public class Animation {
	
	private int speed;
	private int speedCounter;
	private BufferedImage[] images;
	private int currentImageIndex = 0;
	private GameObject object;
	
	public Animation(int speed, BufferedImage[] images, GameObject object) {
		this.speed = speed;
		speedCounter = speed;
		this.images = images;
		this.object = object;
	}
	
	public void runAnimation() {
		speedCounter--;
		
		if(speedCounter < 0) {
			speedCounter = speed;
			currentImageIndex++;
			if(currentImageIndex >= images.length) 
				currentImageIndex = 0;
		}
	}

	public void pauseAnimation(int indexNumber) {
		currentImageIndex = indexNumber;
	}
	
	public int getCurrentFrameIndex() {
		return currentImageIndex;
	}
	
	public void setCurrentFrameIndex(int indexNumber) {
		currentImageIndex = indexNumber;
	}
	
	public int getLastFrameIndex() {
		return images.length - 1;
	}
	
	public void render(Graphics g, int wScale, int hScale) {
		g.drawImage(images[currentImageIndex], object.getX(), object.getY(), object.getWidth() * wScale , object.getHeight() * hScale, null);
	}
}
