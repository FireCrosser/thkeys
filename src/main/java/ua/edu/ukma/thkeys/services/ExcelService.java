/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.ukma.thkeys.services;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
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
import org.springframework.stereotype.Service;
import ua.edu.ukma.thkeys.dao.TeacherDAO;

@Service
public class ExcelService {

    @Autowired
    public TeacherDAO tdao;

    public Workbook getExcelSchedule(final String teacherName,
            final Integer week) {
        Set<Map<String, String>> schedule;
        if (week != null) {
            schedule = tdao.getTeacherScheduleByWeek(
                    teacherName, week);
        } else {
            schedule = tdao.getTeacherSchedule(
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
}
