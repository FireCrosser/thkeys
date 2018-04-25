/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.ukma.thkeys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.ukma.thkeys.dao.TeacherDAO;

@RestController
@RequestMapping("/")
public class HomeController {
    
    @Autowired
    public TeacherDAO teacherDAO;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String welcome() {
        return "Welcome in thkeys!";
    }
    
    
    @RequestMapping(value = "/clearDb", method = RequestMethod.POST)
    @ResponseBody
    public String clearDb() {
        teacherDAO.flushDb();
        return "Ok";
    }
}
