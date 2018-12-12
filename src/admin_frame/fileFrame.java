package admin_frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import admin.DataProcessing;

import java.awt.GridLayout;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class fileFrame extends JFrame {
	
	
	 private int  selectedRow=-1;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private final JButton button_download = new JButton("\u4E0B\u8F7D\u6587\u4EF6");
	private final JButton button_cancel = new JButton("\u53D6\u6D88");
	private JTextField text_docNum;
	private JTextField text_docName;
	private JTextField text_creator;
	JFileChooser chooser=new JFileChooser();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fileFrame frame = new fileFrame();
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
	 * @throws ClassNotFoundException 
	 */
	public fileFrame() throws SQLException, ClassNotFoundException  {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(5, 5, 422, 243);
		getContentPane().add(tabbedPane);
		
		JPanel downloadPanel = new JPanel();
		tabbedPane.addTab("下载文件", downloadPanel);
		downloadPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 417, 180);
		downloadPanel.add(scrollPane);
		
		String[] columnNames = {"ID","filename","creator","timestamp","description"};
		String[][] tableValues = getTableValues(columnNames);
		
		JTable table = new JTable(tableValues,columnNames);
		scrollPane.setViewportView(table);
		button_cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				fileFrame.this.dispose();
			}
		});
		button_cancel.setBounds(208, 184, 209, 27);
		downloadPanel.add(button_cancel);
	
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				 int  selectedRow = table.getSelectedRow();
			}
			
		});
		
		button_download.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				chooser = new JFileChooser();
				chooser.setFileSelectionMode(1);
				chooser.showOpenDialog(fileFrame.this);
				String path = chooser.getSelectedFile().getAbsolutePath();
				try {
					LoginFrame.getUser().downloadFile(String.valueOf(selectedRow), path);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		button_download.setBounds(0, 184, 209, 27);
		downloadPanel.add(button_download);
		JPanel uploadPanel = new JPanel();
		tabbedPane.addTab("上传文件", uploadPanel);
		uploadPanel.setLayout(null);
		
		JLabel label_docNum = new JLabel("\u6863\u6848\u53F7");
		label_docNum.setBounds(41, 23, 72, 18);
		uploadPanel.add(label_docNum);
		
		JLabel label_description = new JLabel("\u6587\u4EF6\u63CF\u8FF0");
		label_description.setBounds(41, 54, 72, 18);
		uploadPanel.add(label_description);
		
		JLabel label_doc = new JLabel("\u6587\u4EF6\u540D");
		label_doc.setBounds(41, 161, 72, 18);
		uploadPanel.add(label_doc);
		
		text_docNum = new JTextField();
		text_docNum.setBounds(170, 20, 115, 24);
		uploadPanel.add(text_docNum);
		text_docNum.setColumns(10);
		
		JTextArea text_docDiscription = new JTextArea();
		text_docDiscription.setBounds(170, 52, 167, 67);
		uploadPanel.add(text_docDiscription);
		
		text_docName = new JTextField();
		text_docName.setBounds(170, 158, 115, 24);
		uploadPanel.add(text_docName);
		text_docName.setColumns(10);
		
		JButton button_open = new JButton("打开");
		button_open.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				chooser = new JFileChooser();
				chooser.showOpenDialog(fileFrame.this);
				text_docName.setText(chooser.getSelectedFile().getName());
			}
		});
		
		button_open.setBounds(304, 161, 82, 23);
		uploadPanel.add(button_open);
		
		JButton button_upload = new JButton("\u4E0A\u4F20\u6587\u4EF6");
		button_upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_upload.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				String num = text_docNum.getText();
				String description = text_docDiscription.getText();
				String creator = text_creator.getText();
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				String docname = text_docName.getText();
				try {
					DataProcessing.insertDoc(num, creator, timestamp, description,docname);
					fileFrame.this.repaint();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		button_upload.setBounds(0, 184, 207, 27);
		uploadPanel.add(button_upload);
		
		JButton button_cancle = new JButton("\u53D6\u6D88");
		button_cancle.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				fileFrame.this.dispose();
			}
		});
		button_cancle.setBounds(210, 184, 207, 27);
		uploadPanel.add(button_cancle);
		
		JLabel label_creator = new JLabel("\u4F5C\u8005");
		label_creator.setBounds(41, 125, 72, 18);
		uploadPanel.add(label_creator);
		
		text_creator = new JTextField();
		text_creator.setBounds(170, 122, 86, 24);
		uploadPanel.add(text_creator);
		text_creator.setColumns(10);
		
		
		
	}
	
	String[][] getTableValues(String[] columnNames) throws SQLException, ClassNotFoundException{
		 int count ;
			
			Enumeration et = DataProcessing.getAllDocs();
			for(count=0;et.hasMoreElements();count++) 
				et.nextElement();
		String[][] tableValues = new String[count][columnNames.length] ;
		
			Enumeration e =DataProcessing.getAllDocs();
			String[] curValue = new String[count];
			//将每一个枚举量取出来，作为元素存入curValue
			int i = 0;
			while(e.hasMoreElements()) {
				curValue[i] = e.nextElement().toString();
				i++;
			}
			//在每一个元素中
			int j, m;
			for( j=0;j<count;j++) {
				for( m=0;m<curValue[j].split(",").length;m++) {
				tableValues[j][m] = curValue[j].split(",")[m];
				}
			}
			
			return tableValues;
	}
}



