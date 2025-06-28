
import java.util.*;

class Student {
    String rollNumber, name;
    Map<String, Double> subjectGrades;

    Student(String rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.subjectGrades = new HashMap<>();
    }

    void addGrade(String subject, double grade) {
        subjectGrades.put(subject, grade);
    }

    double calculateAverage() {
        if (subjectGrades.isEmpty()) return 0;
        double total = 0;
        for (double grade : subjectGrades.values()) {
            total += grade;
        }
        return total / subjectGrades.size();
    }

    public String toString() {
        return rollNumber + " | " + name + " | Average Grade: " + String.format("%.2f", calculateAverage());
    }
}

public class StudentGradeTracker {
    static Scanner sc = new Scanner(System.in);
    static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==== Student Grade Tracker Menu ====");
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. Display All Students");
            System.out.println("4. Search Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> addGrade();
                case 3 -> displayStudents();
                case 4 -> searchStudent();
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void addStudent() {
        System.out.print("Enter Roll Number: ");
        String roll = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        students.add(new Student(roll, name));
        System.out.println("Student added.");
    }

    static void addGrade() {
        System.out.print("Enter Roll Number: ");
        String roll = sc.nextLine();
        Student student = findStudent(roll);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.print("Enter Subject: ");
        String subject = sc.nextLine();
        System.out.print("Enter Grade: ");
        double grade = sc.nextDouble();
        sc.nextLine();
        student.addGrade(subject, grade);
        System.out.println("Grade added.");
    }

    static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records available.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    static void searchStudent() {
        System.out.print("Enter Roll Number: ");
        String roll = sc.nextLine();
        Student student = findStudent(roll);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }

    static Student findStudent(String roll) {
        for (Student student : students) {
            if (student.rollNumber.equals(roll)) {
                return student;
            }
        }
        return null;
    }
}
