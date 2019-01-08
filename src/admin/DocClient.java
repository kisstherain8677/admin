package admin;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import admin_frame.LoginFrame;
import admin_frame.mainFrame;
public class DocClient  extends JFrame
{
    // input stream from server
   private String message = ""; // message from server
   private static DataOutputStream output; 
   private static DataInputStream input; 
   private String chatServer; // host server for this application
   private static Socket client; // socket to communicate with server
   //private FileInputStream fis;
   private static JFrame Jframe;//����Ҫ�õ��Ľ��棬�����˳�ʱ�ر�
   private static String role;
   private static  User user;
   

   DocClient(String host){
	   
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   chatServer = host;
   }
   
   public void runClient() throws IOException 
   {
      try 
      {
         connectToServer(); 
         getStreams(); 
         processConnection(); 
      } 
      catch ( EOFException eofException ) 
      {
         displayMessage( "\nClient terminated connection" );
         eofException.printStackTrace();
      } 
      catch ( IOException ioException ) 
      {
         ioException.printStackTrace();
      } 
      catch(ClassNotFoundException classException) {
    	  classException.printStackTrace();
      }
      finally 
      {
         closeConnection(); 
      } 
   } 

  
   private void connectToServer() throws IOException
   {      
      displayMessage( "Attempting connection\n" );

      // create Socket to make connection to server
      client = new Socket( InetAddress.getByName( chatServer ), 12345 );

      // display connection information
      displayMessage( "Connected to: " + 
         client.getInetAddress().getHostName() );
   } // end method connectToServer

   
   private void getStreams() throws IOException{
	      output = new DataOutputStream( client.getOutputStream() );      
	      output.flush(); 
	      input = new DataInputStream( client.getInputStream() );
	      displayMessage( "Got I/O streams" );
	   } 
	

   
   private void processConnection() throws IOException, ClassNotFoundException
   {
	   do { 
	         message = input.readUTF(); 
	         if(message.equals("SERVER>>> CLIENT_FILE_DOWN")) {
	        	 String filename=input.readUTF();
	        	 Long fileLength=input.readLong();
	        	 String objectPath = input.readUTF();
	 			 FileOutputStream fos=new FileOutputStream(new File(objectPath+"//"+filename));
	 			 byte[] sendBytes=new byte[1024];
	 			 int transLen=0;
	 			 System.out.println("----��ʼ�����ļ�<"+filename+">,�ļ���СΪ<"+fileLength+">----");
	 			 while(true) {
	 				 int read=0;
	 				 read=input.read(sendBytes);//��ȡ1024���ֽڵ�sendBytes�¹����У�����������ȡ�����ֽ���
	 				 if(read==-1) break;
	 				 transLen+=read;
	 				 System.out.println("�����ļ�����"+100*transLen*1.0/fileLength+"%...");
	 				 fos.write(sendBytes,0,read);
	 				 fos.flush();
	 				 if(transLen>=fileLength) break;
	 			 }
	 			 System.out.println("----�����ļ�<"+filename+">�ɹ�----");
	 			JOptionPane.showMessageDialog(null, "���سɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
	 			Jframe.dispose();
	         }
	         
	         
	         else if(message.equals("UPLOAD_TRUE")) {
	        	 JOptionPane.showMessageDialog(null, "�ϴ��ɹ�","��ʾ" , JOptionPane.PLAIN_MESSAGE);
	        	 Jframe.dispose();
	        	 System.out.println("�ϴ��ɹ�");
	         }
	         
	         else if(message.equals("UPLOAD_FALSE")) {
	        	 JOptionPane.showMessageDialog(null, "�ϴ�ʧ��","��ʾ" , JOptionPane.PLAIN_MESSAGE);
	        	 Jframe.dispose();
	        	 System.out.println("�ϴ�ʧ��");
	         }
	         
	         else if(message.equals("LOGIN_TRUE")) {
	        	 role = input.readUTF();
	        	 ObjectInputStream objectinput = new ObjectInputStream(client.getInputStream());
	        	 
	        		 if(role.equalsIgnoreCase("administrator"))
	        			 user = (Administrator) objectinput.readObject();
	        		 else if(role.equalsIgnoreCase("browser"))
	        			 user = (Browser)objectinput.readObject();
	        		 else if(role.equalsIgnoreCase("operator"))
	        			 user = (Operator)objectinput.readObject();
				 
	        	 System.out.println(user.getName()+user.getPassword());	        		        		        
	        	 mainFrame mf = new mainFrame();
	        	 mf.addWindowListener(new WindowAdapter() {//�ر�������ʱ���Ͽ��˿ͻ����������������
	        		 public void windowClosing(WindowEvent e) {
	        			try {
							closeConnection();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	        			 System.exit(0);
	        		 }
	        	 });
	        	 mf.setVisible(true);
	        	 Jframe.setVisible(false);
	        	 System.out.println("��½�ɹ�");
	         }
	         
	         else if(message.equals("LOGIN_FALSE")) {
	        	 System.out.println("login error");
	        	 JOptionPane.showMessageDialog(Jframe, "��½ʧ��","��ʾ" , JOptionPane.ERROR_MESSAGE);//�˴�����һ��ҪдJframe��Ȼ��ѭ��
	        	 
	         }
	         else if(message.equals("LOGIN_FALSE_ROLE")) {
	        	 System.out.println("false_role");
	        	 JOptionPane.showMessageDialog(Jframe, "���û������������룡","��ʾ" , JOptionPane.ERROR_MESSAGE);
	        	 
	         }
	         else if(message.equals("ADD_TRUE")) {
	        	 JOptionPane.showMessageDialog(null, "��ӳɹ�","��ʾ" , JOptionPane.PLAIN_MESSAGE);
	        	 Jframe.dispose();
	        	 System.out.println("��ӳɹ�");
	         }
	         else if(message.equals("ADD_FALSE")) {
	        	 JOptionPane.showMessageDialog(null, "���ʧ��","��ʾ" , JOptionPane.PLAIN_MESSAGE);
	        	 Jframe.dispose();
	        	 System.out.println("���ʧ��");
	         }
	         
	         else if(message.equals("UPDATE_TRUE")) {
	        	 JOptionPane.showMessageDialog(null, "�޸ĳɹ�","��ʾ" , JOptionPane.PLAIN_MESSAGE);
	        	 Jframe.dispose();
	        	 System.out.println("�޸ĳɹ�");
	         }
	         else if(message.equals("UPDATE_FALSE")) {
	        	 JOptionPane.showMessageDialog(null, "���ʧ��","��ʾ" , JOptionPane.PLAIN_MESSAGE);
	        	 Jframe.dispose();
	        	 System.out.println("���ʧ��");
	         }
	         
	         else if(message.equals("DELETE_TRUE")) {
	        	 JOptionPane.showMessageDialog(null, "ɾ���ɹ�","��ʾ" , JOptionPane.PLAIN_MESSAGE);
	        	 Jframe.dispose();
	        	 System.out.println("ɾ���ɹ�");
	         }
	         
	         else if(message.equals("DELETE_FALSE")) {
	        	 JOptionPane.showMessageDialog(null, "ɾ��ʧ��","��ʾ" , JOptionPane.PLAIN_MESSAGE);
	        	 Jframe.dispose();
	        	 System.out.println("ɾ��ʧ��");
	         }
	         
	         else if(message.equals("CHANGE_SELF_TRUE")) {
	        	 JOptionPane.showMessageDialog(null, "�޸ĳɹ�","��ʾ" , JOptionPane.PLAIN_MESSAGE);
	        	 Jframe.dispose();
	        	 System.out.println("�޸�������Ϣ�ɹ�");
	         }
	         
	         else if(message.equals("CHANGE_SELF_FALSE")) {
	        	 JOptionPane.showMessageDialog(null, "�޸�ʧ��","��ʾ" , JOptionPane.PLAIN_MESSAGE);
	        	 Jframe.dispose();
	        	 System.out.println("�޸�������Ϣʧ��");
	         }
	         
	      } while ( !message.equals( "SERVER>>> Terminate" ) );	

       
   } // end method processConnection

   // close streams and socket
   static void closeConnection() throws IOException{
	     System.out.println( "Closing connection" );
	      String logout="CLIENET>>> CLIENT_LOGOUT";
	      output.writeUTF(logout);
	      output.flush();
	      System.out.println(logout);
	      try{
	         output.close(); 
	         input.close(); 
	         client.close(); 
	      }
	      catch ( IOException ioException ) {
	         ioException.printStackTrace();
	      } 
	   }

   // send message to server
   static void sendData( String message ){
	      try 
	      {
	         output.writeUTF( "CLIENT>>> " + message );
	         output.flush();
	         displayMessage( "CLIENT>>> " + message );
	      } 
	      catch ( IOException ioException )
	      {
	         System.out.println( "Error writing object" );
	      } 
	   } 
	
   
   static void displayMessage( String messageToDisplay ){
	      SwingUtilities.invokeLater(
	         new Runnable()
	         {
	            public void run() 
	            {
	            	System.out.println(messageToDisplay);
	            } 
	         } 
	      ); 
	   }  
   
    public static void Download(String ID,JFrame frame,String objectPath) throws IOException {
	   Jframe=frame;
	   output.writeUTF("DOWNLOAD");
	   output.flush();
       output.writeUTF(ID);
       output.flush();
       output.writeUTF(objectPath);
       output.flush();
   }
   
    public static void Upload(String ID,String Creator,String description,String filename,JFrame frame) throws IOException{
	   Jframe=frame;
		   output.writeUTF("UPLOAD");
	       output.flush();
	       output.writeUTF(ID);
	       output.flush();
	       output.writeUTF(Creator);
	       output.flush();
	       output.writeUTF(description);
	       output.flush();
	       File file=new File(filename.trim());
	       String fileName=file.getName();
	       output.writeUTF(fileName);
	       output.flush();
	       long fileLength=file.length();
	       output.writeLong(fileLength);
	       output.flush();
	       FileInputStream fis=new FileInputStream(file);
	       //DataOutputStream dos=new DataOutputStream(client.getOutputStream());
	       byte[] sendBytes=new byte[1024];
	       int length=0;
	       while((length=fis.read(sendBytes,0,sendBytes.length))>0) {//���ļ������ֽڶ��뵽sendBytes���� �ٽ��������������output
		       output.write(sendBytes,0,length);
		       output.flush();
	       }
	       System.out.println("CLIENT>>> CLIENT_FILE_UP");
   }

    public static void Login(String name,String password,JFrame frame) throws IOException {
	   String login = "CLIENT>>> LOGIN";
	   output.writeUTF(login);
	   output.flush();
	   System.out.println(login);
	   output.writeUTF(name);
	   output.flush();
	   output.writeUTF(password);
	   output.flush();
	   Jframe = frame;
	   
   }
   
    public static void Login(String name,JFrame frame) throws IOException {
	   String login = "CLIENT>>> LOGINOLD";
	   output.writeUTF(login);
	   output.flush();
	   System.out.println(login);
	   output.writeUTF(name);
	   output.flush();
	   Jframe = frame;
	   
   }
   
    public static void addUser(String name,String password,String role,JFrame frame) throws IOException {
	   Jframe = frame;
	   output.writeUTF("USER_ADD");
	   output.flush();
	   output.writeUTF(name);
	   output.flush();
	   output.writeUTF(password);
	   output.flush();
	   output.writeUTF(role);
	   output.flush();
	   System.out.println("CLIENT>>> "+name+"USER_ADD");
   }
   
    public static void updateUser(String name,String password,String role,JFrame frame) throws IOException {
	   Jframe = frame;
	   output.writeUTF("USER_UPDATE");
	   output.flush();
	   output.writeUTF(name);
	   output.flush();
	   output.writeUTF(password);
	   output.flush();
	   output.writeUTF(role);
	   output.flush();
	   System.out.println("CLIENT>>> "+name+"USER_UPDATE");
   }
   
   public static void delUser(String name,JFrame frame) throws IOException{
	   Jframe = frame;
	   output.writeUTF("USER_DELETE");
	   output.flush();
	   output.writeUTF("name");
	   output.flush();
	  System.out.println("CLIENT>>> "+name+"USER_DELETE");
   }
   
    public static void changeSelf(String name,String password,JFrame frame)throws IOException{
	   Jframe = frame;
	   output.writeUTF("USER_CHANGE_SELF");
	   output.flush();
	   output.writeUTF(name);
	   output.flush();
	   output.writeUTF(password);
	   output.flush();
	   System.out.println("CLIENT>>> "+name+"USER_CHANGE_SELF");
   }
   
   public static String getRole() {
	   return DocClient.role;
   }
   
   public static User getUser() {
	   return user;
   }
   
}