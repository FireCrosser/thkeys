/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.ukma.thkeys.service.converter;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import ua.edu.ukma.thkeys.enums.ScheduleEntryType;
import ua.edu.ukma.thkeys.enums.ScheduleEntryTypeFlag;

@Service
//@ConfigurationPropertiesBinding
public class CellToScheduleEntryTypeConverter implements Converter<Cell, ScheduleEntryType> {

    @Override
    public ScheduleEntryType convert(Cell s) {
        ScheduleEntryType set = new ScheduleEntryType();
        switch (s.getCellTypeEnum()) {
            case NUMERIC:
                set.setGroupNumber((int) s.getNumericCellValue());
                set.setFlag(ScheduleEntryTypeFlag.group);
                break;
            case STRING:
                set.setFlag(ScheduleEntryTypeFlag.lection);
                break;
            default:
                set = null;
                break;
        }
        return set;
    }
    
}
