package Editor;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import java.awt.Color;

public class Editor {

	public JFrame frame;
	private JScrollPane OutputScroller;
	private static TextArea Output;
	private TextArea textArea;

	private File myFile;
	private String Title = "Brain F#CK interpreter";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// After everything before this is ready and all is caught up THEN this runs
		EventQueue.invokeLater(new Runnable() {
			// Runs the application and will act as our "new" main method
			public void run() {
				// Just to make sure the menu can actually open
				try {
					// makes it look nice
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

					// Magic happens that starts it all
					Editor window = new Editor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the application.

	public Editor() {
		initialize();
	}

	// Initialize the contents of the frame.

	private void initialize() {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setForeground(Color.DARK_GRAY);
		frame.setBackground(Color.DARK_GRAY);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				try {
					int input = JOptionPane.showConfirmDialog(null, "Do you want to save your file?!", "Yes NO?",
							JOptionPane.YES_NO_OPTION);
					if (input == JOptionPane.YES_OPTION) {
						saveFile();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		frame.setBounds(100, 100, 1000, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		frame.setTitle(Title + " New File");

		// Creates an area for the user to write to
		textArea = new TextArea();
		textArea.setBackground(Color.DARK_GRAY);
		textArea.setForeground(Color.LIGHT_GRAY);
		// Sets the font
		textArea.setFont(new Font("Verdana", Font.PLAIN, 14));
		// Adds all of the fun stuff...
		textArea.setText(
				"Hi, welcom to the interpreter to a language i cant even tell you the name of without cursing.\n");
		textArea.append(
				"And oh boy is it accurate. And well the name you may ask. ITs Brain and somthing that ryms with duck...\n");
		textArea.append("So here are the rules.\n");
		textArea.append("> Moves you to the righ\n");
		textArea.append(
				"< moves you to the left until you hit 0 in the array. oh yeah you have 1 array to work with from 0 to 255. good luck.\n");
		textArea.append("+ Adds 1 to what ever your pointer is on\n");
		textArea.append("- Does the opposate of addition\n");
		textArea.append("[ Starts a loop\n");
		textArea.append("] ends a loop only if the opinter it checks is = 0. yeah figure that on out huh...\n");
		textArea.append(
				". Well you enter what ever you want but all ur gonna get is hot garbage so just enter some ascii letter or sum and that will put it in your array\n");
		textArea.append(", displays what ever your pointing to in ascii...\n");
		textArea.append(
				"This program isnt all that difficult and you cal basically write all the commments you want and itll only pick up the useful stuff..\n");
		textArea.append(
				"I want you to know that even though this is hard it is also quite powerful. and to show you how powerful ill show you how to multiply.......\n");
		textArea.append(
				"+++[>++<-] = 3 X 2... yeah its not hard but idk how to even divide so good luck have fun dont die ill see you when you decide to kill me...\n");

		JScrollPane Scroller = new JScrollPane(textArea);
		frame.getContentPane().add(Scroller, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.DARK_GRAY);
		menuBar.setBackground(Color.LIGHT_GRAY);
		Scroller.setColumnHeaderView(menuBar);

		// Creates a file menu
		JMenu FileDD = new JMenu("File");
		FileDD.setBackground(Color.LIGHT_GRAY);
		menuBar.add(FileDD);

		// These are items inside the file menu
		JMenuItem OpenFileMnIt = new JMenuItem("Open File");
		// If the file is clicked this will run
		OpenFileMnIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openFile();
			}
		});
		FileDD.add(OpenFileMnIt);

		// Save file drop down
		JMenuItem SaveFileMnIt = new JMenuItem("Save File");
		SaveFileMnIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});
		FileDD.add(SaveFileMnIt);

		// Save ass drop down
		JMenuItem SaveAsFileMnIt = new JMenuItem("Save As File");
		SaveAsFileMnIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFileAs();
			}
		});
		FileDD.add(SaveAsFileMnIt);

