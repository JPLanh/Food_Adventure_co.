package Main;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import Components.User;

public class HttpRequests {

	public static User getUser(int getUserID, String getUserName, String getPassword) {
		URL url;
		try {
			url = new URL("http://35.235.118.188:3000/javaUsersLogin/"+getUserName+"/"+getPassword);
			System.out.println(getUserName);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Java client");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			StringBuilder content;

			try (BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()))) {			
				String line;
				content = new StringBuilder();
				while ((line = in.readLine()) != null) {
					content.append(line);
					content.append(System.lineSeparator());
				}
			}
			System.out.println(content.toString());
			return new Gson().fromJson(content.toString(), User.class);
			
//			String inputLine = "username=" + getUserName;
//			inputLine += "password=" + getPassword;
//			String inputLine = " { userName: " + getUserName+", password: " + getPassword+" }";
//			byte[] postData = inputLine.getBytes(StandardCharsets.UTF_8);
//			try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
//				wr.write(postData);
//
//			}
//			StringBuilder content;
//			try (BufferedReader in = new BufferedReader(
//					new InputStreamReader(con.getInputStream()))) {			
//				String line;
//				content = new StringBuilder();
//				while ((line = in.readLine()) != null) {
//					content.append(line);
//				}
//			}	
//			System.out.println(content.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static User registerUser(int getUserID, String getUserName, String getPassword) {
		URL url;
		try {
			url = new URL("http://35.235.118.188:3000/javaUsers/"+getUserID);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", "Java client");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			String inputLine = "userName=" + getUserName;
			inputLine += "&password=" + getPassword;

			byte[] postData = inputLine.getBytes(StandardCharsets.UTF_8);
			try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
				wr.write(postData);
				StringBuilder content;

				try (BufferedReader in = new BufferedReader(
						new InputStreamReader(con.getInputStream()))) {			
					String line;
					content = new StringBuilder();
					while ((line = in.readLine()) != null) {
						content.append(line);
						content.append(System.lineSeparator());
					}
				}
			} catch (IOException e) {
				System.out.println("Server is off");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	

	public static User updateUser(User user) {
		URL url;
		try {
			url = new URL("http://35.235.118.188:3000/JavaUsersModify/"+user.getUserUID());
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", "Java client");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			byte[] postData = user.getRequest().getBytes(StandardCharsets.UTF_8);
			try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
				wr.write(postData);
				StringBuilder content;

				try (BufferedReader in = new BufferedReader(
						new InputStreamReader(con.getInputStream()))) {			
					String line;
					content = new StringBuilder();
					while ((line = in.readLine()) != null) {
						content.append(line);
						content.append(System.lineSeparator());
					}
				}
			} catch (IOException e) {
				System.out.println("Server is off");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static User createUser(String getFirstName, String getLastName, String getEmail, String getPhone, String getDateOfBirth) {
		URL url;
		try {
			url = new URL("http://35.235.118.188:3000/adminUsers");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", "Java client");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			String inputLine = "firstName=" + getFirstName;
			inputLine += "&lastName=" + getLastName;
			inputLine += "&email=" + getEmail;
			inputLine += "&phone=" + getPhone;
			inputLine += "&dateOfBirth=" + getDateOfBirth;
			inputLine += "&userUID=" + new Random().nextInt(9999);
			
			byte[] postData = inputLine.getBytes(StandardCharsets.UTF_8);
			try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
				wr.write(postData);
				StringBuilder content;

				try (BufferedReader in = new BufferedReader(
						new InputStreamReader(con.getInputStream()))) {			
					String line;
					content = new StringBuilder();
					while ((line = in.readLine()) != null) {
						content.append(line);
						content.append(System.lineSeparator());
					}
				}
			} catch (IOException e) {
				System.out.println("Server is off");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static JsonElement userToJson(User getUser)
	{
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting().serializeNulls();
		Gson gson = builder.create();
		return gson.toJsonTree(getUser, new TypeToken<User>() {}.getType());
	}
}
