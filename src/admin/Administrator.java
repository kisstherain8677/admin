package admin;//test
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Scanner;
public class Administrator extends User {

	Administrator(String name, String password, String role) {
		super(name, password, role);
		// TODO Auto-generated constructor stub
	}

	public void showMenu() {
		
		while (true) {
		
		System.out.println("Welcome,administrator.Please chose.");
		System.out.println("1------�޸��ҵ���Ϣ");
		System.out.println("2------��ʾ�������ļ�");
		System.out.println("3------�����ļ�");
		System.out.println("4------��ʾ�����û�");
		System.out.println("5------����û�");
		System.out.println("6------ɾ���û�");
		System.out.println("7------�޸��û���Ϣ");
		System.out.println("0------�˳�ϵͳ");
		
		System.out.println("������ѡ��");
		Scanner scanner = new Scanner(System.in);
		int input = scanner.nextInt();
		
		switch(input) {
		default:System.out.println("��Ч������");break;
		case 0:
			scanner.close();
			 exitSystem();
			 break;
		case 1:
			changeSelfInfo();
			break;
		case 2:
			try {
				showFileList();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
			break;
		case 3:
			System.out.println("������Ҫ���ص��ļ�����");
			Scanner fileIn = new Scanner(System.in);
			String filename = fileIn.nextLine();
			try {
				downloadFile(filename);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
			break;
		case 4:
			listUser();
			break;
		case 5:
			addUser();
			break;
		case 6:
			delUser();
			break;
		case 7:
			changeUserInfo();
			break;
		}
		}
	}
	
	
	public Boolean changeUserInfo()  {
		System.out.println("�������û���");
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();
		System.out.println("�������û�����");
		String password = scanner.nextLine();
		System.out.println("������Ҫ�޸ĳ�Ϊ�����");
		String role = scanner.nextLine();
		if(role.equalsIgnoreCase("administrator")||role.equalsIgnoreCase("browser")||role.equalsIgnoreCase("operator"))
		{if(DataProcessing.users.containsKey(name))
			{try {
				DataProcessing.updateUser(name, password, role);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
			System.out.println("�޸ĳɹ�");
			return true;
			}
		else {
			    System.out.println("�û���������");
				return false;
			}
		}
		else {System.out.println("��Ч������");return false;}
	}
	
	
	public Boolean delUser() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("������Ҫɾ�����û���");
		String name = scanner.nextLine();
		if(DataProcessing.users.containsKey(name)) {
			try {
				DataProcessing.deleteUser(name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
			System.out.println("ɾ���ɹ�");
			return true;
		}
		
		else {
			System.out.println("�û���������");
			return false;
		}
		
		
	}
	
	public Boolean addUser() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("������Ҫ��ӵ��û���");
		String name = scanner.nextLine();
		System.out.println("������Ҫ��ӵ��û�����");
		String password = scanner.nextLine();
		System.out.println("������Ҫ��ӵ��û�����(Administrator/Operator/Browser)");
		String role = scanner.nextLine();
		
		if(!(role.equalsIgnoreCase("Administrator")||role.equalsIgnoreCase("Operator")||role.equalsIgnoreCase("Browser")))
		{
			System.out.println("��Ч������");
		    return false;
		}
		
		else {
		try {
			DataProcessing.insertUser(name, password, role);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		System.out.println("��ӳɹ�");
		return true;
		}
	}
	
	
	public Boolean listUser() {
		Enumeration<User> e;
		try {
			e = DataProcessing.getAllUser();
			
			if(e.hasMoreElements()) {
				System.out.println("�û��б�����");
				
				while(e.hasMoreElements()) {
					User temp = e.nextElement();
					System.out.print("name:"+temp.getName()+" password:"+temp.getPassword()+" role:"+temp.getRole());
					System.out.println("");
					
				}
				return true;
				}
				else {
					System.out.println("��ǰϵͳ���κ��û�����");
					return false;
				}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.toString());
			return false;
		}
		
		

	}
	
	public void changeSelfInfo() {
		System.out.println("������������");
		Scanner in = new Scanner(System.in);
		String newp = in.nextLine();
		try {
			super.changeSelfInfo(newp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		
		
	}
	
	
	

}
