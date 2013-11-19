package es.ull.etsii.VPC;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

public class AccumulativeHistogram extends PVAction{

   public AccumulativeHistogram () {
      super ("Accumulative Histogram");
      // TODO Auto-generated constructor stub
   }
   
   @Override
   public void execute () {
     controller.accumulativeHistogram (controller.getSelectedImage ());
   }

}
