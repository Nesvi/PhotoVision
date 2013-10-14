package es.ull.etsii.VPC;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
      setTitle( "FotoReVision" );
      setLocationRelativeTo ( null );
      setVisible(true);
      
      chartTest ();
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
            try {
				controller.openFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}            
         }
      });
   
   }
   
   public void popImage(File file) throws IOException{
      JFrame out = new JFrame();
      out.add (new ImagePanel(file));
      out.setSize ( WSX, WSY );
      out.setTitle( "FotoReVision" );
      out.setLocationRelativeTo ( null );
      out.setVisible(true);
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
   
   
   public BufferedImage convertToGrey(BufferedImage img) throws IOException{ //converts the image to grey scale
	   int width, height;
	   
	    BufferedImage colorFrame;
	     BufferedImage grayFrame = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
	   
	   WritableRaster  raster = grayFrame.getRaster();
	   
       for(int x = 0; x < raster.getWidth(); x++) {
           for(int y = 0; y < raster.getHeight(); y++){
               int argb = img.getRGB(x,y);
               int r = (argb >> 16) & 0xff;
               int g = (argb >>  8) & 0xff;
               int b = (argb      ) & 0xff;

               int l = (int) (.299 * r + .587 * g + .114 * b);
               raster.setSample(x, y, 0, l);
           }
       }
	   
       
       //Posible ayuda para el histograma
	   /*
	   int levels = 256;
       int bands = raster.getNumBands();
       int histogram[][] = new int[bands][levels];
       
       for (int x = raster.getMinX(); x < raster.getWidth(); x++) {
           for (int y = raster.getMinY(); y < raster.getHeight(); y++) {
               for (int b = 0; b < 3; b++) {
                   int p = raster.getSample(x, y, b);
                   histogram[b][p]++;
               }
           }
       }
       
       for (int b=0;b<histogram.length;b++) {
           System.out.println("Band:"+b);
           for (int i=0;i<histogram[b].length;i++) {
             System.out.println("\t"+i+"="+histogram[b][i]);
           }
       }
   */
       return grayFrame;
   }

}
