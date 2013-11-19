package es.ull.etsii.VPC;

import java.awt.image.BufferedImage;

public class Equalization extends PVAction{

   public Equalization () {
      super ("Equalization");
   }
   
   public void execute()
   {
      
      double[] absoluteHistogramA = Operations.getAbsoluteHistogramData (controller.getSelectedImage ());
      double[] absoluteHistogramB = new double[256];
      
      int pixelsr = 0;
      double[] linearHistogram =new double[256];
      
      for(int i = 0; i < 256; i++){
	 linearHistogram[i] = 1;
	 pixelsr += 1;
      }
      
      absoluteHistogramB[0] = linearHistogram[0];
      for(int i = 1; i < 256; i++){
	 absoluteHistogramB[i] = absoluteHistogramB[i-1] + linearHistogram[i];	 
      }
      
      int pixels = controller.getSelectedImage ().getHeight () * controller.getSelectedImage ().getWidth ();
      
      int M = 256;
      double[] P0 = new double[256];
      double[] Pr = new double[256];
      
      for(int i = 0; i < M; i++)
	 P0[i] = absoluteHistogramA[i]/pixels;
      for(int i = 0; i < M; i++)
	 Pr[i] = absoluteHistogramB[i]/pixelsr;
      
      int[] T = new int[256];
      
      int j;
      for( int a = 0; a < M; a++){
	j = M-1;
	do{
	   
	   T[a] = j;
	   j--;
	   
	}while(j >= 0 && P0[a] <= Pr[j]);
      
      }
      
      BufferedImage copy ;

      copy = Operations.applyVout (controller.getSelectedImage (), T);
      controller.getView().newInnerFrame ("Photo",
		  new ImagePanel(
			copy
		  )
		 );
   }

}
