package de.nm.file;

import java.io.File;
import java.io.FileFilter;

public class ImageFileFilter implements FileFilter  {

	private String[] supportedFileExt;
	
	public ImageFileFilter(String[] supportedFileExtentions) {
		supportedFileExt = supportedFileExtentions;
	}
	
	@Override
	public boolean accept(File pathname) {
		return endsWith(pathname.getName(), supportedFileExt);
	}
	
	public boolean endsWith(String needle, String[] heap) {
		needle = needle.toLowerCase();
		if(heap == null)
			return true;
		
		for(String s : heap) {
			if(needle.endsWith(s.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
}
