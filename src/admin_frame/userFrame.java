package admin_frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
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
import javax.swing.JScrollPane;

public class userFrame extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JTextField changeCodeText;
	private JTextField addCode;
	private JTextField addUser;
	private JPanel panel_add;
	private JPanel panel_change;
	private JPanel panel_del;
	private JTable userTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userFrame frame = new userFrame();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public userFrame() throws Exception {
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
		addConButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				String name = addUser.getText();
				String code = addCode.getText();
				String role = (String)addType.getSelectedItem();
				try {
					DataProcessing.insertUser(name, code, role);
					UserModel model = new UserModel();
					userTable.setModel(model);
				} catch (Exception e) {
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
		e = DataProcessing.getAllUser();//用has-next后指针已经改变
		for(int i=0;e.hasMoreElements();i++) {
			User u = e.nextElement();
			user[i] = u.getName();
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
					UserModel model = new UserModel();
					userTable.setModel(model);
				} catch (Exception e) {
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
		
		
		
		JButton delCanButton = new JButton("取消");
		delCanButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				userFrame.this.dispose();
			}
		});
		
		delCanButton.setBounds(235, 193, 101, 18);
		panel_del.add(delCanButton);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 417, 180);
		panel_del.add(scrollPane);
		UserModel userModel = new UserModel();
		userTable = new JTable(userModel.getValues(),userModel.getColumns());
		scrollPane.setViewportView(userTable);
		//panel_del.add(userTable);
		userTable.setRowSelectionAllowed(true);
		
		
		JButton delConButton = new JButton("确认删除");
		delConButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					int selectionRow = userTable.getSelectedRow();
					String chooseUser=(String) userTable.getModel().getValueAt(selectionRow, 0);
					DataProcessing.deleteUser(chooseUser);
					UserModel model = new UserModel();
					userTable.setModel(model);
//					userTable.validate();
//					userTable.updateUI();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				}
				
				
			}
		});
		delConButton.setBounds(76, 193, 101, 18);
		panel_del.add(delConButton);
	}
	
	
	
	public JTabbedPane getTabbedPane() {
		return this.tabbedPane;
	}
}
