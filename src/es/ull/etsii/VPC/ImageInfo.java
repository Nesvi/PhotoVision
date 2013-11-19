package es.ull.etsii.VPC;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.math.*;
import java.text.DecimalFormat;

public class ImageInfo extends PVAction{

   public static int brillo, contraste;
   
   public ImageInfo () {
      super ("Show image info");
   }

@Override
   public void execute () {
      show(controller.getSelectedImage ());
   }
   
   public void show (BufferedImage selectedImage) {
      JPanel myPanel = new JPanel();
      myPanel.add(new JLabel(getImageInfo (selectedImage, ((ImagePanel)((InnerFrame)controller.getView().getSelectedWindow()).getPanel ()).getFileExtension())));
      controller.getView().newInnerFrame("Info", myPanel);  
   }  
   
   public static String getImageInfo (BufferedImage img, String fileExtension){
   	//extension
      String info = "<html>File extension:   ";
      info += fileExtension + "<br>";
      
      //size
      info += "Image size:       ";
      info += "x: " + img.getWidth() + "  y: " + img.getHeight() + "<br>";
      
      //value range
      Color color;
      color = new Color (img.getRGB (0, 0),true);
      int min = color.getRed();
      int max = color.getRed();
      int acumBrillo = 0;
      for( int i = 0; i < img.getWidth (); i++)
         for( int j = 0; j < img.getHeight (); j++){
            color = new Color (img.getRGB (i, j),true);
            if ((color.getRed()) < min)
               min = (color.getRed());
            if ((color.getRed()) > max)
               max = (color.getRed());
            acumBrillo += color.getRed();
         }
      info += "Value range:      " + "[" + min + ", " + max + "]" + "<br>";

      //brightness
      brillo = (int)(acumBrillo / (img.getWidth() * img.getHeight()));
      info += "Brightness:    " + brillo + "<br>";
      
      //contrast
      int acumContraste = 0;
      int[] tabla = new int[256];
      for( int i = 0; i < img.getWidth (); i++)
         for( int j = 0; j < img.getHeight (); j++){
         	color = new Color (img.getRGB (i, j),true);
         	tabla[(int)color.getRed()]++;
         	acumContraste += (int)((color.getRed() - brillo) * (color.getRed() - brillo));
         }
      contraste = (int) Math.sqrt((double)(acumContraste/(img.getWidth() * img.getHeight())));
      info += "Contrast:         " + contraste + "<br>";
      
      //entropy
      double entropia = 0;
      double frec = 0;
      double total = img.getWidth() * img.getHeight();
      for( int i = 0; i < 256; i++){
      	if (tabla[i] > 0){
      		frec = (double)tabla[i]/total;
      		entropia += ((double)frec * Math.log((double)frec));
      	}
      }
      entropia = entropia * (-1);
      DecimalFormat df = new DecimalFormat("#.##");
      info += "Entropy:      " + df.format(entropia) + "<br></html>";
      
      
      return info;
      
   }
}