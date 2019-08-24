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
	
		//compound condition���� 1 �̳� -1�� �ƴҶ��� exception�� ������ �Ϳ� �ð��� �ҾֵǾ���.
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
	
	//���⼭ �̹����� �κи��� ������ ����� ��Ծ���.
	//image���� �־��� ������ ���� �ϱ⶧����, row �� column�� �ƴϴ�. �׷��⶧����, SuperMarioLike�� �ٸ���.
	public BufferedImage grabImage(int x, int y, int w, int h) {
		return image.getSubimage(x, y, w, h);
	}
}
