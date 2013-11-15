package es.ull.etsii.VPC;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageInfo extends PVAction{
	
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
      String info = "<html>File extension: ";
      info += fileExtension + "<br>";

      info += "Image size:     ";
      info += "x: " + img.getWidth() + "  y: " + img.getHeight() + "<br>";

      Color color;
      color = new Color (img.getRGB (0, 0),true);
      int min = color.getRed();
      int max = color.getRed();
      for( int i = 0; i < img.getWidth (); i++)
         for( int j = 0; j < img.getHeight (); j++){
            color = new Color (img.getRGB (i, j),true);
            if ((color.getRed()) < min)
               min = (color.getRed());
            if ((color.getRed()) > max)
               max = (color.getRed());
         }
      info += "Value range:    " + "[" + min + ", " + max + "]" + "<br></html>";

      return info;
      
   }
}