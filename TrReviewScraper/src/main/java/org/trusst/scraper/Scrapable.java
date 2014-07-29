package org.trusst.scraper;

import java.util.List;

import org.trusst.market.ItemType;
import org.trusst.market.config.ScrapingPart;

public interface Scrapable {
	
	public List<ScrapingPart> getItemProperties();
	
	public ItemType getItemType();
	
}
