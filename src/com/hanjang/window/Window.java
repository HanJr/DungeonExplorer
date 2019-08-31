package com.hanjang.window;


import java.awt.Dimension;

import javax.swing.JFrame;

import com.hanjang.object.Game;



//JFrame�� ���� ���ΰ� �� �ʿ�. ������ �ʿ����� ��Ȯ�� �𸣸�, �׳� �ܿ����鼭 ���
public class Window extends JFrame{

	public Window(double width, double height, Game game) {
	
		setPreferredSize(new Dimension((int)width, (int)height));
		setUndecorated(true); // make the screen true full screen.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(game);
		pack();
		setVisible(true);
		game.start();
	}
}
