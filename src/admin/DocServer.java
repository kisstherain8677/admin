package admin;
import java.net.*;
import java.util.Vector;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import admin_frame.fileFrame;

public class DocServer 
{
	private static DataOutputStream output; 
	   private static DataInputStream input; 
	   private ServerSocket server; 
	   private Socket connection;
   private int counter = 1; 
   static User user;

  
   
   public static void main( String args[] )
   {
      DocServer application;
	try {
		application = new DocServer();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
      
   } 
   

   //将服务器连接到指定端口
   public DocServer()throws IOException {
	   server = new ServerSocket( 12345, 100 );
       int count=1;
       try {
           while (true) {
        	   System.out.println("waiting for connection...");
               connection = server.accept();
               System.out.println("Connection "+ " received from: "+connection.getInetAddress().getHostName() );     
               new ServerThread(connection,"Thread"+count);
               count++;
           }
       }catch (IOException e) {
       }finally {
    	   closeConnection();
       }
      }
   
   
   class ServerThread extends Thread {
	   private Socket client;
       private String client_name;
       private DataOutputStream output; 
	   private DataInputStream input; 
	   

       public ServerThread(Socket s,String client_name)throws IOException {
           client = s;
           output=new DataOutputStream(client.getOutputStream());
           input=new DataInputStream(client.getInputStream());
           System.out.println("Got I/O streams" );
           System.out.println(client_name +" come in...");
           this.client_name=client_name;
           start();
       }
       
       public void run() {
    	   String message = "connection successful.";
    	   System.out.println(message);
    	   
    	   try {
    		   do{ 
 		    	  message = input.readUTF();		    	  
 		    	  if(message.equals("UPLOAD")) {
 		  			Timestamp timestamp=new Timestamp(System.currentTimeMillis());
 		  			
 		  			String Creator=input.readUTF();
 		  			String description=input.readUTF();
 		  			String filename=input.readUTF();
 		  			Long fileLength=input.readLong();
 		  			FileOutputStream fos=new FileOutputStream(new File("D:\\server\\"+filename));
 		  			DataInputStream dis=new DataInputStream(connection.getInputStream());
 		  			byte[] sendBytes=new byte[1024];
 		  			int transLen=0;
 		  			System.out.println("----开始接收文件<"+filename+">,文件大小为<"+fileLength+">----");
 		  			while(true) {
 		  				int read=0;
 		  				read=input.read(sendBytes,0,sendBytes.length);
 		  				if(read<=0) break;
 		  				transLen+=read;
 		  				System.out.println("接收文件进度"+100*transLen*1.0/fileLength+"%...");
 		  				fos.write(sendBytes,0,read);
 		  				fos.flush();
 		  				if(transLen>=fileLength) break;
 		  			}
 		  			System.out.println("----接收文件<"+filename+">成功----");
 		  			if(DataProcessing.insertDoc( Creator, timestamp, description, filename)!=-1){
 		  				output.writeUTF("UPLOAD_TRUE");
 		  				output.flush();
 		  				System.out.println("SERVER>>> CLIENT_FILE_UP");
 		  			}
 		  			else {
 		  				output.writeUTF("UPLOAD_FALSE");
 		  				output.flush();
 		  			}
 		  		}
 		    	 
 		    	  else if(message.equals("DOWNLOAD")) {
 		  			String ID=input.readUTF();
 		  			String objectPath = input.readUTF();
 		  			output.writeUTF("SERVER>>> CLIENT_FILE_DOWN");
 		  			output.flush();
 		  			System.out.println("SERVER>>> CLIENT_FILE_DOWN");
 		  			String filename=DataProcessing.searchDoc(ID).getFilename();
 		  			output.writeUTF(filename);
 		  			output.flush();
 		  			String filepath="D:\\server\\";
 		  			File file=new File(filepath+filename);
 		  			long fileLength=file.length();
 		  			output.writeLong(fileLength);
 		  		    output.flush();
 		  		    output.writeUTF(objectPath);
 		  		    output.flush();
 		  			FileInputStream fis=new FileInputStream(file);
 		  		    byte[] sendBytes=new byte[1024];
 		  		    int length=0;
 		  		    while((length=fis.read(sendBytes,0,sendBytes.length))>0) {
 		  			    output.write(sendBytes,0,length);
 		  			    output.flush();
 		  		    }
 		  		}
 		    	  
 		    	  
 		    	  else if(message.equals("CLIENT>>> LOGIN")) {
 		    		  String name = input.readUTF();
 		    		  String password = input.readUTF();
 		    		  displayMessage("The server is searching user's information...");
 		    		  user = DataProcessing.searchUser(name, password);
 		    		  if(user!=null) {
 		    			 output.writeUTF("LOGIN_TRUE");
 		    			 output.flush();//打开界面的行为交给客户端 服务端只用来传输数据
 			    		 
 		    			 output.writeUTF(user.getRole());
 			    		 output.flush();
 			    		 
 		    			 ObjectOutputStream objectoutput = new ObjectOutputStream(connection.getOutputStream());
 		    			 objectoutput.flush();
 		    			 objectoutput.writeObject(user);
 		    			 objectoutput.flush();
 		    		  }
 		    		  else {
 		    			  //displayMessage("faile to login.Can't find the user.");
 		    			  output.writeUTF("LOGIN_FALSE");
 		    			  output.flush();
 		    		  }
 		    		  
 		    	  }
 		    	  
 		    	  else if(message.equals("USER_ADD")) {
 		    		  String name = input.readUTF();
 		    		  String password = input.readUTF();
 		    		  String role = input.readUTF();
 		    		  if(DataProcessing.insertUser(name, password, role)) {
 		    			  output.writeUTF("ADD_TRUE");
 		    			  output.flush();
 		    			  System.out.println("SERVER>>> "+name+"USER_ADD");
 		    		  }else {
 		    			  output.writeUTF("ADD_FALSE");
 		    			  output.flush();
 		    		  }
 		    	  }
 		    	  
 		    	  else if(message.equals("USER_UPDATE")) {
 		    		  String name = input.readUTF();
 		    		  String password = input.readUTF();
 		    		  String role = input.readUTF();
 		    		  if(DataProcessing.searchUser(name)!=null) {
 		    			  DataProcessing.updateUser(name, password, role);
 		    			  output.writeUTF("UPDATE_TRUE");
 		    			  output.flush();
 		    			  System.out.println("SERVER>>> "+name+"USER_UPDATE");
 		    		  }else {
 		    			  output.writeUTF("UPDATE_FALSE");
 		    			  output.flush();
 		    		  }
 		    	  }
 		    	  
 		    	  else if(message.equals("USER_DELETE")) {
 		    		  String name = input.readUTF();
 		    		  if(DataProcessing.searchUser(name)!=null) {
 		    			  DataProcessing.deleteUser(name);
 		    			  output.writeUTF("DELETE_TRUE");
 		    			  output.flush();
 		    			  System.out.println("SERVER>>> "+name+"USER_DELETE");
 		    		  }else {
 		    			  output.writeUTF("DELETE_FALSE");
 		    			  output.flush();
 		    		  }
 		    	  }
 		    	  
 		    	  else if(message.equals("USER_CHANGE_SELF")) {
 		    		  String name = input.readUTF();
 		    		  String password = input.readUTF();
 		    		  if(DataProcessing.searchUser(name)!=null) {
 		    			  User user = DataProcessing.searchUser(name);
 		    			  DataProcessing.updateUser(name, password, user.getRole());
 		    			  output.writeUTF("CHANGE_SELF_TRUE");
 		    			  output.flush();
 		    			  System.out.println("SERVER>>> "+name+"USER_CHANGE_SELF");
 		    		  }else {
 		    			  output.writeUTF("CHANGE_SELF_FALSE");
 		    			  output.flush();
 		    		  }
 		    	  }
 		    	  
 		    	  else displayMessage(message); 
 		      } while ( !message.equals( "CLIENT>>> CLIENT_LOGOUT" ) );	
    		        System.out.println(client_name+" LOGOUT!");
                    output.close();
                    input.close();
                    client.close();
    	   }catch(Exception e) {
    		   e.printStackTrace();
    	   }
       } 


   }
   
   
   
//   public void runServer()
//   {
//      try // set up server to receive connections; process connections
//      {
//         server = new ServerSocket( 12345, 100 ); // create ServerSocket
//
//         while ( true ) 
//         {
//            try 
//            {
//               waitForConnection(); // wait for a connection
//               getStreams(); // get input & output streams
//               try{processConnection(); }catch(Exception e) {
//            	   e.printStackTrace();// process connection
//               }
//            } // end try
//            catch ( EOFException eofException ) 
//            {
//               displayMessage( "\nServer terminated connection" );
//            } // end catch
//            finally 
//            {
//               closeConnection(); //  close connection
//               counter++;
//            } // end finally
//         } // end while
//      } // end try
//      catch ( IOException ioException ) 
//      {
//         ioException.printStackTrace();
//      } // end catch
//   } // end method runServer

   // wait for connection to arrive, then display connection info
//   private void waitForConnection() throws IOException
//   {
//      System.out.println( "Waiting for connection\n" );
//      connection = server.accept(); // allow server to accept connection            
//      System.out.println( "Connection " + counter + " received from: " +
//         connection.getInetAddress().getHostName() );
//   } // end method waitForConnection

   // get streams to send and receive data
//   private void getStreams() throws IOException{
//       output = new DataOutputStream( connection.getOutputStream() );
//      output.flush();
//       input = new DataInputStream( connection.getInputStream() );
//      displayMessage( "Got I/O streams" );
//   } 


    
	  
   
   // close streams and socket
	   private void closeConnection(){
		      displayMessage( "Terminating connection" );
		      try{
		         output.close(); 
		         input.close(); 
		         connection.close();
		      } 
		      catch ( IOException ioException ) 
		      {
		         ioException.printStackTrace();
		      } 
		   }
		

   // send message to client
   static void sendData( String message ){
	      try{
	         output.writeUTF( "SERVER>>> " + message );
	         output.flush(); 
	         displayMessage( "SERVER>>> " + message );
	      } 
	      catch ( IOException ioException ){
	        System.out.println( "Error writing object" );
	      }
	   }

   // manipulates displayArea in the event-dispatch thread
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
  
   public static User getUser() {
	   return user;
   }
   
   
   
}