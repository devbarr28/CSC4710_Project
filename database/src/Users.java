public class Users 
{
		protected String password;
	 	private int id;
	 	protected String firstName;
	 	protected String lastName;
		protected String username;
	  	protected String role;
	  	protected String creditCard;
	  	protected String address;
	  	protected String phoneNumber;
	  	
	 
	    //constructors
	    public Users() {
	    }
	 
	    public Users(String username, String password, String role) {
	        this.username = username;
	        this.password = password;
	        this.role = role;
	    }
	    
	    public Users( String username, String firstName, String lastName, String password, String role, String creditCard, String address, String phoneNumber) 
	    {
	    	this.username = username;
	    	this.firstName = firstName;
	    	this.lastName = lastName;
			this.role = role;
	    	this.password = password;
	    	this.creditCard = creditCard;
	    	this.address = address;
	    	this.phoneNumber = phoneNumber;
	    	
	    }
	    public int getID() {
	    	return id;
	    }
	    public void setID(int id) {
	    	this.id = id;
	    }
	 
	    
	    public String getUsername() {
	        return username;
	    }
	    public void setUsername(String username) {
	        this.username = username;
	    }
	    public String getPassword() {
	        return password;
	    }
	    public void setPassword(String password) {
	        this.password = password;
	    }
	  
	    public String getRole() {
	    	return role;
	    }
	    public void setRole(String role) {
	    	this.role = role;
	    }
	    public String getCreditCard() {
	    	return creditCard;
	    }
	    public void setCreditCard(String creditCard) {
	    	this.creditCard = creditCard;
	    }
	    public String getAddress() {
	    	return address;
	    }
	    public void setAddress(String address) {
	    	this.address = address;
	    }
	    public String getPhoneNumber() {
	    	return phoneNumber;
	    }
	    public void setPhoneNumber(String phoneNumber) {
	    	this.phoneNumber = phoneNumber;
	    }
	    public String getLastName() {
	    	return lastName;
	    }
	    public void setLastName(String lastName) {
	    	this.lastName = lastName;
	    }
	    public String getFirstName() {
	    	return firstName;
	    }
	    public void setFirstName(String firstName) {
	    	this.firstName = firstName;
	    }
	}
