package controller;

import javax.swing.*;

import model.Task;
import view.ViewHome;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ListenerPert implements MouseListener {
	
	private List<Task> allTaches;
	private JFrame frame;

	public ListenerPert(List<Task> allTaches,JFrame frame) {
		this.allTaches = allTaches;
		this.frame = frame;
	}

    @Override
    public void mouseClicked(MouseEvent e) {
    }

	@Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof JMenuItem) {
        	JMenuItem jb = (JMenuItem) e.getSource();
            switch (jb.getText()) {
                case "Nouveau":
                	newPert();
                    break;
                case "Accueil":
                	home();
                    break;
                case "Quitter":
                	quit();
                    break;
            }
        }
    }

	private void newPert() {
		frame.dispose();
		List<Task> allTaches = new ArrayList<>();
		new ViewHome(allTaches);
	}
	
	private void home() {
		frame.dispose();
		new ViewHome(allTaches);
	}
	
    private void quit() {
		System.exit(0);
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

}