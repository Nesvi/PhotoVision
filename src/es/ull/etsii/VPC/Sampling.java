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

public class Sampling extends PVAction{

   public Sampling() {
   	super ("Sampling");
   }
   
   
   @Override
   public void execute() {
   	launchDialog();
   }
   
   
   public void newSampling (BufferedImage selectedImage, int sample) {
      controller.getView().newInnerFrame("Sampling",
      		new ImagePanel(Operations.newSampling(selectedImage, sample)));
   }
   
   
   public void launchDialog(){
      final JFrame nopd = new JFrame ("New sampling:");
      nopd.getContentPane ().setLayout (new BoxLayout(nopd.getContentPane (),BoxLayout.Y_AXIS));
      final JTextField box = new JTextField ("0",100);
      box.setMaximumSize( box.getPreferredSize() );
      JButton button = new JButton ("Accept");
      
      button.addActionListener (new ActionListener() {
         public void actionPerformed (ActionEvent arg0) {
         	newSampling(controller.getSelectedImage(), (int)Double.parseDouble(box.getText()));
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