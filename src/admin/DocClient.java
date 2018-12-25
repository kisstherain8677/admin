package admin;


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
public class DocClient 
{
    // input stream from server
   private String message = ""; // message from server
   private static DataOutputStream output; 
   private static DataInputStream input; 
   private String chatServer; // host server for this application
   private static Socket client; // socket to communicate with server
   //private FileInputStream fis;
   private static JFrame Jframe;

   

   DocClient(String host){
	  
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
	

   
   private void processConnection() throws IOException
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
	 			 System.out.println("----开始下载文件<"+filename+">,文件大小为<"+fileLength+">----");
	 			 while(true) {
	 				 int read=0;
	 				 read=input.read(sendBytes);
	 				 if(read==-1) break;
	 				 transLen+=read;
	 				 System.out.println("下载文件进度"+100*transLen*1.0/fileLength+"%...");
	 				 fos.write(sendBytes,0,read);
	 				 fos.flush();
	 				 if(transLen>=fileLength) break;
	 			 }
	 			 System.out.println("----下载文件<"+filename+">成功----");
	 			JOptionPane.showMessageDialog(null, "下载成功", "message", JOptionPane.PLAIN_MESSAGE);
	 			Jframe.dispose();
	         }
	      } while ( !message.equals( "SERVER>>> TERMINATE" ) );	

       
   } // end method processConnection

   // close streams and socket
   static void closeConnection() throws IOException{
	      displayMessage( "Closing connection" );
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
	       DataOutputStream dos=new DataOutputStream(client.getOutputStream());
	       byte[] sendBytes=new byte[1024];
	       int length=0;
	       while((length=fis.read(sendBytes,0,sendBytes.length))>0) {
		       output.write(sendBytes,0,length);
		       output.flush();
	       }
	       System.out.println("CLIENT>>> CLIENT_FILE_UP");
   }

   
}