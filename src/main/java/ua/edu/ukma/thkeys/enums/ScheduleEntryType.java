/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.ukma.thkeys.enums;

public class ScheduleEntryType {

    private int groupNumber;
    private ScheduleEntryTypeFlag flag;

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public ScheduleEntryTypeFlag getFlag() {
        return flag;
    }

    public void setFlag(ScheduleEntryTypeFlag flag) {
        this.flag = flag;
    }
}
