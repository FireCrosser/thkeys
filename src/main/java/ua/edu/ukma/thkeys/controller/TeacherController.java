/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.ukma.thkeys.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.ukma.thkeys.dao.TeacherDAO;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    public TeacherDAO teacherDAO;

    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getTeachers() {
        List<String> names = new ArrayList<String>();
        names.addAll(teacherDAO.getTeachersNames());
        return names;
    }
    
    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, String>> getSchedule(
            @RequestParam("name") final String teacherName,
            @RequestParam(value = "week", required = false)
            final Integer weekNumber) {
        Set<Map<String, String>> schedule;
        if (weekNumber != null) {
            schedule = teacherDAO.getTeacherScheduleByWeek(
                    teacherName, weekNumber);
        } else {
            schedule = teacherDAO.getTeacherSchedule(
                    teacherName);
        }
        List<Map<String, String>> scheduleList = new ArrayList<Map<String, String>>();
        scheduleList.addAll(schedule);
        return scheduleList;
    }
}
