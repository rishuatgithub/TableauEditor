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
public class ExtractContent {
	
	public void extractfile(String file, String workingdir) {
		
		MetaStore ms = new MetaStore();
		ms.setInput_filename(file);
		ms.setWorkingdir(workingdir);
		
		byte[] buffer = new byte[4096];
		String input_zip = ms.getInput_filename();
		String output_dir = ms.getOutput_directory_pf();
		
		System.out.println(output_dir);
		System.out.println(input_zip);
		
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
			
			System.out.println("file unzipped done");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
