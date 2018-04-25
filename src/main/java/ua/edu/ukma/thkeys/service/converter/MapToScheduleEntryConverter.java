/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.ukma.thkeys.service.converter;

import java.util.Map;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import ua.edu.ukma.thkeys.domain.ScheduleEntry;
import ua.edu.ukma.thkeys.enums.ScheduleEntryJsonField;
import ua.edu.ukma.thkeys.enums.ScheduleEntryType;
import ua.edu.ukma.thkeys.enums.ScheduleEntryTypeFlag;
import ua.edu.ukma.thkeys.enums.Weekday;

@Service
public class MapToScheduleEntryConverter implements
        Converter<Map<String, String>, ScheduleEntry> {

    @Override
    public ScheduleEntry convert(Map<String, String> s) {
        ScheduleEntry se = new ScheduleEntry();
        String weeks = ScheduleEntryJsonField.weeks.getValue();
        if (s.containsKey(weeks)) {
            se.setWeek(s.get(weeks));
        }
        String subjectName = ScheduleEntryJsonField.subject_name.getValue();
        if (s.containsKey(subjectName)) {
            se.setSubject(s.get(subjectName));
        }
        String auditorium = ScheduleEntryJsonField.auditorium.getValue();
        if (s.containsKey(auditorium)) {
            se.setClassroom(s.get(auditorium));
        }
        String specialityAndYear = ScheduleEntryJsonField.speciality_and_year.
                getValue();
        if (s.containsKey(specialityAndYear)) {
            se.setStudentYear(s.get(specialityAndYear));
        }
        String time = ScheduleEntryJsonField.time.getValue();
        if (s.containsKey(time)) {
            se.setTime(s.get(time));
        }
        String day = ScheduleEntryJsonField.day.getValue();
        if (s.containsKey(day)) {
            se.setWeekday(Weekday.monday.getByUa(s.get(day)));
        }
        String group = ScheduleEntryJsonField.group.getValue();
        if (s.containsKey(group)) {
            String groupString = s.get(group);
            try {
                Integer groupNum = Integer.parseInt(groupString);
                se.setGroup(new ScheduleEntryType(groupNum,
                        ScheduleEntryTypeFlag.group));
            } catch (NumberFormatException | NullPointerException e) {
                se.
                        setGroup(new ScheduleEntryType(
                                ScheduleEntryTypeFlag.lection));
            }
        }
        return se;
    }

}
