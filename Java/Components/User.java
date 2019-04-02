package Components;

import com.google.gson.JsonObject;

public class User implements JsonInterface{
	private int userUID;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String dateOfBirth;
	private int coins, tier, diamonds;
	private boolean leader = false;

	public User(JsonObject json, boolean getLeader){
		userUID = json.get("userUID").getAsInt();
		coins = json.get("coins").getAsInt();
		tier = json.get("tier").getAsInt();
		diamonds = json.get("diamonds").getAsInt();
		userName = json.get("userName").getAsString();
		password = json.get("password").getAsString();
		firstName = json.get("firstName").getAsString();
		lastName = json.get("lastName").getAsString();
		email = json.get("email").getAsString();	
		phone = json.get("phone").getAsString();
		dateOfBirth = json.get("dateOfBirth").getAsString();
		leader = getLeader;
	}
	
	public User(JsonObject json){
		userUID = json.get("userUID").getAsInt();
		coins = json.get("coins").getAsInt();
		tier = json.get("tier").getAsInt();
		diamonds = json.get("diamonds").getAsInt();
		userName = json.get("userName").getAsString();
		password = json.get("password").getAsString();
		firstName = json.get("firstName").getAsString();
		lastName = json.get("lastName").getAsString();
		email = json.get("email").getAsString();	
		phone = json.get("phone").getAsString();
		dateOfBirth = json.get("dateOfBirth").getAsString();
	}

	User(int getUserUID, String getUserName,
			String getPassword, String getFirstName,
			String getLastName, String getEmail, String getPhone,
			String getDateOfBirth){
		userUID = getUserUID;
		userName = getUserName;
		password = getPassword;
		firstName = getFirstName;
		lastName = getLastName;
		email = getEmail;
		phone = getPhone;
		dateOfBirth = getDateOfBirth;
	}
	
	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public int getTier() {
		return tier;
	}

	public void setTier(int tier) {
		this.tier = tier;
	}

	public int getDiamonds() {
		return diamonds;
	}

	public void setDiamonds(int diamonds) {
		this.diamonds = diamonds;
	}

	@Override
	public String getRequest() {
		String inputLine = "userUID=" + userUID;
		inputLine += "&userName=" + userName;
		inputLine += "&password=" + password;
		inputLine += "&firstName=" + firstName;
		inputLine += "&lastName=" + lastName;
		inputLine += "&email=" + email;
		inputLine += "&phone=" + phone;
		inputLine += "&dateOfBirth=" + dateOfBirth;
		inputLine += "&coins=" + coins;
		inputLine += "&diamonds=" + diamonds;
		inputLine += "&tier=" + tier;
		return inputLine;
	}
	
	public int getUserUID() {
		return userUID;
	}
	public void setUserUID(int userUID) {
		this.userUID = userUID;
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

	public boolean isLeader() {
		return leader;
	}

	public void setLeader(boolean leader) {
		this.leader = leader;
	}
	
	@Override
	public String toString() {
		return getRequest();
	}
}
