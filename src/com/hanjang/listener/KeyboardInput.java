package com.hanjang.listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.hanjang.object.Player;
import com.hanjang.object.State;

//Overall Character movement is not clear and organized

public class KeyboardInput extends KeyAdapter{
	
	private Player player;
	
	public KeyboardInput(Player player) {
		this.player = player;
	}
	
	//다음에 이런것을 할 떄에는 각 액션의 프라이오리티를 정해보는 것도 나쁘지 않겠다. 
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode(); 
		
		if(key == KeyEvent.VK_A) {
			//Let the user to move the character even when the character is jumping
			if(player.getState() != State.Jumping)
				player.setState(State.Moving);		
			player.setIsFacing(-1);
			player.setVelX(-5);
		}
		else if(key == KeyEvent.VK_D) {
			if(player.getState() != State.Jumping)
				player.setState(State.Moving);
			player.setIsFacing(1);
			player.setVelX(5);
		}
		else if(key == KeyEvent.VK_W && player.getState() != State.Jumping) {
			player.setState(State.Jumping);
			player.setVelY(-20);
		}	
		else if(key == KeyEvent.VK_S && player.getState() != State.Jumping) {
			//enable the user to stop the character from moving when the crouching is pressed.
			if(player.getState() == State.Moving)
				player.setVelX(0);
			player.setState(State.Crouching);
		}
		else if(key == KeyEvent.VK_K && player.getState() != State.Jumping) {
			if(player.getState() == State.Moving)
				player.setVelX(0);
			player.setState(State.Attacking);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode(); 
		
		if(key == KeyEvent.VK_A) {	
			//enable the user to swap the moving direction of the character smoothly without a pause in the middle
			if(player.getIsFacing() == -1 && (player.getState() == State.Moving || player.getState() == State.Jumping)) {
				if(player.getState() != State.Jumping)
					player.setState(State.Standing);
				player.setVelX(0);				
			}
		}
		else if(key == KeyEvent.VK_D) {	
			//enable the user to swap the moving direction of the character smoothly without a pause in the middle
			if(player.getIsFacing() == 1 && (player.getState() == State.Moving || player.getState() == State.Jumping)) {
				if(player.getState() != State.Jumping)
					player.setState(State.Standing);
				player.setVelX(0);
			}	
		}
		else if(key == KeyEvent.VK_W) {
		}
		else if(key == KeyEvent.VK_S) {
			if(player.getState() != State.Moving && player.getState() != State.Jumping)
				player.setState(State.Standing);
			player.crouchingReset();
		}

	}
}
