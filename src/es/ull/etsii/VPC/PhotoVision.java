package es.ull.etsii.VPC;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.googlecode.charts4j.Data;
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
   
   public PhotoVision(){
      controller = new Controller(this);
      
      initializeComponents();
      
      setSize ( WSX, WSY );
      setDefaultCloseOperation ( EXIT_ON_CLOSE );
      setTitle( "FotoReVisión" );
      setLocationRelativeTo ( null );
      setVisible(true);
      
      chartTest ();
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
      
      JMenuItem mOpenFile = new JMenuItem("Open file");
      mFile.add (mOpenFile);
      mOpenFile.addActionListener (new ActionListener() {
         public void actionPerformed (ActionEvent arg0) {
            controller.openFile();            
         }
      });
   
   }
   
   public void popImage(File file){
      desktop.add (new InnerFrame ("Photo", controller.newImagePanel (file)));
   }
   
   public void chartTest(){
      
      Plot plot = Plots.newPlot(Data.newData(0, 66.6, 33.3, 100));
      LineChart chart = GCharts.newLineChart(plot);
      
      JFrame frame = new JFrame();
      JLabel label = null;
      
      try {
	 label = new JLabel(new ImageIcon(ImageIO.read(new URL(chart.toURLString ()))));
	 System.out.println (chart.toURLString ());
      } catch (MalformedURLException e) {
	 e.printStackTrace();
      } catch (IOException e) {
	 e.printStackTrace();
      }
      
      frame.getContentPane().add(label, BorderLayout.CENTER);
      frame.pack();
      frame.setVisible(true);
   
   }

}
