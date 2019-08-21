package com.hanjang.window;


import java.awt.Dimension;

import javax.swing.JFrame;

import com.hanjang.object.Game;



//JFrame에 대한 공부가 더 필요. 무엇이 필요한지 정확히 모르며, 그냥 외워가면서 사용
public class Window extends JFrame{
	
	private Game game;
	private double width;
	private double height;	
	
	public Window(double width, double height, Game game) {
		
		this.game = game;
		this.width = width;
		this.height = height;
	
		setPreferredSize(new Dimension((int)width, (int)height));
		setUndecorated(true); // make the screen true full screen.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(game);
		pack();
		setVisible(true);
		game.start();
	}
}
