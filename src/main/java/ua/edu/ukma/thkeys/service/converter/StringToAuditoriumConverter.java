/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.ukma.thkeys.service.converter;

import java.util.Arrays;
import java.util.List;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import ua.edu.ukma.thkeys.vo.Auditorium;

@Service
public class StringToAuditoriumConverter implements Converter<String, Auditorium>{

    @Override
    public Auditorium convert(String s) {
        Auditorium a = new Auditorium();
        List<String> elements = Arrays.asList(s.split("-"));
        if (elements.size() < 2) {
            return null;
        }
        elements.forEach(String::trim);
        a.setCampus_no(elements.get(0));
        a.setRoom_no(elements.get(1));
        return a;
    }
    
}
