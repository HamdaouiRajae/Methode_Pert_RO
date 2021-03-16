package view;

import javax.swing.JFrame;
import javax.swing.JTable;

import java.util.List;

import model.Task;

public class ViewHome extends FunctionView {

	private static final long serialVersionUID = 1L;
	
	public ViewHome(List<Task> allTaches) {
		JFrame frame = this.addFrame(100, 100, 600, 300,"Tableau de Pert",true);
		
		this.addLabel(217, 11, 151, 14,frame, "Diagramme de PERT");
		this.addButtonHome(48, 203, 159, 23,allTaches,frame, "Ajouter une t\u00E2che");
		this.addButtonHome(353, 203, 159, 23,allTaches,frame, "Lancer le diagramme");
		
		String[] columnTablePert = new String[] { "Numeros", "Nom de t\u00E2che", "Dur\u00E9e en jours", "T\u00E2che ant\u00E9rieurs" };
		JTable tablePert = this.addTablePert(columnTablePert);
		
		addRow(allTaches, tablePert);
		this.addScrollPane(tablePert,0, 42, 584, 134,frame);
		
	}

	private void addRow(List<Task> tasks, JTable tablePert) {
		for (Task task : tasks) {
			String stringPredecesseur = "";
			if(task.getPredecessor() != null && task.getPredecessor().size() != 0){
				for (int predecesseur : task.getPredecessor()) {
					stringPredecesseur += predecesseur + ";";
				}
				stringPredecesseur = stringPredecesseur.substring(0,stringPredecesseur.length()-1);
			}
			String numero = Integer.toString(task.getNumber());
			String duree = Integer.toString(task.getTime());
			this.addRowInTablePert(tablePert,numero, task.getName(), duree, stringPredecesseur);
		}
	}

}
