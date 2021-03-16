package controller;

import javax.swing.*;

import view.ViewHome;
import model.Task;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ListenerAddTache implements MouseListener {
	
	private List<Task> allTache;
	private JFrame frame;
	private List<JTextField> textFields;
	private List<JLabel> labels;
	private JTable table;

	public ListenerAddTache(List<Task> allTache, JFrame frame) {
		this.allTache = allTache;
		this.frame = frame;
	}

	public ListenerAddTache(List<Task> allTache, JFrame frame, List<JTextField> textFields,List<JLabel> labels,JTable table) {
		this.allTache = allTache;
		this.frame = frame;
		this.textFields = textFields;
		this.labels = labels;
		this.table = table;
	}

    @Override
    public void mouseClicked(MouseEvent e) {
    }

	@Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof JButton) {
        	JButton jb = (JButton) e.getSource();
            switch (jb.getText()) {
                case "Ajouter tache":
                	addTache();
                    break;
                case "Annuler":
                	cancel();
                    break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public void addTache() {
		if (checkNumerosTache() && checkNomTache() && checkDureeTache()) {
			List<Integer> predecesseurs = getPredecesseurCheck();
			Task task = new Task(Integer.parseInt(textFields.get(0).getText()),
					Integer.parseInt(textFields.get(2).getText()), textFields.get(1).getText(), predecesseurs);
			allTache.add(task);
			frame.dispose();
			new ViewHome(allTache);
		}
	}

	private List<Integer> getPredecesseurCheck() {
		List<Integer> predecesseurs = new ArrayList<Integer>();
		for (int i = 0; i < table.getRowCount(); i++) {
			boolean isChecked = (Boolean) table.getValueAt(i, 4);

			if (isChecked) {
				predecesseurs.add((Integer) Integer.parseInt((String) table.getValueAt(i, 0)));
			}
		}
		if(predecesseurs.size() == 0){
			predecesseurs = null;
		}
		return predecesseurs;
	}

	private boolean checkDureeTache() {
		String stringDureeTache = textFields.get(2).getText();
		if (!stringDureeTache.equals(null) && !stringDureeTache.equals("")) {
			try {
				int dureeTache = Integer.parseInt(stringDureeTache);
				if(dureeTache > 0){
					return true;
				}
				labels.get(0).setText("La duree de la tache doit être supérieur à 0.");
				return false;
			} catch (Exception e) {
				labels.get(0).setText("La duree de la tache doit être un chiffre.");
				return false;
			}
		}
		labels.get(0).setText("Le champ duree de la tache doit être rempli.");
		return false;
	}

	private boolean checkNomTache() {
		String stringNomTache = textFields.get(1).getText();

		if (!stringNomTache.equals(null) && !stringNomTache.equals("")) {
			return true;
		}
		labels.get(0).setText("Le champ nom de la tache doit être rempli.");
		return false;
	}

	private boolean checkNumerosTache() {
		String stringNumerosTache = textFields.get(0).getText();

		if (!stringNumerosTache.equals(null) && !stringNumerosTache.equals("")) {
			try {
				int numberTask = Integer.parseInt(stringNumerosTache);
				if(numberTask >= 0){
					for (Task task : allTache) {
						if (task.getNumber() == numberTask) {
							labels.get(0).setText("Le numeros de la tache existe déja.");
							return false;
						}
					}
					return true;
				}
				labels.get(0).setText("Le numeros de la tache doit être supérieur ou égale à 0.");
				return false;
			} catch (Exception e) {
				labels.get(0).setText("Le numeros de la tache doit être un chiffre.");
				return false;
			}
		}
		labels.get(0).setText("Le champ numero de la tache doit être rempli.");
		return false;
	}

	private void cancel() {
		frame.dispose();
		new ViewHome(allTache);
	}
}