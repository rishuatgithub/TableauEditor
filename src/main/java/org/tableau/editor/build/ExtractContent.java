package org.tableau.editor.build;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Extract Content of the file passed
 * @author Rishu
 *
 */
public class ExtractContent extends MetaStore {
	
	public boolean extractfile(String file, String workingdir) {
		
		//MetaStore ms = new MetaStore();
		setInput_filename(file);
		setOutput_directory_pf(workingdir);
		//System.out.println(">>>>"+ms.getOutput_directory_pf());
		//System.out.println(getFilelistsize());
		
		byte[] buffer = new byte[BYTE_SIZE];
		String input_zip = getInput_filename();
		String output_dir = getOutput_directory_pf();
		
		//System.out.println(output_dir);
		//System.out.println(input_zip);
		
		//create output dir
		File folder = new File(output_dir);
		if(!folder.exists()) {
			folder.mkdir();
		}
		
		try {
			ZipInputStream zip = new ZipInputStream(new FileInputStream(input_zip));
			ZipEntry ze = zip.getNextEntry();
			while(ze!=null) {

				String filename = ze.getName();
				File newFile = new File(output_dir + File.separator + filename);
				
				System.out.println("Unzipping File:"+ newFile);
				
				//create subfolders
				new File(newFile.getParent()).mkdirs();
				
				FileOutputStream fos = new FileOutputStream(newFile);
				
				int len;
				while((len = zip.read(buffer))>0) {
					fos.write(buffer, 0, len);
				}
				
				fos.close();
				ze = zip.getNextEntry();
			}
			
			zip.closeEntry();
			zip.close();
			
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
		
		
	}

}
