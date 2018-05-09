/**
 *  GNU GENERAL PUBLIC LICENSE
                       Version 3, 29 June 2007

 Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 Everyone is permitted to copy and distribute verbatim copies
 of this license document, but changing it is not allowed.

Tableau Editor  Copyright (C) 2018  Rishu Kumar Shrivastava (rishu.shrivastava@gmail.com)
 */
package org.tableau.editor.build;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
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
		if(!deleteDir(sourcedirpath)) {
			return false;
		}

		return true;
	}

	public boolean deleteDir(String sourcepath) {
		//delete the path recursively
		Path p = Paths.get(sourcepath);
		try {
			Files.walkFileTree(p, new SimpleFileVisitor<Path>(){

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					Files.delete(file);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {

					Files.delete(dir);
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}