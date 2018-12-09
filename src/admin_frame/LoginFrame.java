package admin_frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import admin.DataProcessing;
import admin.User;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame {

	static User user; 
	private static JFrame loginFrame;
	private static JPanel loginPane;
	private JTextField textField;
	private JPasswordField passwordField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginFrame = new LoginFrame();
					loginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setTitle("系统登陆");
		setSize(380,200);
		Toolkit toolkit = getToolkit();
		Dimension dimension = toolkit.getScreenSize();
		int screenHeight = dimension.height;
		int screenWidth = dimension.width;
		int frm_height = this.getHeight();
		int frm_width = this.getWidth();
		this.setLocation((screenWidth-frm_width)/2, (screenHeight-frm_height)/2);
		this.setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		loginPane = new JPanel();
		loginPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(loginPane);
		loginPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D");
		lblNewLabel.setBounds(65, 56, 72, 18);
		loginPane.add(lblNewLabel);
		
		JLabel label = new JLabel("\u5BC6\u7801");
		label.setBounds(65, 98, 72, 18);
		loginPane.add(label);
		
		JLabel label_1 = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u6587\u4EF6\u7BA1\u7406\u7CFB\u7EDF");
		label_1.setBounds(107, 13, 159, 18);
		loginPane.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(140, 53, 86, 24);
		loginPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(140, 95, 86, 24);
		loginPane.add(passwordField);
		
		JButton button = new JButton("登陆");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String name = textField.getText();
				char[] password = passwordField.getPassword();
				
				try {
					 user = DataProcessing.searchUser(name, String.valueOf(password));
					if(user!=null) {
						user.showMenu();
						dispose();
//					    mainFrame mainframe = new mainFrame();
//						mainframe.setVisible(true);
					}
					else {
						System.out.println("用户不存在或密码错误");
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		button.setBounds(0, 126, 113, 27);
		loginPane.add(button);
		
		JButton button_cancel = new JButton("取消");
		button_cancel.setBounds(249, 126, 113, 27);
		loginPane.add(button_cancel);
		button_cancel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				loginFrame.dispose();
				System.exit(0);
			}
		});
	}
	
	
	public JFrame getloginFrame() {
		return this.loginFrame;
	}
	
	public static User getUser() {
		return user;
	}
	
}
