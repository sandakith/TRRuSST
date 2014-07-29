package org.trusst;

import org.trusst.market.MarketPlace;
import org.trusst.navigator.WebNavigator;
import org.trusst.utils.DDSUtils;

public class Main {
	
	static MarketPlace marketPlace = null;
	static WebNavigator webNavigator = null;
	
	public static void main(String[] args) {
		
		boolean navigationComplete = false;
		
		System.out.println("DDS Start Time : "+  DDSUtils.getCurrentDateTime());
		
		//Initiate Market Place (Load Configuration)
		marketPlace =  new MarketPlace();
		boolean initComplete = marketPlace.init();
		
		if (initComplete) {
			// Navigate and Populate Market 
			webNavigator = new WebNavigator(marketPlace);
			navigationComplete = webNavigator.navigate(	marketPlace.getMarketConfig());
		}else {
			//TODO Log : Could not initiate market 
			System.out.println(navigationComplete);
		}
		
		System.out.println("DDS End Time : "+  DDSUtils.getCurrentDateTime());

	}
	

}
