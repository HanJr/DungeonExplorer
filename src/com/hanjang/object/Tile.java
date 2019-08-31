package com.hanjang.object;

import java.awt.image.BufferedImage;

public class Tile {
	private BufferedImage tileImage;
	private int width;
	private int height;
	private int red;
	private int green;
	private int blue;
	
	public Tile(BufferedImage tileImage, int width, int height, int red, int green, int blue) {
		this.tileImage = tileImage;
		this.width = width;
		this.height = height;
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
}

