package com.hanjang.listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.hanjang.object.Player;
import com.hanjang.object.State;

public class KeyboardInput extends KeyAdapter{
	
	private Player player;
	
	public KeyboardInput(Player player) {
		this.player = player;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode(); 
		
		if(key == KeyEvent.VK_A) {
			player.setState(State.Moving);		
			player.setIsFacing(-1);
			player.setVelX(-5);
		}
		else if(key == KeyEvent.VK_D) {
			player.setState(State.Moving);
			player.setIsFacing(1);
			player.setVelX(5);
		}
		else if(key == KeyEvent.VK_W) {
			player.setState(State.Jumping);
		}	
		else if(key == KeyEvent.VK_S) {
			player.setState(State.Crouching);
		}
		else if(key == KeyEvent.VK_K) {
			player.setState(State.Attacking);

		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode(); 
		
		if(key == KeyEvent.VK_A) {	
			player.setState(State.Standing);
			player.setVelX(0);
		}
		else if(key == KeyEvent.VK_D) {	
			player.setState(State.Standing);
			player.setVelX(0);
		}
		else if(key == KeyEvent.VK_W) {
			player.setState(State.Standing);
		}
		else if(key == KeyEvent.VK_S) {
			player.setState(State.Standing);
		}
	}
}
