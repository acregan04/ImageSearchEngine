import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Range;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class CompareHist {
    public ArrayList<Double>[] run(ArrayList<String> imgsDir, ArrayList<Mat> unproImgsMat, Mat srcBase) {
	
    	
    	 Mat hsvBase = new Mat();
    	 Imgproc.cvtColor(srcBase, hsvBase, Imgproc.COLOR_BGR2HSV );
    	 
    	 Mat histBase = new Mat();	 
    	 
    	 int hBins = 50, sBins = 60;
    	 int[] histSize = { hBins, sBins };
    	 // hue varies from 0 to 179, saturation from 0 to 255
    	 float[] ranges = { 0, 180, 0, 256 };
    	 // Use the 0-th and 1-st channels
    	 int[] channels = { 0, 1 };
    	 
         List<Mat> hsvBaseList = Arrays.asList(hsvBase);
         Imgproc.calcHist(hsvBaseList, new MatOfInt(channels), new Mat(), histBase, new MatOfInt(histSize), new MatOfFloat(ranges), false);
         Core.normalize(histBase, histBase, 0, 1, Core.NORM_MINMAX);
    	
         ArrayList<Double> meth1 = new ArrayList<Double>();
         ArrayList<Double> meth2 = new ArrayList<Double>();
         ArrayList<Double> meth3 = new ArrayList<Double>();
         ArrayList<Double> meth4 = new ArrayList<Double>();
         ArrayList<Double>[] allResults = (ArrayList<Double>[])new ArrayList[4];

         
    	for (int i=0; i<unproImgsMat.size(); i++) {	

    		Mat srcTest = unproImgsMat.get(i);
    		Mat hsvTest = new Mat();
    	    Imgproc.cvtColor(srcTest, hsvTest, Imgproc.COLOR_BGR2HSV );
    	    
            Mat histTest = new Mat();    
           
            List<Mat> hsvTestList = Arrays.asList(hsvTest);
            Imgproc.calcHist(hsvTestList, new MatOfInt(channels), new Mat(), histTest, new MatOfInt(histSize), new MatOfFloat(ranges), false);
            Core.normalize(histTest, histTest, 0, 1, Core.NORM_MINMAX);
            
            
            System.out.println("\n");
            for( int compareMethod = 0; compareMethod < 4; compareMethod++ ) {
            	
            	
                double baseTest = Imgproc.compareHist( histBase, histTest, compareMethod );
                System.out.println("Method " + compareMethod + ": " + baseTest);
                
                
            	if(compareMethod==0)
            		meth1.add(baseTest);
            	else if (compareMethod==1)
            		meth2.add(baseTest);
            	else if (compareMethod==2)
            		meth3.add(baseTest);
            	else if(compareMethod==3)
            		meth4.add(baseTest);
            		      
            }     	
		}
    	allResults[0]=meth1;
    	allResults[1]=meth2;
    	allResults[2]=meth3;
    	allResults[3]=meth4;

    	return allResults;

           
    }
}
