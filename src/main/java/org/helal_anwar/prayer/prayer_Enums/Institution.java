package org.helal_anwar.prayer.prayer_Enums;



public enum Institution {
    /**
     * @author Helal Anwar
     * <p>https://en.wikipedia.org/wiki/Islamic_calendar</p>
     */
    Egyptia_General_Authority_of_Survey(-19.5,-17.5),
    Institute_of_Geophysics_University_of_Tehran(-17.5,-14),
    Islamic_Society_of_Northern_America(-15,-15),
    Muslim_World_League(-18,-17),
    Shia_Ithna_Asharia_Leva_Research_Institute_Qum(-16,-14),
    Umm_Al_Qura_University_Mecca(-18,0.0),
    University_of_Islamic_Science_Karachi(-18,-18);
    private final double FajirAngle;
    private final double IshaAngle;
    Institution(double FajirAngle, double IshaAngle) {
        this.FajirAngle = FajirAngle;
        this.IshaAngle = IshaAngle;
    }

    public double getFajirAngle() {
        return FajirAngle;
    }

    public double getIshaAngle() {
        return IshaAngle;
    }
}
