import java.util.Enumeration;
import java.util.Vector;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities; 

public class BarChart_AWT extends ApplicationFrame
{
	public BarChart_AWT( String applicationTitle , String chartTitle, Vector<championClass> blank )
	{

		super( applicationTitle );        
		JFreeChart barChart = ChartFactory.createBarChart(
				chartTitle,           
				"Champion",            
				"Value",            
				createDataset(blank),          
				PlotOrientation.VERTICAL,           
				true, true, false);

		ChartPanel chartPanel = new ChartPanel( barChart );        
		chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
		setContentPane( chartPanel ); 
	}

	//Populates graph with data
	private CategoryDataset createDataset( Vector<championClass> champVector)
	{
		//Define Bars
		final String kills = "Kills";        
		final String deaths = "Deaths";        
		final String assists = "Assists"; 
		
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );  

		for (Enumeration<championClass> e = champVector.elements(); e.hasMoreElements();)
		{
			championClass current = e.nextElement();
			final String championName = current.getName();
			dataset.addValue(current.getKills(), kills, championName);
			dataset.addValue(current.getDeaths(), deaths, championName);
			dataset.addValue(current.getAssists(), assists, championName);
		}
		
		return dataset; 
	}
}