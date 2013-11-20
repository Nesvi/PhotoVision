package es.ull.etsii.VPC;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class InnerFrame extends JInternalFrame{
   
   private static final int WSX = 500;
   private static final int WSY = 500;
   public static Diff diff;
   public static HistogramSpecification spec;
   public static DifferenceMap mapdiff;
   
   private JPanel panel;
   
   public InnerFrame(String title, JPanel myPanel){
      super(title, true, true, true, true);
      panel = myPanel;
      add(panel);
      setSize ( WSX, WSY );
      setLocation(0,0);
      setVisible (true);
      addInternalFrameListener (new InternalFrameListener() {
         
         @Override
         public void internalFrameOpened (InternalFrameEvent arg0) {
            // TODO Auto-generated method stub
            
         }
         
         @Override
         public void internalFrameIconified (InternalFrameEvent arg0) {
            // TODO Auto-generated method stub
            
         }
         
         @Override
         public void internalFrameDeiconified (InternalFrameEvent arg0) {
            // TODO Auto-generated method stub
            
         }
         
         @Override
         public void internalFrameDeactivated (InternalFrameEvent arg0) {
            // TODO Auto-generated method stub
            
         }
         
         @Override
         public void internalFrameClosing (InternalFrameEvent arg0) {
            // TODO Auto-generated method stub
            
         }
         
         @Override
         public void internalFrameClosed (InternalFrameEvent arg0) {
            // TODO Auto-generated method stub
            
         }
         
         @Override
         public void internalFrameActivated (InternalFrameEvent arg0) {
            // TODO Auto-generated method stub
            switch(SharedData.currentTool){
            case 1:
            	diff.secondImageSelected ();
            case 2: 
               	spec.second();
            case 3:
               	mapdiff.secondImageSelected ();
            default:	
            }
            
         }
      });
      
   }
   
   public JPanel getPanel(){
      return panel;
   }
   
   
   
}