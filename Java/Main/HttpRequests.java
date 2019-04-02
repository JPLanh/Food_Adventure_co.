package Main;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import Components.Avatar;
import Components.Guild;
import Components.Reward;
import Components.User;

public class HttpRequests {

	public static User getUser(int getUserID, String getUserName, String getPassword) {
		URL url;
		try {
			url = new URL("http://35.235.118.188:3000/javaUsersLogin/"+getUserName+"/"+getPassword);
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
			return new Gson().fromJson(content.toString(), User.class);
		} catch (Exception e) {
			return null;
		}
	}

	public static void updateAvatar(int getUserID, String getAvatar) {
		URL url;
		try {
			url = new URL("http://35.235.118.188:3000/defaultAvatar/"+getUserID+"/"+getAvatar);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Java client");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			con.getInputStream();
			
//			StringBuilder content;
//
//			try (BufferedReader in = new BufferedReader(
//					new InputStreamReader(con.getInputStream()))) {			
//				String line;
//				content = new StringBuilder();
//				while ((line = in.readLine()) != null) {
//					content.append(line);
//					content.append(System.lineSeparator());
//				}
//			}
//			return new Gson().fromJson(content.toString(), User.class);
		} catch (Exception e) {
			System.out.println("Errror updating");
		}
	}

	public static void deleteAvatar(String getAvatar) {
		URL url;
		try {
			url = new URL("http://35.235.118.188:3000/deleteAvatar/"+getAvatar);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Java client");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			con.getInputStream();
			
//			StringBuilder content;
//
//			try (BufferedReader in = new BufferedReader(
//					new InputStreamReader(con.getInputStream()))) {			
//				String line;
//				content = new StringBuilder();
//				while ((line = in.readLine()) != null) {
//					content.append(line);
//					content.append(System.lineSeparator());
//				}
//			}
//			return new Gson().fromJson(content.toString(), User.class);
		} catch (Exception e) {
			System.out.println("Errror updating");
		}
	}
	
