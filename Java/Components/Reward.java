package Components;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import Main.HttpRequests;

public class Reward implements JsonInterface{
	private String name, itemID, redemptionID, redeemedDate, purchaseDate, type, discountType;
	private int coin, diamond, tier, strength, agility, intellect, stamina, dexterity, health, discount;

	public Reward(JsonObject json){
		redemptionID = json.get("_id").getAsString();
		name = json.get("name").getAsString();
		coin = json.get("coin").getAsInt();
		tier = json.get("tier").getAsInt();
		diamond = json.get("diamond").getAsInt();
		itemID = json.get("_id").getAsString();
		type = json.get("type").getAsString();
		if (type.equals("Item")) {
			strength = json.get("strength").getAsInt();
			agility = json.get("agility").getAsInt();
			dexterity = json.get("dexterity").getAsInt();
			intellect = json.get("intellect").getAsInt();
			health = json.get("health").getAsInt();
			stamina = json.get("stamina").getAsInt();
		} else if (type.equals("Coupon")) {
			discountType = json.get("discountType").getAsString();
			discount = json.get("discount").getAsInt();			
		}
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
		type = getJson.get("type").getAsString();		
		itemID = getJson.get("_id").getAsString();	
		if (type.equals("Item")) {
			strength = getJson.get("strength").getAsInt();
			agility = getJson.get("agility").getAsInt();
			dexterity = getJson.get("dexterity").getAsInt();
			intellect = getJson.get("intellect").getAsInt();
			health = getJson.get("health").getAsInt();
			stamina = getJson.get("stamina").getAsInt();
		} else if (type.equals("Coupon")) {
			discountType = getJson.get("discountType").getAsString();
			discount = getJson.get("discount").getAsInt();			
		}
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
		tempString += "&type=" + type;
		if (type.equals("Item")) {
			tempString += "&strength=" + strength;
			tempString += "&agility=" + agility;
			tempString += "&dexterity=" + dexterity;
			tempString += "&intellect=" + intellect;
			tempString += "&stamina=" + stamina;
			tempString += "&health=" + health;
		} else {
			tempString += "&discountType=" + discountType;
			tempString += "&discount=" + discount;
		}
		return tempString;
	}
	public String getDiscountType() {
		return discountType;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
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

	public int getStrength() {
		return strength;
	}

	public int getAgility() {
		return agility;
	}

	public int getIntellect() {
		return intellect;
	}

	public int getStamina() {
		return stamina;
	}

	public int getDexterity() {
		return dexterity;
	}

	public int getHealth() {
		return health;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public void setIntellect(int intellect) {
		this.intellect = intellect;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
