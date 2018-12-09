package admin_frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class selfFrame extends JFrame {

	private JPanel contentPane;
	private JTextField userNameText;
	private JTextField oldCodeText;
	private JTextField newCodeText;
	private JTextField conNewCodeText;
	private JTextField userType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					selfFrame frame = new selfFrame();
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
	public selfFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D");
		lblNewLabel.setBounds(98, 50, 72, 18);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u539F\u5BC6\u7801");
		lblNewLabel_1.setBounds(98, 85, 72, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u65B0\u5BC6\u7801");
		lblNewLabel_2.setBounds(98, 116, 72, 18);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u786E\u8BA4\u65B0\u5BC6\u7801");
		lblNewLabel_3.setBounds(84, 147, 86, 18);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u7528\u6237\u7C7B\u578B");
		lblNewLabel_4.setBounds(94, 178, 72, 18);
		contentPane.add(lblNewLabel_4);
		
		userNameText = new JTextField();
		userNameText.setBounds(240, 47, 86, 24);
		contentPane.add(userNameText);
		userNameText.setColumns(10);
		
		oldCodeText = new JTextField();
		oldCodeText.setBounds(240, 83, 86, 24);
		contentPane.add(oldCodeText);
		oldCodeText.setColumns(10);
		
		newCodeText = new JTextField();
		newCodeText.setBounds(240, 113, 86, 24);
		contentPane.add(newCodeText);
		newCodeText.setColumns(10);
		
		conNewCodeText = new JTextField();
		conNewCodeText.setBounds(240, 144, 86, 24);
		contentPane.add(conNewCodeText);
		conNewCodeText.setColumns(10);
		
		userType = new JTextField();
		userType.setBounds(240, 175, 86, 24);
		contentPane.add(userType);
		userType.setColumns(10);
		
		JButton changeButton = new JButton("\u4FEE\u6539");
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		changeButton.setBounds(84, 222, 86, 18);
		contentPane.add(changeButton);
		
		JButton backButton = new JButton("\u8FD4\u56DE");
		backButton.setBounds(240, 222, 86, 18);
		contentPane.add(backButton);
		
		
		
		
		
	}
}
