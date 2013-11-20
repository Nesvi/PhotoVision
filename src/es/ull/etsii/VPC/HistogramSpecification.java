package es.ull.etsii.VPC;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;


public class HistogramSpecification extends PVAction{

   private double[] absoluteHistogramA;
   private double[] absoluteHistogramB;
   private int pixels;
   private int pixelsr;
   private BufferedImage originalImage;
   
   public HistogramSpecification () {
      super ("Histogram specification");
   }
   
   
   public void execute(){
      
      absoluteHistogramA = Operations.getAbsoluteHistogramData (controller.getSelectedImage ());
      
      for( int i = 1; i < 256; i++)
	 absoluteHistogramA[i] = absoluteHistogramA[i] + absoluteHistogramA[i-1];
   
      pixels = controller.getSelectedImage ().getWidth () * controller.getSelectedImage ().getHeight ();
      originalImage = controller.getSelectedImage ();
      SharedData.currentTool = 2;
   }
   
   public void second(){
      absoluteHistogramB = Operations.getAbsoluteHistogramData (controller.getSelectedImage ());
      
      for( int i = 1; i < 256; i++)
	 absoluteHistogramB[i] = absoluteHistogramB[i] + absoluteHistogramB[i-1];
      
      pixelsr = controller.getSelectedImage ().getWidth () * controller.getSelectedImage ().getHeight ();
      
      histogramSpecification ();
      
      SharedData.currentTool = -1;
   }
   
   private void histogramSpecification()
   {
      
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
      
      controller.getView().newInnerFrame ("Photo",
		  new ImagePanel(
			Operations.applyVout (originalImage, T)
		  )
		 );
   }
   
}
