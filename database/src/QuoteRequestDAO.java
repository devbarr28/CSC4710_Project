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
	 public List<QuoteRequest> listAllQuotes() throws SQLException {
	        List<QuoteRequest> listQuote = new ArrayList<QuoteRequest>();        
	        String sql = "SELECT * FROM QuoteRequests";      
	        connect_func();      
	        statement = (Statement) connect.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);
	         
	        while (resultSet.next()) {
	            int clientID = resultSet.getInt("clientID");
	            Double price = resultSet.getDouble("price");
	            String scheduleStart = resultSet.getString("schedueStart");
	            String scheduleEnd = resultSet.getString("scheduleEnd");
	            
	            QuoteRequest quotes = new QuoteRequest(clientID, price, scheduleStart, scheduleEnd);
	            listQuote.add(quotes);
	        }        
	        resultSet.close();
	        disconnect();        
	        return listQuote;
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
	protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }

    
	public boolean insert(QuoteRequest quoteRequest) {
	    try {
	        connect_func("root", "pass1234"); 

	        String sql = "INSERT INTO QuoteRequests(clientID, price, scheduleStart, scheduleEnd) VALUES ((SELECT id FROM Users WHERE username = ?), ?, ?, ?)";
	         PreparedStatement preparedStatement = connect.prepareStatement(sql);
	        preparedStatement.setDouble(1, quoteRequest.getPrice());
	        preparedStatement.setString(2, quoteRequest.getScheduleStart());
	        preparedStatement.setString(3, quoteRequest.getScheduleEnd());


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
	public List<QuoteRequest> getOneTreeQuotes() {
        List<QuoteRequest> oneTreeQuotes = new ArrayList<>();
        try {
            connect_func();
            String sql = "SELECT qr.* " +
                         "FROM QuoteRequests qr " +
                         "LEFT JOIN Trees t ON qr.QuoteID = t.quoteid " +
                         "WHERE t.id IS NULL OR t.size = 1";
            preparedStatement = connect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Populate oneTreeQuotes from the result set
            while (resultSet.next()) {
                QuoteRequest quoteRequest = new QuoteRequest();
                quoteRequest.setQuoteID(resultSet.getInt("QuoteID"));
                quoteRequest.setClientID(resultSet.getInt("clientID"));
                quoteRequest.setPrice(resultSet.getDouble("price"));
                quoteRequest.setScheduleStart(resultSet.getString("scheduleStart"));
                quoteRequest.setScheduleEnd(resultSet.getString("scheduleEnd"));
                oneTreeQuotes.add(quoteRequest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return oneTreeQuotes;
    }
}

