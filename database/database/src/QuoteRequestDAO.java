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

@WebServlet("/QuoteRequestDAO")
public class QuoteRequestDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	public QuoteRequestDAO() {}
	
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

    
	public boolean insert(QuoteRequest quoteRequest) {
	    try {
	        connect_func("root", "pass1234"); 

	        String sql = "INSERT INTO QuoteRequest (treeSize, treeHeight, location, proximityToHouse) VALUES (?, ?, ?, ?)";
	        PreparedStatement preparedStatement = connect.prepareStatement(sql);
	        preparedStatement.setString(1, quoteRequest.getTreeSize());
	        preparedStatement.setString(2, quoteRequest.getTreeHeight());
	        preparedStatement.setString(3, quoteRequest.getLocation());
	        preparedStatement.setString(4, quoteRequest.getProximityToHouse());

	        int rowsAffected = preparedStatement.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
}

