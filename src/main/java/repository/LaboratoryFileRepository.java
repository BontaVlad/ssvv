package repository;

import model.Laboratory;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LaboratoryFileRepository implements Repository<Laboratory> {
    private String filename;

    public LaboratoryFileRepository(String filename) {
        this.filename = filename;
    }

    @Override
    public void save(Laboratory entity) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(entity.toCSV());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addGrade(
            String studentRegistrationNumber,
            int labNumber,
            float grade
    ) {
        File origFile = new File(filename);
        File tempFile = new File("temp");

        try (BufferedReader reader = new BufferedReader(new FileReader(origFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String line = null;

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                int currentLabNumber = 0;
                String currentDateString = tokens[1];
                int currentProblemNumber = 0;
                String currentStudentRegistrationNumber = tokens[4];

                try {
                    currentLabNumber = Integer.valueOf(tokens[0]);
                    currentProblemNumber = Integer.valueOf(tokens[2]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    continue;
                }

                if ((currentLabNumber == labNumber)
                        && (currentStudentRegistrationNumber.equals(studentRegistrationNumber))) {
                    Laboratory laboratory = null;
                    try {
                        laboratory = new Laboratory(
                                currentLabNumber,
                                currentDateString,
                                currentProblemNumber,
                                currentStudentRegistrationNumber
                        );
                    } catch (ParseException e) {
                        e.printStackTrace();
                        continue;
                    }

                    laboratory.setGrade(grade);
                    writer.write(laboratory.toCSV());
                    writer.newLine();
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean deleted = origFile.delete();
        if (!deleted) {
            System.err.println("origFile.delete()");
        }

        boolean renamed = tempFile.renameTo(origFile);
        if (!renamed) {
            System.err.println("tempFile.renameTo(origFile)");
        }
    }

    public Map<String, List<Laboratory>> findAll() {
        Map<String, List<Laboratory>> laboratoryMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = null;

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                int labNumber = 0;
                String dateString = tokens[1];
                int problemNumber = 0;
                float grade = 0;
                String studentRegistrationNumber = tokens[4];

                try {
                    labNumber = Integer.valueOf(tokens[0]);
                    problemNumber = Integer.valueOf(tokens[2]);
                    grade = Float.valueOf(tokens[3]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    continue;
                }

                Laboratory laboratory = null;
                try {
                    laboratory = new Laboratory(
                            labNumber,
                            dateString,
                            problemNumber,
                            studentRegistrationNumber
                    );
                } catch (ParseException e) {
                    e.printStackTrace();
                    continue;
                }

                laboratory.setGrade(grade);

                if (laboratoryMap.get(laboratory.getStudentRegistrationNumber()) == null) {
                    List<Laboratory> laboratoryList = new ArrayList<>();
                    laboratoryList.add(laboratory);
                    laboratoryMap.put(
                            laboratory.getStudentRegistrationNumber(),
                            laboratoryList
                    );
                } else {
                    laboratoryMap.get(laboratory.getStudentRegistrationNumber()).add(laboratory);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return laboratoryMap;
    }
}
