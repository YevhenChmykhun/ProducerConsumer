package com.yevhenchmykhun.main;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.yevhenchmykhun.view.ThreadWindow;

public class Main {
	public static void main(String[] args) {

		String lookEndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";

		try {
			UIManager.setLookAndFeel(lookEndFeel);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		ThreadWindow test = new ThreadWindow("Thread Window");
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setSize(650, 550);
		test.setLocationRelativeTo(null);
		test.setVisible(true);

		System.out.println("Main completed work.");
	}
}
