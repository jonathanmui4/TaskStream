package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        System.out.println("Printing deadlines");
        //printDeadlines(tasksData);
        printDeadlineUsingStream(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        System.out.println("Total number of deadlines: " + countDeadlinesUsingStream(tasksData));
        //printData(tasksData);
        printDatawithStreams(tasksData);
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    private static int countDeadlinesUsingStream(ArrayList<Task> tasksData) {
        int count = 0;
        count = (int) tasksData.stream()
                .filter((t) -> t instanceof Deadline)
                .count();
        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        System.out.println("Printing Data by looping");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDatawithStreams(ArrayList<Task> tasksData) {
        System.out.println("Printing data with streams");
        tasksData.stream() //Convert data to stream
                .forEach(
                        System.out::println
                ); //terminal operator
    }

    //Non-stream version of printDeadlineUsingStream
    public static void printDeadlines(ArrayList<Task> tasksData) {
        ArrayList<Task> deadlines = new ArrayList<Task>();
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                deadlines.add(t);
                System.out.println(t);
            }
        }
    }

    public static void printDeadlineUsingStream(ArrayList<Task> tasksData) {
        System.out.println("Print Deadlines using stream");
        tasksData.stream()
                .filter((t) -> t instanceof Deadline) //filtering operation using lambda
                .forEach(System.out::println);
    }
}
