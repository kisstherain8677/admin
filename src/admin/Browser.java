package admin;

import java.util.Scanner;

public class Browser extends User {
	
	
	Browser(String name,String password,String role){
		super(name, password, role);
	}
	
	
	public void changeSelfInfo() {
		System.out.println("������������");
		Scanner in = new Scanner(System.in);
		String newp = in.nextLine();
		super.changeSelfInfo(newp);
		
		
	}

	@Override
	public void showMenu() {
		System.out.println("Welcome,Browser.");
		
		while (true) {
			
			System.out.println("Welcome,Browser.Please chose.");
			System.out.println("1------�޸��ҵ���Ϣ");
			System.out.println("2------��ʾ�������ļ�");
			System.out.println("3------�����ļ�");
			System.out.println("0------�˳�ϵͳ");
			
			System.out.println("������ѡ��");
			Scanner scanner = new Scanner(System.in);
			int input = scanner.nextInt();
			
			switch(input) {
			default: break;
			case 0:
				 exitSystem();
				 break;
			case 1:
				changeSelfInfo();
				break;
			case 2:
				showFileList();
				break;
			case 3:
				System.out.println("������Ҫ���ص��ļ�����");
				Scanner fileIn = new Scanner(System.in);
				String filename = fileIn.nextLine();
				downloadFile(filename);
				break;
			
			}
			}
		
	}

}
