/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.ukma.thkeys.service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.edu.ukma.thkeys.dao.ClassroomDAO;
import ua.edu.ukma.thkeys.dao.TeacherDAO;
import ua.edu.ukma.thkeys.domain.ScheduleEntryExcel;
import ua.edu.ukma.thkeys.enums.ExcelHeaderName;
import ua.edu.ukma.thkeys.enums.Weekday;
import ua.edu.ukma.thkeys.vo.Auditorium;
import ua.edu.ukma.thkeys.vo.TeacherSubjectInfo;

@Service
public class ExcelService {

    @Autowired
    private ConversionService conversionService;
    @Autowired
    public TeacherDAO teacherDao;
    @Autowired
    public ClassroomDAO classroomDao;

    public Workbook getExcelSchedule(final String teacherName,
            final Integer week) {
        Set<Map<String, String>> schedule;
        if (week != null) {
            schedule = teacherDao.getTeacherScheduleByWeek(
                    teacherName, week);
        } else {
            schedule = teacherDao.getTeacherSchedule(
                    teacherName);
        }
        if (CollectionUtils.isEmpty(schedule)) {
            return null;
        }
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Schedule");
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLACK.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);
        Map<String, String> scheduleKeysIterator = schedule.stream().findAny().
                orElse(null);
        int cellCounter = 0;
        for (Map.Entry<String, String> entry : scheduleKeysIterator.entrySet()) {
            Cell cell = headerRow.createCell(cellCounter++);
            cell.setCellValue(entry.getKey());
            cell.setCellStyle(headerCellStyle);
        }
        int rowCounter = 1;
        for (Map<String, String> map : schedule) {
            cellCounter = 0;
            Row row = sheet.createRow(rowCounter++);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                Cell cell = row.createCell(cellCounter++);
                cell.setCellValue(entry.getValue());
            }
        }
        Iterator rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = (Row) rowIterator.next();
            Iterator cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = (Cell) cellIterator.next();
                sheet.autoSizeColumn(cell.getColumnIndex());
            }
        }
        return workbook;
    }

    public void parseExcelSchedule(final List<MultipartFile> files) {
        List<TeacherSubjectInfo> teacherSubjectInfos = parseExcelFile(files.
                stream().findAny().orElse(null));
        teacherDao.insertSubjectsData(teacherSubjectInfos);
        List<Auditorium> classrooms = teacherSubjectInfos.stream()
                .map(t -> conversionService.convert(t.getAuditorium(), Auditorium.class))
                .collect(Collectors.toList());
        classroomDao.insertClassroomsData(classrooms);
    }

    private List<TeacherSubjectInfo> parseExcelFile(final MultipartFile file) {
        Workbook workbook = null;
        try (InputStream inputStream = new BufferedInputStream(file.
                getInputStream())) {
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException ex) {
            Logger.getLogger(ExcelService.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        if (workbook == null) {
            throw new RuntimeException("Cannot open the file!");
        }
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();
        Iterator<Cell> cellIterator;
        Iterator<ExcelHeaderName> headerNamesIterator;
        boolean isHeaderPresent = false;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            cellIterator = row.cellIterator();
            headerNamesIterator = Arrays.asList(ExcelHeaderName.values()).
                    iterator();
            String cellValue = null;
            ExcelHeaderName headerName = null;
            while (cellIterator.hasNext() && headerNamesIterator.hasNext()) {
                Cell cell = cellIterator.next();
                cellValue = cell.getStringCellValue();
                headerName = headerNamesIterator.next();
                if (!cellValue.toLowerCase().equals(headerName.getUaName())) {
                    break;
                }
            }
            if (!headerNamesIterator.hasNext()
                    && cellValue != null
                    && headerName != null
                    && cellValue.toLowerCase().equals(headerName.getUaName())) {
                isHeaderPresent = true;
                System.out.println("PEREMOGA!");
                break;
            }
        }
        List<ScheduleEntryExcel> schedule = new ArrayList<>();
        if (isHeaderPresent) {
            Weekday weekday = null;
            while (rowIterator.hasNext()) {
                ScheduleEntryExcel entry = conversionService.convert(
                        rowIterator.
                                next(), ScheduleEntryExcel.class);
                if (entry != null) {
                    if (entry.getWeekday() != null) {
                        weekday = entry.getWeekday();
                    } else {
                        entry.setWeekday(weekday);
                    }
                    if (entry.isCorrect()) {
                        schedule.add(entry);
                    }
                }
            }
        }
        schedule.forEach(System.out::println);
        return schedule.stream().
                map(s -> conversionService.convert(s,
                TeacherSubjectInfo.class))
                .peek(t -> t.setSpecYear("3"))
                .collect(Collectors.toList());
    }
}
