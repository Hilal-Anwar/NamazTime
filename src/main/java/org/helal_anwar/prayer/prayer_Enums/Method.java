package org.helal_anwar.prayer.prayer_Enums;

public enum Method {
    Standard_Method("Standard Method"),Hanafi_Method("Hanafi Method");
    private final String name;
    Method(String name) {
        this.name=name;
    }
    public String getName() {
        return name;
    }
}
