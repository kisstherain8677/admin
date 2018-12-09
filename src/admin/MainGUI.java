package admin;

import java.awt.EventQueue;

import javax.swing.JFrame;

import admin_frame.LoginFrame;

public class MainGUI {
	User user;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame loginframe = new LoginFrame();
					loginframe.setVisible(true);
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

}
