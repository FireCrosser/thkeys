/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.ukma.thkeys.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import ua.edu.ukma.thkeys.ContextTest;

public class ExcelServiceTest extends ContextTest {

    @Autowired
    private ExcelService es;

    @Test
    public void testParseExcelFile() throws IOException {
        String filename = "Інформатика  -3 весна 17-18н.р.  Microsoft Office Excel.xlsx";
        Path path = Paths.get("/home/pat/ukma5/nosql/laba/2017_2018__Spring/"
                + "Інформатика  -3 весна 17-18н.р.  Microsoft Office Excel.xlsx");
        MultipartFile file = new MockMultipartFile(filename, Files.newInputStream(path));
        es.parseExcelSchedule(Arrays.asList(file));
    }

}
