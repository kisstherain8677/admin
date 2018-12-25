package admin;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;

import admin_frame.LoginFrame;

public class MainGUI {
	public static  User user;
	public static DocClient application;
	public static void main(String[] args) {
		   if ( args.length == 0 )
		      application = new DocClient( "127.0.0.1" ); // connect to localhost
		   else
		      application = new DocClient( args[ 0 ] ); // use args to connect
		   
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
		   
		      try {
				application.runClient();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // run client application
		
	} 	

}
