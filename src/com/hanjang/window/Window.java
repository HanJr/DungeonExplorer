package com.hanjang.window;


import java.awt.Dimension;

import javax.swing.JFrame;

import com.hanjang.object.Game;



//JFrame�� ���� ���ΰ� �� �ʿ�. ������ �ʿ����� ��Ȯ�� �𸣸�, �׳� �ܿ����鼭 ���
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
