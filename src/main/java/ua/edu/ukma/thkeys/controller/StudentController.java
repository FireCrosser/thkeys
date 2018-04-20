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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.ukma.thkeys.dao.StudentDAO;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    public StudentDAO studentDao;

    @RequestMapping(value = "/specialities", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getSpecialities() {
        List<String> specialities = new ArrayList<String>();
        specialities.addAll(studentDao.getSpecialities());
        return specialities;
    }
}
