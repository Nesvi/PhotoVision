package es.ull.etsii.VPC;

import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageInfo extends PVAction{
	
	public ImageInfo () {
		super ("Show image info");
	}

@Override
   public void execute () {
      getImageInfo(controller.getSelectedImage ());
   }
   
   public void getImageInfo (BufferedImage selectedImage) {
      JPanel myPanel = new JPanel();
      myPanel.add(new JLabel(Operations.getImageInfo (((ImagePanel)((InnerFrame)controller.getView().getSelectedWindow()).getPanel ()).getImage(), ((ImagePanel)((InnerFrame)controller.getView ().getSelectedWindow()).getPanel ()).getFileExtension())));
      controller.getView().newInnerFrame("Info", myPanel);  
   }  
}