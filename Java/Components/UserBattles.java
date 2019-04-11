package Components;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import Main.HttpRequests;

public class UserBattles {
	private Battles room;
	private Avatar participant;
	private boolean result;
	private int damage, group ;
	private String ID;
	
	public UserBattles(JsonObject json) {
		ID = json.get("_id").getAsString();
		damage = json.get("damage").getAsInt();
		group = json.get("Group").getAsInt();
		result = json.get("result").getAsBoolean();
		room = new Gson().fromJson(json.get("room").getAsJsonArray().get(0), Battles.class);
		participant = new Gson().fromJson(json.get("participant").getAsJsonArray().get(0), Avatar.class);
	}

	public Battles getRoom() {
		return room;
	}

	public Avatar getParticipant() {
		return participant;
	}

	public boolean isResult() {
		return result;
	}

	public int getDamage() {
		return damage;
	}

	public int getGroup() {
		return group;
	}

	public String getID() {
		return ID;
	}

	public void setRoom(Battles room) {
		this.room = room;
	}

	public void setParticipant(Avatar participant) {
		this.participant = participant;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public void setID(String iD) {
		ID = iD;
	}

}
