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
		System.out.println("请输入用户名");
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();
		System.out.println("请输入用户密码");
		String password = scanner.nextLine();
		System.out.println("请输入要修改成为的身份");
		String role = scanner.nextLine();
		if(role.equalsIgnoreCase("administrator")||role.equalsIgnoreCase("browser")||role.equalsIgnoreCase("operator"))
		{if(DataProcessing.users.containsKey(name))
			{try {
				DataProcessing.updateUser(name, password, role);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
			System.out.println("修改成功");
			return true;
			}
		else {
			    System.out.println("用户名不存在");
				return false;
			}
		}
		else {System.out.println("无效的输入");return false;}
	}
	
	
	public Boolean delUser() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入要删除的用户名");
		String name = scanner.nextLine();
		if(DataProcessing.users.containsKey(name)) {
			try {
				DataProcessing.deleteUser(name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
			System.out.println("删除成功");
			return true;
		}
		
		else {
			System.out.println("用户名不存在");
			return false;
		}
		
		
	}
	
	public Boolean addUser() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入要添加的用户名");
		String name = scanner.nextLine();
		System.out.println("请输入要添加的用户密码");
		String password = scanner.nextLine();
		System.out.println("请输入要添加的用户类型(Administrator/Operator/Browser)");
		String role = scanner.nextLine();
		
		if(!(role.equalsIgnoreCase("Administrator")||role.equalsIgnoreCase("Operator")||role.equalsIgnoreCase("Browser")))
		{
			System.out.println("无效的输入");
		    return false;
		}
		
		else {
		try {
			DataProcessing.insertUser(name, password, role);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		System.out.println("添加成功");
		return true;
		}
	}
	
	
	public Boolean listUser() {
		Enumeration<User> e;
		try {
			e = DataProcessing.getAllUser();
			
			if(e.hasMoreElements()) {
				System.out.println("用户列表如下");
				
				while(e.hasMoreElements()) {
					User temp = e.nextElement();
					System.out.print("name:"+temp.getName()+" password:"+temp.getPassword()+" role:"+temp.getRole());
					System.out.println("");
					
				}
				return true;
				}
				else {
					System.out.println("当前系统无任何用户数据");
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
