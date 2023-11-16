import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class ControlServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private userDAO userDAO = new userDAO();
	    private QuoteRequestDAO QuoteRequestDAO = new QuoteRequestDAO();
	    private String currentUser;
	    private HttpSession session=null;
	    
	    public ControlServlet()
	    {
	    	
	    }
	    
	    public void init()
	    {
	    	currentUser= "";
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getServletPath();
	        System.out.println(action);
	    
	    try {
        	switch(action) {  
        	case "/login":
        		login(request,response);
        		break;
        	case "/register":
        		register(request, response);
        		break;
        	case "/initialize":
        		userDAO.init();
        		System.out.println("Database successfully initialized!");
        		rootPage(request,response,"");
        		break;
        	case "/root":
        		rootPage(request,response, "");
        		break;
        	case "/logout":
        		logout(request,response);
        		break;
        	 case "/list": 
                 System.out.println("The action is: list");
                 listUser(request, response);           	
                 break;
        	 case "/submitRequest":
        		 System.out.println("Request Submitted");
        		 submitQuoteRequest(request,response);
        	}
	    }
	    catch(Exception ex) {
        	System.out.println(ex.getMessage());
	    	}
	    }
        	
	    private void listUser(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listUser started: 00000000000000000000000000000000000");

	     
	        List<Users> listUsers = userDAO.listAllUsers();
	        request.setAttribute("listUser", listUsers);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listPeople finished: 111111111111111111111111111111111111");
	    }
	    	        
	    private void rootPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("root view");
			request.setAttribute("listUsers", userDAO.listAllUsers());
	    	request.getRequestDispatcher("rootView.jsp").forward(request, response);
	    }
	    
	    
	    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");

	        if (username.equals("root") && password.equals("pass1234")) {
	            System.out.println("Login Successful! Redirecting to root");
	            session = request.getSession();
	            session.setAttribute("username", username);
	            request.getRequestDispatcher("rootView.jsp").forward(request, response);
	        } else if (username.equals("david") && password.equals("david123")) {
	            System.out.println("Login Successful! Redirecting to David's page");
	            session = request.getSession();
	            session.setAttribute("username", username);
	            request.getRequestDispatcher("davidView.jsp").forward(request, response); 
	        } else if (userDAO.isValid(username, password)) {
	            currentUser = username;
	            System.out.println("Login Successful! Redirecting to client view");
	            request.getRequestDispatcher("clientView.jsp").forward(request, response);
	        } else {
	            request.setAttribute("loginStr", "Login Failed: Please check your credentials.");
	            request.getRequestDispatcher("login.jsp").forward(request, response);
	        }
	    }

	    private void submitQuoteRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        int clientID = Integer.parseInt(request.getParameter("id"));
	        double price = Double.parseDouble(request.getParameter("price"));
	        String scheduleStart = request.getParameter("scheduleStart");
	        String scheduleEnd = request.getParameter("scheduleEnd");

	        QuoteRequest quoteRequest = new QuoteRequest(clientID, price, scheduleStart, scheduleEnd);

	        QuoteRequestDAO quoteRequestDAO = new QuoteRequestDAO();

	            quoteRequestDAO.insert(quoteRequest);
	            response.sendRedirect("quoteRequestConfirmation.jsp");
	    }   
	    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String username = request.getParameter("username");
	    	String firstName = request.getParameter("firstName");
	    	String lastName = request.getParameter("lastName");
	   	 	String password = request.getParameter("password");
	   	 	String role = request.getParameter("role");	   	 	
	   	 	String confirm = request.getParameter("confirmation");
	   	 	String creditCard = request.getParameter("creditCard");
	   	 	String address = request.getParameter("address");
	   	 	String phoneNumber = request.getParameter("phoneNumber");
	   	 	
	   	 	if (password.equals(confirm)) {
	   	 		if (!userDAO.checkUsername(username)) {
		   	 		System.out.println("Registration Successful! Added to database");
					Users users = new Users( username,firstName, lastName, password, role, creditCard, address, phoneNumber);
		   	 		userDAO.insert(users);
		   	 		response.sendRedirect("login.jsp");
	   	 		}
		   	 	else {
		   	 		System.out.println("Username taken, please enter new username");
		    		 request.setAttribute("errorOne","Registration failed: Username taken, please enter a new username.");
		    		 request.getRequestDispatcher("register.jsp").forward(request, response);
		   	 	}
	   	 	}
	   	 	else {
	   	 		System.out.println("Password and Password Confirmation do not match");
	   		 request.setAttribute("errorTwo","Registration failed: Password and Password Confirmation do not match.");
	   		 request.getRequestDispatcher("register.jsp").forward(request, response);
	   	 	}
	    }    
	    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	currentUser = "";
        		response.sendRedirect("login.jsp");
        	}    
	    
}
	  	        
	     
