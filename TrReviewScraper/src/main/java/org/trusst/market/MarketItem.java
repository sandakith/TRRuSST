package org.trusst.market;

public class MarketItem {

	ItemType 	itemType = null;
	
	String 		itemId = null;
	String 		itemName = null;
	String 		ItemDeveloper = null;
	String 		ItemDeveloperRating = null;	
	String		itemAvgRating = null;
	String		ratingCount = null;
	String 		itemPrice = null;
	String 		numOfDownloads = null;
	String 		lastUpdate = null;
	String 		starRatings = null;
	String 		ItemUserReviews = null;
	
	String ItemSize = null;
	String CurrentVersion = null;
	String ContentRating = null;

	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public ItemType getItemType() {
		return itemType;
	}
	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDeveloper() {
		return ItemDeveloper;
	}
	public void setItemDeveloper(String itemDeveloper) {
		ItemDeveloper = itemDeveloper;
	}
	public String getItemDeveloperRating() {
		return ItemDeveloperRating;
	}
	public void setItemDeveloperRating(String itemDeveloperRating) {
		ItemDeveloperRating = itemDeveloperRating;
	}
	public String getItemAvgRating() {
		return itemAvgRating;
	}
	public void setItemAvgRating(String itemAvgRating) {
		this.itemAvgRating = itemAvgRating;
	}
	public String getRatingCount() {
		return ratingCount;
	}
	public void setRatingCount(String ratingCount) {
		this.ratingCount = ratingCount;
	}
	public String getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getNumOfDownloads() {
		return numOfDownloads;
	}
	public void setNumOfDownloads(String numOfDownloads) {
		this.numOfDownloads = numOfDownloads;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getStarRatings() {
		return starRatings;
	}
	public void setStarRatings(String starRatings) {
		this.starRatings = starRatings;
	}
	public String getItemUserReviews() {
		return ItemUserReviews;
	}
	public void setItemUserReviews(String itemUserReviews) {
		ItemUserReviews = itemUserReviews;
	}
	public String getItemSize() {
		return ItemSize;
	}
	public void setItemSize(String itemSize) {
		ItemSize = itemSize;
	}
	public String getCurrentVersion() {
		return CurrentVersion;
	}
	public void setCurrentVersion(String currentVersion) {
		CurrentVersion = currentVersion;
	}
	public String getContentRating() {
		return ContentRating;
	}
	public void setContentRating(String contentRating) {
		ContentRating = contentRating;
	}

}
