package ua.edu.ukma.thkeys.tests;

import java.io.IOException;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.edu.ukma.thkeys.ContextTest;

import ua.edu.ukma.thkeys.dao.DatabaseFiller;
import ua.edu.ukma.thkeys.dao.StudentDAO;

public class StudentTester extends ContextTest {

    @Autowired
    public DatabaseFiller databaseFiller;

    @Autowired
    public StudentDAO stDao;

//	@Test
    public void getStudentsSpecialities() throws IOException {
        databaseFiller.fillSubjectsData();

        Set<String> specs = stDao.getSpecialities();

        for (String sp : specs) {
            System.out.println(sp);
        }
    }

}
