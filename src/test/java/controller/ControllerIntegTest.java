package controller;

import model.Laboratory;
import model.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings("Duplicates")
public class ControllerIntegTest {
    private Controller controller;

    private File fileStudents;
    private File fileLaboratories;

    private static final String FILE_STUDENTS = "test_students.txt";
    private static final String FILE_LABORATORIES = "test_laboratories.txt";


    @Before
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
    public void tc_a_add_student() {
        Student s1 = new Student(
                "ggie2018",
                "Gigel Gigel",
                911
        );
        assertTrue(controller.saveStudent(s1));

        Student s2 = new Student(
                "amie2019",
                "Ana Maria",
                912
        );
        assertTrue(controller.saveStudent(s2));
    }

    @Test
    public void tc_b_add_laboratory() {
        Laboratory l1 = null;
        try {
            l1 = new Laboratory(
                    1,
                    "27/03/2018",
                    1,
                    "ggie2018"
            );
        } catch (ParseException e) {
            assert false;
        }
        assertTrue(controller.saveLaboratory(l1));

        Laboratory l2 = null;
        try {
            l2 = new Laboratory(
                    1,
                    "27/03/2018",
                    2,
                    "amie2019"
            );
        } catch (ParseException e) {
            assert false;
        }
        assertTrue(controller.saveLaboratory(l2));
    }

    @Test
    public void tc_c_passed_students() {
//        createStudentsAndGrades();
//        List<Student> list = controller.passedStudents();
//
//        assertEquals(1, list.size());
//        assertEquals("amie2019", list.get(0).getRegistrationNumber());

        assertEquals(0, controller.passedStudents().size());
    }

    @Test
    public void tc_a_b_c_integr() {
        createStudentsAndGrades();

        List<Student> list = controller.passedStudents();

        assertEquals(1, list.size());
        assertEquals("amie2019", list.get(0).getRegistrationNumber());
    }

    private void createStudentsAndGrades() {
        Student s1 = new Student(
                "ggie2018",
                "Gigel Gigel",
                911
        );
        controller.saveStudent(s1);

        Student s2 = new Student(
                "amie2019",
                "Ana Maria",
                912
        );
        controller.saveStudent(s2);

        Laboratory l1 = null;
        try {
            l1 = new Laboratory(
                    1,
                    "27/03/2018",
                    1,
                    "ggie2018"
            );
        } catch (ParseException e) {
            assert false;
        }
        controller.saveLaboratory(l1);

        Laboratory l2 = null;
        try {
            l2 = new Laboratory(
                    1,
                    "27/03/2018",
                    2,
                    "amie2019"
            );
        } catch (ParseException e) {
            assert false;
        }
        controller.saveLaboratory(l2);

        controller.addGrade("ggie2018", 1, 4);
        controller.addGrade("amie2019", 1, 10);
    }

    @Test
    public void tc_a_b_integ() {
        Student s1 = new Student(
                "ggie2018",
                "Gigel Gigel",
                911
        );
        assertTrue(controller.saveStudent(s1));

        Laboratory l1 = null;
        try {
            l1 = new Laboratory(
                    1,
                    "27/03/2018",
                    1,
                    "ggie2018"
            );
        } catch (ParseException e) {
            assert false;
        }
        assertTrue(controller.saveLaboratory(l1));

        assertTrue(controller.addGrade("ggie2018", 1, 10));
    }
}