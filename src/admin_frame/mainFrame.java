package admin_frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import admin.User;

import java.awt.FlowLayout;
import java.awt.MenuBar;

import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class mainFrame extends JFrame {

	private JPanel contentPane;
	
	

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu userMenu = new JMenu("用户管理");
		menuBar.add(userMenu);
		
		JMenuItem item_changeUser = new JMenuItem("修改用户");
		item_changeUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					userFrame userframe = new userFrame();
					userframe.setVisible(true);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		userMenu.add(item_changeUser);
		
		JMenuItem item_addUser = new JMenuItem("新增用户");
		item_addUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					userFrame userframe = new userFrame();
					userframe.setVisible(true);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		userMenu.add(item_addUser);
		
		JMenuItem item_delUser = new JMenuItem("删除用户");
		item_delUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					userFrame userframe = new userFrame();
					userframe.setVisible(true);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		userMenu.add(item_delUser);
		
		JMenu fileMenu = new JMenu("档案管理");
		menuBar.add(fileMenu);
		
		JMenuItem item_download = new JMenuItem("下载文件");
		item_download.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				System.out.println("hhhh");
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							fileFrame fileframe = new fileFrame();
							fileframe.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		fileMenu.add(item_download);
		
		JMenuItem item_upload = new JMenuItem("上传文件");
		item_upload.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				fileFrame ff;
				try {
					ff = new fileFrame();
					ff.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		fileMenu.add(item_upload);
		
		JMenu selfMenu = new JMenu("个人信息管理");
		menuBar.add(selfMenu);
		
		JMenuItem item_changeSelf = new JMenuItem("修改信息");
		selfMenu.add(item_changeSelf);
		item_changeSelf.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				LoginFrame.user.changeSelfInfo();
			}
		});
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		
	}
}
