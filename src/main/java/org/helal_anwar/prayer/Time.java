package org.helal_anwar.prayer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

class Time {
    /**
     * @author Helal Anwar
     * @see Prayers
     * @see SalahTime
     */
    static LocalTime formatTime(double time) {
        time = time - 24 * Math.floor(time / 24);
        int hours = (int) Math.floor(time);
        int minutes = (int) Math.round((time - Math.floor(time)) * 60);
        hours = hours + minutes / 60;
        minutes = minutes % 60;
        String value = ((hours > 9) ? hours : "0" + hours) + ":" + ((minutes > 9) ? minutes : "0" + minutes);
        return LocalTime.parse(value);
    }

    static long[] TimeDifference(LocalTime time1, LocalTime time2) {
        long difference_In_Minutes = 0, difference_In_Hours = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        try {
            Date date1 = simpleDateFormat.parse(String.valueOf(time1));
            Date date2 = simpleDateFormat.parse(String.valueOf(time2));
            long difference_In_Time=date2.getTime() - date1.getTime();
            difference_In_Minutes = TimeUnit.MILLISECONDS.toMinutes(difference_In_Time) % 60;
            difference_In_Hours = TimeUnit.MILLISECONDS.toHours(difference_In_Time) % 24;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new long[]{difference_In_Hours, difference_In_Minutes};
    }

    static LocalTime add(LocalTime timing, long hours, long minutes) {
        return timing.plusHours(hours).plusMinutes(minutes);
    }

}

