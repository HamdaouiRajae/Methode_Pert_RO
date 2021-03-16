package model;

import java.util.ArrayList;
import java.util.List;

public class Task {
	private int number;
	private int time;
	private String name;
	private List<Integer> predecessor;

	// Après avoir récuperer tout les resultats
	private int lastDelay;
	private int firstDelay;
	private List<Task> taskPredecessor = new ArrayList<>();
	private List<Task> taskSuccessors = new ArrayList<>();

	// Pour le graph
	private int xInFlow;
	private int yInFlow;
	private int xOutFlow;
	private int yOutFlow;

	public Task(int number, int time, String name, List<Integer> predecessor) {
		super();
		this.number = number;
		this.time = time;
		this.name = name;
		this.predecessor = predecessor;
	}

	public Task() {
		super();
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(List<Integer> predecessor) {
		this.predecessor = predecessor;
	}

	public int getLastDelay() {
		return lastDelay;
	}

	public void setLastDelay(Task task) {
		if (task.getTaskSuccessors().size() == 0) {
			this.lastDelay = task.getFirstDelay();
		} else {
			int delaiPlusTard = task.getTaskSuccessors().get(0).getFirstDelay() - task.getTaskSuccessors().get(0).getTime();
			for (Task successeur : task.getTaskSuccessors()) {
				int tmpDelaiPlusTard = successeur.getFirstDelay() - successeur.getTime();
				if (tmpDelaiPlusTard < delaiPlusTard) {
					delaiPlusTard = tmpDelaiPlusTard;
				}
			}
			this.lastDelay = delaiPlusTard;
		}
	}

	public int getFirstDelay() {
		return firstDelay;
	}

	public void setFirstDelay(int firstDelay) {
		this.firstDelay = firstDelay;
	}

	public List<Task> getTaskPredecessor() {
		return taskPredecessor;
	}

	public int getXInFlow() {
		return xInFlow;
	}

	public void setXInFlow(int xInFlow) {
		this.xInFlow = xInFlow;
	}

	public int getYInFlow() {
		return yInFlow;
	}

	public void setYInFlow(int yInFlow) {
		this.yInFlow = yInFlow;
	}

	public int getXOutFlow() {
		return xOutFlow;
	}

	public void setXOutFlow(int xOutFlow) {
		this.xOutFlow = xOutFlow;
	}

	public int getYOutFlow() {
		return yOutFlow;
	}

	public void setYOutFlow(int yOutFlow) {
		this.yOutFlow = yOutFlow;
	}
	
	public void setTaskPredecessor(List<Task> taskPredecessor) {
		this.taskPredecessor = taskPredecessor;
	}


	public void setTaskPredecessorWithAllTask(List<Task> allTache) {
		List<Task> taskPredecessor = new ArrayList<Task>();
		if (this.getPredecessor() != null) {
			for (Integer intTaskPredecessor : this.getPredecessor()) {
				for (Task task : allTache) {
					if (task.getNumber() == intTaskPredecessor) {
						taskPredecessor.add(task);
					}
				}
			}
		}
		this.taskPredecessor = taskPredecessor;
	}

	public List<Task> getTaskSuccessors() {
		return taskSuccessors;
	}
	
	public void setTaskSuccessors(List<Task> taskSuccessors) {
		this.taskSuccessors = taskSuccessors;
	}

	public void setTaskSuccessorsWithAllTask(List<Task> allTache) {
		List<Task> taskSuccessors = new ArrayList<Task>();
		for (Task task : allTache) {
			if (task.getPredecessor() != null) {
				for (Integer intTaskSuccessors : task.getPredecessor()) {
					if (intTaskSuccessors == this.getNumber()) {
						taskSuccessors.add(task);
					}
				}
			}
		}
		this.taskSuccessors = taskSuccessors;
	}

	public boolean isLastPredecessor(List<Integer> predecessorsAlreadyTested, int taskNumber) {
		int numberOfPredecessorsTested = 0;

		for (Task predecesseurs : this.taskPredecessor) {
			for (int numberPredecessors : predecessorsAlreadyTested) {
				if (numberPredecessors == predecesseurs.getNumber()) {
					numberOfPredecessorsTested++;
				}
			}
		}

		if (this.taskPredecessor.size() == numberOfPredecessorsTested) {
			return true;
		}

		return false;
	}

	public boolean isNotContainIn(List<Task> tasks) {
		for (Task task : tasks) {
			if (task.getNumber() == this.number) {
				return false;
			}
		}
		return true;
	}

	public void modifyFirstDelay() {
		firstDelay = this.getTime() + this.getTaskPredecessor().get(0).getFirstDelay();
		for (Task predecessors : this.getTaskPredecessor()) {
			int timeWithPredecessor = this.getTime() + predecessors.getFirstDelay();
			if (timeWithPredecessor > firstDelay) {
				firstDelay = timeWithPredecessor;
			}
		}
	}

	public boolean checkIsRedLine(Task task, Task predecesseurs) {
		int diffLastDelay = task.getLastDelay() - predecesseurs.getLastDelay();
		if(task.getFirstDelay() == task.getLastDelay() && predecesseurs.getFirstDelay() == predecesseurs.getLastDelay() && diffLastDelay == task.getTime()){
			return true;
		}
		return false;
	}

}
