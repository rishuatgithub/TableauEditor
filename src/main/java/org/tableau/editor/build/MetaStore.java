package org.tableau.editor.build;


public class MetaStore {

	
	private int filelistsize;
	private String workingdir;
	private String input_filename;
	private String output_directory_pf;
	
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
		this.output_directory_pf = output_directory_pf + this.input_filename.substring(0, this.input_filename.lastIndexOf('.'));
	}
	
}
