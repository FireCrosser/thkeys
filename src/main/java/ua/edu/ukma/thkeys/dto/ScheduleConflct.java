/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.ukma.thkeys.dto;

import java.util.List;
import ua.edu.ukma.thkeys.domain.ScheduleEntry;
import ua.edu.ukma.thkeys.enums.ScheduleConflictType;

public class ScheduleConflct {
    
    private List<ScheduleEntry> entries;
    private ScheduleConflictType conflictType;
}
