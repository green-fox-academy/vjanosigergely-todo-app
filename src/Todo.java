import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Todo {

  public static void main(String[] args) {

    Todo todo = new Todo();
    List<String> tasks = new ArrayList<>();
    Path filepath = Paths.get("C:\\Users\\vjano\\greenfox\\vjanosigergely-todo-app\\TODO_list.txt");

    try {
      tasks = Files.readAllLines(filepath);
    } catch (IOException e) {
      System.out.println("Unable to read file: TODO-list.txt");
    }

    if (args.length == 0) {
      System.out.println("Command Line Todo application");
      System.out.println("=============================");
      System.out.println();
      System.out.println("Command line arguments: \n" +
          "\t  -l   Lists all the tasks \n" +
          "\t  -a   Adds a new task \n" +
          "\t  -r   Removes an task \n" +
          "\t  -c   Completes an task \n");

    } else if (args[0].equals("l")) {
      todo.listTasks(tasks);
    } else if (args[0].equals("a")) {
      try {
        String taskToAdd = args[1];
        Files.write(filepath, todo.addNew(tasks, taskToAdd));
      } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("Unable to add: no task provided");
      } catch (IOException e) {

      }
    } else if (args[0].equals("r")) {
      try {
        String indexOfTaskToRemove = args[1];
        int index = Integer.parseInt(indexOfTaskToRemove);
        Files.write(filepath, todo.remove(tasks, index));

      } catch (IOException e) {

      } catch (NumberFormatException n) {
        System.out.println("Unable to remove: index is not a number");

      } catch (Exception e) {
        if (args.length == 1) {
          System.out.println("Unable to remove: no index provided");
        } else if (Integer.parseInt(args[1]) > tasks.size()) {
          System.out.println("Unable to remove: index is out of bound");
        }

      }
    } else if (args[0].equals("c")) {
      try {
        String taskToComplete = args[1];
        int index = Integer.parseInt(taskToComplete);
        Files.write(filepath, todo.complete(tasks, index));
      } catch (IOException e) {

      } catch (NumberFormatException n) {
        System.out.println("Unable to check: index is not a number");

      } catch (Exception e) {
        if (args.length == 1) {
          System.out.println("Unable to check: no index provided");
        } else if (Integer.parseInt(args[1]) > tasks.size()) {
          System.out.println("Unable to check: index is out of bound");
        }

      }
    } else {

      System.out.println("Unsupported argument");

      System.out.println("Command Line Todo application");
      System.out.println("=============================");
      System.out.println();
      System.out.println("Command line arguments: \n" +
          "\t  -l   Lists all the tasks \n" +
          "\t  -a   Adds a new task \n" +
          "\t  -r   Removes an task \n" +
          "\t  -c   Completes an task \n");
    }
  }

  public void listTasks(List<String> tasks) {
    if (tasks.size() == 0) {
      System.out.println("No todos for today! :)");
    } else {
      for (int i = 0; i < tasks.size(); i++) {
        System.out.println(i + 1 + " - " + tasks.get(i));
      }
    }
  }

  public List<String> addNew(List<String> tasks, String taskToAdd) {
    String stringToAdd = "[ ] " + taskToAdd;
    tasks.add(stringToAdd);
    return tasks;
  }

  public List<String> remove(List<String> tasks, int index) {
    tasks.remove(index - 1);
    return tasks;
  }

  public List<String> complete(List<String> tasks, int index) {
    String completed = tasks.get(index - 1).replaceFirst(" ", "X");
    tasks.set(index - 1, completed);
    return tasks;
  }


}

//Command Line Todo application
//=============================
//
//Command line arguments:
//    -l   Lists all the tasks
//    -a   Adds a new task
//    -r   Removes an task
//    -c   Completes an task