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
    
    public List<Users> listAllUsers() throws SQLException {
        List<Users> listUser = new ArrayList<Users>();        
        String sql = "SELECT * FROM Users";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");
            String creditCard = resultSet.getString("creditCard");
            String address = resultSet.getString("address");
            String phoneNumber = resultSet.getString("phoneNumber");
             
            Users users = new Users(username, firstName, lastName, password, role, creditCard,address,phoneNumber);
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
    
    public void insert(Users users) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into User( username, password, role, creditCard, address, phoneNumber, firstName, lastName) values (?, ?, ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, users.getUsername());
			preparedStatement.setString(2, users.getPassword());
			preparedStatement.setString(3, users.getRole());
			preparedStatement.setString(4, users.getCreditCard());
			preparedStatement.setString(5, users.getAddress());
			preparedStatement.setString(6, users.getPhoneNumber());
			preparedStatement.setString(7, users.getLastName());
			preparedStatement.setString(8, users.getFirstName());
			
        preparedStatement.close();
    }
    
    public boolean delete(String username) throws SQLException {
        String sql = "DELETE FROM User WHERE username = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, username);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(Users users) throws SQLException {
        String sql = "update User set password = ?, role = ?, where username = ?, where firstName = ?, where lastName = ?, where creditCard = ?, where address = ?, where phoneNumber = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, users.getUsername());
        preparedStatement.setString(2, users.getFirstName());
        preparedStatement.setString(3, users.getLastName());
		preparedStatement.setString(4, users.getPassword());
		preparedStatement.setString(5, users.getRole());
		preparedStatement.setString(6, users.getCreditCard());
		preparedStatement.setString(7, users.getAddress());
		preparedStatement.setString(8, users.getPhoneNumber());
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public Users getUser(String username) throws SQLException {
    	Users user = null;
        String sql = "SELECT * FROM User WHERE username = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, username);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
        	String firstName = resultSet.getString("firstName");
        	String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");
            String creditCard = resultSet.getString("creditCard");
            String address = resultSet.getString("address");
            String phoneNumber = resultSet.getString("phoneNumber");
            user = new Users( username,firstName,lastName, password, role, creditCard, address, phoneNumber);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }
    
    public boolean checkUsername(String username) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM Users WHERE username = ?";
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
    	String sql = "SELECT * FROM Users WHERE password = ?";
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
    	String sql = "SELECT * FROM Users";
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
                	"id INTEGER AUTO_INCREMENT PRIMARY KEY," +
                	"role VARCHAR(50)," +
                	"password VARCHAR(50),"+
                	"username VARCHAR(50),"+
                    "firstname VARCHAR(50)," +
                    "lastname VARCHAR(50)," +
                    "creditCard CHAR(50)," +
                    "phoneNumber VARCHAR(50)," +
                    "address VARCHAR(50),"+
                    "UNIQUE(username))",

                "CREATE TABLE if not exists QuoteRequests(" +
                	"QuoteID INTEGER AUTO_INCREMENT PRIMARY KEY," +
                    "clientID INTEGER," +
                    "price DOUBLE," +
                    "scheduleStart VARCHAR(50)," +
                    "scheduleEnd VARCHAR(50)," +
                    "FOREIGN KEY (clientID) REFERENCES Users(id))",

                "CREATE TABLE if not exists Trees(" +
                	"id INTEGER,"+
                    "quoteID INTEGER," +
                    "size DOUBLE," +
                    "height DOUBLE," +
                    "distanceFromHouse DOUBLE," +
                    "PRIMARY KEY(id)," +
                    "FOREIGN KEY(quoteID) REFERENCES QuoteRequests(QuoteID))",

                "CREATE TABLE if not exists CounterRequest(" +
                	"id INTEGER," +
                    "userid INTEGER," +
                    "quoteID INTEGER," +
                    "price DOUBLE," +
                    "schedulestart VARCHAR(50)," +
                    "scheduleend VARCHAR(50)," +
                    "note VARCHAR(200)," +
                    "FOREIGN KEY(userid) REFERENCES Users(id)," +
                    "FOREIGN KEY(quoteID) REFERENCES QuoteRequests(QuoteID))",

                "CREATE TABLE if not exists Orders(" +
                    "id INTEGER AUTO_INCREMENT PRIMARY KEY," +
                    "quoteID INTEGER," +
                    "price DOUBLE," +
                    "schedulestart DATETIME," +
                    "scheduleend DATETIME," +
                    "FOREIGN KEY(quoteID) REFERENCES QuoteRequests(QuoteID))",

                "CREATE TABLE if not exists Bills(" +
                    "id INTEGER AUTO_INCREMENT PRIMARY KEY," +
                    "orderid INTEGER," +
                    "price DOUBLE," +
                    "discount DOUBLE," +
                    "balance DOUBLE," +
                    "status VARCHAR(20)," +
                    "FOREIGN KEY(orderid) REFERENCES Orders(id))",

                "CREATE TABLE if not exists BillsMessages(" +
                    "id INTEGER AUTO_INCREMENT PRIMARY KEY," +
                    "userid INTEGER," +
                    "billid INTEGER," +
                    "msgtime DATETIME," +
                    "price DOUBLE," +
                    "schedulestart DATETIME," +
                    "scheduleend DATETIME," +
                    "note VARCHAR(200)," +
                    "FOREIGN KEY(userid) REFERENCES Users(id)," +
                    "FOREIGN KEY(billid) REFERENCES Bills(id))"
            };
			
        String[] TUPLES = {
        		
        		  "insert into Users(username, role, password, creditCard, address, phoneNumber, firstname, lastname) values " +
        		  "('susie@gmail.com', 'Client', 'susie1234', '1234-5678-9876-5432', '123 Main St', '313-555-1234', 'Susie', 'Que')",
        		  "insert into Users(username, role, password, creditCard, address, phoneNumber, firstname, lastname) values " +
        		  "('don@gmail.com', 'Client', 'don123', '5678-9876-5432-1234', '456 Elm St', '313-555-7899', 'Don', 'Zemke')" , 
        		  "insert into Users(username, role, password, creditCard, address, phoneNumber, firstname, lastname) values " +
        		  "('joe@gmail.com', 'Client', 'joe1234', '5432-1234-5678-9876', '101 Pine St', '313-555-9012','Joe','Thomas') " ,
        		  "insert into Users(username, role, password, creditCard, address, phoneNumber, firstname, lastname) values " +
        		  "('wallace@gmail.com', 'Client', 'wallace1234', '4321-1234-5678-8765', '202 Cedar St', '313-123-5559','Wallace','Smtih') " ,
        		  "insert into Users(username, role, password, creditCard, address, phoneNumber, firstname, lastname) values " +
        		  "('amelia@gmail.com', 'Client', 'amelia1234', '8765-4321-1234-5678', '303 Birch St', '313-683-5560','Amelia','Johnson') " ,
        		  "insert into Users(username, role, password, creditCard, address, phoneNumber, firstname, lastname) values " +
        		  "('sophie@gmail.com', 'Client', 'sophie1234', '1111-2222-3333-4444', '404 Spruce St', '313-900-5561','Sophie','Spags') " ,
        		  "insert into Users(username, role, password, creditCard, address, phoneNumber, firstname, lastname) values " +
        		  "('angelo@gmail.com', 'Client', 'angelo1234', '2222-3333-4444-5555', '505 Redwood St', '313-333-2345','Angelo','Vasquez') " ,
        		  "insert into Users(username, role, password, creditCard, address, phoneNumber, firstname, lastname) values " +
        		  "('rudy@gmail.com', 'Client', 'rudy1234', '3333-4444-5555-6666', '606 Sequoia St', '313-243-8923','Rudy','Williams') " ,
        		  "insert into Users(username, role, password, creditCard, address, phoneNumber, firstname, lastname) values " +
        		  "('jeannette@gmail.com', 'Client', 'jeannette1234', '4444-5555-6666-7777', '707 Maple St', '313-720-3422','Jeannette','Jones') " ,
        		  "insert into Users(username, role, password, creditCard, address, phoneNumber, firstname, lastname) values " +
        		  "('james@gmail.com', 'Client', 'james123', '1111-2222-3333-4444', '123 windridge St', '313-666-2134','James','Brown')",
        			    

        			    "insert into QuoteRequests(clientID, price, scheduleStart, scheduleEnd) values " +
        			        "((SELECT id FROM Users WHERE username = 'don@gmail.com'), 100.00, '2023-01-01 10:00:00', '2023-01-01 12:00:00')",
        			    "insert into QuoteRequests(clientID, price, scheduleStart, scheduleEnd) values " +
        			        "((SELECT id FROM Users WHERE username = 'margarita@gmail.com'), 150.00, '2023-01-02 11:00:00', '2023-01-02 14:00:00')",
        			    // Add other QuoteRequests INSERT statements similarly

        			    // Add other INSERT statements similarly
        		
      /*
        	    
				"insert into QuoteRequests(clientID, price, scheduleStart, scheduleEnd) " +
				"values ((SELECT id FROM Users WHERE username = 'don@gmail.com'), 100.00, '2023-01-01 10:00:00', '2023-01-01 12:00:00'), " +
				"((SELECT id FROM Users WHERE username = 'margarita@gmail.com'), 150.00, '2023-01-02 11:00:00', '2023-01-02 14:00:00')," +

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
				"(2, 2, '2023-01-02 13:45:00', 150.00, '2023-01-02 14:45:00', '2023-01-02 15:45:00', 'Note 2')" */

        	};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++) {
        	statement.execute(INITIAL[i]);
        	System.out.println("Create is ok"+ i);
        }
        for (int i = 0; i < TUPLES.length; i++) {
            System.out.println("Executing SQL statement: "+ i + TUPLES[i]);
            statement.execute(TUPLES[i]);
            System.out.println("Execution successful");
        }
        	
    
        disconnect();
    }
    
    
   
    
    
    
    
    
	
	

}
