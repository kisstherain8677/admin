package admin_frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import admin.DocClient;
import admin.DocServer;
import admin.User;

import java.awt.FlowLayout;
import java.awt.MenuBar;

import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class mainFrame extends JFrame {

	private JPanel contentPane;
	private fileFrame fileframe;

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
					userframe.getTabbedPane().setSelectedIndex(0);
					
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
					userframe.getTabbedPane().setSelectedIndex(1);
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
					userframe.getTabbedPane().setSelectedIndex(2);
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
							 fileframe = new fileFrame();
							fileframe.getTabbedPane().setSelectedIndex(0);
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
					ff.getTabbedPane().setSelectedIndex(1);
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
				selfFrame sf = new selfFrame();
				sf.setVisible(true);
			}
		});
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String role = DocClient.getRole();
		if(role.equalsIgnoreCase("administrator")) {		 
			this.setVisible(true);
		}else if(role.equalsIgnoreCase("operator")) {
			 
			getJMenuBar().getMenu(0).setEnabled(false);
			setVisible(true);
		}else if(role.equalsIgnoreCase("browser")) {
			
			getJMenuBar().getMenu(0).setEnabled(false);
			MouseListener[] ml = getJMenuBar().getMenu(1).getItem(1).getMouseListeners();
			getJMenuBar().getMenu(1).getItem(1).removeMouseListener(ml[1]);
			getJMenuBar().getMenu(1).getItem(1).setEnabled(false);
			setVisible(true);
		}
		
	}
	
	public fileFrame getFileFrame() {
		return this.fileframe;
	}
	
	
	
	
	
	
}
