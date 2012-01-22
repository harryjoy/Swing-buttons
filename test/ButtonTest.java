package test;

import harsh.p.raval.buttons.AngularButton;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ButtonTest {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(250,250);
		frame.setLayout(null);
		JButton button = new AngularButton("test");
		button.setBackground(Color.yellow);
		button.setBounds(20, 20, 100, 100);
		frame.add(button);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}