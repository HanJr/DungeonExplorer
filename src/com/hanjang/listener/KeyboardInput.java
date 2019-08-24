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
			player.setState(State.MovingLeft);			
			player.setVelX(-5);
		}
		else if(key == KeyEvent.VK_D) {
			player.setState(State.MovingRight);
			player.setVelX(5);
		}
		else if(key == KeyEvent.VK_W) {
			
		}
		else if(key == KeyEvent.VK_S) {
			
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode(); 
		
		if(key == KeyEvent.VK_A) {
			player.setState(State.StandingL);	
			player.setVelX(0);
		}
		else if(key == KeyEvent.VK_D) {
			player.setState(State.StandingR);	
			player.setVelX(0);
		}
		else if(key == KeyEvent.VK_W) {
			
		}
		else if(key == KeyEvent.VK_S) {
			
		}
	}
}
