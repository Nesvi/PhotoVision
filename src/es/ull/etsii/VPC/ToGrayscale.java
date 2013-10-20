package es.ull.etsii.VPC;

import javax.swing.text.View;

public class ToGrayscale extends PVAction{

   public ToGrayscale () {
      super ("Convert to grayscale");
   }
   
   @Override
   public void execute () {
      //controller.absoluteHistogram (controller.getSelectedImage ());
      controller.convertToGrayscale(controller.getSelectedImage ());
   }
   
}
