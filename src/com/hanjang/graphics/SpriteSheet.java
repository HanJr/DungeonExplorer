package com.hanjang.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage image;
	private BufferedImage[] imageSet;
	
	public SpriteSheet(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage[] grabImageSet(int startImageX, int imageWidth, int imageHeight, int numOfImages, int editingDirection) {
		int startX = startImageX;
	
		//compound condition에서 1 이나 -1이 아닐때에 exception을 던지는 것에 시간이 할애되었다.
		if(editingDirection != 1 && editingDirection != -1) {
			throw new IllegalArgumentException();
		}
		imageSet = new BufferedImage[numOfImages];
		
		
		for(int imageIndex = 0; imageIndex < numOfImages; imageIndex++) {
			imageSet[imageIndex] = grabImage(startX, 0, imageWidth, imageHeight);
			startX += imageWidth * editingDirection;
		}
		return imageSet;
	}
	
	//여기서 이미지의 부분만을 빼오는 방법을 까먹었다.
	//image에서 주어진 정보를 토대로 하기때문에, row 나 column이 아니다. 그렇기때문에, SuperMarioLike과 다른것.
	public BufferedImage grabImage(int x, int y, int w, int h) {
		return image.getSubimage(x, y, w, h);
	}
}
