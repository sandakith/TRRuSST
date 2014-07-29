package org.trusst.market.config;

import java.util.List;

import org.trusst.market.ItemType;
import org.trusst.scraper.Scrapable;


public class BookConfig implements Scrapable{

	public ItemType getItemType() {
		return ItemType.BOOK;
	}

	public List<ScrapingPart> getItemProperties() {
		// TODO Define Configuration 
		return null;
	}
	
}
