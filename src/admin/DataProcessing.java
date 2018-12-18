package admin;

import java.util.Enumeration;
import java.util.Hashtable;
import java.sql.*;

public  class DataProcessing {
	
	static Hashtable<String,Doc> docs;
	static Hashtable<String,User> users;
	static {
		users = new Hashtable<String,User>();
		docs = new Hashtable<String,Doc>();
		
	}
	
	
	static String driverName="com.mysql.cj.jdbc.Driver";               // 加载数据库驱动类必须加 cj
    static String url="jdbc:mysql://localhost:3306/document?serverTimezone=UTC"; // 声明数据库的URL mysql6以上需要指定时区
    static String user="root";                                      // 数据库用户
    static String password="86771385";
    static Connection connection;
   
    //连接数据库 将数据载入哈希表 connection未关闭每次调用init（）函数，都要关闭静态变量connection
	public static  void init() throws ClassNotFoundException, SQLException{
		Class.forName(driverName);
		 connection = DriverManager.getConnection(url, user, password);
		 Statement statement=null;
		 ResultSet resultSet=null;
			//将数据读入hashtable
			statement = connection.createStatement();
			String sqlDoc = "select * from Doc_info";
			resultSet = statement.executeQuery(sqlDoc);
			while(resultSet.next()) {
				String id=resultSet.getString("Id");
				String creator=resultSet.getString("creator");
				String time=resultSet.getString("timestamp");
				String description = resultSet.getString("description");
				String filename = resultSet.getString("filename");
				Doc doc = new Doc(id,creator,time,description,filename);
				docs.put(id,doc);
			}
			String sqlUser = "select * from User_info";
			resultSet = statement.executeQuery(sqlUser);
			while(resultSet.next()) {
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				String role = resultSet.getString("role");
				
				if(role.equalsIgnoreCase("Administrator")) {
					users.put(username, new Administrator(username,password,role));
				}
				if(role.equalsIgnoreCase("Browser")) {
					users.put(username, new Browser(username,password,role));
				}
				if(role.equalsIgnoreCase("Operator")) {
					users.put(username, new Operator(username,password,role));
				}
				
			}
				resultSet.close();
				statement.close();
				
	   
	    }
	
	
	
	public static Doc searchDoc(String ID) throws SQLException, ClassNotFoundException {
		    Statement statement=null;
		    ResultSet resultSet=null;
		    String id=null,creator=null,time=null,description=null,filename=null;
	    	init();
			String sql="select * from Doc_info where id='"+ID+"'";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()){
				id=resultSet.getString("Id");
				creator=resultSet.getString("creator");
				time=resultSet.getString("timestamp");
				description = resultSet.getString("description");
				filename = resultSet.getString("filename");
			}
			Doc doc = new Doc(id,creator,time,description,filename);
			resultSet.close();                        
            statement.close();                        
            return doc;
	}
	
	public static Enumeration<Doc> getAllDocs() throws SQLException, ClassNotFoundException{		
		
		Statement statement=null;
		 ResultSet resultSet=null;
		init();
		String sql = "select * from Doc_info";
		Enumeration <Doc>e = null;
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {
			String id=resultSet.getString("Id");
			String creator=resultSet.getString("creator");
			String time=resultSet.getString("timestamp");
			String description = resultSet.getString("description");
			String filename = resultSet.getString("filename");
			Doc doc = new Doc(id,creator,time,description,filename);
			docs.put(id,doc );
		}
		e = docs.elements();
		resultSet.close();                        
        statement.close();                        
        
		return e;
	} 
	
	public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException, ClassNotFoundException{
		
		PreparedStatement pre=null;		
		init();
		if (docs.containsKey(ID))
			{
			System.out.println("ID已经存在");////insert into user_info(username,password,role) value(?,?,?)
			return false;                          
			}
		else{
			String sql = "insert into doc_info(creator,timestamp,description,filename) values(?,?,?,?);";
			pre= connection.prepareStatement(sql);
			//pre.setInt(1, Integer.parseInt(ID));
			pre.setString(1, creator);
			pre.setTimestamp(2, timestamp);
			pre.setString(3, description);
			pre.setString(4, filename);
			pre.executeUpdate();
			pre.close();
			Doc doc = new Doc(ID, creator, timestamp.toString(), description, filename);
			docs.put(ID, doc);
			return true;
		}
	} 
	
	public static User searchUser(String name) throws SQLException, ClassNotFoundException{
		
		init();
		if (users.containsKey(name)) {
			return users.get(name);			
		}
		else {
			System.out.println("用户不存在");
			return null;
		}

	}
	
	public static User searchUser(String name, String password) throws SQLException, ClassNotFoundException {
		
		init();
		
		if (users.containsKey(name)) {
			User temp =users.get(name);
			if ((temp.getPassword()).equals(password))
				return temp;
		}
		return null;
	}
	
	public static Enumeration<User> getAllUser() throws SQLException, ClassNotFoundException{
		
		init();
		Enumeration e2 =users.elements();
		while(e2.hasMoreElements()) {
		System.out.println(e2.nextElement().toString());}
		Enumeration<User> e  = users.elements();
		return e;
	}
	
	
	
	public static boolean updateUser(String name, String password, String role) throws SQLException, ClassNotFoundException{
		
		PreparedStatement pre=null;
		init();
		String sql = "update user_info set password=?,role=? where username=?";
		pre = connection.prepareStatement(sql);
		pre.setString(1, password);
		pre.setString(2, role);
		pre.setString(3, name);
		pre.executeUpdate();
		pre.close();
		
		return true;
	}
	
	public static boolean insertUser(String name, String password, String role) throws SQLException, ClassNotFoundException{		
		
		PreparedStatement pre=null;
		init();
		if (users.containsKey(name))
			return false;
		else{
			String sql = "insert into user_info(username,password,role) value(?,?,?)";
			pre = connection.prepareStatement(sql);
			pre.setString(1, name);
			pre.setString(2, password);
			pre.setString(3, role);
			pre.executeUpdate();
			pre.close();
			User u = new User(name,password,role);
			users.put(name, u);
			return true;
		}
	}
	
	public static boolean deleteUser(String name) throws SQLException, ClassNotFoundException{
		PreparedStatement pre=null;
		init();
		if (users.containsKey(name)){
			
			String sql = "delete from user_info where username='"+name+"'";
			pre = connection.prepareStatement(sql);
			pre.executeUpdate();
			users.remove(name);
			return true;
		}else
			return false;
		
	}	
            
	public static void disconnectFromDB() {
		if ( connection!=null ){
			// close Statement and Connection            
			try{
 			    
			}catch(Exception e) {
				e.printStackTrace();
			}                             
		} 
   }           

	
	
	
	
}
