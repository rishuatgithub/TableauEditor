package org.tableau.editor.build;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.GroupLayout;
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
	private static final long serialVersionUID = 1L;
	
	JFrame jf;
	JButton browsebtn, okbtn, cancelbtn;
	JTextField dirselected, connectionFrom, connectionTo, schemaFrom, schemaTo;
	JTextArea fileslist;
	JLabel selectdir, dirlistLbl, oldversionLbl, newversionLbl, serverLbl, schemaLbl;
	
	public AppMaster() {
		
		jf = new JFrame("Tableau Editor");
		Container myPanel = jf.getContentPane();
		
		GroupLayout gl = new GroupLayout(myPanel);
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);
		myPanel.setLayout(gl);
		
		
		selectdir = new JLabel("Select Tableau Directory");
				
		browsebtn = new JButton("Browse");
		browsebtn.addActionListener(this);
		
		okbtn = new JButton("Start Proceesing");
		okbtn.addActionListener(this);
		
		cancelbtn = new JButton("Reset");
		cancelbtn.addActionListener(this);
		
		dirselected = new JTextField();
		//dirselected.setMinimumSize(browsebtn.getMinimumSize());
		
		fileslist = new JTextArea();
		fileslist.setEditable(false);
		JScrollPane scroll = new JScrollPane(fileslist);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				
		dirlistLbl = new JLabel("List of files to be processed");
				
		oldversionLbl = new JLabel("Current Configuration");
		newversionLbl = new JLabel("New Configuration");
		
		serverLbl = new JLabel("Server Credentials : ");
		schemaLbl = new JLabel("Database Schema : ");
		
		connectionFrom = new JTextField();
		connectionTo = new JTextField();
		
		schemaFrom = new JTextField();
		schemaTo = new JTextField();
		
		gl.setHorizontalGroup(
				gl.createSequentialGroup()
					.addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(selectdir)
								.addComponent(dirlistLbl)
								.addComponent(serverLbl)
								.addComponent(schemaLbl)
							)
					.addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(dirselected)
								.addComponent(scroll)
								.addComponent(oldversionLbl)
								.addComponent(connectionFrom)
								.addComponent(schemaFrom)
								.addComponent(okbtn)
							)
					.addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(browsebtn)
								.addComponent(newversionLbl)
								.addComponent(connectionTo)
								.addComponent(schemaTo)
								.addComponent(cancelbtn)
							)
				);

		gl.setVerticalGroup(
				gl.createSequentialGroup()
					.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(selectdir)
								.addComponent(dirselected)
								.addComponent(browsebtn)
							)
					.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(dirlistLbl)
								.addComponent(scroll)
							)
					.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(oldversionLbl)
								.addComponent(newversionLbl)
							)
					.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(serverLbl)
								.addComponent(connectionFrom)
								.addComponent(connectionTo)
							)
					.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(schemaLbl)
							.addComponent(schemaFrom)
							.addComponent(schemaTo)
						)
					.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(okbtn)
							.addComponent(cancelbtn)
						)
				);
		
				
		jf.setVisible(true);
		//jf.setSize(600, 500);
		jf.pack();
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
				dirselected.setText(f.getAbsolutePath());
				
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
		
	
		
		if(e.getSource() == cancelbtn) {
			dirselected.setText(null);
			fileslist.setText(null);
			connectionFrom.setText(null);
			connectionTo.setText(null);
			schemaFrom.setText(null);
			schemaTo.setText(null);
		}
		
		
		
		
	}

}