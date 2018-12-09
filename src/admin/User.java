package admin;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public abstract class User {
	private String name;
	private String password;
	private String role;
	
	User(String name,String password,String role){
		this.name=name;
		this.password=password;
		this.role=role;				
	}
	
	public boolean changeSelfInfo(String password) throws SQLException{
		//写用户信息到存储
		if (DataProcessing.updateUser(name, password, role)){
			this.password=password;
			System.out.println("修改成功");
			return true;
		}else
			return false;
	}
	
	public boolean downloadFile(String id) throws IOException{
		double ranValue=Math.random();
		if (ranValue>0.9)
			throw new IOException( "Error in accessing file" );
		
		Scanner in = new Scanner(System.in);
		System.out.println("Please input the path you want to download to.");
		String path = in.nextLine();
		try {
			String filename = DataProcessing.searchDoc(id).getFilename();
			FileInputStream fin = new FileInputStream("D://server//"+filename);
			int i,j = 0;
			byte[] content = new byte[fin.available()];
			while((i = fin.read())!=-1) {
				content[j] = (byte) i;
				j++;
			fin.close();
				
			File file = new File(path+"//"+filename);
			FileOutputStream fout = new FileOutputStream(file);	
			for(int i1 =0;i1<content.length;i1++) {
				fout.write(content[i1]);
				
			}
			System.out.println("下载成功！");
			fout.close();
				
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return true;
	}
	
	public void showFileList() throws SQLException{
		double ranValue=Math.random();
		if (ranValue>0.9)
			throw new SQLException( "Error in accessing file DB" );
		Enumeration <Doc> e =DataProcessing.getAllDocs();
		System.out.println("列表如下：");
		while(e.hasMoreElements()) {
			Doc next = e.nextElement();
			System.out.println(next.toString());
			
		}
		
	}
	
	public abstract void showMenu() ;
	
	public void exitSystem(){
		System.out.println("系统退出, 谢谢使用 ! ");
		System.exit(0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

}
