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
package org.helal_anwar.prayer.prayer_Enums;

public enum IslamicMonths {
    /**
     * @author Helal Anwar
     */
    Al_Muharram("Muharram"),
    Safar("Safar"),
    Rabi_Al_Awwal("Rabi' al-awwal"),
    Rabi_Ath_Thani("Rabi' al-thani"),
    Jumada_Al_Ula("Jumada al-awwal"),
    Jumada_Ath_Thaniyah("Jumada al-thani"),
    Rajab("Rajab"),
    Shaban("Sha'aban"),
    Ramadan("Ramadan"),
    Shawwal("Shawwal"),
    Du_Al_QA_Dah("Dhu al-Qi'dah"),
    Du_al_Hijjah("Dhu al-Hijjah");
    private final String monthName;
    IslamicMonths(String monthName) {
         this.monthName=monthName;
    }
    public String getMonthName(){return monthName;}
}
