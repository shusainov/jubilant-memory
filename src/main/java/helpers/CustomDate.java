package helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CustomDate {

    public static int getCurrentDayValue() {
        Calendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static String convertDate(String date, String oldFormat, Locale locale, String newFormat) {
        SimpleDateFormat formatterOld = new SimpleDateFormat(oldFormat, locale);
        SimpleDateFormat formatterNew = new SimpleDateFormat(newFormat,locale);
        Date dateVal;
        String result;
        try {
            dateVal = formatterOld.parse(date);
            result = formatterNew.format(dateVal);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
