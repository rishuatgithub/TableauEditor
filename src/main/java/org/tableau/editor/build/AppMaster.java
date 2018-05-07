/**
 *  GNU GENERAL PUBLIC LICENSE
                       Version 3, 29 June 2007

 Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 Everyone is permitted to copy and distribute verbatim copies
 of this license document, but changing it is not allowed.

                            Preamble

  The GNU General Public License is a free, copyleft license for
software and other kinds of works.

  The licenses for most software and other practical works are designed
to take away your freedom to share and change the works.  By contrast,
the GNU General Public License is intended to guarantee your freedom to
share and change all versions of a program--to make sure it remains free
software for all its users.  We, the Free Software Foundation, use the
GNU General Public License for most of our software; it applies also to
any other work released this way by its authors.  You can apply it to
your programs, too.

  When we speak of free software, we are referring to freedom, not
price.  Our General Public Licenses are designed to make sure that you
have the freedom to distribute copies of free software (and charge for
them if you wish), that you receive source code or can get it if you
want it, that you can change the software or use pieces of it in new
free programs, and that you know you can do these things.

  To protect your rights, we need to prevent others from denying you
these rights or asking you to surrender the rights.  Therefore, you have
certain responsibilities if you distribute copies of the software, or if
you modify it: responsibilities to respect the freedom of others.

  For example, if you distribute copies of such a program, whether
gratis or for a fee, you must pass on to the recipients the same
freedoms that you received.  You must make sure that they, too, receive
or can get the source code.  And you must show them these terms so they
know their rights.

  Developers that use the GNU GPL protect your rights with two steps:
(1) assert copyright on the software, and (2) offer you this License
giving you legal permission to copy, distribute and/or modify it.

  For the developers' and authors' protection, the GPL clearly explains
that there is no warranty for this free software.  For both users' and
authors' sake, the GPL requires that modified versions be marked as
changed, so that their problems will not be attributed erroneously to
authors of previous versions.

  Some devices are designed to deny users access to install or run
modified versions of the software inside them, although the manufacturer
can do so.  This is fundamentally incompatible with the aim of
protecting users' freedom to change the software.  The systematic
pattern of such abuse occurs in the area of products for individuals to
use, which is precisely where it is most unacceptable.  Therefore, we
have designed this version of the GPL to prohibit the practice for those
products.  If such problems arise substantially in other domains, we
stand ready to extend this provision to those domains in future versions
of the GPL, as needed to protect the freedom of users.

  Finally, every program is threatened constantly by software patents.
States should not allow patents to restrict development and use of
software on general-purpose computers, but in those that do, we wish to
avoid the special danger that patents applied to a free program could
make it effectively proprietary.  To prevent this, the GPL assures that
patents cannot be used to render the program non-free.

Tableau Editor  Copyright (C) 2018  Rishu Kumar Shrivastava (rishu.shrivastava@gmail.com)
 */
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

				
				
				boolean build_content = bc.buildContent(ms.getOutput_directory_pf(), ms.getInput_filename());
				if(build_content) {
					message_file += "Re-building Successfull"+System.lineSeparator();	
				}else {
					message_file += "Re-building of File Failed."+System.lineSeparator();
				}
				progressText.setText(message_file);
				

			}

		}


	}

}
