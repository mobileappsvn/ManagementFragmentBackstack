package robert.com.unittest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class Utility {
    private static final String TAG = "Utility";
    public static final String DATE_READ_TIME_FORMAT = "yyyyMMddHHmmss";

    public static String convertGMTtoLocale(String time) {
        String stringFomat = "yyyyMMddHHmmssSSS";
        SimpleDateFormat dateFormat = new SimpleDateFormat(stringFomat,
                Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            Date date = dateFormat.parse(time);
            dateFormat = new SimpleDateFormat(stringFomat, Locale.getDefault());
            dateFormat.setTimeZone(TimeZone.getDefault());
            return dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long convertTimeToMilisecond(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS",
                Locale.getDefault());
        Date date;
        try {
            date = dateFormat.parse(time);
            long timeInMili = date.getTime();
            return timeInMili;
        } catch (ParseException e) {
        }
        return 0L;
    }

    public static String convertReadTimeGMTtoLocale(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_READ_TIME_FORMAT,
                Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            Date date = dateFormat.parse(time);
            dateFormat = new SimpleDateFormat(DATE_READ_TIME_FORMAT, Locale.getDefault());
            dateFormat.setTimeZone(TimeZone.getDefault());
            return dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encryptMessageToSend(String content) {
        if (content == null) {
            return null;
        }
        content = content.replaceAll("\"", "&dq");
        content = content.replaceAll("\\\\", "&bs");
        content = content.replaceAll("\n", "&nl");
        return content;
    }

    public static String decryptMessageReceived(String content) {
        if (content == null) {
            return null;
        }
        content = content.replaceAll("&dq", "\"");
        content = content.replaceAll("&bs", "\\\\");
        content = content.replaceAll("&nl", "\n");
        return content;
    }

}