	public static User searchUser(int getUserID) {
		URL url;
		try {
			url = new URL("http://35.235.118.188:3000/adminSearch/"+getUserID);
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

	public static void terminateUser(User user) {
		URL url;
		try {
			url = new URL("http://35.235.118.188:3000/terminateAccount/");
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
	}	
	
	public static Avatar createAvatar(Avatar getAvatar, User getUser) {
		URL url;
		try {
			url = new URL("http://35.235.118.188:3000/avatar/" + getAvatar.getName() + "/" + getUser.getUserUID());
			System.out.println(url);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", "Java client");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			byte[] postData = getAvatar.getRequest().getBytes(StandardCharsets.UTF_8);
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

	public static ArrayList<Avatar> getAllUserAvatar(User getUser) {
		URL url;
		try {
			url = new URL("http://35.235.118.188:3000/avatar/" + getUser.getUserUID());
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
				}
			}	
			JsonArray getJsonArr = toJsonArr(content.toString());
			try {
				ArrayList<Avatar> allCard = new ArrayList<Avatar>();
				for (JsonElement x : getJsonArr)
				{
					if (getJsonArr != null) {
						Avatar temp = new Avatar(x.getAsJsonObject());
						//Avatar temp = new Avatar((toJsonArr(x.getAsJsonObject().get("avatarID").toString()).get(0)).getAsJsonObject());
						allCard.add(temp);
					}
				}
				return allCard;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} catch (MalformedURLException e) {
			System.out.println("ERROR");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	public static ArrayList<Reward> getAllRewards() {
		URL url;
		try {
			url = new URL("http://35.235.118.188:3000/rewards/");
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
				}
			}	
			JsonArray getJsonArr = toJsonArr(content.toString());
			try {
				ArrayList<Reward> allCard = new ArrayList<Reward>();
				for (JsonElement x : getJsonArr)
				{
					if (getJsonArr != null) {
						Reward temp = new Reward(x.getAsJsonObject());
						allCard.add(temp);
					}
				}
				return allCard;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} catch (MalformedURLException e) {
			System.out.println("ERROR");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Reward searchReward(Reward getReward) {
		URL url;
		try {
			System.out.println(URLEncoder.encode(getReward.getName(), "UTF-8"));
			url = new URL("http://35.235.118.188:3000/adminCreateReward/" + URLEncoder.encode(getReward.getName(), "UTF-8"));
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
			return new Gson().fromJson(content.toString(), Reward.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void purchaseReward(User getUser, String getReward) {
		URL url;
		try {
			url = new URL("http://35.235.118.188:3000/rewards/" + Integer.toString(getUser.getUserUID()) +"/"+ URLEncoder.encode(getReward, "UTF-8"));//getReward.replaceAll(" ", "<>"));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Java client");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			con.getInputStream();
//			byte[] postData = getReward.getRequest().getBytes(StandardCharsets.UTF_8);
//			try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
//				wr.write(postData);
//				StringBuilder content;
//
//				try (BufferedReader in = new BufferedReader(
//						new InputStreamReader(con.getInputStream()))) {			
//					String line;
//					content = new StringBuilder();
//					while ((line = in.readLine()) != null) {
//						content.append(line);
//						content.append(System.lineSeparator());
//					}
//				}
//			} catch (IOException e) {
//				System.out.println("Server is off");
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Reward> getAllMyRewards(User getUser) {
		URL url;
		try {
			url = new URL("http://35.235.118.188:3000/rewards/" + getUser.getUserUID());
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
				}
			}	
			JsonArray getJsonArr = toJsonArr(content.toString());
			System.out.println(content.toString());
			try {
				ArrayList<Reward> allCard = new ArrayList<Reward>();
				for (JsonElement x : getJsonArr)
				{
					if (getJsonArr != null) {
						Reward temp = new Reward(x.getAsJsonObject(), true);
						allCard.add(temp);
					}
				}
				return allCard;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} catch (MalformedURLException e) {
			System.out.println("ERROR");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void finishPurchase(User getUser, String getReward) {
		URL url;
		try {
			url = new URL("http://35.235.118.188:3000/rewards/" + Integer.toString(getUser.getUserUID()) +"/"+ getReward.replaceAll(" ", "<>"));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", "Java client");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			con.getInputStream();
//			byte[] postData = getReward.getRequest().getBytes(StandardCharsets.UTF_8);
//			try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
//				wr.write(postData);
//				StringBuilder content;
//
//				try (BufferedReader in = new BufferedReader(
//						new InputStreamReader(con.getInputStream()))) {			
//					String line;
//					content = new StringBuilder();
//					while ((line = in.readLine()) != null) {
//						content.append(line);
//						content.append(System.lineSeparator());
//					}
//				}
//			} catch (IOException e) {
//				System.out.println("Server is off");
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public static void refundReward(User getUser, String rewardID) {
		URL url;
		try {
			url = new URL("http://35.235.118.188:3000/refunds/" + Integer.toString(getUser.getUserUID()) +"/"+ rewardID);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Java client");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			con.getInputStream();
//			byte[] postData = getReward.getRequest().getBytes(StandardCharsets.UTF_8);
//			try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
//				wr.write(postData);
//				StringBuilder content;
//
//				try (BufferedReader in = new BufferedReader(
//						new InputStreamReader(con.getInputStream()))) {			
//					String line;
//					content = new StringBuilder();
//					while ((line = in.readLine()) != null) {
//						content.append(line);
//						content.append(System.lineSeparator());
//					}
//				}
//			} catch (IOException e) {
//				System.out.println("Server is off");
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Guild createGuild(User getUser, String searchGuild) {
		URL url;
		try {
			url = new URL("http://35.235.118.188:3000/guild/" + Integer.toString(getUser.getUserUID()) +"/" + searchGuild.replaceAll(" ", "<>"));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
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
			return new Gson().fromJson(content.toString(), Guild.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	

	public static Guild JoinGuild(User getUser, String searchGuild) {
		URL url;
		try {
			url = new URL("http://35.235.118.188:3000/guild/" + Integer.toString(getUser.getUserUID()) +"/" + searchGuild.replaceAll(" ", "<>"));
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
			return new Gson().fromJson(content.toString(), Guild.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	

	public static void promoteUser(String getLeader, String getMember, String getGuild) {
		URL url;
		try {
			url = new URL("http://35.235.118.188:3000/promote/" + getLeader +"/" + getMember + "/" + getGuild.replaceAll(" ", "<>"));
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public static ArrayList<User> getGuildMates(String getGuild) {
		URL url;
		try {
			url = new URL("http://35.235.118.188:3000/aGuild/" + getGuild.replaceAll(" ", "<>"));
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
			JsonArray getJsonArr = toJsonArr(content.toString());
			try {
				ArrayList<User> allCard = new ArrayList<User>();
				for (JsonElement x : getJsonArr)
				{
					if (getJsonArr != null) {
						User temp = new User((toJsonArr(x.getAsJsonObject().get("member").toString()).get(0)).getAsJsonObject());
						if (x.getAsJsonObject().get("leader").getAsBoolean()) temp.setLeader(true);
						allCard.add(temp);
					}
				}
				return allCard;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	

	
	public static ArrayList<Guild> getMyGuilds(User getUser) {
		URL url;
		try {
			url = new URL("http://35.235.118.188:3000/myGuild/" + Integer.toString(getUser.getUserUID()));
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
			
			JsonArray getJsonArr = toJsonArr(content.toString());
			try {
				ArrayList<Guild> allCard = new ArrayList<Guild>();
				for (JsonElement x : getJsonArr)
				{
					if (getJsonArr != null) {
						Guild temp = new Guild((toJsonArr(x.getAsJsonObject().get("guild").toString()).get(0)).getAsJsonObject());
						allCard.add(temp);
					}
				}
				return allCard;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	public static ArrayList<Guild> getGuildList() {
		URL url;
		try {
			url = new URL("http://35.235.118.188:3000/guild");
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
			
			JsonArray getJsonArr = toJsonArr(content.toString());
			try {
				ArrayList<Guild> allCard = new ArrayList<Guild>();
				for (JsonElement x : getJsonArr)
				{
					if (getJsonArr != null) {
						Guild temp = new Guild(x.getAsJsonObject());
						allCard.add(temp);
					}
				}
				return allCard;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	public static void finishRefund(User getUser, String rewardID) {
		URL url;
		try {
			url = new URL("http://35.235.118.188:3000/refunds/" + Integer.toString(getUser.getUserUID()) +"/"+ rewardID);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", "Java client");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			con.getInputStream();
//			byte[] postData = getReward.getRequest().getBytes(StandardCharsets.UTF_8);
//			try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
//				wr.write(postData);
//				StringBuilder content;
//
//				try (BufferedReader in = new BufferedReader(
//						new InputStreamReader(con.getInputStream()))) {			
//					String line;
//					content = new StringBuilder();
//					while ((line = in.readLine()) != null) {
//						content.append(line);
//						content.append(System.lineSeparator());
//					}
//				}
//			} catch (IOException e) {
//				System.out.println("Server is off");
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Reward updateReward(Reward getReward) {
		URL url;
		try {
			url = new URL("http://35.235.118.188:3000/adminCreateReward/" + URLEncoder.encode(getReward.getName(), "UTF-8")); //getReward.getName().replaceAll(" ", "<>"));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", "Java client");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			byte[] postData = getReward.getRequest().getBytes(StandardCharsets.UTF_8);
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
	

	public static JsonArray toJsonArr(String getResult) 
	{
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting().serializeNulls();
		Gson gson = builder.create();
		try {
			JsonArray getObj = gson.fromJson(getResult, JsonElement.class).getAsJsonArray();
			return getObj;
		} catch (IllegalStateException e) {
			//			e.printStackTrace();
			return null;
		}
	}
}
