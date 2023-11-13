public class user 
{
		protected String password;
	 	protected String username;
	  	protected String role;
	  	protected String creditCard;
	  	protected String address;
	  	protected String phoneNumber;
	  	
	 
	    //constructors
	    public user() {
	    }
	 
	    public user(String username) 
	    {
	        this.username = username;
	    }
	    public user(String username, String password, String role) {
	        this.username = username;
	        this.password = password;
	        this.role = role;
	    }
	    
	    public user(String username, String password, String role, String creditCard, String address, String phoneNumber) 
	    {
	    	this.username = username;
			this.role = role;
	    	this.password = password;
	    	this.creditCard = creditCard;
	    	this.address = address;
	    	this.phoneNumber = phoneNumber;
	    	
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
	    
	}
