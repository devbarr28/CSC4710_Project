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

    // Create a method to insert a new quote request into the database
    public boolean insert(QuoteRequest quoteRequest) {
        try {
            String sql = "INSERT INTO QuoteRequest (treeType, treeSize, treeHeight, location, proximityToHouse) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, quoteRequest.getTreeType());
            preparedStatement.setString(2, quoteRequest.getTreeSize());
            preparedStatement.setString(3, quoteRequest.getTreeHeight());
            preparedStatement.setString(4, quoteRequest.getLocation());
            preparedStatement.setString(5, quoteRequest.getProximityToHouse());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
