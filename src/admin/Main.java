package admin;
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
			default:break;
			case 1:
				System.out.println("�������û���");
				Scanner scanner = new Scanner(System.in);
				String name = scanner.nextLine();
				System.out.println("����������");
				String password = scanner.nextLine();
				
				if(DataProcessing.users.containsKey(name)) {
					User temp = DataProcessing.users.get(name);
					if(password.equals(temp.getPassword())) {
					temp.showMenu();
					}
					else {
						System.out.println("�������");
					}
					
				}
				
				else
					System.out.println("�û�������");
				break;
			case 2:
				System.out.println("ϵͳ�˳���ллʹ�ã�");
				System.exit(0);
			}
			
		}
		
		
		
	}
	}


