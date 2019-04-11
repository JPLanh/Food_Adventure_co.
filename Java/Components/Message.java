package Components;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import Main.HttpRequests;

public class Message {
	String to, from, subject, bodyMsg, date, id;
	boolean read, replied;


	public Message(JsonObject json){
		subject = json.get("subject").getAsString();
		bodyMsg = json.get("bodyMsg").getAsString();
		date = json.get("date").getAsString();
		read = json.get("read").getAsBoolean();
		replied = json.get("read").getAsBoolean();
		to = json.get("to").getAsJsonArray().get(0).getAsJsonObject().get("userName").getAsString();
		from = json.get("from").getAsJsonArray().get(0).getAsJsonObject().get("userName").getAsString();
		id = json.get("_id").getAsString();
	}
	
	public String getTo() {
		return to;
	}

	public String getFrom() {
		return from;
	}

	public String getSubject() {
		return subject;
	}

	public String getBodyMsg() {
		return bodyMsg;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setBodyMsg(String bodyMsg) {
		this.bodyMsg = bodyMsg;
	}

	public String getDate() {
		return date;
	}

	public boolean isRead() {
		return read;
	}

	public boolean isReplied() {
		return replied;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public void setReplied(boolean replied) {
		this.replied = replied;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
