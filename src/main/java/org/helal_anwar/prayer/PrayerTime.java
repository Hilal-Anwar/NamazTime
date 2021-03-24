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
import org.helal_anwar.prayer.prayer_Enums.Method;
import org.helal_anwar.prayer.prayer_Enums.TimeZones;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.JulianFields;
class PrayerTime {
    /**
     * @author Helal Anwar
     * @see Prayers
     * @see SalahTime
     * @see Time
     * @see TimeFormat
     */
    private double longitude;
    private double latitude;
    private double offSetTime;
    private LocalDate date = LocalDate.now();
    private Method method = Method.Standard_Method;
    private TimeFormat timeFormat = TimeFormat.TWELVE_HOURS;
    private Institution institution;

    PrayerTime(double latitude, double longitude, TimeZones timeZones, Institution institution) {
        this.longitude = longitude;
        this.latitude = latitude;
        date = LocalDate.now(ZoneId.of(timeZones.getUTC_Value()));
        offSetTime = zoneIdToOffSetTime(timeZones.getUTC_Value());
        this.institution = institution;
    }

    PrayerTime() {
    }

    private double zoneIdToOffSetTime(String zoneId) {
        zoneId = ZoneId.of(zoneId).normalized().toString();
        double hours = Double.parseDouble(zoneId.substring(0, zoneId.indexOf(':')));
        double minutes = Double.parseDouble(zoneId.substring(zoneId.indexOf(':') + 1)) / 60;
        return hours + minutes;
    }

    public double getEOT() {
        double DAY = JulianFields.JULIAN_DAY.getFrom(getDate()) - 2451545;
        double GRADIANT = resetAngle(357.529 + 0.98560028 * DAY);
        double q = resetAngle(280.459 + 0.98564736 * DAY);
        double L = resetAngle(q + (1.915 * Math.sin(Math.toRadians(GRADIANT)))
                + (0.020 * Math.sin(Math.toRadians(2 * GRADIANT))));
        double e = 23.439 - (0.00000036 * DAY);
        double RA = resetAngle(Math.toDegrees(Math.atan2(Math.cos(Math.toRadians(e))
                * Math.sin(Math.toRadians(L)), Math.cos(Math.toRadians(L)))));
        if (getDate().getMonthValue() == 3) {
            if (getDate().getDayOfMonth() == 20)
                return -7.3666666666666666666666666666667 / 60;
            if (getDate().getDayOfMonth() == 21 || getDate().getDayOfMonth() == 22)
                return -7.0666666666666666666666666666667 / 60;
        }
        return ((q - RA)) / 15;
    }

    private double resetAngle(double a) {
        a = a - (360 * (Math.floor(a / 360.0)));
        a = a < 0 ? (a + 360) : a;
        return a;
    }

    public TimeFormat getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(TimeFormat timeFormat) {
        this.timeFormat = timeFormat;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getOffSetTime() {
        return offSetTime;
    }

    public void setOffSetTime(double offSetTime) {
        this.offSetTime = offSetTime;
    }

    public double getHourAngle(double angle) {
        return Math.toDegrees(Math.acos((Math.sin(Math.toRadians(angle)) -
                Math.sin(Math.toRadians(getDeclination())) * Math.sin(Math.toRadians(getLatitude()))) /
                (Math.cos(Math.toRadians(getDeclination())) * Math.cos(Math.toRadians(getLatitude())))));
    }

    double getDuhurTime() {
        return 12 + getOffSetTime() - getLongitude() / 15 - getEOT();
    }

    double getAsrTime() {
        return getMethod().equals(Method.Standard_Method) ?
                getDuhurTime() + getHourAngle(Math.toDegrees(Math.atan(1 / (1 +
                        Math.tan(Math.toRadians(Math.abs(getDeclination() - getLatitude()))))))) / 15
                : getDuhurTime() + getHourAngle(Math.toDegrees(Math.atan(1 / (2
                + Math.tan(Math.toRadians(Math.abs(getDeclination() - getLatitude()))))))) / 15;
    }

    double getMaghribTime() {
        return getDuhurTime() + getHourAngle(-0.833) / 15;
    }

    private double getDeclination() {
        return -23.45 * Math.cos(Math.toRadians((double) 360 / 365 * (getDate().getDayOfYear() + 10)));
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    double getIshaTime() {
        if (getInstitution().equals(Institution.Umm_Al_Qura_University_Mecca))
            return getMaghribTime() + (double) 92 / 60;
        return getDuhurTime() + getHourAngle(getInstitution().getIshaAngle()) / 15;
    }

    double getFajirTime() {
        return getDuhurTime() - getHourAngle(getInstitution().getFajirAngle()) / 15;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }
}
