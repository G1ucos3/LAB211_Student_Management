package common;

import java.util.*;
import model.Student;
import common.Validate;
import model.Report;

/**
 *
 * @author ASUS
 */
public class Manage {

    public void createStudent(ArrayList<Student> st) {
        Validate validate = new Validate();
        while (true) {
            System.out.print("Enter id: ");
            String id = validate.checkString();
            System.out.print("Enter name student: ");
            String name = validate.checkString();
            if (!validate.checkIdExist(st, id, name)) {
                System.out.println("Id exist!");
                continue;
            }
            System.out.print("Enter semester: ");
            String semester = validate.checkString();
            System.out.print("Enter name course: ");
            String course = validate.checkCourse();
            if (validate.checkInfo(st, id, name, semester, course)) {
                st.add(new Student(id, name, semester, course));
                System.out.println("Add success!");
            } else {
                System.out.println("Duplicate information!");
            }

            if (st.size() >= 2) {
                System.out.print("Do you want to continue (Y/N): ");
                if (!validate.checkInput("Y", "N")) {
                    break;
                }
            }
        }
    }

    public void printList(ArrayList<Student> st) {
        System.out.println("=====STUDENT LIST=====");
        for (Student student : st) {
            System.out.println(student.toString());
        }
    }

    public ArrayList<Student> findByName(ArrayList<Student> st) {
        ArrayList<Student> ls = new ArrayList<>();
        Validate validate = new Validate();
        System.out.println("Enter student name to search: ");
        String name = validate.checkString();
        for (Student student : st) {
            if ((student.getStudentName()).contains(name)) {
                ls.add(student);
            }
        }
        return ls;
    }

    public void findAndSort(ArrayList<Student> st) {
        if (st.isEmpty()) {
            System.out.println("List empty!");
            return;
        }
        ArrayList<Student> list = findByName(st);
        if (list.isEmpty()) {
            System.out.println("Not exist.");
        } else {
            Collections.sort(list);
            for (Student student : list) {
                System.out.println(student.toString());
            }
        }
    }

    public void updateOrDelete(ArrayList<Student> st) {
        Validate validate = new Validate();
        if (st.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        System.out.print("Enter id: ");
        String id = validate.checkString();
        ArrayList<Student> listStudentFindByName = getListStudentById(st, id);
        if (listStudentFindByName.isEmpty()) {
            System.out.println("Not found!");
            return;
        } else {
            Student student = listForFound(listStudentFindByName);
            System.out.print("Do you want to update (U) or delete (D) student: ");
            if (validate.checkInput("U", "D")) {
                System.out.print("Enter id: ");
                String idStudent = validate.checkString();
                System.out.print("Enter name student: ");
                String name = validate.checkString();
                System.out.print("Enter semester: ");
                String semester = validate.checkString();
                System.out.print("Enter name course: ");
                String course = validate.checkCourse();

                if (validate.checkInfo(st, id, name, semester, course)) {
                    student.setId(idStudent);
                    student.setStudentName(name);
                    student.setSemester(semester);
                    student.setCourseName(course);
                    System.out.println("Update success!");
                }
                return;
            } else {
                st.remove(student);
                int size = st.size();
                size --;
                System.out.println("Delete success!");
                return;
            }
        }
    }

    public Student listForFound(ArrayList<Student> st) {
        Validate validate = new Validate();
        System.out.println("List student found: ");
        int count = 1;
        for (Student student : st) {
            System.out.printf("%-10d%-15s%-15s%-15s\n", count,student.getStudentName(), student.getSemester(),student.getCourseName());
            count++;
        }
        System.out.print("Enter student: ");
        int choice = validate.checkLimit(1, st.size());
        return st.get(choice - 1);
    }

    public ArrayList<Student> getListStudentById(ArrayList<Student> st, String id) {
        ArrayList<Student> getListStudentById = new ArrayList<>();
        for (Student student : st) {
            if (id.equalsIgnoreCase(student.getId())) {
                getListStudentById.add(student);
            }
        }
        return getListStudentById;
    }

    public void report(ArrayList<Student> st) {
        Validate validate = new Validate();
        if (st.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        ArrayList<Report> report = new ArrayList<>();
        int size = st.size();
        for (int i = 0; i < size; i++) {
            int total = 0;
            for (Student student : st) {
                String id = student.getId();
                String courseName = student.getCourseName();
                String studentName = student.getStudentName();
                for (Student studentCountTotal : st) {
                    if (id.equalsIgnoreCase(studentCountTotal.getId())
                            && courseName.equalsIgnoreCase(studentCountTotal.getCourseName())) {
                        total++;
                    }
                }
                if (validate.checkReportExist(report, studentName,
                        courseName, total)) {
                    report.add(new Report(student.getStudentName(), studentName, total));
                }
            }
        }
        for (int i = 0; i < report.size(); i++) {
            System.out.printf("%-15s|%-10s|%-5d\n", report.get(i).getStudentName(),report.get(i).getCourseName(), report.get(i).getTotalCourse());
        }
    }
}
