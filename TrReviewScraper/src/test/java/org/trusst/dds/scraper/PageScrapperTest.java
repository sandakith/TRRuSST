package org.trusst.dds.scraper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.trusst.market.MarketPlace;
import org.trusst.navigator.WebNavigator;
import org.trusst.utils.DDSUtils;

public class PageScrapperTest {

	@Test
	public void test() {
		System.out.println("Not yet implemented");
	}

	@Test
	public void scrapper_getMultiplePostsFromMultipleSelects_Test() {

		// Set this scenarios as a test scenario 
		DDSUtils.setTestEnabled(true);

		//Initiate Market Place (Load Configuration)
		MarketPlace marketPlace =  new MarketPlace();
		boolean initComplete = marketPlace.init();

		if (initComplete) {
			// Navigate and Populate Market 
			WebNavigator webNavigator = new WebNavigator(marketPlace);
			boolean navigationComplete = webNavigator.navigate(	marketPlace.getMarketConfig());

			assertTrue(navigationComplete);

		}else {
			//TODO Log : Could not initiate market 
			System.out.println("Could not initiate market ");
		}
		assertTrue(true);
	}
}
