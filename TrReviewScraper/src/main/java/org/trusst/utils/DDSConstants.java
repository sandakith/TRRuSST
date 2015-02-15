package org.trusst.utils;


public class DDSConstants {
	
	//Market 
	public static final String MARKET = "https://play.google.com";

	// Market Constants 
	public static final String MARKET_APP = "App";
	public static final String MARKET_BOOK = "Book";
	public static final String MARKET_MUSIC = "Music";
	public static final String MARKET_MOVIE = "Movie";
	
	// Navigating Constants 
	public static final String  MARKET_CONFIG_PATH = "./config/work/MarketConfig.xml";
	public static final String  APP_CONFIG_PATH = "./config/work/AppConfig.xml";
	public static final String  MARKET_TEST_CONFIG_PATH = "./config/test/MarketConfig.xml";
	public static final String  APP_TEST_CONFIG_PATH = "./config/test/AppConfig.xml";
	
	// Scraping Constants
	public static final String SCRAPE_URL = "doc-url";
	public static final String SCRAPE_TITLE = "doc-tytle"; 
	public static final String SCRAPE_DIV_TEXT = "div-text";
	public static final String SCRAPE_DIV_AVAILABLE_TEXT = "div-available-text";
	public static final String SCRAPE_A_CLASS = "a-class"; 
	public static final String SCRAPE_NODE_ITEMPROP = "node-itemprop";
    // General Scraping Constants
    public static final String SCRAPE_NODE_SELECT_TEXT = "node-select-text";
    public static final String SCRAPE_NODE_SELECT_ATTR = "node-select-attr";
	public static final String SCRAPE_MULTIPLE = "multiple";
	public static final String SCRAPE_POST_MULTIPLE = "multiple-posts";


	// Item Constants 
	public static final String ITEM_ID = "ItemId"; 
	public static final String ITEM_NAME = "ItemName"; 
	public static final String ITEM_AVG_RATING = "ItemAvgRating"; 
	public static final String ITEM_DEV = "ItemDeveloper"; 
	public static final String ITEM_DEV_RATING = "ItemDeveloperRating"; 
	public static final String ITEM_RATING_COUNT = "RatingCount"; 
	public static final String ITEM_LAST_UPDATE = "LastUpdate"; 
	public static final String ITEM_NO_OF_INSTALLS = "NumOfInstalls"; 
	public static final String ITEM_PRICE = "ItemPrice"; 
	public static final String ITEM_FIVE_STAR_RATING = "ItemFiveStarRating";
    public static final String ITEM_FOUR_STAR_RATING = "ItemFourStarRating";
    public static final String ITEM_THREE_STAR_RATING = "ItemThreeStarRating";
    public static final String ITEM_TWO_STAR_RATING = "ItemTwoStarRating";
    public static final String ITEM_ONE_STAR_RATING = "ItemOneStarRating";
    public static final String ITEM_USER_REVIEWS = "ItemUserReviews";
	
	// App Constants
	public static final String ITEM_SIZE = "ItemSize";
	public static final String ITEM_CURRENT_VERSION = "CurrentVersion"; 
	public static final String ITEM_CONTENT_RATING = "ContentRating"; 
	
	// Navigation Operators 
	public static final String NAV_START_OP_EQ = "start=";

    //Test Scenario Constants

    public static final String TEST_APP_LOCATION = "https://play.google.com/store/apps/details?id=com.livares.keralahangouts";   // (Has Only 1 review)
    // public static final String TEST_APP_LOCATION = "https://play.google.com/store/apps/details?id=com.lri.keralaattractions";    // Has 15 reviews including anonymous
    // public static final String TEST_APP_LOCATION = "https://play.google.com/store/apps/details?id=com.zagmoid.blocks3d";         // (Has Only 2 pages of reviews)
    // public static final String TEST_APP_LOCATION = "https://play.google.com/store/apps/details?id=com.zagmoid.carrom3d";         // Has two full pages
    // public static final String TEST_APP_LOCATION = "https://play.google.com/store/apps/details?id=com.livares.kiddiedoodle.mal";
    // public static final String TEST_APP_LOCATION = "https://play.google.com/store/apps/details?id=com.yahoo.mobile.client.android.weather";
    // public static final String TEST_APP_LOCATION = "https://play.google.com/store/apps/details?id=com.accuweather.paid.android";
    // public static final String TEST_APP_LOCATION = "https://play.google.com/store/apps/details?id=com.shake.charge"; (They Removed)

    // Weather forecast apps
    //public static final String TEST_APP_LOCATION = "https://play.google.com/store/apps/details?id=com.gau.go.launcherex.gowidget.weatherwidget";
    //public static final String TEST_APP_LOCATION = "https://play.google.com/store/apps/details?id=com.weather.Weather";






}