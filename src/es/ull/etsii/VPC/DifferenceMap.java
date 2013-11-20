package es.ull.etsii.VPC;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class DifferenceMap extends PVAction{

   public BufferedImage firstImage, secondImage;
   
   public DifferenceMap () {
      super ("Difference Map");
   }
   
   public void execute () {
      firstImage = controller.getSelectedImage ();
      SharedData.currentTool = 3;
   }
   
   public void secondImageSelected(){
      if(firstImage != null){
	 secondImage = controller.getSelectedImage ();
	 mapDiff(firstImage, secondImage); 	 
	 firstImage = null;
      }
      SharedData.currentTool = -1;
   
   }
   
   public static BufferedImage deepCopy(BufferedImage bi) {
      ColorModel cm = bi.getColorModel();
      boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
      WritableRaster raster = bi.copyData(null);
      return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
   
   public void mapDiff(final BufferedImage A, final BufferedImage B){
      
      final JFrame nopd = new JFrame ("Umbral:");
      nopd.getContentPane ().setLayout (new BoxLayout(nopd.getContentPane (),BoxLayout.Y_AXIS));
      final JTextField box = new JTextField ("0",100);
      box.setMaximumSize( box.getPreferredSize() );
      JButton button = new JButton ("Accept");
      
      button.addActionListener (new ActionListener() {
         public void actionPerformed (ActionEvent arg0) {
         	int umbral = Integer.parseInt(box.getText());
         	BufferedImage diff = Operations.imageDifference (A, B);
         	
         	/*
         	BufferedImage bufImg = ImageIO.read( imageURL );
         	    BufferedImage convertedImg = new BufferedImage(bufImg.getWidth(), bufImg.getHeight(), BufferedImage.TYPE_USHORT_565_RGB);
         	    convertedImg.getGraphics().drawImage(bufImg, 0, 0, null);
         	*/
         	BufferedImage output = new BufferedImage(A.getWidth(), A.getHeight(), BufferedImage.TYPE_INT_ARGB);
         	output.getGraphics().drawImage(A, 0, 0, null);
         	//BufferedImage output = deepCopy (A);
         	
         	for(int i = 0; i < A.getWidth (); i++)
         	   for( int j = 0; j < A.getHeight (); j++)
         	      if( new Color(diff.getRGB (i, j)).getRed() >= umbral)
         		 output.setRGB (i, j, (new Color(255,0,0)).getRGB ());
         	
         	controller.getView().newInnerFrame ("Photo",
    		  new ImagePanel(
    			output
    		  )
    		 );
    	
         	nopd.dispose ();
         }
         
      });
      
      nopd.add (box);
      nopd.add (button);      
      
      nopd.setSize ( 300, 75 );
      nopd.setLocationRelativeTo ( null );
      nopd.setVisible(true);
   	
   }

   
}
