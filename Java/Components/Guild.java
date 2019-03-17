package Components;

import com.google.gson.JsonObject;

public class Guild {
	private String _id, name, establishDate; 
	private int rank;
	
	public Guild(JsonObject json){
		_id = json.get("_id").getAsString();
		name = json.get("name").getAsString();
		establishDate = json.get("establishDate").getAsString();
		rank = json.get("rank").getAsInt();
		}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEstablishDate() {
		return establishDate;
	}

	public void setEstablishDate(String establishDate) {
		this.establishDate = establishDate;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
}
