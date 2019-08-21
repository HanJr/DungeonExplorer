package com.hanjang.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.hanjang.object.Button;

public class MouseInput extends MouseAdapter{
	
	ArrayList<Button> buttonList = new ArrayList<Button>();
	
	public MouseInput(ArrayList<Button> buttonList) {
		this.buttonList = buttonList;
	}
	
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if(x > buttonList.get(2).getX() && x < buttonList.get(2).getX() + buttonList.get(2).getWidth()) {
			if(y > buttonList.get(2).getY() && y < buttonList.get(2).getY() + buttonList.get(2).getHeight()) {
				System.exit(0);
			}
		}
	}
}
