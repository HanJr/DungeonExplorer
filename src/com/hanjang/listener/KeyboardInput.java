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
		else if(key == KeyEvent.VK_W && player.getState() != State.Jumping) {
			player.setState(State.Jumping);
			player.setVelY(-15);
		}	
		else if(key == KeyEvent.VK_S) {
			//enable the user to stop the character from moving when the crouching is pressed.
			if(player.getState() == State.Moving)
				player.setVelX(0);
			player.setState(State.Crouching);
		}
		else if(key == KeyEvent.VK_K) {
			player.setState(State.Attacking);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode(); 
		
		if(key == KeyEvent.VK_A) {	
			//enable the user to swap the moving direction of the character smoothly without a pause in the middle
			if(player.getIsFacing() == -1 && player.getState() == State.Moving) {
				player.setState(State.Standing);
				player.setVelX(0);				
			}
		}
		else if(key == KeyEvent.VK_D) {	
			//enable the user to swap the moving direction of the character smoothly without a pause in the middle
			if(player.getIsFacing() == 1 && player.getState() == State.Moving) {
				player.setState(State.Standing);
				player.setVelX(0);
			}	
		}
		else if(key == KeyEvent.VK_W) {
			player.setState(State.Standing);
		}
		else if(key == KeyEvent.VK_S) {
			if(player.getState() != State.Moving)
				player.setState(State.Standing);
			player.crouchingReset();
		}
	}
}
