package admin;
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
		default:break;
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
	
	
	public Boolean changeUserInfo() {
		System.out.println("�������û���");
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();
		System.out.println("�������û�����");
		String password = scanner.nextLine();
		System.out.println("������Ҫ�޸ĳ�Ϊ�����");
		String role = scanner.nextLine();
		
		if(DataProcessing.users.containsKey(name))
			{DataProcessing.update(name, password, role);
			System.out.println("�޸ĳɹ�");
			return true;
			}
		else {
			    System.out.println("�û���������");
				return false;
			}
	}
	
	
	public Boolean delUser() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("������Ҫɾ�����û���");
		String name = scanner.nextLine();
		if(DataProcessing.users.containsKey(name)) {
			DataProcessing.delete(name);
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
		DataProcessing.insert(name, password, role);
		System.out.println("��ӳɹ�");
		return true;
		}
	}
	
	
	public Boolean listUser() {
		Enumeration<User> e=DataProcessing.getAllUser();
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
		

	}
	
	public void changeSelfInfo() {
		System.out.println("������������");
		Scanner in = new Scanner(System.in);
		String newp = in.nextLine();
		super.changeSelfInfo(newp);
		
		
	}
	
	
	

}
