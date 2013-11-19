package es.ull.etsii.VPC;

import java.awt.image.BufferedImage;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.View;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class BrightnessContrast extends PVAction{

   public BrightnessContrast() {
   	super ("Brightness/Contrast");
   }
   
   
   @Override
   public void execute() {
   	launchDialog();
   }
   
   
   public void correctBrightnessContrast (BufferedImage selectedImage, double brillo, double contraste) {
      ImageInfo.getImageInfo (selectedImage, "none");
      controller.getView().newInnerFrame("Brightness/Contrast",
      		new ImagePanel(Operations.brightnessContrastCorrection(selectedImage, brillo, contraste,ImageInfo.brillo, ImageInfo.contraste)));
   }
   
   
   public void launchDialog(){
      final JFrame nopa = new JFrame ("Brightness value :");
      nopa.getContentPane ().setLayout (new BoxLayout(nopa.getContentPane (),BoxLayout.Y_AXIS));
      final JTextField boxA = new JTextField ("0",100);
      boxA.setMaximumSize( boxA.getPreferredSize() );
      JButton buttonA = new JButton ("Accept");
      
      buttonA.addActionListener (new ActionListener() {
         public void actionPerformed (ActionEvent arg0) {
            final JFrame nopd = new JFrame ("Contrast value:");
            nopd.getContentPane ().setLayout (new BoxLayout(nopd.getContentPane (),BoxLayout.Y_AXIS));
            final JTextField boxB = new JTextField ("0",100);
            boxB.setMaximumSize( boxB.getPreferredSize() );
            JButton buttonB = new JButton ("Accept");
         	
            buttonB.addActionListener (new ActionListener() {
            	public void actionPerformed (ActionEvent arg0) {
            		
            		correctBrightnessContrast(controller.getSelectedImage(), Double.parseDouble(boxA.getText()), Double.parseDouble(boxB.getText()));
            		nopd.invalidate ();
            		nopd.dispose();
            	}
            });
            nopd.add (boxB);
            nopd.add (buttonB);      
            
            nopd.setSize ( 300, 75 );
            nopd.setLocationRelativeTo ( null );
            nopd.setVisible(true);
            nopa.dispose();
         }
      });
      
      nopa.add (boxA);
      nopa.add (buttonA);      
      
      nopa.setSize ( 300, 75 );
      nopa.setLocationRelativeTo ( null );
      nopa.setVisible(true);
   }
   
}
