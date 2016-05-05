package de.nm.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileProcessor {
	public static final String WAITING = "waiting";
	public static final String PROCESSING = "processing";
	public static final int WAIT_TIME_IN_MILLIS = 250; 
	
	public static String status = WAITING;
	public static String state = "";
	public static String currentfile = "";
	
	
	public static void foreachFile(File path, String[] format, FileAction action) {
		foreachFile(path, format, action, false);
	}
	
	public static void foreachFile(File path, String[] format, FileAction action, boolean recursivly) {
		List<FileAction> list = new ArrayList<FileAction>();
		list.add(action);
		foreachFile(path, format, list, recursivly);
	}
	
	public static void foreachFile(File path, String[] format, List<FileAction> actions) {
		foreachFile(path, format, actions, false);
	}
	
	public static void foreachFile(File path, String[] format, List<FileAction> actions,  boolean recursivly) {
		
		status = PROCESSING;

		ImageFileFilter filter = new ImageFileFilter(format);
		Iterator<File> it = FileUtils.iterateFiles(path, null, recursivly);
		LoopInfo info = new LoopInfo();

		it.forEachRemaining(f -> {
			if(!filter.accept(f)) {
				return;
			}
			currentfile = f.getAbsolutePath();
			
			for(FileAction action : actions) {
				state = action.getClass().getSimpleName();
				try {
					action.process(f, info);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		state = "";
		currentfile = "";
		status = WAITING;
	}
	
	public static boolean isActive() {
		synchronized (status) {
			return (status == PROCESSING);
		}
	}
	
	public static boolean endsWith(String needle, String[] heap) {
		needle = needle.toLowerCase();
		for(String s : heap) {
			if(needle.endsWith(s.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
}
