package org.trusst.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.trusst.market.config.NavigatingPart;

public class URLNavigator {

	static boolean itemsAvailable = true; 
	
	public static List<String> getNavigationLinksListFromURL(NavigatingPart itemProperty) {

		Document doc = null;
		try {
			// Connect to URL 
			doc = Jsoup.connect(itemProperty.getUrl()).get();
			
		} catch (IOException e) {
			itemsAvailable = false;
			return null;
		}

		// Located links element from location 
		Elements resultLinks = doc.select(itemProperty.getLocation()); 

		// Loaded list of links 
		return loadElementsToList(resultLinks);

	}

	public static List<String> loadElementsToList(Elements elems) {
		ArrayList<String> resultList = new ArrayList<String>();
		for (Iterator<Element> iterator = elems.iterator(); iterator.hasNext();) {
			Element element = (Element) iterator.next();
			resultList.add(DDSConstants.MARKET+element.attr("href"));
		} 
		return resultList;
	}

	public static boolean isItemsAvailable(NavigatingPart itemProperty) {
		return itemsAvailable;
	}
	
	public static void setItemsAvailable() {
		 itemsAvailable = true;
	}


}
