package ua.edu.ukma.thkeys.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ua.edu.ukma.thkeys.dao.TeacherDAO;
import ua.edu.ukma.thkeys.domain.ScheduleEntry;

@Service
public class ScheduleService {
    
    @Autowired
    public TeacherDAO teacherDao;
    
    @Autowired
    public ConversionService conversionService;
    
    public Object getScheduleForMethodist() {
        Set<String> teacherNames = teacherDao.getTeachersNames();
        Set<Map<String, String>> scheduleMaps = new HashSet<>();
        teacherNames.forEach(name -> {
            scheduleMaps.addAll(teacherDao.getTeacherSchedule(name));
        });
        List<ScheduleEntry> scheduleList = scheduleMaps.stream()
                .map(entry -> conversionService.convert(entry,
                ScheduleEntry.class))
                .collect(Collectors.toList());
        scheduleList.forEach(System.out::println);
        return null;
    }
}
