# NamazTime
Namaz timings can be calculated







import org.helal_anwar.prayer.Prayers;
import org.helal_anwar.prayer.SalahTime;
import org.helal_anwar.prayer.prayer_Enums.Institution;
import org.helal_anwar.prayer.prayer_Enums.TimeZones;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        System.out.println("Namaz time for jamshedpur/Jharkhand/India/Asia lat:22.805618 , long:86.2029 and offsetTime/timezone:5.5 hours");
        System.out.println("Timing form date 2021-01-01 to 2022-01-01 ");
        System.out.println();
        SalahTime salahTime = new SalahTime(22.805618, 86.2029, TimeZones.Asia_Kolkata,
                Institution.University_of_Islamic_Science_Karachi);
        TreeMap<LocalDate, Map<Prayers, String>> x =
                salahTime.getPrayerFrom(LocalDate.parse("2021-01-01"), LocalDate.parse("2022-01-01"));
        for (LocalDate d : x.keySet()) {
            salahTime.setDate(d);
            System.out.println(d + "," + salahTime.getIslamicYear() + "," +
                    salahTime.getIslamicMonth() + "," + salahTime.getIslamicWeekDays() + "," + x.get(d));
        }
    }
}



Download the jar https://github.com/Hilal-Anwar/NamazTime/tree/master/out/artifacts/NamazTime_jar
