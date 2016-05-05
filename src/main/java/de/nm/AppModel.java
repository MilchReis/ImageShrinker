package de.nm;

import java.awt.Image;
import java.io.File;

import org.apache.commons.io.FilenameUtils;

import com.sun.jimi.core.Jimi;

import de.nm.file.FileAction;
import de.nm.file.FileProcessor;
import de.nm.file.LoopInfo;
import de.nm.img.ExifHelper;

public class AppModel {

	public static final String[] IMAGE_FORMATS = {"png", "jpg", "bmp"};
	
	private File source;
	private boolean override;
	private ProgressDialog dialog;
	
	public void setSource(File selectedPath) {
		source = selectedPath;
	}

	public void shrinkImages() {
		if(dialog != null) 
			dialog.dispose();
	
		dialog = new ProgressDialog();
		
		new Thread(() -> {
			FileProcessor.foreachFile(source, IMAGE_FORMATS, new FileAction() {
				@Override
				public void process(File file, LoopInfo info) {
					try {
						Image img = Jimi.getImage(file.getAbsolutePath());
						String name = FilenameUtils.getBaseName(file.getName());
						
						File tmp = new File(file.getParent(), name + "_s.jpg");
						Jimi.putImage(img, tmp.getAbsolutePath());
						
						ExifHelper.copyExifData(file, tmp, null);
						
						if(override) {
							file.delete();
							tmp.renameTo(file);
						}
						
					} catch(Exception e) {
						e.printStackTrace();
					}
					dialog.update(file);
				}
			},
			true);
			
			dialog.finish();
		}).start();
	}

	public void setOverride(boolean selected) {
		override = selected;
	}

}
