package org.helal_anwar.prayer.prayer_Enums;

public enum IslamicWeek {
    Al_Ithnayn("Al Ithnayn"),
    Ath_Thulatha("Ath Thulatha"),Al_Arbia("Al Arbia"),
    Al_Khamis("Al Khamis"), Al_Jummah("Al Jumu'ah"),
    As_Sabt("As Sabt"),Al_Ahad("Al Ahad");
     private final String dayName;
    IslamicWeek(String dayName) {
        this.dayName=dayName;
    }
    public String getDayName() {
        return dayName;
    }
}
