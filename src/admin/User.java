package admin;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import admin_frame.LoginFrame;
import admin_frame.mainFrame;
import admin_frame.selfFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public  class User {
	private String name;
	private String password;
	private String role;
	
	public User(String name,String password,String role){
		this.name=name;
		this.password=password;
		this.role=role;				
	}
	
	public void  changeSelfInfo() {
		JFrame selfframe = new selfFrame();
		JTextField userNameText= (JTextField) selfframe.getContentPane().getComponent(5);
		JTextField userRole = (JTextField) selfframe.getContentPane().getComponent(9);
		JTextField oldCode = (JTextField)selfframe.getContentPane().getComponent(6);
		JTextField newCode = (JTextField)selfframe.getContentPane().getComponent(7);
		JTextField cirnewCode = (JTextField)selfframe.getContentPane().getComponent(8);
		JButton okButton = (JButton)selfframe.getContentPane().getComponent(10);
		JButton canclButton = (JButton)selfframe.getContentPane().getComponent(11);
		
		userNameText.setEditable(false);
		userRole.setEditable(false);
		userNameText.setText(this.getName());
		userRole.setText(this.getRole());
		User user = LoginFrame.getUser();
		
		canclButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				selfframe.dispose();
			}
		});
		
		okButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				if(oldCode.getText().equals(user.getPassword())) {
					if(newCode.getText().equals(cirnewCode.getText())) {
						try {
							DataProcessing.updateUser(user.getName(), newCode.getText(), user.getRole());
							selfframe.dispose();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else {System.out.println("两次密码输入不一致");}
				}else {System.out.println("原密码输入有误");}
			}
		});
		
		selfframe.setVisible(true);
	}
	
	
	public void  uploadFile(String id,String pathFile) {
	System.out.println("未实例化");
	}
	
	
	public boolean downloadFile(String id,String path) throws IOException{

		Scanner in = new Scanner(System.in);
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
	
	public void showFileList() throws SQLException, ClassNotFoundException{
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
	
	public void showMenu() {
		System.out.println("未实例化");
		
	}
	
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
	
	public String toString() {
		return this.name+","+this.password+","+this.role;
	}

}
