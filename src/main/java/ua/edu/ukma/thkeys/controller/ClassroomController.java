/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.ukma.thkeys.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.ukma.thkeys.dao.ClassroomDAO;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {

    @Autowired
    public ClassroomDAO classroomDao;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getClassroms(
            @RequestParam(name = "campus", required = false) 
            final Integer campusNumber) {
        List<String> classrooms = new ArrayList<String>();
        if (campusNumber != null) {
            classrooms.addAll(classroomDao.
                    getClassroomsOfTheCampus(campusNumber));
        } else {
            classrooms.addAll(classroomDao.getAllClassrooms());
        }
        return classrooms;
    }
}
