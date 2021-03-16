package view;

import java.awt.Container;
import java.util.List;

import javax.accessibility.AccessibleContext;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import model.Task;

public class ViewPert extends FunctionView {

	private static final long serialVersionUID = 1L;

	public ViewPert(List<List<Object>> listInfosGraphs, int tamponsX, int heightFrame, List<Task> allTask) {
		


		JFrame frame = this.addCircle(listInfosGraphs, tamponsX, heightFrame);
		
		JMenuBar menuBar = addMenuBar(frame);
		
		
		JMenu file = addMenu(menuBar, "Fichier");
		addMenuItem(allTask,file,"Nouveau", frame);
		addMenuItem(allTask,file,"Accueil", frame);
		addSeparator(file);
		addMenuItem(allTask,file,"Quitter", frame);
		

	}

}
