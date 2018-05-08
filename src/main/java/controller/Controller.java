package controller;

import model.Laboratory;
import model.Student;
import repository.LaboratoryFileRepository;
import repository.StudentFileRepository;
import validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Controller {
    private StudentFileRepository studentRepository;
    private LaboratoryFileRepository laboratoryRepository;

    public Controller(String studentFile, String laboratoryFile) {
    	this.studentRepository = new StudentFileRepository(studentFile);
    	this.laboratoryRepository = new LaboratoryFileRepository(laboratoryFile);
    }
    
    public boolean saveStudent(Student student) {
        if (Validator.validateStudent(student)) {
            this.studentRepository.save(student);
            return true;
        } else {
            return false;
        }
    }

    public boolean saveLaboratory(Laboratory laboratory) {
        if (Validator.validateLaboratory(laboratory)) {
            this.laboratoryRepository.save(laboratory);
            return true;
        } else {
            return false;
        }
    }

    public boolean addGrade(String studentRegistrationNumber, int labNumber, float grade) {
        if (Validator.validateGrade(grade)) {
            this.laboratoryRepository.addGrade(studentRegistrationNumber, labNumber, grade);
            return true;
        } else {
            return false;
        }
    }

    public List<Student> passedStudents() {
        List<Student> passedStudents = new ArrayList<>();

        List<Student> studentList = studentRepository.findAll();
        Map<String, List<Laboratory>> laboratoryMap = this.laboratoryRepository.findAll();

        for (Entry<String, List<Laboratory>> entry : laboratoryMap.entrySet()) {
            float grade = 0;
            for (Laboratory laboratory : entry.getValue()) {
                grade += laboratory.getGrade();
            }
            grade /= entry.getValue().size();

            if (grade >= 5) {
                Student student = new Student();
                student.setRegistrationNumber(entry.getKey());
                int indexOf = studentList.indexOf(student);
                passedStudents.add(studentList.get(indexOf));
            }
        }

        return passedStudents;
    }
}
