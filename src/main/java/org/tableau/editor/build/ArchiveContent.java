package org.tableau.editor.build;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ArchiveContent extends MetaStore {
	
	public boolean archivefiles(String file, String workingdir) {
		
		String archive_dir = workingdir+File.separator+"Archive";
		String filename = file.substring(file.lastIndexOf(File.separator)+1);
		
		File folder = new File(archive_dir);
		if(!folder.exists()) {
			folder.mkdir();
		}
		
		try {
			Files.move(Paths.get(workingdir+File.separator+filename), 
					Paths.get(archive_dir+File.separator+filename), 
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}

}
