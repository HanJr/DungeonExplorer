package com.hanjang.object;

import java.awt.image.BufferedImage;

public class Map {
	private BufferedImage mapBluePrint;
	private Tile[] tiles;
	
	public Map(BufferedImage mapBluePrint, Tile[] tiles) {
		this.mapBluePrint = mapBluePrint;
		this.tiles = tiles;
	}
	
	
}
