package admin;
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
			default:break;
			case 1:
				System.out.println("请输入用户名");
				Scanner scanner = new Scanner(System.in);
				String name = scanner.nextLine();
				System.out.println("请输入密码");
				String password = scanner.nextLine();
				
				if(DataProcessing.users.containsKey(name)) {
					User temp = DataProcessing.users.get(name);
					if(password.equals(temp.getPassword())) {
					temp.showMenu();
					}
					else {
						System.out.println("密码错误");
					}
					
				}
				
				else
					System.out.println("用户不存在");
				break;
			case 2:
				System.out.println("系统退出，谢谢使用！");
				System.exit(0);
			}
			
		}
		
		
		
	}
	}


