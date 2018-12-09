package admin;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;



public class Main {
  
	public static void main(String[] args) {
		
		while(true) {
			System.out.println("****欢迎进入档案管理系统****");
			System.out.println("1------登陆");
			System.out.println("2------退出");
			System.out.println("***************************");
			Scanner startInput = new Scanner(System.in);
			int input = startInput.nextInt();
			
			switch(input) {
			default:System.out.println("无效的输入");break;
			case 1:
				System.out.println("请输入用户名");
				Scanner scanner = new Scanner(System.in);
				String name = scanner.nextLine();
				System.out.println("请输入密码");
				String password = scanner.nextLine();
				
				try {
					User user = DataProcessing.searchUser(name,password);
					user.showMenu();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.toString());
				}
				
				
				
				break;
			case 2:
				System.out.println("系统退出，谢谢使用！");
				startInput.close();
				System.exit(0);
			}
			
		}
		
		
		
	}
	}


