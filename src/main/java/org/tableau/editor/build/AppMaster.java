package org.tableau.editor.build;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 * App Master for the Tableau Editor
 * @author Rishu
 *
 */

public class AppMaster extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JFrame jf;
	JButton browsebtn, okbtn, cancelbtn;
	JTextField dirselected, connectionFrom, connectionTo, schemaFrom, schemaTo;
	JTextArea fileslist, progressText;
	JLabel selectdir, dirlistLbl, oldversionLbl, newversionLbl, serverLbl, schemaLbl;
	
	MetaStore ms = new MetaStore();
	ArrayList<String> filetoprocess = new ArrayList<String>();
	
	String message_file="";
	
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
		
		progressText = new JTextArea();
		
		
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
								.addComponent(progressText,GroupLayout.DEFAULT_SIZE, 200, GroupLayout.DEFAULT_SIZE)
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
					.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(progressText)
							)
				);
		
				
		jf.setVisible(true);
		jf.setSize(700, 500);
		//jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	public static void main(String[] args) {
		new AppMaster();
	}

	public void actionPerformed(ActionEvent e) {
		
		
		//browse for files
		if(e.getSource() == browsebtn) {
			JFileChooser fc = new JFileChooser();
			int returnval = fc.showOpenDialog(browsebtn);
			fc.setDialogTitle("Select Directory");
			fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			fc.setAcceptAllFileFilterUsed(false);
			
			if(returnval == JFileChooser.APPROVE_OPTION) {
				File f = fc.getCurrentDirectory();
				//System.out.println("---"+f.getAbsolutePath());
				 
				ms.setWorkingdir(f.getAbsolutePath());
				dirselected.setText(ms.getWorkingdir());
				
				File dir = new File(f.getAbsolutePath());
				File[] filelist = dir.listFiles();
				
				String filetextarea = "";
				
				for (File files: filelist) {
					
					if(files.getName().contains(ms.FILE_SEARCH_PATTERN)) {
						filetextarea += files.getName()+System.lineSeparator();
						filetoprocess.add(files.getAbsolutePath());						
					} else {
						filetextarea = "Tableau Files Not found. TWBX files expected.";
					}
					
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
			progressText.setText("");
		}
		
		if(e.getSource() == okbtn) {
			
			message_file += "Processing Started "+System.lineSeparator();
			
			ExtractContent ec = new ExtractContent();
			ArchiveContent ac = new ArchiveContent();
			ReplaceContent rc = new ReplaceContent();
			
			ms.setFilelistsize(filetoprocess.size());
			ms.setConnectionFrom(connectionFrom.getText());
			ms.setConnectionTo(connectionTo.getText());
			ms.setSchemaFrom(schemaFrom.getText());
			ms.setSchemaTo(schemaTo.getText());
		
			for(int i=0; i<ms.getFilelistsize(); i++) {
				ms.setInput_filename(filetoprocess.get(i));
				ms.setOutput_directory_pf(ms.getWorkingdir());
				ms.setInput_filename_twb(filetoprocess.get(i));
				ms.setOutput_filename_edited(ms.getInput_filename_twb());
				
				message_file += "Extracting File : "+ms.getInput_filename()+System.lineSeparator();
				
				boolean extract_status = ec.extractfile(ms.getInput_filename(), ms.getWorkingdir());
				if(extract_status) {
					message_file += "Extraction Success"+System.lineSeparator();
				}else {
					message_file += "Extraction Failure"+System.lineSeparator();
				}
				progressText.setText(message_file);
				
				boolean archive_file_status = ac.archivefiles(ms.getInput_filename(), ms.getWorkingdir());
				if(archive_file_status) {
					message_file += "Original file archived successfully"+System.lineSeparator();	
				}else {
					message_file += "Archiving of file failed"+System.lineSeparator();
				}
				progressText.setText(message_file);
				
				/*System.out.println("------------------------");
				System.out.println(ms.getWorkingdir());
				System.out.println(ms.getInput_filename());
				System.out.println(ms.getOutput_directory_pf());
				System.out.println(ms.getInput_filename_twb());
				System.out.println(ms.getConnectionFrom());
				System.out.println(ms.getOutput_filename_edited());*/
				
				boolean replace_file_status = rc.replacecontent(ms.getInput_filename_twb(),ms.getOutput_filename_edited(), ms.getConnectionFrom(), 
									ms.getConnectionTo(), ms.getSchemaFrom(), ms.getSchemaTo());
				if(replace_file_status) {
					message_file += "Features successfully replaced"+System.lineSeparator();	
				}else {
					message_file += "Error faced in replacing features"+System.lineSeparator();
				}
				progressText.setText(message_file);
				
			}
			
		}
		
		
	}

}
