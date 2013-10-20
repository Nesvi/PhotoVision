package es.ull.etsii.VPC;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import com.googlecode.charts4j.DataUtil;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.LineChart;
import com.googlecode.charts4j.Plot;
import com.googlecode.charts4j.Plots;

public class Controller {

   private PhotoVision view;
   private Vector<ImagePanel> images;
   private Vector<PVAction> imageFilters;
   
   Controller(PhotoVision fr){
      view = fr;
      images = new Vector<ImagePanel>();
      imageFilters = new Vector<PVAction>();
   }
   
   public void initActions(){ //Here is where you add the actions to the system
      PVAction.setController (this);
      new ToGrayscale();   
   }
   
   public void openFile(){
      JFileChooser openFile = new JFileChooser();
      
      if( openFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
	 File file = openFile.getSelectedFile ();
	 view.newImageInnerFrame (file);
      }
   }
   
   public ImagePanel newImagePanel(File file){//Maybe useless
   
      images.add(new ImagePanel (file));
      return images.get (images.size ()-1);
      
   }
   
   public ImagePanel newImagePanel(BufferedImage file){ //Maybe useless
      
      images.add(new ImagePanel (file));
      return images.get (images.size ()-1);
      
   }
   
   public void convertToGrey(){
	   
   }

   public void addPVAction (PVAction imageFilter) {
      imageFilters.add(imageFilter);
      view.addImageFilterToMenu (imageFilter);
   }
   
   public void absoluteHistogram(BufferedImage img){//Move to controller

      double[] chartData = Operations.getAbsoluteHistogramData (img);
      
      Plot plot = Plots.newPlot(DataUtil.scale (chartData));
      LineChart chart = GCharts.newLineChart(plot);
      chart.setSize (300, 300);
      
      try {
	 view.newChartInnerFrame (ImageIO.read(new URL(chart.toURLString ())));
      } catch (MalformedURLException e) {
	 e.printStackTrace();
      } catch (IOException e) {
	 e.printStackTrace();
      }
   
   }

   public BufferedImage getSelectedImage () {
      return ((ImagePanel)((InnerFrame)view.getSelectedWindow()).getPanel ()).getImage ();
   }

   public void convertToGrayscale (BufferedImage selectedImage) {
      view.newImageInnerFrame (Operations.convertToGrey (selectedImage));
      
   }
   
}
