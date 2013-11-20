package es.ull.etsii.VPC;

import java.awt.image.BufferedImage;

public class Equalization extends PVAction{

   public Equalization () {
      super ("Equalization");
   }
   
   public void execute(){
      BufferedImage copia = new BufferedImage(controller.getSelectedImage ().getWidth (),controller.getSelectedImage ().getHeight (), BufferedImage.TYPE_INT_ARGB);
      double size = copia.getWidth() * copia.getHeight();
      double[] lut = Operations.getAbsoluteHistogramData (controller.getSelectedImage ());
      
      for(int i = 1; i < 256; i++)
	 lut[i] += lut[i-1];
      
      int[] vout = new int[256];
      double m = 256;
      //partimos la variedad de pixeles por la cantidad de pixeles
      double var = m / size;

      //creamos la look up table para el valor de salida
      for(int i = 0; i < m; i++) {
              //realizamos el calculo de Vout segu la formula max[0, round(M/size * C0(Vin)) - 1]
              int Vout = (int) Math.round((var * lut[i]) - 1);
              if (Vout < 0) {
                      Vout = 0;
              }
              vout[i] = Vout;
      }
      
      controller.getView().newInnerFrame ("Photo",
		  new ImagePanel(
			Operations.applyVout (controller.getSelectedImage (), vout)
		  )
		 );
   }

}
