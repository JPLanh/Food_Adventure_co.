package Components;

import java.util.HashMap;

import com.google.gson.JsonObject;

import Main.HttpRequests;

public class Avatar implements JsonInterface {
	private String _id, name;
	private int hairStyle, hairColor, eyeColor, furColor, animal;
	private boolean main = false;
	HashMap<String, Integer> attributes = new HashMap<String, Integer>();

	public Avatar(JsonObject json){
		if (json.get("main") != null) main = json.get("main").getAsBoolean();
		if (json.getAsJsonObject().get("avatarID") != null) {
			JsonObject getJson = HttpRequests.toJsonArr(json.getAsJsonObject().get("avatarID").toString()).get(0).getAsJsonObject();
			_id = getJson.get("_id").getAsString();
			name = getJson.get("name").getAsString();
			hairStyle = getJson.get("hairStyle").getAsInt();
			hairColor = getJson.get("hairColor").getAsInt();
			eyeColor = getJson.get("eyeColor").getAsInt();
			furColor = getJson.get("furColor").getAsInt();
			animal = getJson.get("animal").getAsInt();
			attributes.put("Strength", getJson.get("strength").getAsInt());
			attributes.put("Agility", getJson.get("agility").getAsInt());
			attributes.put("Intellect", getJson.get("intellect").getAsInt());
			attributes.put("Dexterity", getJson.get("dexterity").getAsInt());
			attributes.put("Stamina", getJson.get("stamina").getAsInt());
			attributes.put("Health", getJson.get("health").getAsInt());
			attributes.put("Level", getJson.get("level").getAsInt());
		} else {
			_id = json.get("_id").getAsString();
			name = json.get("name").getAsString();
			hairStyle = json.get("hairStyle").getAsInt();
			hairColor = json.get("hairColor").getAsInt();
			eyeColor = json.get("eyeColor").getAsInt();
			furColor = json.get("furColor").getAsInt();
			animal = json.get("animal").getAsInt();
			attributes.put("Strength", json.get("strength").getAsInt());
			attributes.put("Agility", json.get("agility").getAsInt());
			attributes.put("Intellect", json.get("intellect").getAsInt());
			attributes.put("Dexterity", json.get("dexterity").getAsInt());
			attributes.put("Stamina", json.get("stamina").getAsInt());
			attributes.put("Health", json.get("health").getAsInt());
			attributes.put("Level", json.get("level").getAsInt());
			
		}
	}


	public Avatar() {
		hairStyle = 0;
		hairColor = 0;
		eyeColor = 0;
		furColor = 0;
		animal = 0;
	}
	public String get_id() {
		return _id;
	}

	public boolean isMain() {
		return main;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public void setMain(boolean main) {
		this.main = main;
	}
	public String getName() {
		return name;
	}
	public int getHairStyle() {
		return hairStyle;
	}
	public int getHairColor() {
		return hairColor;
	}
	public int getEyeColor() {
		return eyeColor;
	}
	public int getFurColor() {
		return furColor;
	}
	public int getAnimal() {
		return animal;
	}
	public HashMap<String, Integer> getAttributes() {
		return attributes;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setHairStyle(int hairStyle) {
		this.hairStyle = hairStyle;
	}
	public void setHairColor(int hairColor) {
		this.hairColor = hairColor;
	}
	public void setEyeColor(int eyeColor) {
		this.eyeColor = eyeColor;
	}
	public void setFurColor(int furColor) {
		this.furColor = furColor;
	}
	public void setAnimal(int animal) {
		this.animal = animal;
	}
	public void setAttributes(HashMap<String, Integer> attributes) {
		this.attributes = attributes;
	}
	@Override
	public String getRequest() {
		String inputLine = "name=" + name;
		inputLine += "&hairStyle=" + hairStyle;
		inputLine += "&hairColor=" + hairColor;
		inputLine += "&eyeColor=" + eyeColor;
		inputLine += "&furColor=" + furColor;
		inputLine += "&animal=" + animal;
		return inputLine;
	}
}
