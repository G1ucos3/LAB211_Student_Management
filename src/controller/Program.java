package controller;

import common.*;
import java.util.ArrayList;
import model.Student;
import view.Menu;

/**
 *
 * @author ASUS
 */
public class Program extends Menu<String> {

    static String[] mChoice = {"Create", "Find and Sort", "Update/Delete", "Report", "Exit"};
    Manage manage;
    Validate validate;
    ArrayList st;

    public Program() {
        super("WELCOME TO STUDENT MANAGEMENT", mChoice);
        manage = new Manage();
        validate = new Validate();
        st = new ArrayList<Student>();
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                manage.createStudent(st);
                break;
            case 2:
                manage.findAndSort(st);
                break;
            case 3:
                manage.updateOrDelete(st);
                break;
            case 4:
                manage.report(st);
                break;
            default:
                System.out.println("Exit!");
                System.exit(0);
        }
    }
}
