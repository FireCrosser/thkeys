/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.ukma.thkeys.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.edu.ukma.thkeys.dao.TeacherDAO;
import ua.edu.ukma.thkeys.service.ExcelService;

@CrossOrigin
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    public TeacherDAO teacherDAO;

    @Autowired
    public ExcelService excelService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
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

    @RequestMapping(value = "/scheduleExcel", method = RequestMethod.GET)
    public void getScheduleExcel(
            @RequestParam("name") final String teacherName,
            @RequestParam(value = "week", required = false)
            final Integer weekNumber,
            HttpServletResponse response) throws IOException {
        Workbook workbook = excelService.getExcelSchedule(teacherName,
                weekNumber);
        if (workbook != null) {
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + "schedule.xlsx");
            response.setContentType("application/vnd.ms-excel");
            OutputStream out = response.getOutputStream();
            workbook.write(out);
            workbook.close();
            out.flush();
        }
    }

    @RequestMapping(value = "/uploadScheduleFiles", method = RequestMethod.POST)
    @ResponseBody
    public List<String> uploadScheduleFiles(
            @RequestParam("files") MultipartFile[] files) {
        List<MultipartFile> filesList = Arrays.asList(files);
        excelService.parseExcelSchedule(filesList);
        List<String> fileNames = filesList.stream()
                .map(f -> f.getOriginalFilename())
                .collect(Collectors.toList());
        return fileNames;
    }
}
