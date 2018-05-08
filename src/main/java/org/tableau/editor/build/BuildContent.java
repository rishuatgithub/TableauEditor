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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Zip the file back after the editing.
 * @author Rishu Shrivastava (rishu.shrivastava@gmail.com)
 * @version 1.0.0
 * 
 */


public class BuildContent {

	public boolean buildContent(String sourcedirpath, String Zipfilepath) {

		Path p;
		try {
			p = Files.createFile(Paths.get(Zipfilepath));

			try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p))) {
				Path pp = Paths.get(sourcedirpath);
				Files.walk(pp)
				.filter(path -> !Files.isDirectory(path))
				.forEach(path -> {
					ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
					try {
						zs.putNextEntry(zipEntry);
						Files.copy(path, zs);
						zs.closeEntry();
					} catch (IOException e) {
						System.err.println(e);
					}
				});
			} 
			
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
		
		//cleanup source directory path
		new File(sourcedirpath).deleteOnExit();
		
		return true;
	}
}