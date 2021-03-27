package com.complexica.test.utils;
import java.util.Calendar;
import java.util.TimeZone;

//Singleton class provide application utilities
public class AppUtility {
    private static AppUtility instance;
    private AppUtility() {

    }
    public static AppUtility getAppUtilityInstance() {
        //double locking
        if (instance == null) {
            synchronized (AppUtility.class) {
                if (instance == null)
                    instance = new AppUtility();
            }
        }
        return instance;
    }

    /**
     *
     * @param millis
     * @return dd/MM/yyyy format
     */
    public String convertMillisToDate(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        calendar.setTimeZone(TimeZone.getDefault());
        return IAppConstants.CUSTOM_DATE_FORMAT.format(calendar.getTime());
    }
}
