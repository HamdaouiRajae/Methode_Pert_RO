import java.util.ArrayList;
import java.util.List;
import java.util.*;

import model.Task;
import view.ViewHome;

public class Main {

	private static List<Task> allTask = new ArrayList<Task>();

	public static void main(String[] args) {
		Task task1 = new Task(1,4,"a",null);
		Task task2 = new Task(2,2,"b",null);
	
		
		List<Integer> l2 = new ArrayList<>();
		l2.add(1);
		Task task3 = new Task(3,1,"c",l2);
		
		List<Integer> l3 = new ArrayList<>();
		l3.add(1);
		l3.add(2);
		Task task4 = new Task(4,1,"d",l3);
		
		List<Integer> l4 = new ArrayList<>();
		l4.add(1);
		Task task5 = new Task(5,2,"e",l4);
		
		List<Integer> l5 = new ArrayList<>();
		l5.add(3);
		Task task6 = new Task(6,2,"f",l5);
		
		List<Integer> l6 = new ArrayList<>();
		l6.add(4);
		l6.add(6);
		Task task7 = new Task(7,2,"g",l6);
		
		List<Integer> l7 = new ArrayList<>();
		l7.add(5);
		Task task8 = new Task(8,10,"h",l7);
		
		List<Integer> l8 = new ArrayList<>();
		l8.add(7);
        Task task9 = new Task(9,4,"i",l8);
        
		List<Integer> l9 = new ArrayList<>();
		l9.add(9);
		l9.add(8);

		Task task10 = new Task(10,1,"j",l9);

		/*List<Integer> l10 = new ArrayList<>();
		l10.add(5);
		l10.add(6);
		l10.add(7);
		Task task10 = new Task(10,240,"test1",l10);
		
		List<Integer> l11 = new ArrayList<>();
		l11.add(10);
		l11.add(8);
		Task task11 = new Task(11,360,"test1",l11);
		
		List<Integer> l12 = new ArrayList<>();
		l12.add(8);
		l12.add(10);
		Task task12 = new Task(12,240,"test1",l12);*/
		
		allTask.add(task1);
		allTask.add(task2);
		allTask.add(task3);
		allTask.add(task4);
		allTask.add(task5);
		allTask.add(task6);
		allTask.add(task7);
		allTask.add(task8);
		allTask.add(task9);
		allTask.add(task10);
		/*allTask.add(task11);
		allTask.add(task12);*/

		new ViewHome(allTask);
	}


}

