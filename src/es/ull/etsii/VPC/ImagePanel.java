package es.ull.etsii.VPC;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

   private static final long serialVersionUID = 1L;
   
   private BufferedImage image;
   String fileExtension;

   public ImagePanel(){
      image = null;
      fileExtension = "";
   }
   
   public ImagePanel(File file) {
       try {                
          image = ImageIO.read(file);
          //image = Operations.convertToGrey (ImageIO.read(file));//Provisional
          Operations.getAbsoluteHistogramData (image);

          String fileName = file.getName(); 
          int i = fileName.lastIndexOf('.');
   	   	  if (i > 0) {
   	   	     fileExtension = fileName.substring(i+1);
   	      }
	  
       } catch (IOException ex) {
            // handle exception...
       }
   }

   public ImagePanel(BufferedImage img) {
      image = img;
  }

   public void setImage(File file){
       try {                
          image = ImageIO.read(file);
          invalidate();
       } catch (IOException ex) {
            // handle exception...
       }
   }
   
   public BufferedImage getImage(){
      return image;
   }
   
   public String getFileExtension(){
	      return fileExtension;
   }
   
   @Override
   protected void paintComponent(Graphics g) {
       super.paintComponent(g);
       if(image != null)
       g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
   }

}