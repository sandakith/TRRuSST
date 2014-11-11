package org.trusst.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class ScrapeHelper {

    // TODO : refactor this page
    // Categorize scraping methods as : USED / DEPRECATED
    // Extend this and create a Droid Marketplace Scrape Helper

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

    public static String getAttributeFromMultipleSelect(Element doc, String location, String locationValue) {
        String[] locations = location.split(",");
        String[] locationValues = locationValue.split(",");
        Element element = doc.select(locations[0]+"["+locations[1]+"="+locationValues[0]+"]").first();
        element = element.select(locations[2]+"["+locations[3]+"]").first();
        String result = (element!=null)?element.attr(locations[3]):"none";
        return result.trim();
    }

    public static String getTextFromMultipleSelect(Element doc, String location, String locationValue) {
        String[] locations = location.split(",");
        String[] locationValues = locationValue.split(",");
        Element currentElement = doc ; // Space to store the current element
        int counter = 0;
        for (int i = 0; i < (locations.length) ; i=+2) { // Location will be always a multiple of two
            if (currentElement == null){
                break;
            } else {
                currentElement = currentElement.select(locations[i] + "[" + locations[i + 1] + "=" + locationValues[counter] + "]").first();
                counter += 1;
            }
        }
        String result = (currentElement!=null)?currentElement.text():"none";
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

    public static String getReviewAuthorIDFromMultipleSelect(Element doc, String location, String locationValue) {
        String result = getAttributeFromMultipleSelect(doc,location,locationValue);
        String author_id = (result!=null)?(result.split("=")[1]):"none";
        return author_id.trim();
    }

	// TODO Decouple this with review selects
	public static String getMultiplePostsFromMultipleSelects(String link, Document doc, String location, String locationValue) {
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
        int reviewCount = 0; // keep track of the number of reviews for each app
        Map<String,String> reviewMap = new HashMap<String,String>(); // Check for the duplicates

        try {
		  // Why this exists : int currentPageLimit = 1;
            // TODO : Get an estimate upper bound for this and let this auto terminate
		  for (int k=0;k<reviewPageLimits;k++) {
			  String currentLocation = valueLocation.toString();
			  currentLocation =  currentLocation.substring(0,currentLocation.length()-1)  + k ;
			//Document newDoc = Jsoup.connect(valueLocation.toString()).post();
			String body = Jsoup.connect(currentLocation).method(Method.POST).ignoreContentType(true).execute().body();
			int startIndex = body.indexOf("\\u003c");
            int endIndex = body.indexOf("\",",startIndex);
			//int endIndex = body.indexOf("]\n]",startIndex);

			Elements reviewElems = null;
			
			if (startIndex < 0 || endIndex < 0){
				reviewElems = doc.getElementsByClass("doc-review");
			}else {

				if (k == 0){
					//Current Reveiw Index Calculation // TODO : Refactor the code
//					String reviewIndex = body.substring(endIndex, body.length());
//					int startReviewIndex = reviewIndex.indexOf(":");
//					int endReviewIndex = reviewIndex.indexOf("}");
//					String currentPageLimitsString = reviewIndex.substring(startReviewIndex+1, endReviewIndex);
//					currentPageLimit = Integer.parseInt(currentPageLimitsString);
				}else {
					// TODO Check this
					// if (currentPageLimit < k ){break;}
				}
		        // Recreate the content of the page
				String reviewContent = body.substring(startIndex, endIndex);
				reviewContent = reviewContent.replace("\\u003c\\", "<");
				reviewContent = reviewContent.replace("\\u003c", "<");
                reviewContent = reviewContent.replace("\\u003e\\", ">");
                reviewContent = reviewContent.replace("\\u003e", ">");
				reviewContent = reviewContent.replace("\\\"", "\"");
                reviewContent = reviewContent.replace("\\u003d\\", "=");
                reviewContent = reviewContent.replace("\\u003d", "=");

                String review_id = null; int reviewPageCount = 0;

                Document passedReviews = Jsoup.parse(reviewContent);
				reviewElems = passedReviews.getElementsByClass("single-review");
                reviewCount += reviewElems.size(); // Update the review count

                for (Iterator<Element> iterator = reviewElems.iterator(); iterator.hasNext();) {
				        Element element = (Element) iterator.next();
                        // Get the author id and validate it was not previous page details
                        if (review_id 0= null &&  )
                        reviewBuffer.append(getValueFromNodeAttr(element,"div,class", "review-header,data-reviewid")+" :: ");
                        reviewBuffer.append(getReviewAuthorIDFromMultipleSelect(element, "span,class,a,href", "author-name,href")+" :: ");
						reviewBuffer.append(getTextFromNodeSelect(element,"span,class", "author-name")+" :: ");
						reviewBuffer.append(getTextFromNodeSelect(element,"span,class", "review-date")+" :: ");
						//reviewBuffer.append(getTextFromNodeIfAvailable(element)+" :: "); // Update : Phone details are not available now
                        reviewBuffer.append("none"+" :: "); // TODO : remove this
						reviewBuffer.append(getValueFromNodeAttr(element,"div,class", "tiny-star star-rating-non-editable-container,aria-label")+" :: ");
						reviewBuffer.append(getTextFromNodeSelect(element,"span,class", "review-title")+" :: ");

                        // Format the review body
                        String review_body = getTextFromNodeSelect(element,"div,class", "review-body");
                        int trimStartIndex = review_body.indexOf("Full Review");
                        if ((review_body.length() - trimStartIndex) == 11){
                            review_body = review_body.substring(0, trimStartIndex);
                        }


                    reviewBuffer.append(review_body+" :: ");

                        //TODO : Add the developer reply to the DB also (if exists)
						reviewBuffer.append(" :::: ");
                    // Update the HashMap
                    reviewMap.put(getTextFromNodeSelect(element,"span,class", "author-name"),getTextFromNodeSelect(element,"span,class", "review-title"));
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
        System.out.println("Current Review Count : " + reviewCount);
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
