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
//			System.out.println("1------�޸��ҵ���Ϣ");
//			System.out.println("2------��ʾ�������ļ�");
//			System.out.println("3------�����ļ�");
//			System.out.println("0------�˳�ϵͳ");
//			
//			System.out.println("������ѡ��");
//			Scanner scanner = new Scanner(System.in);
//			int input = scanner.nextInt();
//			
//			switch(input) {
//			default: System.out.println("��Ч������");break;
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
//				System.out.println("������Ҫ���ص��ļ�����");
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
