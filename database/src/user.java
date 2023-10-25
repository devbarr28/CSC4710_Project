public class user 
{
		protected String password;
	 	protected String email;
	    protected String firstName;
	    protected String lastName;
	    protected String adress_street_num;
	    protected String adress_street;
	    protected String adress_city;
	    protected String adress_state;
	    protected String adress_zip_code;
	    protected String role;
	protected String credit_card;
	protected String phone_number;
	    protected int cash_bal;
	    protected int PPS_bal;
	 
	    //constructors
	    public user() {
	    }
	 
	    public user(String email) 
	    {
	        this.email = email;
	    }
	    
	    public user(String email,String firstName, String lastName, String password,String role, String adress_street_num, String adress_street, String adress_city, String adress_state,String adress_zip_code, String credit_card, String phone_number, String int cash_bal, int PPS_bal) 
	    {
	    	this(firstName,lastName,password,role, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code, credit_card, phone_number, cash_bal,PPS_bal);
	    	this.email = email;
	    }
	 
	
	    public user(String firstName, String lastName, String password,String role, String adress_street_num, String adress_street, String adress_city, String adress_state,String adress_zip_code, String credit_card, String phone_number, int cash_bal,  int PPS_bal) 
	    {
	    	this.firstName = firstName;
	    	this.lastName = lastName;
	    	this.password = password;
	        this.role = role;
	        this.adress_street_num = adress_street_num;
	        this.adress_street = adress_street;
	        this.adress_city= adress_city;
	        this.adress_state = adress_state;
	        this.adress_zip_code = adress_zip_code;
		    this.credit_card = credit_card;
		    this.phone_number = phone_number;
	        this.cash_bal = cash_bal;
	        this.PPS_bal = PPS_bal;
	    }
	    
	   //getter and setter methods
	    public String getEmail() {
	        return email;
	    }
	    public void setEmail(String email) {
	        this.email = email;
	    }
	    
	    public String getFirstName() {
	        return firstName;
	    }
	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }
	    
	    public String getLastName() {
	        return lastName;
	    }
	    public void setLastName(String lastName) {
	        this.lastName = lastName;
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
	    
	    public String getAdress_street_num() {
	        return adress_street_num;
	    }
	    public void setAdress_street_num(String adress_street_num) {
	        this.adress_street_num = adress_street_num;
	    }
	    public String getAdress_street() {
	        return adress_street;
	    }
	    public void setAdress_street(String adress_street) {
	        this.adress_street = adress_street;
	    }
	    public String getAdress_city() {
	        return adress_city;
	    }
	    public void setAdress_city(String adress_city) {
	        this.adress_city = adress_city;
	    }
	    public String getAdress_state() {
	        return adress_state;
	    }
	    public void setAdress_state(String adress_state) {
	        this.adress_state = adress_state;
	    }
	    public String getAdress_zip_code() {
	        return adress_zip_code;
	    }
	    public void setAdress_zip_code(String adress_zip_code) {
	        this.adress_zip_code = adress_zip_code;
	    }

	public void String getCredit_card() {
	        return credit_card;
	}

	public void setCredit_card(String credit_card) {
		this.credit_card = credit_card;
	}

	public void String getPhone_number() {
        	return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
	    public int getCash_bal() {
	    	return cash_bal;
	    }
	    public void setCash_bal(int cash_bal) {
	    	this.cash_bal = cash_bal;
	    }
	    
	    public int getPPS_bal() {
	    	return PPS_bal;
	    }
	    public void setPPS_bal(int PPS_bal) {
	    	this.PPS_bal = PPS_bal;
	    }
	}
