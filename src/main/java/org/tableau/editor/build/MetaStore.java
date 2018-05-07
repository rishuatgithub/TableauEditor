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

import java.io.File;

/**
 * A meta store for the variables in question
 * @author Rishu Shrivastava (rishu.shrivastava@gmail.com)
 * @version 1.0.0
 * 
 */

public class MetaStore {

	final int BYTE_SIZE = 4096;
	final String FILE_SEARCH_PATTERN = ".twbx";

	private int filelistsize;
	private String workingdir;
	private String input_filename;
	private String output_directory_pf;
	private String input_filename_twb;
	private String output_filename_edited;

	private String connectionFrom;
	private String connectionTo;
	private String schemaFrom;
	private String schemaTo;



	public int getFilelistsize() {
		return filelistsize;
	}

	public void setFilelistsize(int filelistsize) {
		this.filelistsize = filelistsize;
	}

	public String getWorkingdir() {
		return workingdir;
	}

	public void setWorkingdir(String workingdir) {
		this.workingdir = workingdir;
	}

	public String getInput_filename() {
		return input_filename;
	}

	public void setInput_filename(String input_filename) {
		this.input_filename = input_filename;
	}

	public String getOutput_directory_pf() {
		return output_directory_pf;
	}

	public void setOutput_directory_pf(String output_directory_pf) {
		String process_fname = this.input_filename;
		this.output_directory_pf = output_directory_pf + process_fname.substring(process_fname.lastIndexOf('\\'),process_fname.lastIndexOf('.'));
	}

	public String getConnectionFrom() {
		return connectionFrom;
	}

	public void setConnectionFrom(String connectionFrom) {
		this.connectionFrom = connectionFrom;
	}

	public String getConnectionTo() {
		return connectionTo;
	}

	public void setConnectionTo(String connectionTo) {
		this.connectionTo = connectionTo;
	}

	public String getSchemaFrom() {
		return schemaFrom;
	}

	public void setSchemaFrom(String schemaFrom) {
		this.schemaFrom = schemaFrom;
	}

	public String getSchemaTo() {
		return schemaTo;
	}

	public void setSchemaTo(String schemaTo) {
		this.schemaTo = schemaTo;
	}

	public String getInput_filename_twb() {
		return input_filename_twb;
	}

	public void setInput_filename_twb(String input_filename_twb) {
		String new_dir = this.output_directory_pf;
		String fname_twbx = this.input_filename;
		String fname_twb = fname_twbx.substring(fname_twbx.lastIndexOf('\\')+1,fname_twbx.length()-1);
		this.input_filename_twb = new_dir + File.separator + fname_twb;

	}

	public String getOutput_filename_edited() {
		return output_filename_edited;
	}

	public void setOutput_filename_edited(String output_filename_edited) {
		String iftwb = this.input_filename_twb;
		String iftwb_sub = iftwb.substring(0,iftwb.lastIndexOf('.'));
		String iftwb_ext = iftwb.substring(iftwb.lastIndexOf('.'),iftwb.length());
		this.output_filename_edited = iftwb_sub + "_ED"+iftwb_ext;
	}





}
