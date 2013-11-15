package es.ull.etsii.VPC;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LinealTransform extends PVAction{

   private int numberOfPoints;
   public LinealTransform () {
      super ("Lineal transformation");
   }

   public void execute(){
      launchDialog();
   }
   
   public void launchDialog(){
      JFrame nopd = new JFrame ("Number of inner points:");
      nopd.getContentPane ().setLayout (new BoxLayout(nopd.getContentPane (),BoxLayout.Y_AXIS));
      final JTextField box = new JTextField ("0",100);
      box.setMaximumSize( box.getPreferredSize() );
      JButton button = new JButton ("Accept");
      
      button.addActionListener (new ActionListener() {
         public void actionPerformed (ActionEvent arg0) {
            insertPoints(Integer.parseInt (box.getText ()));
         }
      });
      
      nopd.add (box);
      nopd.add (button);      
      
      nopd.setSize ( 300, 75 );
      nopd.setLocationRelativeTo ( null );
      nopd.setVisible(true);
   }
   
   public void insertPoints(final int points){
      JFrame ip = new JFrame ("Insert points:");
      ip.getContentPane ().setLayout (new GridLayout (3+points+1, 2));
      
      final JTextField begin = new JTextField ("0");
      final JTextField end= new JTextField ("0");
      
      ip.add(new JLabel("x:"));
      ip.add(new JLabel("y:"));
      ip.add(new JLabel("0"));
      ip.add(begin);
      ip.add(new JLabel("255"));
      ip.add(end);
      
      final JTextField[] innerPoints = new JTextField[points*2];
      
      for( int  i = 0; i < points; i++){ 
	 innerPoints[i*2] = new JTextField("0"); 
	 innerPoints[i*2+1] = new JTextField("0");
	 ip.add (innerPoints[i*2]);
	 ip.add (innerPoints[i*2+1]);
      }
      
      JButton accept = new JButton("Accept");
      ip.add (accept);
      accept.addActionListener (new ActionListener() {
         
         @Override
         public void actionPerformed (ActionEvent arg0) {
            int[] output = new int[points*2+4];
            output[0] = 0;
            output[1] = Integer.parseInt (begin.getText ());
            output[points*2+2] = 255;
            output[points*2+3] = Integer.parseInt(end.getText ());
            
            for( int i = 0; i < points; i++){
         	output[i*2+2] = Integer.parseInt (innerPoints[i*2].getText ());
         	output[i*2+3] = Integer.parseInt (innerPoints[i*2+1].getText ());
            }
            
            applyLinearTransformation (output);
            
         }
      });
     
      
      ip.setSize ( 300, 300 );
      ip.setLocationRelativeTo ( null );
      ip.setVisible(true);
    }
   
   private void applyLinearTransformation(int[] points){
      
      int[] vout = new int[255];
      
      for(int i = 0; i < 255; i++){
	 vout[i] = getColorTransformation(i,points);
      }
      
      controller.getView().newInnerFrame ("Photo",
		  new ImagePanel(
			Operations.applyVout(controller.getSelectedImage (), vout)
		  )
		 );
      
   }
   
   private int getColorTransformation(int color, int[] points){
	   double dx,dy;
           for(int i = 0; i < points.length/2; i++){
            		if(color <= points[i*2]){
            			if (i == 0){
            				dx = points[(i)*2] - points[(i+1)*2];
            				dy = points[(i)*2+1] - points[(i+1)*2+1];
            			}
            			else{
            				dx = points[(i)*2] - points[(i-1)*2];
            				dy = points[(i)*2+1] - points[(i-1)*2+1];
            			}
            		   double m = dy/dx;
            		   
            		   int n = (int) (points[(i)*2+1] - points[(i)*2]*m); 
            		   return (int) (color*m + n);
            		}
             }
             return 0;
   }
   
}
