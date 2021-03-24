/*
    Copyright (C) 2021-21 Helal Anwar

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licens
 */
package org.helal_anwar.prayer;


import org.helal_anwar.prayer.prayer_Enums.Institution;
import org.helal_anwar.prayer.prayer_Enums.IslamicMonths;
import org.helal_anwar.prayer.prayer_Enums.IslamicWeek;
import org.helal_anwar.prayer.prayer_Enums.TimeZones;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.HijrahDate;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SalahTime extends PrayerTime {
    /**
     * @author Helal Anwar
     * @see Prayers
     * @see SalahTime
     * @see HijrahDate
     * @see Time
     * @see TimeFormat
     * @see Institution
     * @see IslamicMonths
     * @see IslamicWeek
     * @see org.helal_anwar.prayer.prayer_Enums.Method
     */
    public SalahTime(double latitude, double longitude, TimeZones timeZones, Institution institution) {
        super(latitude, longitude, timeZones, institution);
    }

    public SalahTime() {
        super();
    }

    public String FajirTime() {
        return Time.formatTime(getFajirTime(), getTimeFormat());
    }

    public String DuhurTime() {
        return Time.formatTime(getDuhurTime(), getTimeFormat());
    }


    public String AsrTime() {
        return Time.formatTime(getAsrTime(), getTimeFormat());
    }

    public String IshaTime() {
        if (getInstitution().equals(Institution.Umm_Al_Qura_University_Mecca) && getIslamicMonth().equals(IslamicMonths.Ramadan))
            return Time.formatTime(getMaghribTime() + (double) 120 / 60, getTimeFormat());
        return Time.formatTime(getIshaTime(), getTimeFormat());
    }

    public String MaghribTime() {
        return Time.formatTime(getMaghribTime(), getTimeFormat());
    }

    public String JummahTime() {
        return Time.add(Time.formatTime(getDuhurTime(), TimeFormat.TWENTY_FOUR_HOURS), 1, 0, getTimeFormat());
    }

    public String TahajjudTime() {
        long[] k = Time.TimeDifference(Time.formatTime(getMaghribTime(), TimeFormat.TWENTY_FOUR_HOURS),
                Time.formatTime(getFajirTime(), TimeFormat.TWENTY_FOUR_HOURS));
        double x = ((double) Math.abs(k[0])) / 2;
        x = (x - Math.floor(x)) * 60;
        int m = (int) (Math.abs(k[1]) + (int) x);
        return Time.add(Time.formatTime(getMaghribTime(), TimeFormat.TWENTY_FOUR_HOURS), Math.abs(k[0] / 2),
                Math.abs(m), getTimeFormat());
    }

    public TreeMap<Prayers, String> allFivePrayers() {
        if (getDate().getDayOfWeek().equals(DayOfWeek.FRIDAY))
            return new TreeMap<>(Map.of(Prayers.Fajir, FajirTime(),
                    Prayers.Duhur, DuhurTime(), Prayers.Jummah, JummahTime(), Prayers.Asr, AsrTime(),
                    Prayers.Maghrib, MaghribTime(), Prayers.Isha, IshaTime(), Prayers.Tahajjud, TahajjudTime()));
        else
            return new TreeMap<>(Map.of(Prayers.Fajir, FajirTime(),
                    Prayers.Duhur, DuhurTime(), Prayers.Asr, AsrTime(),
                    Prayers.Maghrib, MaghribTime(), Prayers.Isha, IshaTime(),
                    Prayers.Tahajjud, TahajjudTime()));
    }

    public TreeMap<Prayers, Double> allFivePrayersHours() {
        return new TreeMap<>(Map.of(Prayers.Fajir, super.getFajirTime(),
                Prayers.Duhur, super.getDuhurTime(), Prayers.Asr, super.getAsrTime(),
                Prayers.Maghrib, super.getMaghribTime(), Prayers.Isha, super.getIshaTime()));
    }

    public TreeMap<LocalDate, Map<Prayers, String>> getPrayerFrom(LocalDate from, LocalDate till) {
        TreeMap<LocalDate, Map<Prayers, String>> val = new TreeMap<>();
        while (!from.plusDays(1).equals(till)) {
            this.setDate(from);
            val.put(from, this.allFivePrayers());
            from = from.plusDays(1);
        }
        return val;
    }

    public TreeMap<LocalDate, Map<Prayers, Double>> getPrayerFromInHours(LocalDate from, LocalDate till) {
        TreeMap<LocalDate, Map<Prayers, Double>> val = new TreeMap<>();
        while (!from.plusDays(1).equals(till)) {
            this.setDate(from);
            val.put(from, this.allFivePrayersHours());
            from = from.plusDays(1);
        }
        return val;
    }

    public Map<String,Long> getPrayerTimeDifference(Prayers prayer1, Prayers prayer2) {
        long []diff_time=Time.TimeDifference(getT(prayer1), getT(prayer2));
        return Map.of("Hours",Math.abs(diff_time[0]),"Minutes",Math.abs(diff_time[1]));
    }

    public IslamicWeek getIslamicWeekDays() {
        TreeMap<Integer, IslamicWeek> x = IntStream.range(0, 7).boxed().
                collect(Collectors.toMap(i -> i + 1, i ->
                        IslamicWeek.values()[i], (a, b) -> b, TreeMap::new));
        return x.get(getDate().getDayOfWeek().getValue());
    }

    public IslamicMonths getIslamicMonth() {
        TreeMap<Integer, IslamicMonths> x = IntStream.range(0, 12).boxed().
                collect(Collectors.toMap(i -> i + 1, i -> IslamicMonths.values()[i], (a, b) -> b, TreeMap::new));
        return x.get(getIslamicMonthValue());
    }

    private String getT(Prayers p1) {
        switch (p1) {
            case Fajir:
                return FajirTime();
            case Duhur:
                return DuhurTime();
            case Asr:
                return AsrTime();
            case Maghrib:
                return MaghribTime();
            case Isha:
                return IshaTime();
            default:
                throw new IllegalStateException("Unexpected value: " + p1);
        }
    }

    public HijrahDate getIslamicDateNow() {
        return LocalTime.now().getHour() > Math.floor(getMaghribTime()) ?
                HijrahDate.from(getDate().plusDays(1)) : HijrahDate.from(getDate());
    }

    public HijrahDate getIslamicDate() {
        return HijrahDate.from(getDate());
    }

    public String getIslamicYear() {
        String x = HijrahDate.from(getDate()).toString();
        return x.substring(x.lastIndexOf(' ') + 1);
    }

    public int getIslamicMonthValue() {
        return Integer.parseInt(getIslamicYear().substring(getIslamicYear().indexOf('-') + 1, getIslamicYear().lastIndexOf('-')));
    }
}
