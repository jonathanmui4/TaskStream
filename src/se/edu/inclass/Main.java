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

        System.out.println("Printing sorted deadlines");
        printDeadlinesUsingStream(tasksData);

        ArrayList<Task> filteredList = filterTasksByString(tasksData, "11");
        printData(filteredList);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        System.out.println("Total number of deadlines: " + countDeadlinesUsingStream(tasksData));
        //printData(tasksData);
        printDatawithStreams(tasksData);
    }

    public static ArrayList<Task> filterTasksByString(ArrayList<Task> tasksData, String s) {
        ArrayList<Task> filteredList;
        filteredList = (ArrayList<Task>) tasksData.stream()
                .filter((t) -> t.getDescription().contains(s))
                .collect(Collectors.toList());

        return filteredList;
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

    public static void printDeadlinesUsingStream(ArrayList<Task> tasks) {
        tasks.stream()
                .filter((t) -> t instanceof Deadline)
                .sorted((a, b) -> a.getDescription().toLowerCase().compareTo(b.getDescription().toLowerCase()))
    }
}
