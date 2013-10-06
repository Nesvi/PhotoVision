package es.ull.etsii.VPC;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class FotoReVision extends JFrame{

   private static final int WSX = 500;
   private static final int WSY = 500;
   
   private Controller controller;
   
   private BorderLayout mainLayout;
   
   private JMenuBar menuBar;
   
   private JFrame[] JFrames;
   
   public FotoReVision(){
      controller = new Controller(this);
      JFrames = new JFrame[10];
      
      initializeComponents();
      
      setSize ( WSX, WSY );
      setDefaultCloseOperation ( EXIT_ON_CLOSE );
      setTitle( "FotoReVisión" );
      setLocationRelativeTo ( null );
      setVisible(true);

   }
   
   public void initializeComponents(){
      
      mainLayout = new BorderLayout();
      setLayout (mainLayout);
      
      createMenu();
      
      
   }
   
   public void createMenu (){
   
      menuBar = new JMenuBar ();
      setJMenuBar (menuBar);
      
      JMenu mFile = new JMenu("File");
      menuBar.add (mFile);
      
      JMenuItem mOpenFile = new JMenuItem("Open file");
      mFile.add (mOpenFile);
      mOpenFile.addActionListener (new ActionListener() {
         public void actionPerformed (ActionEvent arg0) {
            controller.openFile();            
         }
      });
   
   }
   
   public void popImage(File file){
      JFrame out = new JFrame();
      out.add (new ImagePanel(file));
      out.setSize ( WSX, WSY );
      out.setTitle( "FotoReVisión" );
      out.setLocationRelativeTo ( null );
      out.setVisible(true);
   }

}
