/**
 *  
 *  GNU GENERAL PUBLIC LICENSE
    Version 3, 29 June 2007

 Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 Everyone is permitted to copy and distribute verbatim copies
 of this license document, but changing it is not allowed.

 Tableau Editor  Copyright (C) 2018  Rishu Kumar Shrivastava (rishu.shrivastava@gmail.com)
 */
package org.tableau.editor.build;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 * The Master Class of the Tableau Editor
 * @author Rishu Shrivastava (rishu.shrivastava@gmail.com)
 * @version 1.0.0
 * 
 */

public class AppMaster extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	JFrame jf;
	JButton browsebtn, okbtn, cancelbtn;
	JTextField dirselected, connectionFrom, connectionTo, schemaFrom, schemaTo;
	JTextArea fileslist, progressText;
	JLabel selectdir, dirlistLbl, oldversionLbl, newversionLbl, serverLbl, schemaLbl, progressLbl;

	MetaStore ms = new MetaStore();
	ArrayList<String> filetoprocess = new ArrayList<String>();

	String message_file="";
	ImageIcon img = new ImageIcon("src/main/resources/images/Tableau-EditorIcon.png");
	
	Properties prop;
	
	public AppMaster() {
				
		try {
			FileInputStream fis = new FileInputStream("src/main/resources/config/master_config.properties");
			prop = new Properties();
			prop.load(fis);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		jf = new JFrame(prop.getProperty("TableauEditor.Title"));
		Container myPanel = jf.getContentPane();

		GroupLayout gl = new GroupLayout(myPanel);
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);
		myPanel.setLayout(gl);


		selectdir = new JLabel(prop.getProperty("TableauEditor.Label.SelectDirectoryLabel"));

		browsebtn = new JButton(prop.getProperty("TableauEditor.Button.Browse"));
		browsebtn.addActionListener(this);

		okbtn = new JButton(prop.getProperty("TableauEditor.Button.StartProcessing"));
		okbtn.addActionListener(this);

		cancelbtn = new JButton(prop.getProperty("TableauEditor.Button.Cancel"));
		cancelbtn.addActionListener(this);

		dirselected = new JTextField();

		fileslist = new JTextArea();
		fileslist.setEditable(false);
		JScrollPane scroll = new JScrollPane(fileslist);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		dirlistLbl = new JLabel(prop.getProperty("TableauEditor.Label.ListofFiles"));

		oldversionLbl = new JLabel(prop.getProperty("TableauEditor.Label.CurrentConfigLabel"));
		newversionLbl = new JLabel(prop.getProperty("TableauEditor.Label.NewConfigLabel"));

		serverLbl = new JLabel(prop.getProperty("TableauEditor.Label.ServerLabel"));
		schemaLbl = new JLabel(prop.getProperty("TableauEditor.Label.DBSchemaLabel"));

		connectionFrom = new JTextField();
		connectionTo = new JTextField();

		schemaFrom = new JTextField();
		schemaTo = new JTextField();

		progressText = new JTextArea();

		progressLbl = new JLabel(prop.getProperty("TableauEditor.Label.ProgressLog"));

		gl.setHorizontalGroup(
				gl.createSequentialGroup()
				.addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(selectdir)
						.addComponent(dirlistLbl)
						.addComponent(serverLbl)
						.addComponent(schemaLbl)
						.addComponent(progressLbl)
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
						.addComponent(progressLbl)
						.addComponent(progressText)
						)
				);


		jf.setVisible(true);
		jf.setSize(700, 500);
		jf.setIconImage(img.getImage());
		//jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	public static void main(String[] args) throws FileNotFoundException {
		
		new AppMaster();
	}

	public void actionPerformed(ActionEvent e) {


		//browse for files
		if(e.getSource() == browsebtn) {
			JFileChooser fc = new JFileChooser();
			int returnval = fc.showOpenDialog(browsebtn);
			fc.setDialogTitle(prop.getProperty("TableauEditor.Action.OpenBrowseMessage"));
			fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			fc.setAcceptAllFileFilterUsed(false);

			if(returnval == JFileChooser.APPROVE_OPTION) {
				File f = fc.getCurrentDirectory();

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
						filetextarea = prop.getProperty("TableauEditor.Message.FileNotFound");
					}

				}
				fileslist.setText(filetextarea);

			}
		}

		// on click on RESET or CANCEL button
		if(e.getSource() == cancelbtn) {
			dirselected.setText(null);
			fileslist.setText(null);
			connectionFrom.setText(null);
			connectionTo.setText(null);
			schemaFrom.setText(null);
			schemaTo.setText(null);
			progressText.setText("");
		}

		// On click of OK or START PROCESSING btn
		if(e.getSource() == okbtn) {

			message_file += prop.getProperty("TableauEditor.Message.ProcessingStart")+System.lineSeparator();

			ExtractContent ec = new ExtractContent();
			ArchiveContent ac = new ArchiveContent();
			ReplaceContent rc = new ReplaceContent();
			BuildContent bc = new BuildContent();

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

				message_file += prop.getProperty("TableauEditor.Message.ExtractingFile")+ms.getInput_filename()+System.lineSeparator();

				// extract file
				boolean extract_status = ec.extractfile(ms.getInput_filename(), ms.getWorkingdir());
				if(extract_status) {
					message_file += prop.getProperty("TableauEditor.Message.ExtractingSuccess")+System.lineSeparator();
				}else {
					message_file += prop.getProperty("TableauEditor.Message.ExtractingFailure")+System.lineSeparator();
				}
				progressText.setText(message_file);

				// archive original file
				boolean archive_file_status = ac.archivefiles(ms.getInput_filename(), ms.getWorkingdir());
				if(archive_file_status) {
					message_file += prop.getProperty("TableauEditor.Message.ArchiveSuccess")+System.lineSeparator();	
				}else {
					message_file += prop.getProperty("TableauEditor.Message.ArchiveFailure")+System.lineSeparator();
				}
				progressText.setText(message_file);

				// edit the file with passed parameters
				boolean replace_file_status = rc.replacecontent(ms.getInput_filename_twb(),ms.getOutput_filename_edited(), ms.getConnectionFrom(), 
						ms.getConnectionTo(), ms.getSchemaFrom(), ms.getSchemaTo());
				if(replace_file_status) {
					message_file += prop.getProperty("TableauEditor.Message.ReplaceSuccess")+System.lineSeparator();	
				}else {
					message_file += prop.getProperty("TableauEditor.Message.ReplaceFailure")+System.lineSeparator();
				}
				progressText.setText(message_file);

				// rebuild the tableau file and cleanup
				boolean build_content = bc.buildContent(ms.getOutput_directory_pf(), ms.getInput_filename());
				if(build_content) {
					message_file += prop.getProperty("TableauEditor.Message.RebuildSuccess")+System.lineSeparator();
				}else {
					message_file += prop.getProperty("TableauEditor.Message.RebuildFailure")+System.lineSeparator();
				}
				progressText.setText(message_file);
				
				progressText.setText(prop.getProperty("TableauEditor.Message.ProcessingEnd"));
				
			}

		}


	}

}
