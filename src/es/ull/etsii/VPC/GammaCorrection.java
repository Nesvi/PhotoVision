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

public class GammaCorrection extends PVAction{

   public GammaCorrection() {
   	super ("Gamma Correction");
   }
   
   
   @Override
   public void execute() {
   	launchDialog();
   }
   
   
   public void correctGamma (BufferedImage selectedImage, double gamma) {
      controller.getView().newInnerFrame("Gamma",
      		new ImagePanel(Operations.gammaCorrection(selectedImage, gamma)));
   }
   
   
   public void launchDialog(){
      JFrame nopd = new JFrame ("Gamma value:");
      nopd.getContentPane ().setLayout (new BoxLayout(nopd.getContentPane (),BoxLayout.Y_AXIS));
      final JTextField box = new JTextField ("0",100);
      box.setMaximumSize( box.getPreferredSize() );
      JButton button = new JButton ("Accept");
      
      button.addActionListener (new ActionListener() {
         public void actionPerformed (ActionEvent arg0) {
         	correctGamma(controller.getSelectedImage(), Double.parseDouble(box.getText()));
         }
      });
      
      nopd.add (box);
      nopd.add (button);      
      
      nopd.setSize ( 300, 75 );
      nopd.setLocationRelativeTo ( null );
      nopd.setVisible(true);
   }
   
}
