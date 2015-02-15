package org.trusst.scraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.trusst.market.MarketItem;
import org.trusst.market.config.ScrapingPart;
import org.trusst.market.item.ItemPart;
import org.trusst.market.item.factory.MarketItemFactory;
import org.trusst.utils.DBUtils;
import org.trusst.utils.DDSConstants;
import org.trusst.utils.DDSUtils;
import org.trusst.utils.ScrapeHelper;

public class WebScrapper {

	MarketItem marketItemInstance = null;

	public boolean scrape(String link, Scrapable config) {
		boolean scrapeSucess = false; // Start of Web Scrape

		try {

			// DDSUtils.sleepForRandomTime(3000);
			// retrieve the document using Jsoup
			Connection conn = Jsoup.connect(link);
			// Set timeout and user agent
			conn.timeout(12000);
			conn.userAgent("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)");

			// Obtain the document
			Document doc = conn.get();

			List<ItemPart> listOfItemParts = new ArrayList<ItemPart>();

			// Loop over scrapable config
			List<ScrapingPart> itemProperties = config.getItemProperties();

			for (Iterator<ScrapingPart> iterator = itemProperties.iterator(); iterator.hasNext();) {
				ScrapingPart itemProperty = (ScrapingPart) iterator.next();

				// Scrape each property
				String scrapedProperty = scrapeProperty(link, doc, itemProperty);
                // TODO : REMOTE THIS SYSOUT
                System.out.println(itemProperty.getName() + " : " + scrapedProperty);
                // Add to item part lsit
				listOfItemParts.add(new ItemPart(itemProperty.getName(),scrapedProperty));
			}

			// Construct market item
			marketItemInstance = MarketItemFactory.getMarketItemInstance(
					config, listOfItemParts);

			// Load item to the database
			scrapeSucess = DBUtils.populateMarketItemToDB(marketItemInstance);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return scrapeSucess;
	}

	public String scrapeProperty(String link, Document doc,
			ScrapingPart itemProperty) {
		String scrapedResult = null;
		if (DDSConstants.SCRAPE_URL.equals(itemProperty.getType())) {
			scrapedResult = ScrapeHelper.getIdFromURL(link);
		}
		if (DDSConstants.SCRAPE_TITLE.equals(itemProperty.getType())) {
			scrapedResult = ScrapeHelper.getTitleFromDocument(doc);
		}
		if (DDSConstants.SCRAPE_DIV_TEXT.equals(itemProperty.getType())) {
			scrapedResult = ScrapeHelper.getValueFromNodeAttr(doc, itemProperty.getLocation(), itemProperty.getLocationValue());

		}
		if (DDSConstants.SCRAPE_DIV_AVAILABLE_TEXT.equals(itemProperty.getType())) {
			scrapedResult = ScrapeHelper.getTextFromAvailableDivID(doc, itemProperty.getLocation());
		}
        if (DDSConstants.SCRAPE_NODE_SELECT_TEXT.equals(itemProperty.getType())) {
            scrapedResult = ScrapeHelper.getTextFromNodeSelect(doc, itemProperty.getLocation(), itemProperty.getLocationValue());
        }
        if (DDSConstants.SCRAPE_NODE_SELECT_ATTR.equals(itemProperty.getType())) {
            scrapedResult = ScrapeHelper.getValueFromNodeAttr(doc, itemProperty.getLocation(), itemProperty.getLocationValue());
        }
		if (DDSConstants.SCRAPE_A_CLASS.equals(itemProperty.getType())) {
			scrapedResult = ScrapeHelper.getTextFromAClass(doc, itemProperty.getLocation());
		}
		if (DDSConstants.SCRAPE_NODE_ITEMPROP.equals(itemProperty.getType())) {
			scrapedResult = ScrapeHelper .getTextFromNodeItemProp(doc, itemProperty.getLocation(), itemProperty.getLocationValue());
		}
		if (DDSConstants.SCRAPE_MULTIPLE.equals(itemProperty.getType())) {
			scrapedResult = ScrapeHelper.getMultipleTextFromMultipleSelects( doc, itemProperty.getLocation(), itemProperty.getLocationValue());
		}
		if (DDSConstants.SCRAPE_POST_MULTIPLE.equals(itemProperty.getType())) {
			scrapedResult = ScrapeHelper.getMultiplePostsFromMultipleSelects( link, doc, itemProperty.getLocation(), itemProperty.getLocationValue());
		}

		return scrapedResult;
	}
}
