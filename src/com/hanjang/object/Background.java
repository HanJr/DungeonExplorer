package com.hanjang.object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Background {
	
	private ArrayList<BufferedImage> bgList;
	int x, y = 0;
	int bgCurrent = 0;
	
	
	public Background(ArrayList<BufferedImage> bgList) {
		this.bgList = bgList;
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
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(bgList.get(bgCurrent), x, y, bgList.get(bgCurrent).getWidth() * 2 , 1080 , null);
	}
}
