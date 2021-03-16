package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.Task;

public class ViewAddTask extends FunctionView {


	private static final long serialVersionUID = 1L;

	public ViewAddTask(List<Task> allTaches) {
		
		JFrame frame = this.addFrame(100, 100, 670, 463,"Ajouter tâche",true);
		
		this.addLabel(295, 20, 80, 14, frame, "Ajouter tâche");
		this.addLabel(34, 89, 144, 14, frame, "Numeros de la tache :");
		this.addLabel(34, 135, 144, 14, frame, "Nom de la tache :");
		this.addLabel(34, 180, 144, 14, frame, "Duree de la tache : ");
		this.addLabel(34, 226, 89, 14, frame, "Predesseurs : ");
		this.addLabel(310, 180, 66, 14, frame, "( En jour )");
		
		List<JLabel> labels = new ArrayList<JLabel>();
		labels.add(this.addLabel(60, 41, 511, 37, frame, ""));
		
		List<JTextField> textFields = new ArrayList<JTextField>();
		textFields.add(this.addTextField(175, 86, 123, 20,10, frame));
		textFields.add(this.addTextField(175, 132, 123, 20,10, frame));
		textFields.add(this.addTextField(175, 177, 123, 20,10, frame));
		
		String[] columnTablePert = new String[] { "Numeros", "Nom de t\u00E2che", "Dur\u00E9e en jours", "T\u00E2che ant\u00E9rieurs", "Cocher" };
		JTable tablePert = this.addTablePertBoolean(columnTablePert);
		
		addRow(allTaches,tablePert);
		
		this.addButtonAddTache(465, 361, 137, 23, allTaches, frame,labels,textFields,tablePert, "Ajouter tache");
		this.addButtonAddTache(45, 361, 137, 23, allTaches, frame, "Annuler");
		
		addScrollPane(tablePert, 175, 225, 447, 99, frame);
		
	}

	private void addRow(List<Task> allTaches,JTable table) {
		for (Task task : allTaches) {
			String stringPredecesseur = "";
			if (task.getPredecessor() != null) {
				for (int predecesseur : task.getPredecessor()) {
					stringPredecesseur += predecesseur + ";";
				}
				stringPredecesseur = stringPredecesseur.substring(0, stringPredecesseur.length() - 1);
			}
			String numero = Integer.toString(task.getNumber());
			String duree = Integer.toString(task.getTime());
			this.addRowInTablePertBoolean(table, numero, task.getName(), duree, stringPredecesseur);
		}
	}

}
