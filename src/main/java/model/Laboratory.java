package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Laboratory {
    private int labNumber;
    private Date date;
    private int problemNumber;
    private float grade = 1;
    private String studentRegistrationNumber;

    public Laboratory(
            int labNumber,
            String dateString,
            int problemNumber,
            String studentRegistrationNumber
    ) throws ParseException {
        this.labNumber = labNumber;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.date = format.parse(dateString);
        this.problemNumber = problemNumber;
        this.studentRegistrationNumber = studentRegistrationNumber;
    }

    public Laboratory(
            int labNumber,
            String date,
            int problemNumber,
            Float grade,
            String studentRegistrationNumber
    ) throws ParseException {
        this.labNumber = labNumber;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.date = format.parse(date);
        this.problemNumber = problemNumber;
        this.grade = grade;
        this.studentRegistrationNumber = studentRegistrationNumber;
    }

    public int getLabNumber() {
        return labNumber;
    }

    public void setLabNumber(int labNumber) {
        this.labNumber = labNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getProblemNumber() {
        return problemNumber;
    }

    public void setProblemNumber(int problemNumber) {
        this.problemNumber = problemNumber;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String getStudentRegistrationNumber() {
        return studentRegistrationNumber;
    }

    public void setStudentRegistrationNumber(String studentRegistrationNumber) {
        this.studentRegistrationNumber = studentRegistrationNumber;
    }

    @Override
    public String toString() {
        int month = date.getMonth() + 1;
        int year = date.getYear() + 1900;
        return labNumber + " " + date.getDay() + "/" + month + "/" + year + " "
                + problemNumber + " " + grade + " " + studentRegistrationNumber;
    }

    public String toCSV() {
//        int day = date.getDay();
//        int month = date.getMonth() + 1;
//        int year = date.getYear() + 1900;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = format.format(date);

        return labNumber + "," + dateString + "," + problemNumber + "," + grade + "," + studentRegistrationNumber;
    }
} 