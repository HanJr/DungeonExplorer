package com.hanjang.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import com.hanjang.object.Button;

public class MouseMotionInput extends MouseMotionAdapter{
	private Button button;
	
	public MouseMotionInput(Button button) {
		this.button = button;
	}
	
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		button.setCurrentImage(button.getDefaultImage());
		
		if(x > button.getX() && x < button.getWidth() + button.getX()) {
			if(y > button.getY() && y < button.getHeight() + button.getY()) {
				button.setCurrentImage(button.getEnteredImage());
			}
		}
	
			
	}
}
