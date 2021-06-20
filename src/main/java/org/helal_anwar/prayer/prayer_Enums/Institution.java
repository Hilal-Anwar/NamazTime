package org.helal_anwar.prayer.prayer_Enums;



public enum Institution {
    /**
     * @author Helal Anwar
     * <p>https://en.wikipedia.org/wiki/Islamic_calendar</p>
     */
    Egyptia_General_Authority_of_Survey(-19.5,-17.5,"Egyptian General Authority of Survey"),
    Institute_of_Geophysics_University_of_Tehran(-17.5,-14,"Institute of Geophysics, University of Tehran"),
    Islamic_Society_of_Northern_America(-15,-15,"Islamic Society of North America (ISNA)"),
    Muslim_World_League(-18,-17,"Muslim World League"),
    Shia_Ithna_Asharia_Leva_Research_Institute_Qum(-16,-14,"Shia Ithna Ashari, Leva Research Institute, Qum"),
    Umm_Al_Qura_University_Mecca(-18.5,0.0,"Umm al-Qura University, Makkah"),
    University_of_Islamic_Science_Karachi(-18,-18,"University of Islamic Sciences, Karachi");
    private final double FajirAngle;
    private final double IshaAngle;
    private final String InstitutionName;
    Institution(double FajirAngle, double IshaAngle,String InstitutionName) {
        this.FajirAngle = FajirAngle;
        this.IshaAngle = IshaAngle;
        this.InstitutionName=InstitutionName;
    }

    public double getFajirAngle() {
        return FajirAngle;
    }

    public double getIshaAngle() {
        return IshaAngle;
    }

    public String getInstitutionName() {
        return InstitutionName;
    }
}
