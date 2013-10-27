package es.ull.etsii.VPC;

import javax.swing.text.View;

public class ImageInfo extends PVAction{
	
	public ImageInfo () {
		super ("Show image info.");
	}

@Override
   public void execute () {
      controller.getImageInfo(controller.getSelectedImage ());
   }
   
}