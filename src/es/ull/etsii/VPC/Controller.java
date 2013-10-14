package es.ull.etsii.VPC;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

public class Controller {

   private FotoReVision view;
   
   Controller(FotoReVision fr){
      view = fr;
   }
   
   public void openFile() throws IOException{
      JFileChooser openFile = new JFileChooser();
      
      if( openFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
	 File file = openFile.getSelectedFile ();
	 view.popImage (file);
      }
   }
   
}
