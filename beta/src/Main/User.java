package Main;

public class User {

	private int UserUID;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String dateOfBirth;

	User(int getUserUID, String getUserName,
			String getPassword, String getFirstName,
			String getLastName, String getEmail, String getPhone,
			String getDateOfBirth){
		UserUID = getUserUID;
		userName = getUserName;
		password = getPassword;
		firstName = getFirstName;
		lastName = getLastName;
		email = getEmail;
		phone = getPhone;
		dateOfBirth = getDateOfBirth;
	}
	
	public int getUserUID() {
		return UserUID;
	}
	public void setUserUID(int userUID) {
		UserUID = userUID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getInputLine() {
		return "UserUID="+UserUID+
				"&userName="+userName+
				"&password="+password+
				"&firstName="+firstName+
				"&lastName="+lastName+
				"&email="+email+
				"&phone="+phone+
				"&dateOfBirth="+dateOfBirth;
	}
}
