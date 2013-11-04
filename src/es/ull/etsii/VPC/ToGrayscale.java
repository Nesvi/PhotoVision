package es.ull.etsii.VPC;

import java.awt.image.BufferedImage;

import javax.swing.text.View;

public class ToGrayscale extends PVAction{

   public ToGrayscale () {
      super ("Convert to grayscale");
   }
   
   @Override
   public void execute () {
      //controller.absoluteHistogram (controller.getSelectedImage ());
      convertToGrayscale(controller.getSelectedImage ());
   }
   
   public void convertToGrayscale (BufferedImage selectedImage) {
      controller.getView().newInnerFrame ("Photo",
	    				  new ImagePanel(
	    					Operations.convertToGrey (selectedImage)
	    				  )
	    				 );
   }
   
}
