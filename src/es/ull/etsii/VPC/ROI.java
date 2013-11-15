package es.ull.etsii.VPC;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ROI extends PVAction{

   int ix,iy;
   BufferedImage image;
   boolean active;
   
   public ROI () {
      super ("ROI");
   }
   @Override
   public void execute () {
      showPanel();
   }
   
   private void showPanel () {
      JPanel panel = new ImagePanel(controller.getSelectedImage ());
      image = controller.getSelectedImage ();
      
      panel.addMouseListener (new MouseListener() {
         
         @Override
         public void mouseReleased (MouseEvent e) {
            
            if( e.getX () >= 0 && e.getX () < image.getWidth () && e.getY () >= 0 && e.getY () < image.getHeight ()){
               cut(ix,iy,e.getX (),e.getY ());
            }
            
         }
         
         @Override
         public void mousePressed (MouseEvent e) {
            if( e.getX () >= 0 && e.getX () < image.getWidth () && e.getY () >= 0 && e.getY () < image.getHeight ()){
               ix = e.getX ();
               iy = e.getY ();
               active = true;
            }
            
         }
         
         @Override
         public void mouseExited (MouseEvent e) {
            // TODO Auto-generated method stub
            active = false;
         }
         
         @Override
         public void mouseEntered (MouseEvent e) {
            // TODO Auto-generated method stub
            
         }
         
         @Override
         public void mouseClicked (MouseEvent e) {
            // TODO Auto-generated method stub
            
         }
      });
      
      JFrame frame = new JFrame ("ROI");
      frame.add (panel);
      frame.setSize ( 300, 300 );
      frame.setLocationRelativeTo ( null );
      frame.setVisible(true);
   }
   
   public void cut(int x, int y, int ex, int ey){
      controller.getView().newInnerFrame ("Photo",
		  new ImagePanel(
			image.getSubimage (x, y, ex-x, ey-y)
		  )
		 );
   }
   
}
