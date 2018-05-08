package ui;

import controller.Controller;
import model.Laboratory;
import model.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.List;

public class LaboratoriesUI {
	private Controller controller;

    public LaboratoriesUI() {
        this.controller = new Controller("students_1.txt", "laboratories_1.txt");
    }


    public void run() throws IOException {
        System.out.println("Starting");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println(" 1) Add student\n 2) Add Laboratory\n 3) Add grade\n 4) Get passing students\n 0) exit\n");

                String line = br.readLine();

                if (line.equals("1")){
                    addStudentMenu(br);
                }
                else if (line.equals("2")) {
                    addLaboratoryMenu(br);
                }
                else if (line.equals("3")) {
                    addGradeMenu(br);
                }
                else if (line.equals("4")) {
                    getPassingStudentsMenu();
                }
                else if (line.equals("0")) {
                    break;
                }
                else {
                    System.out.println("Wrong input! Try again!");
                }
            }
        }

        System.out.println("Bye");
    }

    private void getPassingStudentsMenu() {
        List<Student> passingStudents = controller.passedStudents();
        System.out.println("Passing students: ");
        for (Student student : passingStudents) {
            System.out.println("\t - " + student.toString());
        }
    }

    private void addGradeMenu(BufferedReader br) throws IOException {
        String studentRegistrationNumber = null;
        int labNumber = 0;
        float grade = 0;

        System.out.println("Reg number: ");
        studentRegistrationNumber = br.readLine();
        System.out.println("Lab number: ");
        String labNumberString = br.readLine();
        System.out.println("Grade: ");
        String gradeString = br.readLine();

        try {
            labNumber = Integer.parseInt(labNumberString);
            grade = Float.parseFloat(gradeString);
        } catch (NumberFormatException e) {
            System.out.println("Invalid grade");
        }

        Boolean success = controller.addGrade(studentRegistrationNumber, labNumber, grade);
        if (!success) {
            System.out.println("Cannot save grade");
        }
    }

    private void addLaboratoryMenu(BufferedReader br) throws IOException {
        int laboratoryNumber = 0;
        int problemNumber = 0;
        String date = null;
        String studentRegistrationNumber = null;

        System.out.println("Lab number: ");
        String labNumberString = br.readLine();
        System.out.println("Problem number: ");
        String labProblemNumberString = br.readLine();

        try {
            laboratoryNumber = Integer.parseInt(labNumberString);
            problemNumber = Integer.parseInt(labProblemNumberString);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }

        System.out.println("Date (dd/mm/yyy)");
        date = br.readLine();
        System.out.println("Student reg number");
        studentRegistrationNumber = br.readLine();

        Laboratory lab = null;
        try {
            lab = new Laboratory(laboratoryNumber, date, problemNumber, studentRegistrationNumber);
        } catch (ParseException e) {
            System.out.println("Invalid input");
        }

        boolean success = controller.saveLaboratory(lab);
        if (!success) {
            System.out.println("Cannot save laboratory");
        }
    }

    private void addStudentMenu(BufferedReader br) throws IOException {
        String studentRegistrationNumber = null;
        String name = null;
        int group = 0;

        System.out.print("Registration number: ");
        studentRegistrationNumber = br.readLine();

        System.out.print("Name: ");
        name = br.readLine();

        System.out.print("Group number: ");
        String groupString = br.readLine();
        try {
            group = Integer.parseInt(groupString);
        } catch (NumberFormatException e) {
            System.out.println("Invalid group - not a number");
        }

        Student student = new Student(studentRegistrationNumber, name, group);
        boolean success = controller.saveStudent(student);
        if (!success) {
            System.out.println("Invalid student");
        }
    }
} 