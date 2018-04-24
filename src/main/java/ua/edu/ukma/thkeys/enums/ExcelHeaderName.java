/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.ukma.thkeys.enums;

public enum ExcelHeaderName {
    weekday(""),
    time("час"),
    subject("дисципліна"),
    teacher("викладач"),
    group("група"),
    week("тижні"),
    classroom("аудиторія");

    private final String ua;

    ExcelHeaderName(final String ua) {
        this.ua = ua;
    }

    public String getUaName() {
        return ua;
    }
}
