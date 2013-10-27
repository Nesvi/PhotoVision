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
   
}