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
    public List<Users> getBigClients() {
        List<Users> bigClients = new ArrayList<>();
        try {
            connect_func();
            String sql = "SELECT u.id, u.firstname, u.lastname, u.creditCard, u.username, u.phoneNumber, COUNT(t.id) as treeCount " +
                         "FROM Users u " +
                         "LEFT JOIN Trees t ON u.id = t.id " +
                         "GROUP BY u.id " +
                         "ORDER BY treeCount DESC " +
                         "LIMIT 1";
            preparedStatement = connect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Populate bigClients from the result set
            while (resultSet.next()) {
                Users user = new Users();
                user.setID(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setCreditCard(resultSet.getString("creditCard"));
                user.setUsername(resultSet.getString("username"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                bigClients.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bigClients;
    }
    
    public List<Users> getEasyClients() {
        List<Users> easyClients = new ArrayList<>();
        try {
            connect_func();
            String sql = "SELECT u.* " +
                         "FROM Users u " +
                         "LEFT JOIN QuoteRequests qr ON u.id = qr.clientID " +
                         "WHERE qr.status = 'accepted' AND qr.Price IS NULL";
            preparedStatement = connect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Populate easyClients from the result set
            while (resultSet.next()) {
                Users user = new Users();
                user.setID(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setCreditCard(resultSet.getString("creditCard"));
                user.setUsername(resultSet.getString("username"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                easyClients.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return easyClients;
    }
    public List<Users> getProspectiveClients() {
        List<Users> prospectiveClients = new ArrayList<>();
        try {
            connect_func();
            String sql = "SELECT u.* " +
                         "FROM Users u " +
                         "LEFT JOIN QuoteRequests qr ON u.id = qr.clientID " +
                         "LEFT JOIN Orders o ON qr.QuoteID = o.quoteID " +
                         "WHERE o.id IS NULL AND qr.status = 'Accepted'";
            preparedStatement = connect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Populate prospectiveClients from the result set
            while (resultSet.next()) {
                Users user = new Users();
                user.setID(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setCreditCard(resultSet.getString("creditCard"));
                user.setUsername(resultSet.getString("username"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                prospectiveClients.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return prospectiveClients;
    }
    public List<Users> getGoodClients() {
        List<Users> goodClients = new ArrayList<>();
        try {
            connect_func();
            String sql = "SELECT * FROM Users u WHERE NOT EXISTS (SELECT * FROM Bills b WHERE b.id = u.id AND b.status = 'Unpaid')";
            preparedStatement = connect.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Populate goodClients from the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                String creditCard = resultSet.getString("creditCard");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");

                Users user = new Users(id, username, firstName, lastName, password, role, creditCard, address, phoneNumber);
                goodClients.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return goodClients;
    }

    // Get clients who have never paid any bill after it is due
    public List<Users> getBadClients() {
        List<Users> badClients = new ArrayList<>();
        try {
            connect_func();
            String sql = "SELECT * FROM Users u WHERE EXISTS (SELECT * FROM Bills b WHERE b.id = u.id AND b.status = 'Unpaid')";
            preparedStatement = connect.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Populate badClients from the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                String creditCard = resultSet.getString("creditCard");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");

                Users user = new Users(id, username, firstName, lastName, password, role, creditCard, address, phoneNumber);
                badClients.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return badClients;
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
                    "status VARCHAR(50)," +
                    "scheduleStart VARCHAR(50)," +
                    "scheduleEnd VARCHAR(50)," +
                    "FOREIGN KEY (clientID) REFERENCES Users(id))",

                "CREATE TABLE if not exists Trees(" +
                	"id INTEGER AUTO_INCREMENT PRIMARY KEY,"+
                    "quoteID INTEGER," +
                    "size DOUBLE," +
                    "height DOUBLE," +
                    "distanceFromHouse DOUBLE," +
                    "FOREIGN KEY(quoteID) REFERENCES QuoteRequests(QuoteID))",

                "CREATE TABLE if not exists CounterRequest(" +
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
        	    "INSERT INTO Users(username, role, password, creditCard, address, phoneNumber, firstname, lastname) VALUES " +
        	        "('susie@gmail.com', 'Client', 'susie1234', '1234-5678-9876-5432', '123 Main St', '313-555-1234', 'Susie', 'Que')",
        	    "INSERT INTO Users(username, role, password, creditCard, address, phoneNumber, firstname, lastname) VALUES " +
        	        "('don@gmail.com', 'Client', 'don123', '5678-9876-5432-1234', '456 Elm St', '313-555-7899', 'Don', 'Zemke')",
        	    "INSERT INTO Users(username, role, password, creditCard, address, phoneNumber, firstname, lastname) VALUES " +
        	        "('joe@gmail.com', 'Client', 'joe1234', '5432-1234-5678-9876', '101 Pine St', '313-555-9012','Joe','Thomas')",
        	    "INSERT INTO Users(username, role, password, creditCard, address, phoneNumber, firstname, lastname) VALUES " +
        	        "('wallace@gmail.com', 'Client', 'wallace1234', '4321-1234-5678-8765', '202 Cedar St', '313-123-5559','Wallace','Smtih')",
        	    "INSERT INTO Users(username, role, password, creditCard, address, phoneNumber, firstname, lastname) VALUES " +
        	        "('amelia@gmail.com', 'Client', 'amelia1234', '8765-4321-1234-5678', '303 Birch St', '313-683-5560','Amelia','Johnson')",
        	    "INSERT INTO Users(username, role, password, creditCard, address, phoneNumber, firstname, lastname) VALUES " +
        	        "('sophie@gmail.com', 'Client', 'sophie1234', '1111-2222-3333-4444', '404 Spruce St', '313-900-5561','Sophie','Spags')",
        	    "INSERT INTO Users(username, role, password, creditCard, address, phoneNumber, firstname, lastname) VALUES " +
        	        "('angelo@gmail.com', 'Client', 'angelo1234', '2222-3333-4444-5555', '505 Redwood St', '313-333-2345','Angelo','Vasquez')",
        	    "INSERT INTO Users(username, role, password, creditCard, address, phoneNumber, firstname, lastname) VALUES " +
        	        "('rudy@gmail.com', 'Client', 'rudy1234', '3333-4444-5555-6666', '606 Sequoia St', '313-243-8923','Rudy','Williams')",
        	    "INSERT INTO Users(username, role, password, creditCard, address, phoneNumber, firstname, lastname) VALUES " +
        	        "('jeannette@gmail.com', 'Client', 'jeannette1234', '4444-5555-6666-7777', '707 Maple St', '313-720-3422','Jeannette','Jones')",
        	    "INSERT INTO Users(username, role, password, creditCard, address, phoneNumber, firstname, lastname) VALUES " +
        	        "('james@gmail.com', 'Client', 'james123', '1111-2222-3333-4444', '123 windridge St', '313-666-2134','James','Brown')",

        	    "INSERT INTO QuoteRequests(clientID, price, status, scheduleStart, scheduleEnd) VALUES " +
        	        "((SELECT id FROM Users WHERE username = 'don@gmail.com'), 100.00,'accepted', '2023-01-01 10:00:00', '2023-01-01 12:00:00')",
        	    "INSERT INTO QuoteRequests(clientID, price, status, scheduleStart, scheduleEnd) VALUES " +
        	        "((SELECT id FROM Users WHERE username = 'susie@gmail.com'), 150.00,'accepted', '2023-01-02 11:00:00', '2023-01-02 14:00:00')",
        	        "INSERT INTO QuoteRequests(clientID, price, status, scheduleStart, scheduleEnd) VALUES " +
        	        "((SELECT id FROM Users WHERE username = 'joe@gmail.com'), 120.00,'accepted', '2023-01-03 13:00:00', '2023-01-03 15:00:00')",
        	        "INSERT INTO QuoteRequests(clientID, price, status, scheduleStart, scheduleEnd) VALUES " +
        	        "((SELECT id FROM Users WHERE username = 'wallace@gmail.com'), 80.00,'rejected', '2023-01-04 09:30:00', '2023-01-04 11:30:00')",
        	        "INSERT INTO QuoteRequests(clientID, price, status, scheduleStart, scheduleEnd) VALUES " +
        	        "((SELECT id FROM Users WHERE username = 'amelia@gmail.com'), 200.00,'rejected', '2023-01-05 14:00:00', '2023-01-05 16:00:00')",
        	        "INSERT INTO QuoteRequests(clientID, price, status, scheduleStart, scheduleEnd) VALUES " +
        	        "((SELECT id FROM Users WHERE username = 'sophie@gmail.com'), 90.00,'accepted', '2023-01-06 12:30:00', '2023-01-06 14:30:00')",
        	        "INSERT INTO QuoteRequests(clientID, price, status, scheduleStart, scheduleEnd) VALUES " +
        	        "((SELECT id FROM Users WHERE username = 'angelo@gmail.com'), 110.00,'rejected', '2023-01-07 11:45:00', '2023-01-07 13:45:00')",
        	        "INSERT INTO QuoteRequests(clientID, price, status, scheduleStart, scheduleEnd) VALUES " +
        	        "((SELECT id FROM Users WHERE username = 'rudy@gmail.com'), 130.00,'pending', '2023-01-08 10:15:00', '2023-01-08 12:15:00')",
        	        "INSERT INTO QuoteRequests(clientID, price, status, scheduleStart, scheduleEnd) VALUES " +
        	        "((SELECT id FROM Users WHERE username = 'jeannette@gmail.com'), 160.00,'pending', '2023-01-09 15:30:00', '2023-01-09 17:30:00')",
        	        "INSERT INTO QuoteRequests(clientID, price, status, scheduleStart, scheduleEnd) VALUES " +
        	        "((SELECT id FROM Users WHERE username = 'james@gmail.com'), 180.00,'accepted', '2023-01-10 08:45:00', '2023-01-10 10:45:00')",

        	    "INSERT INTO Trees(quoteID, size, height, distanceFromHouse) VALUES " +
        	        "((SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'don@gmail.com')), 10.5, 15.0, 5.0)",
        	    "INSERT INTO Trees(quoteID, size, height, distanceFromHouse) VALUES " +
        	        "((SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'susie@gmail.com')), 8.0, 12.0, 3.0)",
        	    "INSERT INTO Trees(quoteID, size, height, distanceFromHouse) VALUES " +
        	        "((SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'joe@gmail.com')), 9.5, 14.0, 4.5)",
        	    "INSERT INTO Trees(quoteID, size, height, distanceFromHouse) VALUES " +
        	        "((SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'wallace@gmail.com')), 7.0, 10.0, 2.5)",
        	    "INSERT INTO Trees(quoteID, size, height, distanceFromHouse) VALUES " +
        	        "((SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'amelia@gmail.com')), 12.0, 18.0, 6.0)",
        	    "INSERT INTO Trees(quoteID, size, height, distanceFromHouse) VALUES " +
        	        "((SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'sophie@gmail.com')), 5.5, 8.0, 2.0)",
        	    "INSERT INTO Trees(quoteID, size, height, distanceFromHouse) VALUES " +
        	        "((SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'angelo@gmail.com')), 6.5, 9.5, 3.5)",
        	    "INSERT INTO Trees(quoteID, size, height, distanceFromHouse) VALUES " +
        	        "((SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'rudy@gmail.com')), 8.5, 12.5, 4.0)",
        	    "INSERT INTO Trees(quoteID, size, height, distanceFromHouse) VALUES " +
        	        "((SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'jeannette@gmail.com')), 11.0, 16.0, 5.5)",
        	    "INSERT INTO Trees(quoteID, size, height, distanceFromHouse) VALUES " +
        	        "((SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'james@gmail.com')), 13.5, 20.0, 7.0)",

        	    "INSERT INTO CounterRequest(userid, quoteID, price, schedulestart, scheduleend, note) VALUES " +
        	        "(1, (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'don@gmail.com')), 100.00, '2023-01-01 11:30:00', '2023-01-01 12:30:00', 'Note 1')",
        	    "INSERT INTO CounterRequest(userid, quoteID, price, schedulestart, scheduleend, note) VALUES " +
        	        "(2, (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'susie@gmail.com')), 150.00, '2023-01-02 12:45:00', '2023-01-02 13:45:00', 'Note 2')",
        	    "INSERT INTO CounterRequest(userid, quoteID, price, schedulestart, scheduleend, note) VALUES " +
        	        "(3, (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'joe@gmail.com')), 120.00, '2023-01-03 14:00:00', '2023-01-03 15:00:00', 'Note 3')",
        	    "INSERT INTO CounterRequest(userid, quoteID, price, schedulestart, scheduleend, note) VALUES " +
        	        "(4, (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'wallace@gmail.com')), 80.00, '2023-01-04 10:00:00', '2023-01-04 11:00:00', 'Note 4')",
        	    "INSERT INTO CounterRequest(userid, quoteID, price, schedulestart, scheduleend, note) VALUES " +
        	        "(5, (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'amelia@gmail.com')), 200.00, '2023-01-05 15:00:00', '2023-01-05 16:00:00', 'Note 5')",
        	    "INSERT INTO CounterRequest(userid, quoteID, price, schedulestart, scheduleend, note) VALUES " +
        	        "(6, (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'sophie@gmail.com')), 90.00, '2023-01-06 13:00:00', '2023-01-06 14:00:00', 'Note 6')",
        	    "INSERT INTO CounterRequest(userid, quoteID, price, schedulestart, scheduleend, note) VALUES " +
        	        "(7, (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'angelo@gmail.com')), 110.00, '2023-01-07 12:15:00', '2023-01-07 13:15:00', 'Note 7')",
        	    "INSERT INTO CounterRequest(userid, quoteID, price, schedulestart, scheduleend, note) VALUES " +
        	        "(8, (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'rudy@gmail.com')), 130.00, '2023-01-08 11:30:00', '2023-01-08 12:30:00', 'Note 8')",
        	    "INSERT INTO CounterRequest(userid, quoteID, price, schedulestart, scheduleend, note) VALUES " +
        	        "(9, (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'jeannette@gmail.com')), 160.00, '2023-01-09 16:30:00', '2023-01-09 17:30:00', 'Note 9')",
        	    "INSERT INTO CounterRequest(userid, quoteID, price, schedulestart, scheduleend, note) VALUES " +
        	        "(10, (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'james@gmail.com')), 180.00, '2023-01-10 09:45:00', '2023-01-10 10:45:00', 'Note 10')",

        	       
        	        "INSERT INTO Orders (quoteID, price, schedulestart, scheduleend) VALUES " +
        	          "((SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'don@gmail.com')), 100.00, '2023-01-01 10:00:00', '2023-01-01 12:00:00')",
        	          "INSERT INTO Orders (quoteID, price, schedulestart, scheduleend) VALUES " +
        	          "((SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'susie@gmail.com')), 150.00, '2023-01-02 11:00:00', '2023-01-02 14:00:00')",
        	          "INSERT INTO Orders (quoteID, price, schedulestart, scheduleend) VALUES " +
        	          "((SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'joe@gmail.com')), 200.00, '2023-01-03 12:00:00', '2023-01-03 15:00:00')",
        	          "INSERT INTO Orders (quoteID, price, schedulestart, scheduleend) VALUES " +
        	          "((SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'wallace@gmail.com')), 250.00, '2023-01-04 13:00:00', '2023-01-04 16:00:00')",
        	          "INSERT INTO Orders (quoteID, price, schedulestart, scheduleend) VALUES " +
        	          "((SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'amelia@gmail.com')), 300.00, '2023-01-05 14:00:00', '2023-01-05 17:00:00')",
        	          "INSERT INTO Orders (quoteID, price, schedulestart, scheduleend) VALUES " +
        	          "((SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'sophie@gmail.com')), 350.00, '2023-01-06 15:00:00', '2023-01-06 18:00:00')",
        	          "INSERT INTO Orders (quoteID, price, schedulestart, scheduleend) VALUES " +
        	          "((SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'angelo@gmail.com')), 400.00, '2023-01-07 16:00:00', '2023-01-07 19:00:00')",
        	          "INSERT INTO Orders (quoteID, price, schedulestart, scheduleend) VALUES " +
        	          "((SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'rudy@gmail.com')), 450.00, '2023-01-08 17:00:00', '2023-01-08 20:00:00')",
        	          "INSERT INTO Orders (quoteID, price, schedulestart, scheduleend) VALUES " +
        	          "((SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'jeannette@gmail.com')), 500.00, '2023-01-09 18:00:00', '2023-01-09 21:00:00')",
        	          "INSERT INTO Orders (quoteID, price, schedulestart, scheduleend) VALUES " +
        	          "((SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'james@gmail.com')), 550.00, '2023-01-10 19:00:00', '2023-01-10 22:00:00')",

        	     
        	        "INSERT INTO Bills (orderid, price, discount, balance, status) VALUES " +
        	          "((SELECT id FROM Orders WHERE quoteID = (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'don@gmail.com'))), 100.00, 0.0, 100.00, 'pending')",
        	          "INSERT INTO Bills (orderid, price, discount, balance, status) VALUES " +
        	          "((SELECT id FROM Orders WHERE quoteID = (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'susie@gmail.com'))), 150.00, 0.0, 150.00, 'pending')",
        	          "INSERT INTO Bills (orderid, price, discount, balance, status) VALUES " +
        	          "((SELECT id FROM Orders WHERE quoteID = (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'joe@gmail.com'))), 200.00, 0.0, 200.00, 'pending')",
        	          "INSERT INTO Bills (orderid, price, discount, balance, status) VALUES " +
        	          "((SELECT id FROM Orders WHERE quoteID = (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'wallace@gmail.com'))), 250.00, 0.0, 250.00, 'pending')",
        	          "INSERT INTO Bills (orderid, price, discount, balance, status) VALUES " +
        	          "((SELECT id FROM Orders WHERE quoteID = (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'amelia@gmail.com'))), 300.00, 0.0, 300.00, 'pending')",
        	          "INSERT INTO Bills (orderid, price, discount, balance, status) VALUES " +
        	          "((SELECT id FROM Orders WHERE quoteID = (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'sophie@gmail.com'))), 350.00, 0.0, 350.00, 'pending')",
        	          "INSERT INTO Bills (orderid, price, discount, balance, status) VALUES " +
        	          "((SELECT id FROM Orders WHERE quoteID = (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'angelo@gmail.com'))), 400.00, 0.0, 400.00, 'pending')",
        	          "INSERT INTO Bills (orderid, price, discount, balance, status) VALUES " +
        	          "((SELECT id FROM Orders WHERE quoteID = (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'rudy@gmail.com'))), 450.00, 0.0, 450.00, 'pending')",
        	          "INSERT INTO Bills (orderid, price, discount, balance, status) VALUES " +
        	          "((SELECT id FROM Orders WHERE quoteID = (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'jeannette@gmail.com'))), 500.00, 0.0, 500.00, 'pending')",
        	          "INSERT INTO Bills (orderid, price, discount, balance, status) VALUES " +
        	          "((SELECT id FROM Orders WHERE quoteID = (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'james@gmail.com'))), 550.00, 0.0, 550.00, 'pending')",

        	       
        	        "INSERT INTO BillsMessages (billid, msgtime, price, schedulestart, scheduleend, note) VALUES "+
        	         " ((SELECT id FROM Bills WHERE orderid = (SELECT id FROM Orders WHERE quoteID = (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'don@gmail.com')))), '2023-01-01 12:30:00', 100.00, '2023-01-01 13:30:00', '2023-01-01 14:30:00', 'Note 1')",
        	         "INSERT INTO BillsMessages (billid, msgtime, price, schedulestart, scheduleend, note) VALUES "+
        	         "((SELECT id FROM Bills WHERE orderid = (SELECT id FROM Orders WHERE quoteID = (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'susie@gmail.com')))), '2023-01-02 13:45:00', 150.00, '2023-01-02 14:45:00', '2023-01-02 15:45:00', 'Note 2')",
        	         "INSERT INTO BillsMessages (billid, msgtime, price, schedulestart, scheduleend, note) VALUES "+
        	         "((SELECT id FROM Bills WHERE orderid = (SELECT id FROM Orders WHERE quoteID = (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'joe@gmail.com')))), '2023-01-03 14:30:00', 200.00, '2023-01-03 15:30:00', '2023-01-03 16:30:00', 'Note 3')",
        	         "INSERT INTO BillsMessages (billid, msgtime, price, schedulestart, scheduleend, note) VALUES "+
        	         "((SELECT id FROM Bills WHERE orderid = (SELECT id FROM Orders WHERE quoteID = (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'wallace@gmail.com')))), '2023-01-04 15:45:00', 250.00, '2023-01-04 16:45:00', '2023-01-04 17:45:00', 'Note 4')",
        	         "INSERT INTO BillsMessages (billid, msgtime, price, schedulestart, scheduleend, note) VALUES "+
        	         " ((SELECT id FROM Bills WHERE orderid = (SELECT id FROM Orders WHERE quoteID = (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'amelia@gmail.com')))), '2023-01-05 16:30:00', 300.00, '2023-01-05 17:30:00', '2023-01-05 18:30:00', 'Note 5')",
        	         "INSERT INTO BillsMessages (billid, msgtime, price, schedulestart, scheduleend, note) VALUES "+
        	         " ((SELECT id FROM Bills WHERE orderid = (SELECT id FROM Orders WHERE quoteID = (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'sophie@gmail.com')))), '2023-01-06 17:45:00', 350.00, '2023-01-06 18:45:00', '2023-01-06 19:45:00', 'Note 6')",
        	         "INSERT INTO BillsMessages (billid, msgtime, price, schedulestart, scheduleend, note) VALUES "+
        	         "((SELECT id FROM Bills WHERE orderid = (SELECT id FROM Orders WHERE quoteID = (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'angelo@gmail.com')))), '2023-01-07 18:30:00', 400.00, '2023-01-07 19:30:00', '2023-01-07 20:30:00', 'Note 7')",
        	         "INSERT INTO BillsMessages (billid, msgtime, price, schedulestart, scheduleend, note) VALUES "+
        	         "((SELECT id FROM Bills WHERE orderid = (SELECT id FROM Orders WHERE quoteID = (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'rudy@gmail.com')))), '2023-01-08 19:45:00', 450.00, '2023-01-08 20:45:00', '2023-01-08 21:45:00', 'Note 8')",
        	         "INSERT INTO BillsMessages (billid, msgtime, price, schedulestart, scheduleend, note) VALUES "+
        	         "((SELECT id FROM Bills WHERE orderid = (SELECT id FROM Orders WHERE quoteID = (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'jeannette@gmail.com')))), '2023-01-09 20:30:00', 500.00, '2023-01-09 21:30:00', '2023-01-09 22:30:00', 'Note 9')",
        	         "INSERT INTO BillsMessages (billid, msgtime, price, schedulestart, scheduleend, note) VALUES "+
        	         "((SELECT id FROM Bills WHERE orderid = (SELECT id FROM Orders WHERE quoteID = (SELECT QuoteID FROM QuoteRequests WHERE clientID = (SELECT id FROM Users WHERE username = 'james@gmail.com')))), '2023-01-10 21:15:00', 550.00, '2023-01-10 22:15:00', '2023-01-10 23:15:00', 'Note 10');"

        	         
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
