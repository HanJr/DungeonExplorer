package com.hanjang.object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Background {
	
	private ArrayList<BufferedImage> bgList;
	private BufferedImage bgImage;
	int x, y = 0;
	int bgCurrent = 0;
	int scale;
	int numRepeat;
	
	public Background(ArrayList<BufferedImage> bgList, int scale, int numRepeat) {
		this.bgList = bgList;
		bgImage = bgList.get(bgCurrent);
		this.scale = scale;
		this.numRepeat = numRepeat;
	}
	
	public Background(BufferedImage bgImage, int scale, int numRepeat) {
		this.bgImage = bgImage;
		this.scale = scale;
		this.numRepeat = numRepeat;
	}
	
	public int getX() {
		return x;
	}
	
	public void tick() {
		x--;	
		//move to next bg, only for lobby 
		if(x <= -bgList.get(bgCurrent).getWidth() / 2) {
			x = 0;
			bgCurrent++;
			if(bgCurrent >= bgList.size())
				bgCurrent = 0;
			bgImage = bgList.get(bgCurrent);
		}
	}
	
	//왜 룹 사용이 안되는거야? x의 update트랙을 잘 쫓아가야한다.
	public void render(Graphics g) {
			int firstX = x;
			for(int i = 0; i < numRepeat; i++) {
				g.drawImage(bgImage, x, y, bgImage.getWidth() * scale , 1080, null);
				x += bgImage.getWidth() * scale;
			}
			
			x = firstX;
	}
}
