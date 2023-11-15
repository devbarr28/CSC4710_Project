import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/userDAO")
public class userDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public userDAO(){}
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
    	//uses default connection to the database
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false&user=john&password=john123");
            System.out.println(connect);
        }
    }
    
    public boolean database_login(String username, String password) throws SQLException{
    	try {
    		connect_func("root","abc123");
    		String sql = "select * from User where username = ?";
    		preparedStatement = connect.prepareStatement(sql);
    		preparedStatement.setString(1, username);
    		ResultSet rs = preparedStatement.executeQuery();
    		return rs.next();
    	}
    	catch(SQLException e) {
    		System.out.println("failed login");
    		return false;
    	}
    }
	//connect to the database 
    public void connect_func(String username, String password) throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/userdb?"
  			          + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }
    
    public List<user> listAllUsers() throws SQLException {
        List<user> listUser = new ArrayList<user>();        
        String sql = "SELECT * FROM User";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");
            String creditCard = resultSet.getString("creditCard");
            String address = resultSet.getString("address");
            String phoneNumber = resultSet.getString("phoneNumber");
             
            user users = new user(username, password, role, creditCard,address,phoneNumber);
            listUser.add(users);
        }        
        resultSet.close();
        disconnect();        
        return listUser;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void insert(user users) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into User( username, password, role, creditCard, address, phoneNumber) values ( ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, users.getUsername());
			preparedStatement.setString(2, users.getPassword());
			preparedStatement.setString(3, users.getRole());
			preparedStatement.setString(4, users.getCreditCard());
			preparedStatement.setString(5, users.getAddress());
			preparedStatement.setString(6, users.getPhoneNumber());
		

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM User WHERE username = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, id);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(user users) throws SQLException {
        String sql = "update User set password = ?, role = ?, where email = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, users.getID());
        preparedStatement.setString(2, users.getUsername());
		preparedStatement.setString(3, users.getPassword());
		preparedStatement.setString(4, users.getRole());
		preparedStatement.setString(5, users.getCreditCard());
		preparedStatement.setString(6, users.getAddress());
		preparedStatement.setString(7, users.getPhoneNumber());
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public user getUser(int id) throws SQLException {
    	user user = null;
        String sql = "SELECT * FROM User WHERE username = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, id);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
        	String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");
            String creditCard = resultSet.getString("creditCard");
            String address = resultSet.getString("address");
            String phoneNumber = resultSet.getString("phoneNumber");
            user = new user( username, password, role, creditCard, address, phoneNumber);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }
    
    public boolean checkUsername(String username) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM User WHERE username = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
    	return checks;
    }
    
    public boolean checkPassword(String password) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM User WHERE password = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
       	return checks;
    }
    
    
    
    public boolean isValid(String username, String password) throws SQLException
    {
    	String sql = "SELECT * FROM User";
    	connect_func();
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	resultSet.last();
    	
    	int setSize = resultSet.getRow();
    	resultSet.beforeFirst();
    	
    	for(int i = 0; i < setSize; i++)
    	{
    		resultSet.next();
    		if(resultSet.getString("username").equals(username) && resultSet.getString("password").equals(password)) {
    			return true;
    		}		
    	}
    	return false;
    }
    
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
     
        
       
        String[] INITIAL = {
                "drop database if exists testdb;",
                "create database testdb;",
                "use testdb;",

                "CREATE TABLE if not exists Users(" +
                    "id INTEGER AUTO_INCREMENT," +
                	"username VARCHAR(50),"+
                    "firstname VARCHAR(50)," +
                    "lastname VARCHAR(50)," +
                    "creditCard CHAR(16)," +
                    "phoneNumber VARCHAR(50)," +
                    "PRIMARY KEY(id)," +
                    "UNIQUE(username))",

                "CREATE TABLE if not exists quoteRequest(" +
                    "quoteID INTEGER AUTO_INCREMENT," +
                    "contractorid INTEGER," +
                    "clientid INTEGER," +
                    "price DOUBLE," +
                    "schedulestart DATETIME," +
                    "scheduleend DATETIME," +
                    "PRIMARY KEY(id)," +
                    "FOREIGN KEY (contractorid) REFERENCES Users(id)," +
                    "FOREIGN KEY (clientid) REFERENCES Users(id))",

                "CREATE TABLE if not exists Trees(" +
                    "id INTEGER AUTO_INCREMENT," +
                    "quoteID INTEGER," +
                    "size DOUBLE," +
                    "height DOUBLE," +
                    "distanceFromHouse DOUBLE," +
                    "PRIMARY KEY(id)," +
                    "FOREIGN KEY(quoteID) REFERENCES quoteRequest(quoteID))",

                "CREATE TABLE if not exists QuotesMessages(" +
                    "id INTEGER AUTO_INCREMENT," +
                    "userid INTEGER," +
                    "quoteID INTEGER," +
                    "msgtime DATETIME," +
                    "price DOUBLE," +
                    "schedulestart DATETIME," +
                    "scheduleend DATETIME," +
                    "note VARCHAR(200)," +
                    "PRIMARY KEY(id)," +
                    "FOREIGN KEY(userid) REFERENCES Users(id)," +
                    "FOREIGN KEY(quoteID) REFERENCES quoteRequest(quoteID))",

                "CREATE TABLE if not exists Orders(" +
                    "id INTEGER AUTO_INCREMENT," +
                    "quoteID INTEGER," +
                    "price DOUBLE," +
                    "schedulestart DATETIME," +
                    "scheduleend DATETIME," +
                    "PRIMARY KEY(id)," +
                    "FOREIGN KEY(quoteID) REFERENCES quoteRequest(quoteID))",

                "CREATE TABLE if not exists Bills(" +
                    "id INTEGER AUTO_INCREMENT," +
                    "orderid INTEGER," +
                    "price DOUBLE," +
                    "discount DOUBLE," +
                    "balance DOUBLE," +
                    "status VARCHAR(20)," +
                    "PRIMARY KEY(id)," +
                    "FOREIGN KEY(orderid) REFERENCES Orders(id))",

                "CREATE TABLE if not exists BillsMessages(" +
                    "id INTEGER AUTO_INCREMENT," +
                    "userid INTEGER," +
                    "billid INTEGER," +
                    "msgtime DATETIME," +
                    "price DOUBLE," +
                    "schedulestart DATETIME," +
                    "scheduleend DATETIME," +
                    "note VARCHAR(200)," +
                    "PRIMARY KEY(id)," +
                    "FOREIGN KEY(userid) REFERENCES Users(id)," +
                    "FOREIGN KEY(billid) REFERENCES Bills(id))"
            };
			
        String[] TUPLES = {
        	    "insert into User(username, role, password, creditCard, address, phoneNumber) " +
        	    "values ('susie@gmail.com', 'Client', 'susie1234', '1234-5678-9876-5432', '123 Main St', '555-555-5555'), " +
        	    "('don@gmail.com', 'Client', 'don123', '5678-9876-5432-1234', '456 Elm St', '555-555-5556'), " +
        	    "('margarita@gmail.com', 'Client', 'margarita1234', '9876-5432-1234-5678', '789 Oak St', '555-555-5557'), " +
        	    "('jo@gmail.com', 'Client', 'jo1234', '5432-1234-5678-9876', '101 Pine St', '555-555-5558'), " +
        	    "('wallace@gmail.com', 'Client', 'wallace1234', '4321-1234-5678-8765', '202 Cedar St', '555-555-5559'), " +
        	    "('amelia@gmail.com', 'Client', 'amelia1234', '8765-4321-1234-5678', '303 Birch St', '555-555-5560'), " +
        	    "('sophie@gmail.com', 'Client', 'sophie1234', '1111-2222-3333-4444', '404 Spruce St', '555-555-5561'), " +
        	    "('angelo@gmail.com', 'Client', 'angelo1234', '2222-3333-4444-5555', '505 Redwood St', '555-555-5562'), " +
        	    "('rudy@gmail.com', 'Client', 'rudy1234', '3333-4444-5555-6666', '606 Sequoia St', '555-555-5563'), " +
        	    "('jeannette@gmail.com', 'Client', 'jeannette1234', '4444-5555-6666-7777', '707 Maple St', '555-555-5564'), " +
        	    "('root', 'default', 'pass1234', '1111-2222-3333-4444', '123 Admin St', '555-555-5565')," +
        	    
				"insert into quoteRequests(contractorid, clientid, price, schedulestart, scheduleend)" +
				"values (1, 2, 100.00, '2023-01-01 10:00:00', '2023-01-01 12:00:00')," +
				"(2, 3, 150.00, '2023-01-02 11:00:00', '2023-01-02 14:00:00')," +

				"insert into Trees(quoteID, size, height, distanceFromHouse) values " +
				"(1, 10.5, 15.0, 5.0)," +
				"(2, 8.0, 12.0, 3.0)," +

				"insert into QuotesMessages(userid, quoteid, msgtime, price, schedulestart, scheduleend, note) values " +
				"(1, 1, '2023-01-01 11:30:00', 100.00, '2023-01-01 12:30:00', '2023-01-01 13:30:00', 'Note 1')," +
				"(2, 2, '2023-01-02 12:45:00', 150.00, '2023-01-02 13:45:00', '2023-01-02 14:45:00', 'Note 2')," +
	
				"insert into Orders(quoteid, price, schedulestart, scheduleend) values " +
				"(1, 100.00, '2023-01-01 10:00:00', '2023-01-01 12:00:00')," +
				"(2, 150.00, '2023-01-02 11:00:00', '2023-01-02 14:00:00')," +

				"insert into Bills(orderid, price, discount, balance, status) values " +
				"(1, 100.00, 0.0, 100.00, 'pending')," +
				"(2, 150.00, 0.0, 150.00, 'pending')," +

				"insert into BillsMessages(userid, billid, msgtime, price, schedulestart, scheduleend, note) values " +
				"(1, 1, '2023-01-01 12:30:00', 100.00, '2023-01-01 13:30:00', '2023-01-01 14:30:00', 'Note 1')," +
				"(2, 2, '2023-01-02 13:45:00', 150.00, '2023-01-02 14:45:00', '2023-01-02 15:45:00', 'Note 2')" 

        	};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++) {
        	statement.execute(INITIAL[i]);
        	System.out.println("Statment is ok"+ i);
        }
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        disconnect();
    }
    
    
   
    
    
    
    
    
	
	

}
