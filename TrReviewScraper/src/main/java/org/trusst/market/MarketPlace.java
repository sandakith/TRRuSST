package org.trusst.market;

import org.trusst.market.config.AppConfig;
import org.trusst.market.config.BookConfig;
import org.trusst.market.config.MarketConfig;
import org.trusst.market.config.MovieConfig;
import org.trusst.market.config.MusicConfig;
import org.trusst.scraper.Scrapable;
import org.trusst.utils.DDSConstants;
import org.trusst.utils.DDSUtils;
import org.trusst.utils.XMLSerializer;

public class MarketPlace {

	private MarketConfig marketConfig = null;
	private AppConfig appConfig = null;
	private BookConfig bookConfig = null;
	private MovieConfig movieConfig = null;
	private MusicConfig musicConfig = null;

	public boolean init() {

		marketConfig = (MarketConfig) XMLSerializer.deserializeXMLToObject(
				(!DDSUtils.isTestEnabled()) ? DDSConstants.MARKET_CONFIG_PATH
						: DDSConstants.MARKET_TEST_CONFIG_PATH,
				MarketConfig.class);
		appConfig = (AppConfig) XMLSerializer.deserializeXMLToObject((!DDSUtils
				.isTestEnabled()) ? DDSConstants.APP_CONFIG_PATH
				: DDSConstants.APP_TEST_CONFIG_PATH, AppConfig.class);
		// TODO Initiate others

		return ((marketConfig == null) && (appConfig == null)) ? false : true;
	}

	public MarketConfig getMarketConfig() {
		return marketConfig;
	}

	public Scrapable getScrapableConfigType(String type) {
		Scrapable config = null;
		if (DDSConstants.MARKET_APP.equals(type)) {
			config = appConfig;
		} else if (DDSConstants.MARKET_BOOK.equals(type)) {
			config = bookConfig;
		} else if (DDSConstants.MARKET_MUSIC.equals(type)) {
			config = musicConfig;
		} else if (DDSConstants.MARKET_MOVIE.equals(type)) {
			config = movieConfig;
		}
		return config;
	}

}
