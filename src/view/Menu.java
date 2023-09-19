package view;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ASUS
 * @param <T>
 */
public abstract class Menu<T> {

    protected String title;
    protected ArrayList<T> mChoice;

    public Menu() {
        mChoice = new ArrayList<>();
    }

    public Menu(String tl, String[] mc) {
        title = tl;
        mChoice = new ArrayList<>();
        for (String s : mc) {
            mChoice.add((T) s);
        }
    }

    public void display() {
        System.out.println(title);
        System.out.println("-----------------------------");
        for (int i = 0; i < mChoice.size(); i++) {
            System.out.println((i + 1) + "." + mChoice.get(i));
        }
        System.out.println("-----------------------------");
    }

    public int getChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        return choice;
    }

    public abstract void execute(int choice);

    public void run() {
        int choice;
        do {
            display();
            choice = getChoice();
            execute(choice);
        } while (choice > 0 && choice < mChoice.size());

    }
}
