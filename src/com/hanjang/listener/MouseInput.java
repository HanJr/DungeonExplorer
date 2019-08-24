package com.hanjang.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.hanjang.object.Button;
import com.hanjang.object.Game;
import com.hanjang.object.State;

public class MouseInput extends MouseAdapter{
	
	ArrayList<Button> buttonList = new ArrayList<Button>();
	private Game game;
	
	public MouseInput(ArrayList<Button> buttonList, Game game) {
		this.buttonList = buttonList;
		this.game = game;
	}
	
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if(x > buttonList.get(0).getX() && x < buttonList.get(0).getX() + buttonList.get(0).getWidth()) {
			if(y > buttonList.get(0).getY() && y < buttonList.get(0).getY() + buttonList.get(0).getHeight()) {
				game.setGameState(State.InGame);
			}
		}
		
		
		//exit button
		if(x > buttonList.get(2).getX() && x < buttonList.get(2).getX() + buttonList.get(2).getWidth() && game.getGameState() == State.MainLobby) {
			if(y > buttonList.get(2).getY() && y < buttonList.get(2).getY() + buttonList.get(2).getHeight()) {
				System.exit(0);
			}
		}
	}
}
