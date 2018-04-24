/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.ukma.thkeys.enums;

public enum ScheduleEntryTypeFlag {
    group("група"),
    lection("лекція");
    
    private final String ukrainianName;
    
    ScheduleEntryTypeFlag(String ukrainianName) {
        this.ukrainianName = ukrainianName;
    }

    public String getUa() {
        return ukrainianName;
    }
}
