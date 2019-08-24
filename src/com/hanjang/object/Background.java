package com.hanjang.object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Background {
	
	private ArrayList<BufferedImage> bgList;
	private BufferedImage bgImage;
	int x, y = 0;
	int bgCurrent = 0;
	
	
	public Background(ArrayList<BufferedImage> bgList) {
		this.bgList = bgList;
		bgImage = bgList.get(bgCurrent);
	}
	
	public Background(BufferedImage bgImage) {
		this.bgImage = bgImage;
	}
	
	public int getX() {
		return x;
	}
	
	public void tick() {
		x--;
		
		if(x <= -bgList.get(bgCurrent).getWidth() / 2) {
			x = 0;
			bgCurrent++;
			if(bgCurrent >= bgList.size())
				bgCurrent = 0;
			bgImage = bgList.get(bgCurrent);
		}
	}
	
	public void render(Graphics g, int scale) {
		g.drawImage(bgImage, x, y, bgImage.getWidth() * scale , 1080 , null);
	}
}
