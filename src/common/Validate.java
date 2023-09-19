package common;

import java.util.ArrayList;
import java.util.Scanner;
import model.*;

/**
 *
 * @author ASUS
 */
public class Validate {

    final static Scanner sc = new Scanner(System.in);

    public String checkString() {
        while (true) {
            String result = sc.nextLine();
            if (result.isBlank()) {
                System.err.println("Empty!");
                System.out.println("Enter again: ");
            } else {
                return result;
            }
        }
    }

    public boolean checkInput(String a, String b) {
        while (true) {
            String result = checkString();
            if (result.equalsIgnoreCase(a)) {
                return true;
            } else if (result.equalsIgnoreCase(b)) {
                return false;
            }
            System.err.println("Enter only " + a + " or " + b);
            System.out.print("Enter again: ");
        }
    }

    public boolean checkIdExist(ArrayList<Student> st, String id, String name) {
        for (Student student : st) {
            if (id.equalsIgnoreCase(student.getId())
                    && !name.equalsIgnoreCase(student.getStudentName())) {
                return false;
            }
        }
        return true;
    }

    public String checkCourse() {
        while (true) {
            String result = checkString();
            if (result.equalsIgnoreCase("Java")
                    || result.equalsIgnoreCase(".Net")
                    || result.equalsIgnoreCase("C/C++")) {
                return result;
            } else {
                System.err.println("There are only three courses: Java, .Net, C/C++");
                System.out.println("Enter agian: ");
            }
        }
    }

    public boolean checkInfo(ArrayList<Student> st, String id, String studentName, String semester, String courseName) {
        int size = st.size();
        for (Student student : st) {
            if (id.equalsIgnoreCase(student.getId())
                    && studentName.equalsIgnoreCase(student.getStudentName())
                    && semester.equalsIgnoreCase(student.getSemester())
                    && courseName.equalsIgnoreCase(student.getCourseName())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkReportExist(ArrayList<Report> r, String name, String course, int total) {
        for (Report report : r) {
            if (name.equalsIgnoreCase(report.getStudentName())
                    && course.equalsIgnoreCase(report.getCourseName())
                    && total == report.getTotalCourse()) {
                return false;
            }
        }
        return true;
    }
    
    public int checkLimit(int min, int max) {
        while (true) {
            try {
                int result = Integer.parseInt(sc.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();

                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Input number in rage [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }
}
