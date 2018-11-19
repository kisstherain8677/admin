package admin;

import java.util.Scanner;

public class Operator extends User {
	
	Operator(String name,String password,String role){
		super(name,password,role);
	}

	@Override
	public void showMenu() {
		
while (true) {
			
			System.out.println("Welcome,operator.Please chose.");
			System.out.println("1------修改我的信息");
			System.out.println("2------显示可下载文件");
			System.out.println("3------下载文件");
			System.out.println("4------上传文件");
			System.out.println("0------退出系统");
			
			System.out.println("请输入选项");
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
				System.out.println("请输入要下载的文件名");
				Scanner fileIn = new Scanner(System.in);
				String filename = fileIn.nextLine();
				downloadFile(filename);
				break;
			case 4:
				uploadFile();
			
			}
			}

	}
	
	
	public void changeSelfInfo() {
		System.out.println("请输入新密码");
		Scanner in = new Scanner(System.in);
		String newp = in.nextLine();
		super.changeSelfInfo(newp);
		
		
	}
	
	public void  uploadFile() {
		
		System.out.println("......upload complete.");
		
	}
	
	

}
