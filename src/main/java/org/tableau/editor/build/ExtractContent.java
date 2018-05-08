/**
 *  GNU GENERAL PUBLIC LICENSE
                       Version 3, 29 June 2007

 Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 Everyone is permitted to copy and distribute verbatim copies
 of this license document, but changing it is not allowed.

Tableau Editor  Copyright (C) 2018  Rishu Kumar Shrivastava (rishu.shrivastava@gmail.com)
 */
package org.tableau.editor.build;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Extract the content of the zip files
 * @author Rishu Shrivastava (rishu.shrivastava@gmail.com)
 * @version 1.0.0
 * 
 */
public class ExtractContent extends MetaStore {

	public boolean extractfile(String file, String workingdir) {

		setInput_filename(file);
		setOutput_directory_pf(workingdir);

		byte[] buffer = new byte[BYTE_SIZE];
		String input_zip = getInput_filename();
		String output_dir = getOutput_directory_pf();

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

				//System.out.println("Unzipping File:"+ newFile);

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
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} 


	}

}
