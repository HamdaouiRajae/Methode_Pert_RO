package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.*;

import model.Task;
import model.TaskArray;
import view.ViewPert;

public class CtrlPert {
	
	@SuppressWarnings("unchecked")
	public CtrlPert(List<Task> allTask){
		TaskArray taskFirst = new TaskArray();
		
		taskFirst.addFirstTask(allTask);
		
		
		for (Task task : allTask) {
			task.setTaskPredecessorWithAllTask(allTask);
			task.setTaskSuccessorsWithAllTask(allTask);
		}
		
		taskFirst.addLastTask(allTask);

		taskFirst.generateTableau();



		for (int i = taskFirst.getNbrColonne() - 1; i >= 0; i--) {
			for (Task task : (List<Task>) taskFirst.getTableauTache().get(i).get("tasks")) {
				task.setLastDelay(task);
			}
		}

		int heightFrame = 0;
		int tailleX = 50;
		int tailleY = 50;
		int tailleRayon = 120;
		int tailleXTache = tailleRayon + tailleX;
		int tailleYTache = tailleRayon + tailleY;
		int tamponsX = 0;
		int tamponsY = 0;
		
		List<List<Object>> listInfosGraphs = new ArrayList<>();

		for (Map<String, Object> tableau : taskFirst.getTableauTache()) {
			tamponsY = 0;

			for (Task task : (List<Task>) tableau.get("tasks")) {

				setXYentrantEtSortant(tailleX, tailleY, tailleRayon, tamponsX, tamponsY, task);

				List<Object> InfosGraphs = createCircleTacheAvecLesTraits(tailleX, tailleY, tailleRayon, tamponsX, tamponsY, task);

				listInfosGraphs.add(InfosGraphs);
				tamponsY = tamponsY + tailleYTache;

			}

			if (tamponsY > heightFrame) {
				heightFrame = tamponsY;
			}

			tamponsX = tamponsX + tailleXTache;

		}
		int numberAllTask = allTask.size();
		allTask.remove(numberAllTask - 1);
		allTask.remove(numberAllTask - 2);
		new ViewPert(listInfosGraphs, tamponsX, heightFrame, allTask);
	}





	private void setXYentrantEtSortant(int tailleX, int tailleY, int tailleRayon, int tamponsX, int tamponsY, Task task) {
		task.setXInFlow(tailleX + tamponsX);
		task.setYInFlow(tailleY + tamponsY + tailleRayon / 2);
		task.setXOutFlow(tailleX + tamponsX + tailleRayon);
		task.setYOutFlow(tailleY + tamponsY + tailleRayon / 2);
	}

	private List<Object> createCircleTacheAvecLesTraits(int tailleX, int tailleY, int tailleRayon, int tamponsX,
			int tamponsY, Task task) {
		List<List<Integer>> allTrait = new ArrayList<List<Integer>>();
		createTraitEntreLesTaches(task, allTrait);

		List<Object> InfosGraphs = new ArrayList<Object>();
		createCircle(tailleX, tailleY, tailleRayon, tamponsX, tamponsY, task, allTrait, InfosGraphs);
		return InfosGraphs;
	}

	private void createCircle(int tailleX, int tailleY, int tailleRayon, int tamponsX, int tamponsY, Task task,
			List<List<Integer>> allTrait, List<Object> InfosGraphs) {
		InfosGraphs.add(tailleX + tamponsX);
		InfosGraphs.add(tailleY + tamponsY);
		InfosGraphs.add(tailleRayon);
		if (task.getNumber() == 0) {
			InfosGraphs.add("Debut");
		} else if(task.getNumber() == -1){
			InfosGraphs.add("fin");
		} else{
			InfosGraphs.add(task.getNumber());
		}
		InfosGraphs.add(task.getLastDelay());
		InfosGraphs.add(task.getFirstDelay());
		InfosGraphs.add(allTrait);
	}

	private void createTraitEntreLesTaches(Task task, List<List<Integer>> allTrait) {
		for (Task predecesseurs : task.getTaskPredecessor()) {
			List<Integer> trait = new ArrayList<>();
			trait.add(predecesseurs.getXOutFlow());
			trait.add(predecesseurs.getYOutFlow());
			trait.add(task.getXInFlow());
			trait.add(task.getYInFlow());
			
			if (task.checkIsRedLine(task,predecesseurs)) {
				trait.add(1);
			} else {
				trait.add(0);
			}
			allTrait.add(trait);
		}
	}

}
