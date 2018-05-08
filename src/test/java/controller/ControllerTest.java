package controller;

import model.Laboratory;
import model.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.text.ParseException;

import static org.junit.Assert.*;

public class ControllerTest {
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
    }

    @After
    public void tearDown() throws Exception {
        fileStudents.delete();
        fileLaboratories.delete();
    }

    @Test
    public void tc01_saveStudent_pass() {
        Student s = new Student(
                "ggie2018",
                "Gigel Gigel",
                911
        );
        assertTrue(controller.saveStudent(s));
    }

    @Test
    public void tc02_saveStudent_invalidGroupHigher() {
        Student s = new Student(
                "ggie2018",
                "Gigel Gigel",
                1000
        );
        assertFalse(controller.saveStudent(s));
    }

    @Test
    public void tc03_saveStudent_invalidRegistrationNumber() {
        Student s = new Student(
                "gg2018",
                "Gigel Gigel",
                911
        );
        assertFalse(controller.saveStudent(s));
    }

    @Test
    public void tc04_saveStudent_nullRegistrationNumber() {
        Student s = new Student(
                null,
                "Gigel Gigel",
                911
        );
        try {
            controller.saveStudent(s);
            assert false;
        } catch (NullPointerException e) {
            assert true;
        }
    }

    @Test
    public void tc05_saveStudent_invalidNameSingleWord() {
        Student s = new Student(
                "ggie2018",
                "Gigel",
                911
        );
        assertFalse(controller.saveStudent(s));
    }

    @Test
    public void tc06_saveStudent_nullName() {
        Student s = new Student(
                "ggie2018",
                null,
                911
        );
        try {
            controller.saveStudent(s);
            assert false;
        } catch (NullPointerException e) {
            assert true;
        }
    }

    @Test
    public void tc07_saveStudent_invalidGroupLower() {
        Student s = new Student(
                "ggie2018",
                "Gigel Gigel",
                99
        );
        assertFalse(controller.saveStudent(s));
    }

    @Test
    public void tc08_saveStudent_validRegistrationNumberLowValue() {
        Student s = new Student(
                "aaaa0000",
                "Gigel Gigel",
                911
        );
        assertTrue(controller.saveStudent(s));
    }

    @Test
    public void tc09_saveStudent_invalidRegistrationNumberLowValue_1() {
        Student s = new Student(
                "aaa0000",
                "Gigel Gigel",
                911
        );
        assertFalse(controller.saveStudent(s));
    }

    @Test
    public void tc10_saveStudent_invalidRegistrationNumberLowValue_2() {
        Student s = new Student(
                "aaaa000",
                "Gigel Gigel",
                911
        );
        assertFalse(controller.saveStudent(s));
    }

    @Test
    public void tc11_saveStudent_validRegistrationNumberHighValue() {
        Student s = new Student(
                "ZZZZ9999",
                "Gigel Gigel",
                911
        );
        assertTrue(controller.saveStudent(s));
    }

    @Test
    public void tc12_saveStudent_invalidRegistrationNumberHighValue_1() {
        Student s = new Student(
                "ZZZ9999",
                "Gigel Gigel",
                911
        );
        assertFalse(controller.saveStudent(s));
    }

    @Test
    public void tc13_saveStudent_invalidRegistrationNumberHighValue_2() {
        Student s = new Student(
                "ZZZZ999",
                "Gigel Gigel",
                911
        );
        assertFalse(controller.saveStudent(s));
    }

    @Test
    public void tc14_saveStudent_validNameShortestWords() {
        Student s = new Student(
                "aaie2018",
                "A A",
                911
        );
        assertTrue(controller.saveStudent(s));
    }

    @Test
    public void tc15_saveStudent_invalidNameShortestWords_1() {
        Student s = new Student(
                "aaie2018",
                "A",
                911
        );
        assertFalse(controller.saveStudent(s));
    }

    @Test
    public void tc16_saveStudent_invalidNameShortestWords_2() {
        Student s = new Student(
                "aaie2018",
                "A A A",
                911
        );
        assertFalse(controller.saveStudent(s));
    }

    @Test
    public void tc17_saveStudent_invalidNameLongWords() {
        Student s = new Student(
                "aaie2018",
                "AAAAAAAAAAAAAAAAAAAAA",
                911
        );
        assertFalse(controller.saveStudent(s));
    }

    @Test
    public void tc18_saveStudent_validNameLongWords() {
        Student s = new Student(
                "aaie2018",
                "AAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAA",
                911
        );
        assertTrue(controller.saveStudent(s));
    }

    @Test
    public void tc19_saveStudent_invalidNameEmptyString() {
        Student s = new Student(
                "aaie2018",
                "",
                911
        );
        assertFalse(controller.saveStudent(s));
    }

    @Test
    public void tc20_saveStudent_validGroupLowerBoundary() {
        Student s = new Student(
                "ggie2018",
                "Gigel Gigel",
                100
        );
        assertTrue(controller.saveStudent(s));
    }

    @Test
    public void tc21_saveStudent_validGroupLowerBoundaryPlusOne() {
        Student s = new Student(
                "ggie2018",
                "Gigel Gigel",
                101
        );
        assertTrue(controller.saveStudent(s));
    }

    @Test
    public void tc22_saveStudent_validGroupUpperBoundary() {
        Student s = new Student(
                "ggie2018",
                "Gigel Gigel",
                999
        );
        assertTrue(controller.saveStudent(s));
    }

    @Test
    public void tc23_saveStudent_validGroupUpperBoundaryMinusOne() {
        Student s = new Student(
                "ggie2018",
                "Gigel Gigel",
                998
        );
        assertTrue(controller.saveStudent(s));
    }


    // ---------------- WBT ----------------------
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