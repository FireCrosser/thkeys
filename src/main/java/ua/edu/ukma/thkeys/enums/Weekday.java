/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.ukma.thkeys.enums;

public enum Weekday {

    monday("понеділок"),
    tuesday("вівторок"),
    wednesday("середа"),
    thursday("четвер"),
    friday("п'ятниця"),
    saturday("субота"),
    sunday("неділя");

    private final String ukrainianName;

    Weekday(String ukrainianName) {
        this.ukrainianName = ukrainianName;
    }

    public String getUa() {
        return ukrainianName;
    }

    public Weekday getByUa(final String name) {
        for (Weekday weekday : Weekday.values()) {
            if (weekday.getUa().equals(name.toLowerCase())) {
                return weekday;
            }
        }
        return null;
    }
}
