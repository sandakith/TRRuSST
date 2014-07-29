package org.trusst.market.item.builder;

import java.util.Iterator;
import java.util.List;

import org.trusst.market.MarketItem;
import org.trusst.market.item.AppItem;
import org.trusst.market.item.ItemPart;
import org.trusst.utils.DDSConstants;

public class AppItemBuilder implements MarketItemBuilder {

	public MarketItem buildMarketItem(List<ItemPart> listOfItemParts) {

		AppItem appItem = new AppItem();

		for (Iterator<ItemPart> iterator = listOfItemParts.iterator(); iterator
				.hasNext();) {
			ItemPart itemPart = (ItemPart) iterator.next();

			if (DDSConstants.ITEM_ID.equals(itemPart.getName())) {
				appItem.setItemId(itemPart.getValue());
			} else if (DDSConstants.ITEM_NAME.equals(itemPart.getName())) {
				appItem.setItemName(itemPart.getValue());
			} else if (DDSConstants.ITEM_DEV.equals(itemPart.getName())) {
				appItem.setItemDeveloper(itemPart.getValue());
			} else if (DDSConstants.ITEM_DEV_RATING.equals(itemPart.getName())) {
				appItem.setItemDeveloperRating(itemPart.getValue());
			} else if (DDSConstants.ITEM_AVG_RATING.equals(itemPart.getName())) {
				appItem.setItemAvgRating(itemPart.getValue());
			} else if (DDSConstants.ITEM_RATING_COUNT
					.equals(itemPart.getName())) {
				appItem.setRatingCount(itemPart.getValue());
			} else if (DDSConstants.ITEM_LAST_UPDATE.equals(itemPart.getName())) {
				appItem.setLastUpdate(itemPart.getValue());
			} else if (DDSConstants.ITEM_NO_OF_INSTALLS.equals(itemPart
					.getName())) {
				appItem.setNumOfDownloads(itemPart.getValue());
			} else if (DDSConstants.ITEM_PRICE.equals(itemPart.getName())) {
				appItem.setItemPrice(itemPart.getValue());
			} else if (DDSConstants.ITEM_STAR_RATINGS
					.equals(itemPart.getName())) {
				appItem.setStarRatings(itemPart.getValue());
			} else if (DDSConstants.ITEM_SIZE.equals(itemPart.getName())) {
				appItem.setItemSize(itemPart.getValue());
			} else if (DDSConstants.ITEM_CURRENT_VERSION.equals(itemPart
					.getName())) {
				appItem.setCurrentVersion(itemPart.getValue());
			} else if (DDSConstants.ITEM_CONTENT_RATING.equals(itemPart
					.getName())) {
				appItem.setContentRating(itemPart.getValue());
			} else if (DDSConstants.ITEM_USER_REVIEWS
					.equals(itemPart.getName())) {
				appItem.setItemUserReviews(itemPart.getValue());
			}

		}
		return appItem;
	}

}
