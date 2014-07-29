package org.trusst.market.item.builder;

import java.util.List;

import org.trusst.market.MarketItem;
import org.trusst.market.item.ItemPart;

public interface MarketItemBuilder {
	
	MarketItem marketItem = null; 
	
	public MarketItem buildMarketItem(List<ItemPart> listOfItemParts);

}
