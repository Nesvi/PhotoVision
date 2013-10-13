package es.ull.etsii.VPC;

import java.io.File;
import java.util.Vector;

import javax.swing.JFileChooser;


public class Controller {

   private PhotoVision view;
   private Vector<ImagePanel> images;
   
   Controller(PhotoVision fr){
      view = fr;
      images = new Vector<ImagePanel>();
   }
   
   public void openFile(){
      JFileChooser openFile = new JFileChooser();
      
      if( openFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
	 File file = openFile.getSelectedFile ();
	 view.popImage (file);
      }
   }
   
   public ImagePanel newImagePanel(File file){
   
      images.add(new ImagePanel (file));
      return images.get (images.size ()-1);
      
   }
   
   public void convertToGrey(){
	   
   }
}
