package org.tableau.editor.build;

import java.io.File;

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
