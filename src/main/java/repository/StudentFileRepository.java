package repository;

import model.Student;

import java.io.*;
import java.util.*;

public class StudentFileRepository implements Repository<Student> {
    private String filename;

    public StudentFileRepository(String filename) {
        this.filename = filename;
    }

    @Override
    public void save(Student entity) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(entity.toCSV());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Student> findAll() {
        List<Student> allStudentsList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = null;

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                String registrationNumber = tokens[0];
                String name = tokens[1];
                int group;

                try {
                    group = Integer.valueOf(tokens[2]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    continue;
                }

                Student student = new Student(registrationNumber, name, group);
                allStudentsList.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allStudentsList;
    }
}
