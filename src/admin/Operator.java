package admin;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

import admin_frame.LoginFrame;
import admin_frame.mainFrame;

public class Operator extends User {
	
	Operator(String name,String password,String role){
		super(name,password,role);
	}

	@Override
	public void showMenu() {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame mf = new mainFrame();
					mf.getJMenuBar().getMenu(0).setEnabled(false);
					mf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
//while (true) {
//			
//			System.out.println("Welcome,operator.Please chose.");
//			System.out.println("1------�޸��ҵ���Ϣ");
//			System.out.println("2------��ʾ�������ļ�");
//			System.out.println("3------�����ļ�");
//			System.out.println("4------�ϴ��ļ�");
//			System.out.println("0------�˳�ϵͳ");
//			
//			System.out.println("������ѡ��");
//			Scanner scanner = new Scanner(System.in);
//			int input = scanner.nextInt();
//			
//			switch(input) {
//			default: System.out.println("��Ч������");break;
//			case 0:
//				scanner.close();
//				exitSystem();
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
//				System.out.println("������Ҫ���ص��ļ�id");
//				Scanner fileIn = new Scanner(System.in);
//				String filename = fileIn.nextLine();
//				try {
//					downloadFile(filename);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					System.out.println(e.toString());
//				}
//				break;
//			case 4:
//				uploadFile();
//			
//			}
//			}

	}
	
	
//	public void changeSelfInfo() {
//
//		
//	}
	
	public void  uploadFile() {
		
		System.out.println("please input the filename.");
		Scanner in = new Scanner(System.in);
		String filename = in.nextLine();
		System.out.println("Please input the ID");
		String id = in.nextLine();
		System.out.println("Please input the creator");
		String creator = in.nextLine();
		System.out.println("Please input the description");
		String description = in.nextLine();
		
		
			try {
				File file = new File("D://client//"+filename);
				FileInputStream fin = new FileInputStream(file);//�Զ�����ת��
				int i,j=0;
				byte[] content = new byte[fin.available()];
				while((i = fin.read())!=-1) {
					content[j] = (byte)i;
					j++;
				}
				System.out.println("�ļ����ݣ�"+new String(content));
				fin.close();
				
				
				
				File cfile = new File("D://server//"+filename);
				FileOutputStream fout = new FileOutputStream(cfile);
				for(int i1=0;i1<content.length;i1++) {
					fout.write(content[i1]);
				}
				fout.close();
				
			}catch (IOException e) {
				System.out.println(e.getMessage());
			}
		
			
			try {
				DataProcessing.insertDoc(id, creator, new Timestamp(System.currentTimeMillis()), description, filename);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		System.out.println("upload complete.");
		}
		
		
	}
	
	


