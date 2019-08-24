package com.hanjang.object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Block {
	private BufferedImage blockImage;
	private int x, y;
	
	
	public Block(int x, int y, BufferedImage blockImage) {
		this.blockImage = blockImage;
		this.x = x;
		this.y = y;
	}
	
	public void render(Graphics g) {
		g.drawImage(blockImage, x, y, 80, 80, null);
	}
}
