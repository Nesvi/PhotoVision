package es.ull.etsii.VPC;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
   
   int x,y,color;
   
   private ImagePanel self;
   
   public ImagePanel(){
      image = null;
      fileExtension = ""; 
      self = this;
   }
   
   public ImagePanel(File file) {
      try {                
         image = ImageIO.read(file);
         //image = Operations.convertToGrey (ImageIO.read(file));//Provisional
         Operations.getAbsoluteHistogramData (image);

         String fileName = file.getName(); 
         fileExtension = file.getName();
         fileExtension = fileExtension.replaceAll(".+\\.", "");
         
         /*int i = fileName.lastIndexOf('.');
  	   	  if (i > 0) {
  	   	     fileExtension = fileName.substring(i+1);
  	      }*/
	  
      } catch (IOException ex) {
           // handle exception...
      }
      self = this;
      addMouseMotionListener (new MouseMotionListener() {
         
         @Override
         public void mouseMoved (MouseEvent arg0) {
            x = arg0.getX ();
            y = arg0.getY ();
            self.repaint ();
            if( x < image.getWidth () && y < image.getHeight ())
               color = (new Color(image.getRGB (x, y))).getRed ();
            else
               color = 0;
            
         }
         
         @Override
         public void mouseDragged (MouseEvent arg0) {
            // TODO Auto-generated method stub
            
         }
      });
      
  }

   public ImagePanel(BufferedImage img) {
      image = img;
      self = this;
      addMouseMotionListener (new MouseMotionListener() {
         
         @Override
         public void mouseMoved (MouseEvent arg0) {
            x = arg0.getX ();
            y = arg0.getY ();
            self.repaint ();
            if( x < image.getWidth () && y < image.getHeight ())
               color = (new Color(image.getRGB (x, y))).getRed ();
            else
               color = 0;
            
         }
         
         @Override
         public void mouseDragged (MouseEvent arg0) {
            // TODO Auto-generated method stub
            
         }
      });
  }

   public void setImage(File file){
       try {                
          image = ImageIO.read(file);
          self.repaint ();
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
       g.drawString ("x= "+x+" ; y= "+y+" ; color= "+color, 0, image.getHeight ()+20);
   }

   
}
