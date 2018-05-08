package validator;

import model.Laboratory;
import model.Student;

public class Validator {

    public static boolean validateStudent(Student student) {
        if (null == student) {
            return false;
        }

        if (!student.getRegistrationNumber().matches("[a-zA-Z]{4}[\\d]{4}")) {
            return false;
        }

        if (!student.getName().matches("[a-zA-Z]+[\\s][a-zA-Z]+")) {
            return false;
        }

        if ((student.getGroup() >= 1000) || (student.getGroup() <= 99)) {
            return false;
        }

        return true;
    }

    public static boolean validateLaboratory(Laboratory laboratory) {
        if (null == laboratory) {
            return false;
        }

        if ((laboratory.getLabNumber() < 1) || (laboratory.getLabNumber() > 14)) {
            return false;
        }

        if ((laboratory.getProblemNumber() > 10) || (laboratory.getProblemNumber() < 1)) {
            return false;
        }

        return true;
    }

    public static boolean validateGrade(float grade) {
        return ((grade >= 1) && (grade <= 10));
    }
} 