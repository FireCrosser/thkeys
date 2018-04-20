/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.ukma.thkeys.dao;

import java.io.IOException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.edu.ukma.thkeys.ContextTest;

/**
 *
 * @author michael
 */
public class DatabaseFillerTest extends ContextTest{
    
    @Autowired
    public DatabaseFiller databaseFiller;
    
    @Test
    public void fillClassroomsData() throws IOException {
        databaseFiller.fillClassroomsData();
    }
    
}
