package admin;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;



public class Main {
  
	public static void main(String[] args) {
		
		while(true) {
			System.out.println("****��ӭ���뵵������ϵͳ****");
			System.out.println("1------��½");
			System.out.println("2------�˳�");
			System.out.println("***************************");
			Scanner startInput = new Scanner(System.in);
			int input = startInput.nextInt();
			
			switch(input) {
			default:System.out.println("��Ч������");break;
			case 1:
				System.out.println("�������û���");
				Scanner scanner = new Scanner(System.in);
				String name = scanner.nextLine();
				System.out.println("����������");
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
				System.out.println("ϵͳ�˳���ллʹ�ã�");
				startInput.close();
				System.exit(0);
			}
			
		}
		
		
		
	}
	}


