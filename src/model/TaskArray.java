package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskArray {
	private List<Task> tasksFirst = new ArrayList<Task>();
	List<Map<String, Object>> taskArray = new ArrayList<Map<String, Object>>();
	private int numberColumn;

	public TaskArray() {
		super();
	}

	public List<Task> getTachesFirst() {
		return tasksFirst;
	}

	public void setTachesFirst(List<Task> TasksFirst) {
		this.tasksFirst = TasksFirst;
	}

	public List<Map<String, Object>> getTableauTache() {
		return taskArray;
	}

	public void setTableauTache(List<Map<String, Object>> taskArray) {
		this.taskArray = taskArray;
	}

	public int getNbrColonne() {
		return numberColumn;
	}

	public void setNbrColonne(int numberColumn) {
		this.numberColumn = numberColumn;
	}

	public void generateTableau() {
		int colonne = 1;
		Map<String, Object> colonneTache = new HashMap<String, Object>();
		List<Integer> predeccesseursDejaParcouru = new ArrayList<>();

		for (Task task : tasksFirst) {
			task.setFirstDelay(task.getTime());
			predeccesseursDejaParcouru.add(task.getNumber());
		}

		colonneTache.put("tasks", tasksFirst);
		colonneTache.put("colonne", colonne);
		taskArray.add(colonneTache);
		recursiveTableau(colonne + 1, tasksFirst, predeccesseursDejaParcouru);

	}

	private void recursiveTableau(int colonne, List<Task> tasks, List<Integer> predeccesseursDejaParcouru) {
		List<Task> nextTaches = new ArrayList<Task>();
		Map<String, Object> colonneTache = new HashMap<String, Object>();
		List<Integer> tmpPredeccesseursDejaParcouru = new ArrayList<Integer>();

		for (Task task : tasks) {
			if (task.getTaskSuccessors().size() != 0) {
				for (Task successeur : task.getTaskSuccessors()) {
					if (successeur.isLastPredecessor(predeccesseursDejaParcouru, task.getNumber())) {
						if (successeur.isNotContainIn(nextTaches)) {
							successeur.modifyFirstDelay();
							nextTaches.add(successeur);
							tmpPredeccesseursDejaParcouru.add(successeur.getNumber());
						}
					}
				}
			}
		}
		if (tmpPredeccesseursDejaParcouru.size() != 0) {
			predeccesseursDejaParcouru.addAll(tmpPredeccesseursDejaParcouru);
			colonneTache.put("tasks", nextTaches);
			colonneTache.put("colonne", colonne);
			taskArray.add(colonneTache);

			numberColumn = colonne;
		}
		if (nextTaches.size() != 0) {
			recursiveTableau(colonne + 1, nextTaches, predeccesseursDejaParcouru);
		}
	}
	
	public void addFirstTask(List<Task> allTaches) {
		Task first = new Task(0, 0, "Debut", null);
		List<Integer> listFirstNumber = new ArrayList<>();
		List<Task> listFirstTask = new ArrayList<>();
		allTaches.add(first);
		listFirstNumber.add(first.getNumber());
		listFirstTask.add(first);
		
		for (Task task : allTaches) {
			if (task.getTaskPredecessor().size() == 0 && task.getPredecessor() == null) {
				if (task.getNumber() != 0) {
					task.setPredecessor(listFirstNumber);
					task.setTaskPredecessorWithAllTask(listFirstTask);
				} else {
					this.tasksFirst.add(task);
				}
			}
		}
	}
	
	public void addLastTask(List<Task> allTask) {
		List<Task> listAllLastTask = new ArrayList<Task>();
		List<Integer> listAllLastNumberTask = new ArrayList<Integer>();
		Task lastTask = new Task(-1, 0, "Fin",null);

		List<Task> lastTaskList = new ArrayList<>();
		lastTaskList.add(lastTask);
		for (Task allLastTask: allTask) {
			if(allLastTask.getTaskSuccessors().size() == 0){
				allLastTask.setTaskSuccessors(lastTaskList);

				listAllLastTask.add(allLastTask);
				listAllLastNumberTask.add(allLastTask.getNumber());

			}
		}
		allTask.add(lastTask);
		lastTask.setTaskPredecessor(listAllLastTask);
		lastTask.setPredecessor(listAllLastNumberTask);
	}

}
