package Components;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import Main.HttpRequests;

public class Reward implements JsonInterface{
	private String name, itemID, redemptionID, redeemedDate, purchaseDate;
	private int coin, diamond, tier;
	
	public Reward(JsonObject json){
		name = json.get("name").getAsString();
		coin = json.get("coin").getAsInt();
		tier = json.get("tier").getAsInt();
		diamond = json.get("diamond").getAsInt();
		itemID = json.get("_id").getAsString();
	}
	
	public Reward(JsonObject json, boolean redemption){
		redemptionID = json.get("_id").getAsString();
		if (!json.get("redeemedDate").isJsonNull()) redeemedDate = json.get("redeemedDate").getAsString();
		purchaseDate = json.get("purchaseDate").getAsString();
		JsonArray rewardJson = HttpRequests.toJsonArr(json.get("reward").toString());
		JsonObject getJson = rewardJson.get(0).getAsJsonObject();
		name = getJson.get("name").getAsString();
		coin = getJson.get("coin").getAsInt();
		tier = getJson.get("tier").getAsInt();
		diamond = getJson.get("diamond").getAsInt();
		itemID = getJson.get("_id").getAsString();			
	}

	Reward(String getName, int getCoin, int getDiamond, int getTier)
	{
		name = getName;
		coin = getCoin;
		diamond = getDiamond;
		tier = getTier;
	}
	
	public Reward(String getName)
	{
		name = getName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public int getDiamond() {
		return diamond;
	}

	public void setDiamond(int diamond) {
		this.diamond = diamond;
	}

	public int getTier() {
		return tier;
	}

	public void setTier(int tier) {
		this.tier = tier;
	}

	public String getItemID() {
		return itemID;
	}

	public void setitemID(String uID) {
		itemID = uID;
	}

	@Override
	public String getRequest() {
		String tempString = "name=" + name.replace(" ", "/s");
		tempString += "&coin=" + coin;
		tempString += "&diamond=" + diamond;
		tempString += "&tier=" + tier;
		return tempString;
	}
	@Override
	public String toString() {
		return "name= " + name + ", coin= " + coin + ", diamond= " + diamond + ", tier=" + tier + ", UID=" + itemID;
	}

	public String getRedemptionID() {
		return redemptionID;
	}

	public void setRedemptionID(String redemptionID) {
		this.redemptionID = redemptionID;
	}

	public String getRedeemedDate() {
		return redeemedDate;
	}

	public void setRedeemedDate(String redeemedDate) {
		this.redeemedDate = redeemedDate;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

}
