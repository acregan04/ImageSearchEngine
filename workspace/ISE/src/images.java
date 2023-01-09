import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import java.util.Arrays;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Range;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

//import GUI.HashMapIndexing;

public class images {

	public static ArrayList<Image> details(Icon icon, String queryImgStr) {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		// File representing the folder that you select using a FileChooser
		//final File dir = new File("C:\\Users\\acreg\\Desktop\\final year\\Project\\workspace\\GUI\\res");
		final File dir = new File("res");

		
		// array of supported extensions (use a List if you prefer)
		final String[] EXTENSIONS = new String[]{
				"jpg" // and other formats you need
		};
		// filter to identify images based on their extensions
		final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

			@Override
			public boolean accept(final File dir, final String name) {
				for (final String ext : EXTENSIONS) {
					if (name.endsWith("." + ext)) {
						return (true);
					}
				}
				return (false);
			}
		};

		//String[] retrievedImagesHI = null;
		//HashMapIndexing hashmapobj = new HashMapIndexing();
		ArrayList<Image> unproImgs = new ArrayList<Image>();
		ArrayList<Image> proImgs = new ArrayList<Image>();
		ArrayList<Mat> unproImgsMat = new ArrayList<Mat>();
		
		Mat matQueryImg = Imgcodecs.imread("queryImgStr");
		
		if (dir.isDirectory()) { // make sure it's a directory
			for (final File f : dir.listFiles(IMAGE_FILTER)) {
				BufferedImage img = null;
				Mat matIm = null;

				try {
					img = ImageIO.read(f);
					matIm = Imgcodecs.imread(f.toString());
					
					//all images in list
					unproImgs.add(img);
					unproImgsMat.add(matIm);
					
					
					// you probably want something more involved here
					// to display in your UI
					System.out.println("image: " + f.getName());
					//System.out.println(" width : " + img.getWidth());
					//System.out.println(" height: " + img.getHeight());
					//System.out.println(" size  : " + f.length());
				} catch (final IOException e) {
					// handle errors here
				}
				
			}
			
			
		
			
	        new compareHist().run(unproImgsMat, matQueryImg);
			
			
			
			
			
			
			
			//SEND TO SEARCH WEIGHTING FUNCTIONS
			//Return int array
			
			//Sort int array
			//Sort arrayList based on int array
			
			
			
			/*
			 * try { retrievedImagesHI = hashmapobj.returnNearest5(icon,
			 * "HistogramIntersection"); } catch (NoSuchAlgorithmException e) { // TODO
			 * Auto-generated catch block System.out.println("FAIL1"); e.printStackTrace();
			 * } catch (IOException e) { // TODO Auto-generated catch block
			 * System.out.println("FAIL2"); e.printStackTrace(); }
			 */
 
			
			
			
			
			
			
			//taking top 5 images
			for (int i = 0; i < 5; i++) {	
				//System.out.println(retrievedImagesHI[i]);
				proImgs.add(unproImgs.get(i));
			}
		}
		return proImgs;
	
	}
}
