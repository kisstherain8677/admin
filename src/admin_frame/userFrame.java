package admin_frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import admin.DataProcessing;
import admin.User;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class userFrame extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JTextField changeCodeText;
	private JTextField addCode;
	private JTextField addUser;
	private JTextField delCode;
	private JTextField delUser;
	private JPanel panel_add;
	private JPanel panel_change;
	private JPanel panel_del;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userFrame frame = new userFrame();
					frame.hidePanel_add();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public userFrame() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		 panel_add = new JPanel();
		panel_add.setToolTipText("");
		tabbedPane.add("添加用户", panel_add);
		panel_add.setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D");
		label.setBounds(56, 33, 72, 18);
		panel_add.add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801");
		label_1.setBounds(56, 82, 72, 18);
		panel_add.add(label_1);
		
		JLabel label_2 = new JLabel("\u7528\u6237\u7C7B\u578B");
		label_2.setBounds(56, 131, 72, 18);
		panel_add.add(label_2);
		
		JComboBox addType = new JComboBox(new String[]{"Administrator","Operator","Browser"});
		addType.setBounds(231, 131, 133, 24);
		panel_add.add(addType);
		
		addCode = new JTextField();
		addCode.setColumns(10);
		addCode.setBounds(231, 79, 133, 24);
		panel_add.add(addCode);
		
		JButton addConButton = new JButton("确认添加");
		addConButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		addConButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				String name = addUser.getText();
				String code = addCode.getText();
				String role = (String)addType.getSelectedItem();
				try {
					DataProcessing.insertUser(name, code, role);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		
		addConButton.setBounds(72, 180, 101, 18);
		panel_add.add(addConButton);
		
		JButton addCanButton = new JButton("取消");
		addCanButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				userFrame.this.dispose();
			}
		});
		addCanButton.setBounds(231, 180, 101, 18);
		panel_add.add(addCanButton);
		
		addUser = new JTextField();
		addUser.setBounds(231, 30, 133, 24);
		panel_add.add(addUser);
		addUser.setColumns(10);
		
		 panel_change = new JPanel();
		tabbedPane.add("修改用户", panel_change);
		panel_change.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D");
		lblNewLabel.setBounds(59, 46, 72, 18);
		panel_change.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801");
		lblNewLabel_1.setBounds(59, 95, 72, 18);
		panel_change.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u7528\u6237\u7C7B\u578B");
		lblNewLabel_2.setBounds(59, 144, 72, 18);
		panel_change.add(lblNewLabel_2);
		
		Enumeration<User> e = DataProcessing.getAllUser();
		int count = 0;
		while(e.hasMoreElements()) {
			e.nextElement();
			count++;
		}
		String[] user = new String[count];
		for(int i=0;e.hasMoreElements();i++) {
			user[i] = e.nextElement().getName();
		}
		
		JComboBox changeUserBox = new JComboBox(user);
		changeUserBox.setBounds(234, 43, 133, 24);
		panel_change.add(changeUserBox);
		String[] type = {"Administrator","Operator","Browser"};
		JComboBox changeTypeCombo = new JComboBox(type);
		changeTypeCombo.setBounds(234, 144, 133, 24);
		panel_change.add(changeTypeCombo);
		
		changeCodeText = new JTextField();
		changeCodeText.setBounds(234, 92, 133, 24);
		panel_change.add(changeCodeText);
		changeCodeText.setColumns(10);
		
		JButton changeConButon = new JButton("确认修改");
		changeConButon.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				String name = (String)changeUserBox.getSelectedItem();
				String code = changeCodeText.getText();
				String type = (String)changeTypeCombo.getSelectedItem(); 
				try {
					DataProcessing.updateUser(name, code,type);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		changeConButon.setBounds(75, 193, 101, 18);
		panel_change.add(changeConButon);
		
		JButton changeCanButton = new JButton("取消");
		changeCanButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				userFrame.this.dispose();
			}
		});
		changeCanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		changeCanButton.setBounds(234, 193, 101, 18);
		panel_change.add(changeCanButton);
		
		 panel_del = new JPanel();
		tabbedPane.add("删除用户", panel_del);
		panel_del.setLayout(null);
		
		JLabel label_3 = new JLabel("\u7528\u6237\u540D");
		label_3.setBounds(60, 46, 72, 18);
		panel_del.add(label_3);
		
		JLabel label_4 = new JLabel("\u5BC6\u7801");
		label_4.setBounds(60, 95, 72, 18);
		panel_del.add(label_4);
		
		delCode = new JTextField();
		delCode.setColumns(10);
		delCode.setBounds(235, 92, 133, 24);
		panel_del.add(delCode);
		
		JButton delConButton = new JButton("确认删除");
		delConButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		delConButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				String name = delUser.getText();
				String code = delCode.getText();
				
				try {
					if(code.equals(DataProcessing.searchUser(name).getPassword()))
					{DataProcessing.deleteUser(name);
					System.out.println("删除成功");}
					else System.out.println("密码不正确");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		delConButton.setBounds(76, 193, 101, 18);
		panel_del.add(delConButton);
		
		JButton delCanButton = new JButton("取消");
		delCanButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				userFrame.this.dispose();
			}
		});
		
		delCanButton.setBounds(235, 193, 101, 18);
		panel_del.add(delCanButton);
		
		delUser = new JTextField();
		delUser.setBounds(235, 43, 133, 24);
		panel_del.add(delUser);
		delUser.setColumns(10);
	}
	
	public void hidePanel_add() {
		this.panel_add.setVisible(false);
	}

	public void hidePanel_change() {
		this.panel_change.setVisible(false);
	}

	public void hidePanel_del() {
		this.panel_del.setVisible(false);
	}
}
