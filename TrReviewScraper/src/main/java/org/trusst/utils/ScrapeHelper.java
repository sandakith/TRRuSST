package org.trusst.utils;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class ScrapeHelper {

	public static String getTitleFromDocument(Document doc) {
		String title = (doc!=null)?doc.title():"none";
        String[] titleText = title.split("-");
		return titleText[0].trim();
	}

	public static String getTextFromDivID(Element doc, String divID){
		Element elementById = doc.getElementsByClass(divID).first();
		String result = (elementById!=null)?elementById.text():"none";
		return result;
	}

	public static String getTextFromAvailableDivID(Document doc, String divID) {
		Elements elementsById = doc.getElementsByClass(divID);
		if (elementsById.size() > 0) {
			Element element = elementsById.first();
			String result = element.text();
			return result;		
		} else {
			return "none";
		}
	}

	public static String getTextFromNodeItemProp(Element doc, String node, String itemprop){
		Element elementByItemprop = doc.select(node+"[itemprop="+itemprop+"]").first();
		String result = (elementByItemprop!=null)?elementByItemprop.text():"none";
		return result;
	}

	public static String getTextFromAClass(Element doc, String location) {
		Element elementByItemprop = doc.select("a[class="+location+"]").first();
		String result = (elementByItemprop!=null)?elementByItemprop.text():"none";
		return result;
	}

	// TODO : Replace the above two by this common method
	public static String getTextFromNodeSelect(Element doc, String location, String locationValue) {
        String[] locations = location.split(",");
        Element element = doc.select(locations[0]+"["+locations[1]+"="+locationValue+"]").first();
		String result = (element!=null)?element.text():"none";
		return result.trim();
	}
	
	public static String getTextFromNodeIfAvailable(Element doc) {
		StringBuffer result = new StringBuffer();
		for (Node child : doc.childNodes()) {
		    if (child instanceof TextNode) {
		        result.append(((TextNode) child).text());
		    }
		}
		return result.toString();
	}
	
	public static String getValueFromNodeAttr(Element doc, String location, String locationValue) {
        String[] locations = location.split(",");
        String[] locationValues = locationValue.split(",");
		Element element = doc.select(locations[0]+"["+locations[1]+"="+locationValues[0]+"]").first();
		String result = (element!=null)?element.attr(locationValues[1]):"none";
		return result.trim();
	}
	
	public static String getMultipleTextFromMultipleSelects(Document doc, String location,	String locationValue) {
		String[] locations = location.split(",");
		String[] locationValues = locationValue.split(",");

		StringBuffer valueList = new StringBuffer();
		Element currentElement = doc;

		for (int i = 0; i < locations.length; i++) {
			if (locations[i].equals("select")){
				currentElement = currentElement.select(locationValues[i]).first();
			}else if(locations[i].equals("multi-select")){
				return getMultipleTextFromSingleSelects(currentElement, locationValues[i], locationValue.split(locationValues[i]+",")[1]);
			}
		}
        if (currentElement!=null) {
            valueList.append(currentElement.text());
        }else {
            return "none";
        }
		return valueList.toString(); 
	}

	public static String getMultipleTextFromSingleSelects(Element doc, String multiSelect, String locationValue) {
		String[] locationValues = locationValue.split(",");
		StringBuffer valueList = new StringBuffer();

		Elements elems = doc.select(multiSelect);
		for (Iterator<Element> iterator = elems.iterator(); iterator.hasNext();) {
			Element currentElement = (Element) iterator.next();
			for (int i = 0; i < locationValues.length; i++) {
				currentElement = currentElement.select(locationValues[i]).first();
			}
			valueList.append((currentElement.text().replaceAll(",", ""))+",");
		}
		return valueList.toString(); 
	}
	
	// TODO Decouple this with review selects
	public static String getMultiplePostsFromMultipleSelects(String link, Document doc, String location,	String locationValue) {
		String[] locations = location.split(",");
		String[] locationValues = locationValue.split(",");

		StringBuffer valueLocation = new StringBuffer();
		StringBuffer reviewBuffer = new StringBuffer();
		int reviewPageLimits = 1;
		

		for (int i = 0; i < locations.length; i++) {
			String[] urlParts = (doc.baseUri()).split("apps/details");
			
			// TODO urlPart[1]
			if (locations[i].equals("select")){
				 //valueLocation.append(urlParts[0]+"getreviews"+urlParts[1]);
				 valueLocation.append(urlParts[0]+"getreviews?id="+getIdFromURL(link));
			}else if(locations[i].equals("multi-param")){
				String[] splitAppends = locationValues[i].split(";");
				for (int j = 0; j < splitAppends.length; j++) {
					valueLocation.append("&"+splitAppends[j]);
				}
			}else if(locations[i].equals("limit")){
				reviewPageLimits = Integer.parseInt(locationValues[i]);
			}
		}
		
		try {
		  int currentPageLimit = 1;
		  for (int k=0;k<reviewPageLimits;k++) {
			  String currentLocation = valueLocation.toString();
			  currentLocation =  currentLocation.substring(0,currentLocation.length()-1)  + k ;
			//Document newDoc = Jsoup.connect(valueLocation.toString()).post();
			String body = Jsoup.connect(currentLocation).method(Method.POST).execute().body();
			int startIndex = body.indexOf("\\u003C");
			int endIndex = body.indexOf("\",\"numPages");
			

			Elements reviewElems = null;
			
			if (startIndex < 0 || endIndex < 0){
				reviewElems = doc.getElementsByClass("doc-review");
			}else {
				
				
				if (k == 0){
					//Current Reveiw Index Calculation 
					String reviewIndex = body.substring(endIndex, body.length());
					int startReviewIndex = reviewIndex.indexOf(":");
					int endReviewIndex = reviewIndex.indexOf("}");
					String currentPageLimitsString = reviewIndex.substring(startReviewIndex+1, endReviewIndex);
					currentPageLimit = Integer.parseInt(currentPageLimitsString);
				}else {
					if (currentPageLimit < k ){break;}
				}
						
				String reviewContent = body.substring(startIndex, endIndex);
				reviewContent = reviewContent.replace("\\u003C\\", "<");
				reviewContent = reviewContent.replace("\\u003C", "<");
				reviewContent = reviewContent.replace("\\\"", "\"");

				Document passedReviews = Jsoup.parse(reviewContent);
				reviewElems = passedReviews.getElementsByClass("doc-review");
			

	  		  for (Iterator<Element> iterator = reviewElems.iterator(); iterator.hasNext();) {
				Element element = (Element) iterator.next();
						reviewBuffer.append(getTextFromNodeSelect(element,"span,class", "doc-review-author")+" :: ");
						reviewBuffer.append(getTextFromNodeSelect(element,"span,class", "doc-review-date")+" :: ");
						reviewBuffer.append(getTextFromNodeIfAvailable(element)+" :: ");
						reviewBuffer.append(getValueFromNodeAttr(element,"div,class", "ratings goog-inline-block,title")+" :: ");
						reviewBuffer.append(getTextFromNodeSelect(element,"h4,class", "review-title")+" :: ");
						reviewBuffer.append(getTextFromNodeSelect(element,"p,class", "review-text")+" :: ");
						reviewBuffer.append(" :::: ");
			  }
			}
		  }
		} catch (StringIndexOutOfBoundsException e) {
			// Continue printing the stack trace 
			e.printStackTrace();
		}catch (IOException e) {
			// Continue printing the stack trace 
			e.printStackTrace();
		}
		
		return reviewBuffer.toString(); 
	}

	public static String getIdFromURL(String link) {
		String[] linkParts = link.split("id=");
		if (DDSUtils.isTestEnabled()){
			return linkParts[1];
		}else {
			String[] resultParts = (linkParts[1].split("&"));
			return resultParts[0];
		}
	}

}
