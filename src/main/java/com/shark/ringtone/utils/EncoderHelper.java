package com.shark.ringtone.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

/**
 * Created by arvind.singh on 4/13/2017.
 */

public class EncoderHelper {

	
	public static String getTimeInMinutes(){
		
		Date currentTime = new Date();

		final SimpleDateFormat sdf =
		        new SimpleDateFormat("EEE, MMM d, yyyy hh:mm:ss a z");

		// Give it to me in GMT time.
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		sdf.format(currentTime);
		
		Calendar time = sdf.getCalendar();
		int dayOfYear = time.get(Calendar.DAY_OF_YEAR) - 1;
		int hourOfDay = time.get(Calendar.HOUR_OF_DAY);
		int minutes = time.get(Calendar.MINUTE);
		
		int inminutes = dayOfYear*24*60 + hourOfDay*60 + minutes;
		
		return String.valueOf(inminutes*60);
	}
	
	public static String getRandomNumber() {
		Random random = new Random();
		int randomNumber = 100000 + random.nextInt(900000);
		return randomNumber + "";
	}

//	public static String getModelNumber() {
//		String model = Build.MODEL;
//		if (model == null) {
//			return "0";
//		} else {
//			return model;
//		}
//
//	}

//	public static String getAppVersionCode(Context context) {
//		String appVersionCode = "0";
//		try {
//			PackageInfo packageInfo = context.getPackageManager()
//					.getPackageInfo(context.getPackageName(), 0);
//			appVersionCode = packageInfo.versionCode + "";
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return appVersionCode;
//	}

//	public static String getUniqueIdentifier() {
//		User user = DiscoverApplication.getDatabaseFactory().getUser();
//		if (user != null && user.getUserUId() != null
//				&& !user.getUserUId().isEmpty()) {
//			return user.getUserUId();
//		} else {
//			return "0";
//		}
//	}

	public static String getStartTimeStampInMilliSecond() {
		return "0";
	}

	public static InputStream getInputStream() {
		

		File file = new File("resource/codetable.txt");
		FileInputStream fis = null;
		try {
			fis =new FileInputStream(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new BufferedInputStream(fis);

	}
	
	public static InputStream getStream() {
		

//		ClassLoader classLoader = EncoderHelper.class.getClassLoader();
//		File file = new File(classLoader.getResource("codetable.txt").getFile());
//		FileInputStream fis = null;
//		try {
//			fis =new FileInputStream(file);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return new BufferedInputStream(fis);
		
		 InputStream inputStream = null;
		 
		 inputStream = EncoderHelper.class.getClassLoader().getResourceAsStream("codetable.txt");
		 return new BufferedInputStream(inputStream );

	}
	
	
	public static List<String> getEncoderParameter(String clipId) {
		   List<String> parameter = new ArrayList<>();

	       parameter.add(EncoderHelper.getTimeInMinutes());
	       parameter.add(clipId);
	       parameter.add("0");
	       parameter.add("Browser");
	       parameter.add(EncoderHelper.getRandomNumber());
	       parameter.add("NA");
	       parameter.add("01");

	       return parameter;	
	}
	

}
