package es.ull.etsii.VPC;

import java.io.File;

import javax.swing.JFileChooser;


public class Controller {

   private PhotoVision view;
   
   Controller(PhotoVision fr){
      view = fr;
   }
   
   public void openFile(){
      JFileChooser openFile = new JFileChooser();
      
      if( openFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
	 File file = openFile.getSelectedFile ();
	 view.popImage (file);
      }
   }
   
   public void convertToGrey(){
	   
   }
}
