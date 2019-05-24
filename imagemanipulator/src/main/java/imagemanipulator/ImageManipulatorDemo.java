package imagemanipulator;

import java.io.File;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageConverter;

/***
 * This is a simple demonstration class
 * @author agkortzis
 * @since May 2019
 */
public class ImageManipulatorDemo {
	
	/**
	 * Loads and transforms the image to grayscale.
	 * Then, it shows both original and grayscale images.
	 * @param args
	 */
	public static void main(String[] args) {
		
		String path = args[0];
		File image = new File(path);
		System.out.println("Source found: " + image.exists());
		ImagePlus original = IJ.openImage(path);
		original.show();
		ImagePlus imp = IJ.openImage(path);
		ImageConverter ic = new ImageConverter(imp);
		ic.convertToGray8();
		imp.updateAndDraw();
		imp.show();
	}

}
