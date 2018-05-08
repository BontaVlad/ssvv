package controller;

import model.Laboratory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.text.ParseException;

import static org.junit.Assert.*;

public class ControllerTestWBT {
    private Controller controller;

    private File fileStudents;
    private File fileLaboratories;

    private static final String FILE_STUDENTS = "test_students.txt";
    private static final String FILE_LABORATORIES = "test_laboratories.txt";

    private static final String EXISTING_STUDENT_REGISTRATION_NUMBER = "aaie1996";

    @Before
    @SuppressWarnings("Duplicates")
    public void setUp() throws Exception {
        fileStudents = new File(FILE_STUDENTS);
        fileStudents.createNewFile();

        fileLaboratories = new File(FILE_LABORATORIES);
        fileLaboratories.createNewFile();

        controller = new Controller(FILE_STUDENTS, FILE_LABORATORIES);

        Laboratory laboratory = new Laboratory(
                1,
                "02/04/2018",
                2,
                EXISTING_STUDENT_REGISTRATION_NUMBER
        );
        controller.saveLaboratory(laboratory);

    }

    @After
    public void tearDown() throws Exception {
        fileStudents.delete();
        fileLaboratories.delete();
    }

    @Test
    public void tc01_wbt_saveLaboratory_valid() {
        Laboratory laboratory = null;
        try {
            laboratory = new Laboratory(
                    1,
                    "27/03/2018",
                    1,
                    "ggie2018"
            );
        } catch (ParseException e) {
            assert false;
        }
        assertTrue(controller.saveLaboratory(laboratory));
    }

    @Test
    public void tc02_wbt_saveLaboratory_invalid() {
        Laboratory laboratory = null;
        try {
            laboratory = new Laboratory(
                    20,
                    "27/03/2018",
                    1,
                    "ggie2018"
            );
        } catch (ParseException e) {
            assert false;
        }
        assertFalse(controller.saveLaboratory(laboratory));
    }

    @Test
    public void tc03_wbt_addGrade_valid() {
        assertTrue(controller.addGrade(EXISTING_STUDENT_REGISTRATION_NUMBER, 1, 10));
    }

    @Test
    public void tc04_wbt_addGrade_invalid() {
        assertFalse(controller.addGrade(EXISTING_STUDENT_REGISTRATION_NUMBER, 1, 11));
    }
}