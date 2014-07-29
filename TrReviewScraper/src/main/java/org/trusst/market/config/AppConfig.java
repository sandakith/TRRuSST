package org.trusst.market.config;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.trusst.market.ItemType;
import org.trusst.scraper.Scrapable;

@XmlRootElement
public class AppConfig implements Scrapable {
	
	private List<ScrapingPart> itemProperties = new ArrayList<ScrapingPart>();

    @XmlElement(name="property")
	public List<ScrapingPart> getItemProperties() {
		return itemProperties;
	}
	
	public ItemType getItemType() {
		return ItemType.APP;
	}

}
