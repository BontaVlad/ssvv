package controller;

import model.Laboratory;
import model.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("Duplicates")
public class IncrementalIntegrationTest {
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

    private Student addStudent() {
        return new Student(
                "ggie2018",
                "Gigel Gigel",
                911
        );
    }

    private Laboratory addLaboratory() {
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
        return l1;
    }

    @Test
    public void tc_incr_a() {
        assertTrue(controller.saveStudent(addStudent()));
    }

    @Test
    public void tc_incr_a_b() {
        assertTrue(controller.saveStudent(addStudent()));
        assertTrue(controller.saveLaboratory(addLaboratory()));
        assertTrue(controller.addGrade("ggie2018", 1, 10));
    }

    @Test
    public void tc_incr_a_b_c() {
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
}
