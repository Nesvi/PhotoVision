package es.ull.etsii.VPC;

import java.awt.image.BufferedImage;

public class Diff extends PVAction{

   private BufferedImage firstImage = null;
   private BufferedImage secondImage = null;
   
   public Diff () {
      super ("Diff");
      // TODO Auto-generated constructor stub
   }

   @Override
   public void execute () {
      firstImage = controller.getSelectedImage ();
      SharedData.currentTool = 1;
   }
   
   public void secondImageSelected(){
      if(firstImage != null){
	 secondImage = controller.getSelectedImage ();
	 controller.getView().newInnerFrame ("Photo",
		  new ImagePanel(
			Operations.imageDifference (firstImage, secondImage)
		  )
		 );
	 
	 firstImage = null;
      }
      SharedData.currentTool = -1;
   
   }
   
   
}
