package es.ull.etsii.VPC;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

public class AbsoluteHistogram extends PVAction{

   public AbsoluteHistogram () {
      super ("Absolute Histogram");
      // TODO Auto-generated constructor stub
   }
   
   @Override
   public void execute () {
     controller.absoluteHistogram (controller.getSelectedImage ());
   }

}
