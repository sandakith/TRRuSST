package org.trusst.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class DDSUtils {
	
	//private static boolean testEnabled = false;
	private static boolean testEnabled = true;

	
	public static boolean isTestEnabled() {
		return testEnabled;
	}

	public static void setTestEnabled(boolean testEnabled) {
		DDSUtils.testEnabled = testEnabled;
	}

	public static void sleepForRandomTime(int multiple){
		Random generator = new Random();
		// pick random sleep time between 0 and 3 seconds
		int sleepTime = generator.nextInt(multiple);
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}

	public static String getCurrentDateTime(){
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter=  new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}
	

}
