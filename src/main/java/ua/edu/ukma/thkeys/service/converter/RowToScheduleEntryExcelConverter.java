/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.ukma.thkeys.service.converter;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import ua.edu.ukma.thkeys.domain.ScheduleEntryExcel;
import ua.edu.ukma.thkeys.enums.ExcelHeaderName;
import ua.edu.ukma.thkeys.enums.Weekday;

@Service
//@ConfigurationPropertiesBinding
public class RowToScheduleEntryExcelConverter implements
        Converter<Row, ScheduleEntryExcel> {
    
    @Autowired
    private CellToScheduleEntryTypeConverter cs;
    
    @Override
    public ScheduleEntryExcel convert(Row s) {
        ScheduleEntryExcel se = new ScheduleEntryExcel();
        Iterator<Cell> cellIterator = s.cellIterator();
        Iterator<ExcelHeaderName> headerNamesIterator = Arrays.asList(
                ExcelHeaderName.values()).iterator();
        Map<String, Weekday> weekdaysByUaName = Arrays.stream(Weekday.values())
                .collect(Collectors.
                        toMap(e -> e.getUa(), e -> e, (e0, e1) -> e0));
        while (cellIterator.hasNext() && headerNamesIterator.hasNext()) {
            Cell cell = cellIterator.next();
            ExcelHeaderName headerName = headerNamesIterator.next();
            if (cell == null || cell.getCellTypeEnum().equals(CellType._NONE)) {
                continue;
            }
            switch (headerName) {
                case weekday:
                    se.setWeekday(weekdaysByUaName.get(
                            cell.getStringCellValue().toLowerCase()));
                case time:
                    se.setTime(cell.getStringCellValue());
                    break;
                case subject:
                    se.setSubject(cell.getStringCellValue());
                    break;
                case teacher:
                    se.setTeacher(cell.getStringCellValue());
                    break;
                case group:
                    se.setGroup(cs.convert(cell));
                    break;
                case week:
                    switch (cell.getCellTypeEnum()) {
                        case NUMERIC:
                            se.setWeek(String.
                                    valueOf(cell.getNumericCellValue()));
                            break;
                        case STRING:
                        default:
                            se.setWeek(String.valueOf(cell.
                                    getStringCellValue()));
                            break;
                    }
                    break;
                case classroom:
                default:
                    se.setClassroom(cell.getStringCellValue());
                    break;
            }
        }
        return se;
    }
    
}
