package com.shark.ringtone.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.shark.ringtone.RingtoneApplication;
/**
 * @author manoj.s
 *
 */
public class FirebaseTokenAuthentication {
	private final static Logger log = LoggerFactory.getLogger(FirebaseTokenAuthentication.class);

	public static void initializeFirebase(){
		//Loading firebase auth key
			InputStream serviceAccount = (new RingtoneApplication()).getClass().getClassLoader().getResourceAsStream(Properties.getPropertyValue(Constants.FIREBASE_KEYFILE));
			try {
				if (FirebaseApp.getApps().isEmpty()){
					log.info("Loading firebase auth key");
					FirebaseOptions options;
						options = new FirebaseOptions.Builder()
						  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
						  .build();
					FirebaseApp.initializeApp(options);
				}else{
					log.info("firebase auth key already loaded");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static boolean authenticate(String token, String uid) throws IOException{
		
		boolean result = false;		
		
		if(Properties.getPropertyValue("firebasekey.auth.required").equalsIgnoreCase("false"))
			return true;
		
		try{
			if (FirebaseApp.getApps().isEmpty()){
				initializeFirebase();
			}
			//verify
			FirebaseToken decodedToken = null;
			decodedToken = FirebaseAuth.getInstance().verifyIdTokenAsync(token).get();
			if(decodedToken.getUid().equals(uid)){
				log.info("firebase token verified successfully");
				result = true;
			}
		}catch(ExecutionException e){
			log.info("Firebase ID token has expired or is not yet valid for firebase_uid:"+uid+" Exception:"+e);
		}catch(Exception e){
			log.info("Exception caught during authentication:"+e);
		}
		return result;
	}
}
