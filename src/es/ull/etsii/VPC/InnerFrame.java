package es.ull.etsii.VPC;

import java.io.File;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class InnerFrame extends JInternalFrame{
   
   private static final int WSX = 500;
   private static final int WSY = 500;
   
   private JPanel panel;
   
   public InnerFrame(String title, JPanel myPanel){
      super(title, true, true, true, true);
      panel = myPanel;
      add(panel);
      setSize ( WSX, WSY );
      setLocation(0,0);
      setVisible (true);
   }
   
   public JPanel getPanel(){
      return panel;
   }
   
   
}