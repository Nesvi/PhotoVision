package es.ull.etsii.VPC;

import java.io.File;
<<<<<<< HEAD
import java.io.IOException;
=======
import java.util.Vector;
>>>>>>> branch 'debugNesvi' of https://github.com/Nesvi/PhotoVision.git

import javax.swing.JFileChooser;

public class Controller {

   private PhotoVision view;
   private Vector<ImagePanel> images;
   
   Controller(PhotoVision fr){
      view = fr;
      images = new Vector<ImagePanel>();
   }
   
   public void openFile() throws IOException{
      JFileChooser openFile = new JFileChooser();
      
      if( openFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
	 File file = openFile.getSelectedFile ();
	 view.newImageInnerFrame (file);
      }
   }
   
<<<<<<< HEAD
=======
   public ImagePanel newImagePanel(File file){
   
      images.add(new ImagePanel (file));
      return images.get (images.size ()-1);
      
   }
   
   public void convertToGrey(){
	   
   }
>>>>>>> branch 'debugNesvi' of https://github.com/Nesvi/PhotoVision.git
}