		// Run button
		JButton btnRun = new JButton("Run");
		btnRun.setBackground(Color.LIGHT_GRAY);
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (textArea == null) {
						JOptionPane.showMessageDialog(null, "There is no code!",
								"Lack of code or anything for that matter.", JOptionPane.WARNING_MESSAGE);
						return;
					}
					saveFile();
					Output.setText("");
					runInterpret(textArea.getText());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		menuBar.add(btnRun);

		// Step backwards NOT WORKING yet
		JButton btnStepBackward = new JButton("Step Backward");
		btnStepBackward.setEnabled(false);
		btnStepBackward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		menuBar.add(btnStepBackward);

		// Step Forward NOT WORKING yet
		JButton btnStepForward = new JButton("Step Forward");
		btnStepForward.setEnabled(false);
		btnStepForward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		menuBar.add(btnStepForward);

		// Creating the new output text area
		Output = new TextArea();
		Output.setBackground(Color.DARK_GRAY);
		Output.setForeground(Color.WHITE);
		Output.setFont(new Font("Verdana", Font.BOLD, 15));
		Output.setText("Output");
		Output.setEditable(false);

		OutputScroller = new JScrollPane(Output);
		frame.getContentPane().add(OutputScroller, BorderLayout.SOUTH);

	}

	// Opens a file
	private void openFile() {
		// try catch because people make mistakes.
		try {
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Choose your file");
			chooser.showOpenDialog(null);

			myFile = chooser.getSelectedFile();
			frame.setTitle(Title + " " + myFile.getName());

			// This justs makes sure the user dosnt crash the program.
			if (!myFile.exists()) {
				JOptionPane.showMessageDialog(null, "This file does not exist!", "Goof", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Will add the file to the screen using the scanner method
			Scanner reader = new Scanner(myFile);
			String contents = "";

			while (reader.hasNextLine()) {
				contents += reader.nextLine() + "\n";
			}
			// Fix resource leak
			reader.close();

			// sets the selected file

			// Pushes the file to the text area
			textArea.setText(contents);
			saveFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Self explanitory
	private void saveFile() {
		try {
			if (myFile == null) {
				JOptionPane.showMessageDialog(null,
						"Failed to save file for there was none selected. Running Save As File.", "Goof",
						JOptionPane.ERROR_MESSAGE);
				saveFileAs();
				return;
			}

			if (!myFile.getName().toLowerCase().endsWith(".txt")) {
				myFile = new File(myFile.getParentFile(), myFile.getName() + ".txt");
			}
			String contents = textArea.getText();
			Formatter formatter = new Formatter(myFile);

			formatter.format("%S", contents);
			formatter.close();

			System.out.println("Saved File");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Oh my god this is 10/10 took a long time but it WORKS
	private void saveFileAs() {
		try {
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Save File As");
			chooser.showSaveDialog(null);

			if (!chooser.getSelectedFile().getName().toLowerCase().endsWith(".txt")) {
				myFile = new File(chooser.getSelectedFile().getParentFile(),
						chooser.getSelectedFile().getName() + ".txt");
			} else {
				myFile = chooser.getSelectedFile();
			}

			String contents = textArea.getText();

			Formatter formatter = new Formatter(myFile);

			formatter.format("%s", contents);
			formatter.close();

			frame.setTitle(Title + " " + myFile.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Self explanitory but it runs the thing... yeah
	private void runInterpret(String str) {
		Interpreter intrepet = new Interpreter(str);
	}

	// Ok this is going to be used to access the output bod from inside the
	// intrepeterthing im too tired for this...
	public static void setOutputBox(ArrayList<Integer> a, char tempChar) {
		// Pretty easy...
		String str = "";
		// This is sloppy but it works
		if (a != null) {
			str = a.toString();
		} else {
			str = Character.toString(tempChar);
		}
		Output.append(
				"---------------------------------------------------------------------------------------------\n");
		Output.append(str);
	}

}
