package admin;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import admin_frame.LoginFrame;
import admin_frame.mainFrame;
import admin_frame.selfFrame;
public class Administrator extends User {

	Administrator(String name, String password, String role) {
		super(name, password, role);
		// TODO Auto-generated constructor stub
	}

	
	public void showMenu() {
		try {
			JFrame mainFrame = new mainFrame();
			mainFrame.setVisible(true);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
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
	
//	public void changeSelfInfo() {
//
//	}
//	
	
	

}
