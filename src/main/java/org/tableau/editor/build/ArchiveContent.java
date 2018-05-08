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
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


/**
 * The Archive Class creates an Archive directory and move original files to this directory after unzipping
 * @author Rishu Shrivastava (rishu.shrivastava@gmail.com)
 * @version 1.0.0
 * 
 */

public class ArchiveContent extends MetaStore {

	public boolean archivefiles(String file, String workingdir) {

		String archive_dir = workingdir + File.separator + "Archive";
		String filename = file.substring(file.lastIndexOf(File.separator) + 1);

		File folder = new File(archive_dir);
		if (!folder.exists()) {
			folder.mkdir();
		}

		try {
			Files.move(Paths.get(workingdir + File.separator + filename),
					Paths.get(archive_dir + File.separator + filename), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

}
