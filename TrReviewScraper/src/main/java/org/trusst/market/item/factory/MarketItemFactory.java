package org.trusst.market.item.factory;

import java.util.List;

import org.trusst.market.ItemType;
import org.trusst.market.MarketItem;
import org.trusst.market.item.ItemPart;
import org.trusst.market.item.builder.AppItemBuilder;
import org.trusst.market.item.builder.MarketItemBuilder;
import org.trusst.scraper.Scrapable;

public class MarketItemFactory {
	
	static MarketItemBuilder itemBuilder = null; 
	
	public static MarketItem getMarketItemInstance(Scrapable item, List<ItemPart> listOfItemParts) {
			
		
		ItemType imageType = item.getItemType();
		
		switch (imageType) {
		case APP:	itemBuilder = new AppItemBuilder(); break;
		case BOOK:  break; //TODO 
		case MOVIE: break;
		case MUSIC: break;
		}

		return itemBuilder.buildMarketItem(listOfItemParts);

	}
}
