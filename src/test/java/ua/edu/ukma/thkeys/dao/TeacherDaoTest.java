/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.ukma.thkeys.dao;

import java.util.Map;
import java.util.Set;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.edu.ukma.thkeys.ContextTest;

public class TeacherDaoTest extends ContextTest {
    
    @Autowired
    private TeacherDAO teacherDAO;
    
    @Test
    public void testTeacherDao() {
        Set<Map<String, String>> schedule = teacherDAO.getTeacherSchedule("teacher");
        for (Map<String, String> entry: schedule) {
            System.out.println(entry);
        }
    }
    
}
