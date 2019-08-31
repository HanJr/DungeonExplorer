package com.hanjang.object;

public class Camera {
	private Player player;
	private Game game;
	private float x, y = 0;
	public Camera(Player player, Game game) {
		this.player = player;
		this.game = game;
	}
	
	public void tick() {
		//밑에것은 왜 안돼?
		//int targetX = -(player.getX() + game.getWidth() / 2); 
	
		//이것과 translate 정확히 무엇을 의미하는지 알아야한다. 
		//int targetX = game.getWidth() / 2; 고정된다. 캐릭터의 X가 없으니 배경화면이 고정된다. 
		
		//- (player.getWidth() * 3) determines the position of the character in camera's view, As it increases, the camera puts the hero more on the left side 
		int targetX = -player.getX() - (player.getWidth() * 3) + game.getWidth() / 2;

		//when x is not subtracted, it is infinite. Think why it is like this
		//x += (targetX - x ) * 0.02f;
		
		x = targetX;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
}

