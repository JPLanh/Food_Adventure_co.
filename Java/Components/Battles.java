package Components;

import com.google.gson.JsonObject;

public class Battles {
	private String ID, date;
	private int room;
	
	public Battles(JsonObject json) {
		ID = json.get("_id").getAsString();
		date = json.get("date").getAsString();
		room = json.get("room").getAsInt();
	}

	public String getID() {
		return ID;
	}

	public String getDate() {
		return date;
	}

	public int getRoom() {
		return room;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setRoom(int room) {
		this.room = room;
	}
}
