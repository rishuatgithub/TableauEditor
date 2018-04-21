package org.tableau.editor.build;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class AppMaster extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3492701885097648184L;
	
	JFrame jf;
	JButton browsebtn, okbtn, cancelbtn;
	JTextField textfield, connectionFrom, connectionTo;
	JTextArea fileslist;
	JLabel dirlistLbl, connectionFromLbl, connectionToLbl;
	
	public AppMaster() {
		
		jf = new JFrame("Tableau Editor");
		
		browsebtn = new JButton("Select Directory");
		browsebtn.addActionListener(this);
		browsebtn.setBounds(10, 10, 150, 30);
		
		okbtn = new JButton("Start Proceesing");
		okbtn.addActionListener(this);
		
		cancelbtn = new JButton("Reset");
		cancelbtn.addActionListener(this);
		
		textfield = new JTextField();
		textfield.setMinimumSize(browsebtn.getMinimumSize());
		textfield.setBounds(10, 10, 200, 30);
		
		fileslist = new JTextArea();
		fileslist.setEditable(false);
		JScrollPane scroll = new JScrollPane(fileslist);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				
		dirlistLbl = new JLabel("List of files to be processed");
				
		connectionFromLbl = new JLabel("Enter the Current Server Details");
		connectionFrom = new JTextField();
		
		connectionToLbl = new JLabel("Enter the New Server Details");
		connectionTo = new JTextField();
		
		jf.add(browsebtn);
		jf.add(textfield);
		
		jf.add(dirlistLbl);
		jf.add(scroll);
		jf.add(connectionFromLbl);
		jf.add(connectionFrom);
		jf.add(connectionToLbl);
		jf.add(connectionTo);
		jf.add(okbtn);
		jf.add(cancelbtn);
		jf.setLayout(new GridLayout(5, 2));
		
		jf.setVisible(true);
		jf.setSize(600, 500);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	public static void main(String[] args) {
		new AppMaster();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == browsebtn) {
			JFileChooser fc = new JFileChooser();
			int returnval = fc.showOpenDialog(browsebtn);
			fc.setDialogTitle("Select Directory");
			fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			fc.setAcceptAllFileFilterUsed(false);
			
			if(returnval == JFileChooser.APPROVE_OPTION) {
				File f = fc.getCurrentDirectory();
				//System.out.println(f.getAbsolutePath());
				textfield.setText(f.getAbsolutePath());
				
				File dir = new File(f.getAbsolutePath());
				File[] filelist = dir.listFiles();
				String filetextarea = " ";
				
				for (File files: filelist) {
					System.out.println(files);
					filetextarea += files.getName()+"\n";
				}
				fileslist.setText(filetextarea);
			}
		}
		
		/*if(e.getSource() == okbtn) {
			System.out.println("List all the files in the directory and start processing");
			
			File dir = new File(textfield.getText());
			File[] filelist = dir.listFiles();
			String filetextarea = " ";
			
			for (File files: filelist) {
				System.out.println(files);
				filetextarea += files.getName()+"\n";
			}
			fileslist.setText(filetextarea);
			
		}*/
		
		if(e.getSource() == cancelbtn) {
			textfield.setText(null);
			fileslist.setText(null);
			connectionFrom.setText(null);
			connectionTo.setText(null);
		}
		
		
	}

}
