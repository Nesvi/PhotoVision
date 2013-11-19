package es.ull.etsii.VPC;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.DataUtil;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.LineChart;
import com.googlecode.charts4j.Plot;
import com.googlecode.charts4j.Plots;

public class PhotoVision extends JFrame{

   private static final int WSX = 500;
   private static final int WSY = 500;
   
   private Controller controller;
   
   private BorderLayout mainLayout;
   
   private JMenuBar menuBar;
   
   private JDesktopPane desktop;
   
   private JMenu mFilters;
   
   public PhotoVision(){
      
      initializeComponents();
      
      controller = new Controller(this);
      controller.initActions ();
      
      setSize ( WSX, WSY );
      setDefaultCloseOperation ( EXIT_ON_CLOSE );
      setTitle( "PhotoVision" );
      setLocationRelativeTo ( null );
      setVisible(true);
   }
   
   public void initializeComponents(){
      
      mainLayout = new BorderLayout();
      setLayout (mainLayout);
      
      desktop = new JDesktopPane ();
      desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
      add (desktop, BorderLayout.CENTER);
      
      desktop.setVisible (true);
      
      
      createMenu();
      
   }
   
   public void createMenu (){
   
      menuBar = new JMenuBar ();
      setJMenuBar (menuBar);
      
      JMenu mFile = new JMenu("File");
      menuBar.add (mFile);
      
      mFilters = new JMenu("Tools");
      menuBar.add (mFilters);
      
      JMenuItem mOpenFile = new JMenuItem("Open file");
      mFile.add (mOpenFile);
      mOpenFile.addActionListener (new ActionListener() {
         public void actionPerformed (ActionEvent arg0) {
            controller.openFile();            
         }
      });
   
   }
   
   public void addImageFilterToMenu(final PVAction ifil){
         
      JMenuItem mf = new JMenuItem(ifil.name);
      mFilters.add (mf);
      
      mf.addActionListener (new ActionListener() {
         public void actionPerformed (ActionEvent arg0) {
            ifil.execute();
         }
      });
      
   }
   
   public InnerFrame newInnerFrame(String title, JPanel in){
      InnerFrame myInnerFrame = new InnerFrame (title, in);
      desktop.add (new InnerFrame (title, in));
      return myInnerFrame;
   }
   /*
   public void newImageInnerFrame(File file){
      ImagePanel panel = controller.newImagePanel (file);
      desktop.add (new InnerFrame ("Photo", panel));
      //controller.absoluteHistogram(panel.getImage ());//Provisional
   }
   
   public void newImageInnerFrame(BufferedImage file){
      ImagePanel panel = controller.newImagePanel (file);
      desktop.add (new InnerFrame ("Photo", panel));
      //controller.absoluteHistogram(panel.getImage ());//Provisional
   }
   
   public void newChartInnerFrame(BufferedImage img){
      InnerFrame chartFrame = new InnerFrame ("Chart", new ImagePanel (img));
      chartFrame.setSize (300, 300);
      desktop.add (chartFrame);
   }
   
   public void newInfoInnerFrame(String infoImage ){
      //InnerFrame infoFrame = new InnerFrame ("Information", new ImagePanel (infoImage));
      //infoFrame.setSize (300, 300);
      //desktop.add (infoFrame);
   	System.out.println(infoImage);
   }
    */
   public JInternalFrame getSelectedWindow () {
      return desktop.getSelectedFrame ();
   }

}
