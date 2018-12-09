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
		System.out.println("1------修改我的信息");
		System.out.println("2------显示可下载文件");
		System.out.println("3------下载文件");
		System.out.println("4------显示所有用户");
		System.out.println("5------添加用户");
		System.out.println("6------删除用户");
		System.out.println("7------修改用户信息");
		System.out.println("0------退出系统");
		
		System.out.println("请输入选项");
		Scanner scanner = new Scanner(System.in);
		int input = scanner.nextInt();
		
		switch(input) {
		default:System.out.println("无效的输入");break;
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
			System.out.println("请输入要下载的文件名。");
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
	
	public void changeSelfInfo() {
		System.out.println("请输入新密码");
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
