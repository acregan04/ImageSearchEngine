import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Item;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Range;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


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

		ArrayList<String> imgsDir = new ArrayList<String>();
		ArrayList<Image> unproImgs = new ArrayList<Image>();
		ArrayList<Image> proImgs = new ArrayList<Image>();
		ArrayList<Mat> unproImgsMat = new ArrayList<Mat>();

		Mat matQueryImg = Imgcodecs.imread(queryImgStr);

		if (dir.isDirectory()) { // make sure it's a directory
			for (final File f : dir.listFiles(IMAGE_FILTER)) {
				BufferedImage img = null;
				Mat matIm = null;

				try {
					img = ImageIO.read(f);
					matIm = Imgcodecs.imread(f.toString());

					imgsDir.add(f.toString());
					unproImgs.add(img);
					unproImgsMat.add(matIm);
					System.out.println("image: " + f.getName());

				} catch (final IOException e) {
					// handle errors here
				}		
			}

			//ArrayList<Double> meth1 = new ArrayList<Double>();
			ArrayList<Double>[] allResults = (ArrayList<Double>[])new ArrayList[4];	
			ArrayList<Image>[] fproImgs = (ArrayList<Image>[])new ArrayList[4];

			//meth1 = new CompareHist().run(imgsDir, unproImgsMat, matQueryImg);
			allResults = new CompareHist().run(imgsDir, unproImgsMat, matQueryImg);		


			//method 0 *Correlation*
			//method 1 *Chi-square*
			//method 2 *Intersection*
			//method 3 *Bhattacharyya*			
			int method = 2;
			
			
			//for(int i=0; i<allResults.length;i++) {

			fproImgs[method] = unproImgs;


			Map<Double, Image> map = new HashMap<Double, Image>();
			for(int y = 0; y < allResults[method].size(); y++) {
				map.put(allResults[method].get(y), fproImgs[method].get(y));   
			}

			Collections.sort(allResults[method]);
			fproImgs[method].clear();

			for(int z = 0; z < map.size(); z++) {
				fproImgs[method].add(map.get(allResults[method].get(z)));
			}


			// print sorted list
			for (double s : allResults[method]){
				System.out.println(s);
			}


			//}



			//taking top 10 images for meth2 and meth4
			if (method==1||method==3) {
				for(int i = 0; i < fproImgs[method].size(); i++) {
					proImgs.add(fproImgs[method].get(i));
				}
			}
			//taking top 10 images for meth1 and meth3
			if (method==0||method==2) {
				for (int i = fproImgs[method].size()-1; i > fproImgs[method].size() -11; i--) {	
					proImgs.add(fproImgs[method].get(i));
				}
			}

		}

		return proImgs;

	}

}
