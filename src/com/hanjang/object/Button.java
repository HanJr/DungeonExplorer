package com.hanjang.object;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class Button {
	private int x;
	private int y;
	private int width;
	private int height;
	private String text;
	private BufferedImage defaultImage;
	private BufferedImage enteredImage;
	private BufferedImage currentImage;
	
	public Button(int x, int y, int width, int height, BufferedImage defaultImage, BufferedImage enteredImage, String text) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.defaultImage = defaultImage;
		this.enteredImage = enteredImage;
		currentImage = defaultImage;
		this.text = text;
		
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}	
	
	public int getWidth() {
		return width;
	}	
	
	public int getHeight() {
		return height;
	}	
	
	public String getText() {
		return text;
	}
	
	public BufferedImage getDefaultImage() {
		return defaultImage;
	}

	public BufferedImage getEnteredImage() {
		return enteredImage;
	}	
	
	public void setCurrentImage(BufferedImage currentImage) {
		this.currentImage = currentImage; 
	}
	
	public void render(Graphics g) {
		g.drawImage(currentImage, x, y, width, height, null);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Arial", Font.PLAIN, 30));
		g2d.drawString(text, x + 70, y + 56);

	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}
