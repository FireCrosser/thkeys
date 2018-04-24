/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.ukma.thkeys.service.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import ua.edu.ukma.thkeys.domain.ScheduleEntryExcel;
import ua.edu.ukma.thkeys.enums.ScheduleEntryType;
import ua.edu.ukma.thkeys.enums.ScheduleEntryTypeFlag;
import ua.edu.ukma.thkeys.vo.TeacherSubjectInfo;

@Service
public class ScheduleEntryExcelToTeacherSubjectInfoConverter 
        implements Converter<ScheduleEntryExcel, TeacherSubjectInfo>{

    @Override
    public TeacherSubjectInfo convert(ScheduleEntryExcel s) {
        TeacherSubjectInfo tsi = new TeacherSubjectInfo();
        tsi.setDayName(s.getWeekday().getUa());
        tsi.setTime(s.getTime());
        tsi.setSubjectName(s.getSubject());
        tsi.setTeacher(s.getTeacher());
        ScheduleEntryType group = s.getGroup();
        if (group.getFlag().equals(ScheduleEntryTypeFlag.lection)) {
            tsi.setGroup(group.getFlag().getUa());
        } else {
            tsi.setGroup(String.valueOf(group.getGroupNumber()));
        }
        tsi.setWeeks(s.getWeek());
        tsi.setAuditorium(s.getClassroom());
        return tsi;
    }
    
}
