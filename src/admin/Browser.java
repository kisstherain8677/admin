package admin;

import java.awt.EventQueue;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JFrame;

import admin_frame.LoginFrame;
import admin_frame.mainFrame;

public class Browser extends User {
	
	
	Browser(String name,String password,String role){
		super(name, password, role);
	}
	
	
//	public void changeSelfInfo() {
//		
//	}

	public void showMenu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame mf = new mainFrame();
					mf.getJMenuBar().getMenu(0).setEnabled(false);
					mf.getJMenuBar().getMenu(1).getItem(1).setEnabled(false);
					mf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
//	@Override
//	public void showMenu()  {
//		System.out.println("Welcome,Browser.");
//		
//		while (true) {
//			
//			System.out.println("Welcome,Browser.Please chose.");
//			System.out.println("1------修改我的信息");
//			System.out.println("2------显示可下载文件");
//			System.out.println("3------下载文件");
//			System.out.println("0------退出系统");
//			
//			System.out.println("请输入选项");
//			Scanner scanner = new Scanner(System.in);
//			int input = scanner.nextInt();
//			
//			switch(input) {
//			default: System.out.println("无效的输入");break;
//			case 0:
//				 exitSystem();
//				 scanner.close();
//				 break;
//			case 1:
//				changeSelfInfo();
//				break;
//			case 2:
//				try {
//					showFileList();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					System.out.println(e.toString());
//				}
//				break;
//			case 3:
//				System.out.println("请输入要下载的文件名。");
//				Scanner fileIn = new Scanner(System.in);
//				String filename = fileIn.nextLine();
//				try {
//					downloadFile(filename);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					System.out.println(e.toString());
//				}
//				
//				break;
//			
//			}
//			}
//		
//	}

}
