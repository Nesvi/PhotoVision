package es.ull.etsii.VPC;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.File;

public class Operations {
   
   public static BufferedImage convertToGrey (BufferedImage img){ // converts image into greyscale

      BufferedImage grayFrame = new BufferedImage (img.getWidth (), img.getHeight (), BufferedImage.TYPE_BYTE_GRAY);

      WritableRaster raster = grayFrame.getRaster ();

      for (int x = 0; x < raster.getWidth (); x++) {

         for (int y = 0; y < raster.getHeight (); y++) {

            int argb = img.getRGB (x, y);

            int r = (argb >> 16) & 0xff;

            int g = (argb >> 8) & 0xff;

            int b = (argb) & 0xff;

            int l = (int) (.299 * r + .587 * g + .114 * b);

            raster.setSample (x, y, 0, l);

         }
      }
      return grayFrame;
   }
   
   public static double[] getAbsoluteHistogramData (BufferedImage img){ // converts image into greyscale
      
      double[] output = new double[256];
      
      for( int i = 0; i < 256; i++)
         output[i] = 0;
      Color color;
      for( int i = 0; i < img.getWidth (); i++)
         for( int j = 0; j < img.getHeight (); j++){
            color = new Color (img.getRGB (i, j),true);
            output[color.getRed ()]++;
         }
      
      return output;
   }
   
   
   public static String getImageInfo (BufferedImage img, String fileExtension){
   	String info = "";
   	info = info + fileExtension +"\n" + "ejemplo";
	   return info;
   
   }
   
   public static int[][] getLOT (BufferedImage img){
 	int [][] LOT = new int[img.getWidth()][img.getHeight()];
 	Color color;
 	for (int i = 0; i < img.getWidth(); i++)
 		for (int j = 0; j < img.getWidth(); j++){
 			color = new Color (img.getRGB (i, j),true);
 			LOT[i][j] = color.getRed();
 		}
 	return LOT;
   }
   
   public static BufferedImage applyVout(BufferedImage image, int[] vout){
   
      BufferedImage copy = new BufferedImage (image.getWidth (), image.getHeight (), BufferedImage.TYPE_INT_ARGB);
      
      for( int i = 0; i < image.getWidth (); i++)
	 for( int j = 0; j < image.getHeight (); j++){
	    Color color = new Color (image.getRGB (i, j),true);
	    int s = vout[color.getRed ()];
	    //System.out.println(s);
	    copy.setRGB (i, j, (new Color(s,s,s)).getRGB());
	 
	 }
   
      return copy;
   }
   
   public static BufferedImage gammaCorrection (BufferedImage img, double gamma){
      BufferedImage gammaImage = new BufferedImage (img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
      WritableRaster raster = gammaImage.getRaster ();
            
      int [][] LOT = getLOT (img);
      int vIn;
      double a;
      double b;
      int vOut;
      Color newGrey;
      int rgb;
           
      for (int i = 0; i < img.getWidth(); i++)
	 for (int j = 0; j < img.getHeight(); j++){
	    vIn = LOT[i][j];
            a =((double)vIn)/255;
            b = Math.pow(a, gamma);
            vOut = (int)(b*255);
            newGrey = new Color(vOut, vOut, vOut);
            rgb = newGrey.getRGB();
            gammaImage.setRGB(i, j, rgb);
          }
      return gammaImage;
   }
   
}