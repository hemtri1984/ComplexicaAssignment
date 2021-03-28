package com.complexica.test.utils;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

    /**
     *
     * @param millis
     * @return dd/MM/yyyy format
     */
    public String convertMillisToLocalDate(long millis) {
        LocalDateTime localdateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault());
        System.out.println("Hour: "+localdateTime.getHour()+"; mins"+localdateTime.getMinute()+"; date "+localdateTime.getDayOfMonth()+"; month "+localdateTime.getMonthValue()+"; year: "+localdateTime.getYear());
        int date = localdateTime.getDayOfMonth();
        String dateStr = String.valueOf(date);
        if(date < 10) {
            dateStr = "0"+dateStr;
        }
        int month = localdateTime.getMonthValue();
        String monthStr = String.valueOf(month);
        if(month < 10) {
            monthStr = "0"+monthStr;
        }
        String yearStr = String.valueOf(localdateTime.getYear());
        System.out.println("coverted date: "+dateStr + "/"+monthStr+"/"+yearStr);
        return dateStr + "/"+monthStr+"/"+yearStr;
    }

    /**
     *
     * @param millis
     * @return hh:mm AM/PM format
     */
    public String convertMillisToLocalTime(long millis) {
        LocalDateTime localdateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault());
        System.out.println("Hour: "+localdateTime.getHour()+"; mins"+localdateTime.getMinute()+"; date "+localdateTime.getDayOfMonth()+"; month "+localdateTime.getMonthValue()+"; year: "+localdateTime.getYear());
        String ampm = "AM";
        int hour = localdateTime.getHour();
        if(hour > 12) {
            ampm = "PM";
            hour = hour - 12;
        }
        String hourStr = String.valueOf(hour);
        if(hour < 10) hourStr = "0"+hourStr;
        int mins = localdateTime.getMinute();
        String minsStr = String.valueOf(mins);
        if(mins < 10) {
            minsStr = "0"+minsStr;
        }
        System.out.println("coverted time: "+hourStr+":"+minsStr+" "+ampm);
        return hourStr+":"+minsStr+" "+ampm;
    }
}
