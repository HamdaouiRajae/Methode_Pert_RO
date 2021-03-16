package view;

import java.awt.Dimension;
import java.awt.ScrollPane;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.ListenerAddTache;
import controller.ListenerHome;
import controller.ListenerPert;
import model.Task;

public abstract class FunctionView extends JFrame {

	private static final long serialVersionUID = 1L;

	public JFrame addCircle(List<List<Object>> listInfosGraphs, int widht, int height) {
		TacheGraph tg = new TacheGraph(listInfosGraphs);
		JFrame frame = new JFrame();

		ScrollPane scrollPane = new ScrollPane();
		tg.setPreferredSize(new Dimension(widht, height));

		scrollPane.add(tg);
		frame.add(scrollPane);
		frame.setSize(700, 700);
		frame.setVisible(true);
		return frame;
	}

	public JFrame addFrame(int x, int y, int w, int h, String s, boolean isNotGraph) {
		JFrame frame = new JFrame(s);
		if (isNotGraph) {
			frame.setLayout(null);
			frame.getContentPane().setLayout(null);
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(x, y, w, h);
		frame.setVisible(true);
		return frame;
	}

	public JButton addButtonAddTache(int x, int y, int w, int h, List<Task> allTaches, JFrame frame,
			List<JLabel> labels, List<JTextField> textFields, JTable table, String s) {
		JButton button = new JButton(s);
		ListenerAddTache ec = new ListenerAddTache(allTaches, frame, textFields, labels, table);
		button.addMouseListener(ec);
		button.setBounds(x, y, w, h);
		frame.getContentPane().add(button);
		return button;
	}

	public JButton addButtonAddTache(int x, int y, int w, int h, List<Task> allTaches, JFrame frame, String s) {
		JButton button = new JButton(s);
		ListenerAddTache ec = new ListenerAddTache(allTaches, frame);
		button.setBounds(x, y, w, h);
		button.addMouseListener(ec);
		frame.getContentPane().add(button);
		return button;
	}

	public JButton addButtonHome(int x, int y, int w, int h, List<Task> allTaches, JFrame frame, String s) {
		JButton button = new JButton(s);
		ListenerHome ec = new ListenerHome(allTaches, frame);
		button.setBounds(x, y, w, h);
		button.addMouseListener(ec);
		frame.getContentPane().add(button);
		return button;
	}

	public JLabel addLabel(int x, int y, int w, int h, JFrame frame, String s) {
		JLabel label = new JLabel(s);
		label.setBounds(x, y, w, h);
		frame.getContentPane().add(label);
		return label;
	}

	public JTextField addTextField(int x, int y, int w, int h, int column, JFrame frame) {
		JTextField textField = new JTextField();
		textField.setBounds(x, y, w, h);
		frame.getContentPane().add(textField);
		textField.setColumns(column);
		return textField;
	}

	public JTable addTablePert(String[] columnTable) {
		JTable table = new JTable(new DefaultTableModel(new Object[][] {}, columnTable));
		return table;
	}

	public JTable addTablePertBoolean(String[] columnTable) {
		DefaultTableModel model = new DefaultTableModel(new Object[][] {}, columnTable);
		JTable table = new JTable(model) {
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				default:
					return Boolean.class;
				}
			}

		};
		return table;
	}

	public void addRowInTablePert(JTable table, String column1, String column2, String column3, String column4) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[] { column1, column2, column3, column4 });
	}

	public void addRowInTablePertBoolean(JTable table, String column1, String column2, String column3, String column4) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[] { column1, column2, column3, column4, false });
	}

	public JScrollPane addScrollPane(JTable table, int x, int y, int w, int h, JFrame frame) {
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(x, y, w, h);
		frame.getContentPane().add(scrollPane);
		return scrollPane;
	}

	public JMenuBar addMenuBar(JFrame frame) {
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		return menuBar;
	}

	public JMenu addMenu(JMenuBar menuBar, String name) {
		JMenu file = new JMenu(name);
		menuBar.add(file);
		return file;
	}

	public void addSeparator(JMenu menuBar) {
		JSeparator separator = new JSeparator();
		menuBar.add(separator);
	}

	public void addMenuItem(List<Task> allTache, JMenu menu, String name, JFrame frame) {
		JMenuItem menuItem = new JMenuItem(name);
		menu.add(menuItem);
		ListenerPert ec = new ListenerPert(allTache, frame);
		menuItem.addMouseListener(ec);
		
	}

}
