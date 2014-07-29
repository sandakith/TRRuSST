package org.trusst.navigator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.trusst.market.MarketPlace;
import org.trusst.market.config.NavigatingPart;
import org.trusst.scraper.Scrapable;
import org.trusst.scraper.WebScrapper;
import org.trusst.utils.DDSConstants;
import org.trusst.utils.DDSUtils;
import org.trusst.utils.URLNavigator;

public class WebNavigator {

	MarketPlace market = null; 
	WebScrapper webScrapper = null;

	public WebNavigator(MarketPlace marketPlace) {
		market = marketPlace;
	}

	public boolean navigate(Navigatable marketConfig){

		boolean navigateSucess = false; // Start of Web navigation  marketConfig

		//clear the database
		//DBUtils.llReceordsFromTable(conn, "APP.MarketItem");

		//Loop over navigatable config
		List<NavigatingPart> itemProperties = marketConfig.getItemProperties();

		// according to navigation configuration 
		// Navigate and scrape 
		for (Iterator<NavigatingPart> itr = itemProperties.iterator(); itr.hasNext();) {
			NavigatingPart itemProperty = (NavigatingPart) itr.next();

			// Load config according to type 
			Scrapable scrapableConfig = market.getScrapableConfigType(itemProperty.getType());

			// Get list of Locations 
			List<String> scrapableLocations = null;
			if (DDSUtils.isTestEnabled()) {
				scrapableLocations = new ArrayList<String>();
				scrapableLocations.add(DDSConstants.TEST_APP_LOCATION);
			}else {
				scrapableLocations = getScrapableLocations(itemProperty);
				System.out.println("Number of Results to Scrape:"+scrapableLocations.size());
			}

			//Scrape each location  
			for (Iterator<String> itr2 = scrapableLocations.iterator(); itr2.hasNext();) {
				String urlToScrape = (String) itr2.next();

				// call web scraper to populate app market 
				webScrapper = new WebScrapper();
				System.out.print("START:"+  DDSUtils.getCurrentDateTime() +":"+ urlToScrape  );
				navigateSucess = webScrapper.scrape(urlToScrape, scrapableConfig);
				System.out.print(" | Scraping successful | ");
				System.out.print("END:"+  DDSUtils.getCurrentDateTime() +"\n");


			}

		}

		return navigateSucess;
	}

	private List<String> getScrapableLocations(NavigatingPart itemProperty){

		// Break the initial URL values from Market Config
		String[] urlParts = (itemProperty.getUrlValue()).split("/");

		// List to store scraping result list
		List<String> navigationResult = new ArrayList<String>(); 
		
		// List to store the temporary navigation URL list
		ArrayList<String> tempURLList = new ArrayList<String>();

		// For each URL part, add and generate scrapable URLs
		for (int i = 0; i < urlParts.length; i++) {

			StringBuffer currentURL = new StringBuffer();
			if (i == 0){
				currentURL.append(itemProperty.getUrl());
				tempURLList.add(currentURL.toString());
			}
			navigationResult.clear();
			navigationResult.addAll(tempURLList);
			tempURLList.clear();
			
			// Iterate over previous results and construct complete set of links 
			for (Iterator<String> iterator = navigationResult.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				if (i != 0){
					currentURL.delete(0, currentURL.length());
					currentURL.append(string);
				}else {
					
				}
				
				// if link part not contains multiple parts, then directly add them 
				if(!(urlParts[i].contains(","))){
					currentURL.append(urlParts[i]+"/");
					tempURLList.add(currentURL.toString());
					currentURL.delete(0, currentURL.length());
				}else{
					// if link part contains multiple parts, then add all of them  
					String[] tempURLParts = (urlParts[i]).split(",");
					for (int j = 0; j < tempURLParts.length; j++) {
						String tempURL = currentURL.toString();
						currentURL.append(tempURLParts[j]+"/");
						tempURLList.add(currentURL.toString());
						currentURL.delete(0, currentURL.length());
						currentURL.append(tempURL);
					}

				}

			}
		}
		
		// Clear the results list to add item links
		navigationResult.clear();

		// Iterate over possible urls which contains scrapable links 
		for (Iterator<String> iterator = tempURLList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			int iterationCounter = 0;

			String navigationString = string; 
			URLNavigator.setItemsAvailable();
			
			// While scrapable links are available
			while(URLNavigator.isItemsAvailable(itemProperty)){

				//List to store the current results 
				List<String> currentResult = null;
				
				// Update the URL with next chink
				if (iterationCounter == 0 ){
					String tempNavigation =  itemProperty.getNavigation();
					if (tempNavigation.contains("*")){
						tempNavigation = tempNavigation.replace('*', '&');
					}
					navigationString += tempNavigation;
				}
				String[] navigationParts = navigationString.split(DDSConstants.NAV_START_OP_EQ);
				itemProperty.setUrl( navigationParts[0]+ DDSConstants.NAV_START_OP_EQ
						+(iterationCounter * Integer.valueOf(itemProperty.getNavigationValue()))
						);
				
				// Sleep for random time
				DDSUtils.sleepForRandomTime(50);
				
				// Navigate current set of links form URL 
				currentResult = URLNavigator.getNavigationLinksListFromURL(itemProperty);

				// Add current result list to navigate list
				if (currentResult != null){
					navigationResult.addAll(currentResult);
				}
				iterationCounter++;
			}
		}
		
		return navigationResult;
	}

}

//--------------------------------------------------
// Retrieved one applink 
//String appURL = "https://play.google.com/store/apps/details?id=com.rovio.angrybirds" ;


