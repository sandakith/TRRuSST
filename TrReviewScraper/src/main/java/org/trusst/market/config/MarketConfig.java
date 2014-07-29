package org.trusst.market.config;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.trusst.navigator.Navigatable;

@XmlRootElement
public class MarketConfig implements Navigatable{
	
	private List<NavigatingPart> marketProperties = new ArrayList<NavigatingPart>();

    @XmlElement(name="property")
    public List<NavigatingPart> getItemProperties() {
		return marketProperties;
	}
	
}
