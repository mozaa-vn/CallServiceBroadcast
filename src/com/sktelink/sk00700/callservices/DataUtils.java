package com.sktelink.sk00700.callservices;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class DataUtils {

	private static final String SHARED_NAME			= "myShared";
	private static final String PRE_PATTERNS		= "preListPattern";
	private static final String PRE_PATTERN_TARGET	= "preTargetPattern";
	private static final String PRE_URL_PATTERNS 	= "preUrlPatterns";
	private static final String PRE_URL_TIME		= "preUrlTime";
	private static final String PRE_HOUR_UPDATE		= "preHourUpdate";
	private static final String PRE_TIME_CALLBACK	= "preTimeCallBack";
	private static final String PRE_ENABLE_SERVER	= "preEnableServer";
	private static final String PRE_IS_IN_TEST		= "preIsInTest";
	private static final String PRE_IS_FIRST_RUN	= "preIsFirstRun";
	private static final String PRE_IS_FIRST_UPDATE	= "preIsFirstUpdate";
	
	private static final String
							PRE_LAST_TIME_UPDATE = "preWriteContctCompletedDay",
							PRE_READ_CALLLOG_COMPLETED		= "preReadCallLogCompleted",
							PRE_WRITE_CALLLOG_COMPLETED		= "preWriteCallLogCompleted",
							PRE_READ_CALLLOG_COMPLETED_DAY	= "preReadCallLogCompletedDay",
							PRE_WRITE_CALLLOG_COMPLETED_DAY = "preWriteCallLogCompletedDay",
							
							PRE_READ_CONTACT_COMPLETED		= "preReadContactCompleted",
							PRE_WRITE_CONTACT_COMPLETED		= "preWriteContctCompleted",
							PRE_READ_CONTACT_COMPLETED_DAY	= "preReadContactCompletedDay",
							PRE_WRITE_CONTACT_COMPLETED_DAY = "preWriteContctCompletedDay";
							
	
	private static Context context;
	private static SharedPreferences prefs;

	/*
	 * TODO: constructor
	 */
	public DataUtils(Context mContext) {
		context = mContext;
		prefs = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
	}


	/*
	 * TODO: get set List Patterns
	 */
	public void setListPattern(String[] arrPattern) {
		try {
			String sPattern = "";
			
			for (int i=0; i< arrPattern.length; i++) {
				if (i == arrPattern.length-1) {
					sPattern += arrPattern[i];
				} else {
					sPattern += arrPattern[i] + ";";
				}
			}
			
			prefs.edit().putString(PRE_PATTERNS, sPattern).commit();
		} catch (Exception ex) {
			Log.d(">>> trams <<<", Log.getStackTraceString(ex));
		}
	}
	
	public List<String> getListPattern(){
		List<String> listPatterns = new ArrayList<String>();
		try {
			String mPatterns = prefs.getString(PRE_PATTERNS, "");
			String sToken = "";
	
			if(mPatterns.equals("")){
				listPatterns.add("001");
				listPatterns.add("002");
				return listPatterns;
			}
			
			StringTokenizer stkPattern = new StringTokenizer(mPatterns.trim(), ";");
			
			while (stkPattern.hasMoreTokens()) {
				sToken = stkPattern.nextToken().toString();
				listPatterns.add(sToken.toString());
				Log.d(">>> trams <<<", sToken);
			}
		} catch (Exception ex) {
			Log.d(">>> trams <<<", Log.getStackTraceString(ex));
		}
		return listPatterns;
	}


	/*
	 * TODO: get set Target Pattern
	 */
	public void setTargetPattern (String targetPattern) {
		prefs.edit().putString(PRE_PATTERN_TARGET, targetPattern).commit();
	}
	
	public String getTargetPattern () {
		return prefs.getString(PRE_PATTERN_TARGET, "00700");
	}


	/*
	 * TODO: get set URL
	 */
	public void setUrlPatterns (String urlPatterns) {
		prefs.edit().putString(PRE_URL_PATTERNS, urlPatterns).commit();
	}
	
	public String getUrlPatterns () {
		return prefs.getString(PRE_URL_PATTERNS, "");
	}

	public void setUrlTime (String urlPatterns) {
		prefs.edit().putString(PRE_URL_TIME, urlPatterns).commit();
	}
	
	public String getUrlTime () {
		return prefs.getString(PRE_URL_TIME, "");
	}

	
	/*
	 * TODO: get set The Hour Update
	 */
	public void setTheHourUpdate (int theHour) {
		prefs.edit().putInt(PRE_HOUR_UPDATE, theHour).commit();
	}

	public int getTheHourUpdate () {
		return prefs.getInt(PRE_HOUR_UPDATE, 9);
	}


	/*
	 * TODO: get set Time Call Back
	 */
	public void setTimeCallBack (int totalTime) {
		prefs.edit().putInt(PRE_TIME_CALLBACK, totalTime).commit();
	}
	
	public int getTimeCallBack () {
		return prefs.getInt(PRE_TIME_CALLBACK, 60000);
	}
	

	/*
	 * TODO: get set connect server
	 */
	public void setEnableServer (boolean state) {
		prefs.edit().putBoolean(PRE_ENABLE_SERVER, state).commit();
	}

	public boolean isEnableServer () {
		return prefs.getBoolean(PRE_ENABLE_SERVER, false);
	}


	/*
	 * TODO: get set is In test
	 */
	public void setInTest (boolean state, int timeCallBack) {
		prefs.edit().putBoolean(PRE_IS_IN_TEST, state).commit();
		prefs.edit().putInt(PRE_TIME_CALLBACK, timeCallBack).commit();
	}

	public boolean isInTest () {
		return prefs.getBoolean(PRE_IS_IN_TEST, false);
	}
	
	
	/*
	 * TODO: get set is First run
	 */
	public void setFirstRun () {
		prefs.edit().putBoolean(PRE_IS_FIRST_RUN, false).commit();
	}

	public boolean isFirstRun () {
		return prefs.getBoolean(PRE_IS_FIRST_RUN, true);
	}

	
	/*
	 * TODO: get set is First Update
	 * will update all contact and call log
	 */
	public void setFirstUpdate () {
		prefs.edit().putBoolean(PRE_IS_FIRST_UPDATE, false).commit();
	}

	public boolean isFirstUpdate () {
		return prefs.getBoolean(PRE_IS_FIRST_UPDATE, true);
	}

	
	/*
	 * TODO: get set read all call log completed 
	 */
	public void setReadAllCallLogCompleted () {
		prefs.edit().putBoolean(PRE_READ_CALLLOG_COMPLETED, true).commit();
	}
	
	public boolean isReadAllCallLogCompleted () {
		return prefs.getBoolean(PRE_READ_CALLLOG_COMPLETED, false);
	}
	
	
	/*
	 * TODO: get set write all call log completed 
	 */	
	public void setWriteAllCallLogCompleted () {
		prefs.edit().putBoolean(PRE_WRITE_CALLLOG_COMPLETED, true).commit();
	}
	
	public boolean isWriteAllCallLogCompleted () {
		return prefs.getBoolean(PRE_WRITE_CALLLOG_COMPLETED, false);
	}
	
	/*
	 * TODO: get set read call log completed by day
	 */	
	public void setReadCallLogCompletedByDay (boolean state_today) {
		prefs.edit().putBoolean(PRE_READ_CALLLOG_COMPLETED_DAY, state_today).commit();
	}
	
	public boolean isReadCallLogCompletedByDay () {
		return prefs.getBoolean(PRE_READ_CALLLOG_COMPLETED_DAY, false);
	}
	

	/*
	 * TODO: get set write call log completed by day 
	 */	
	public void setWriteAllCallLogCompletedByDay (boolean state_today) {
		prefs.edit().putBoolean(PRE_WRITE_CALLLOG_COMPLETED_DAY, state_today).commit();
	}
	
	public boolean isWriteAllCallLogCompletedByDay () {
		return prefs.getBoolean(PRE_WRITE_CALLLOG_COMPLETED_DAY, false);
	}
	

	/*
	 * TODO: get set read all contact completed 
	 */
	public void setReadAllContactCompleted () {
		prefs.edit().putBoolean(PRE_READ_CONTACT_COMPLETED, true).commit();
	}
	
	public boolean isReadAllContactCompleted () {
		return prefs.getBoolean(PRE_READ_CONTACT_COMPLETED, false);
	}
	

	/*
	 * TODO: get set write all contact completed 
	 */
	public void setWriteAllContactCompleted () {
		prefs.edit().putBoolean(PRE_WRITE_CONTACT_COMPLETED, true).commit();
	}
	
	public boolean isWriteAllContactCompleted () {
		return prefs.getBoolean(PRE_WRITE_CONTACT_COMPLETED, false);
	}
	

	/*
	 * TODO: get set read contact completed by day
	 */
	public void setReadContactCompletedByDay (boolean state_today) {
		prefs.edit().putBoolean(PRE_READ_CONTACT_COMPLETED_DAY, state_today).commit();
	}
	
	public boolean isReadContactCompletedByDay () {
		return prefs.getBoolean(PRE_READ_CONTACT_COMPLETED_DAY, false);
	}
	

	/*
	 * TODO: get set write contact completed by day
	 */
	public void setWriteContactCompletedByDay (boolean state_today) {
		prefs.edit().putBoolean(PRE_WRITE_CONTACT_COMPLETED_DAY, state_today).commit();
	}
	
	public boolean isWriteContactCompletedByDay () {
		return prefs.getBoolean(PRE_WRITE_CONTACT_COMPLETED_DAY, false);
	}
	
	
	/*
	 * TODO: get set last time update
	 */
	public void setLastTimeUpdate (int lastTime) {
		prefs.edit().putInt(PRE_LAST_TIME_UPDATE, lastTime).commit();
	}
	
	public int getLastTimeUpdate () {
		return prefs.getInt(PRE_LAST_TIME_UPDATE, 0);
	}
	
}
