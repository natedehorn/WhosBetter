import java.awt.Color;
import java.awt.GradientPaint;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import constant.Region;
import dto.Stats.AggregatedStats;
import dto.Stats.ChampionStats;
import dto.Stats.RankedStats;
import dto.Summoner.Summoner;
import main.java.riotapi.RiotApi;
import main.java.riotapi.RiotApiException;

public class Player {
	public String summonerName;
	public static List<championClass> championData;

	//constructor
	public Player(String summName) throws RiotApiException
	{
		RiotApi api = new RiotApi("e5688ad1-402b-4e51-94b1-d4eaef98930a"); //Riot API Key
		championData = new ArrayList<championClass>();
		summonerName = summName.toLowerCase();
		Summoner summoner = api.getSummonersByName(Region.NA, summonerName).get(summonerName); //Set summoner
		RankedStats rankedStats = api.getRankedStats(summoner.getId()); //Get summoner ranked statistics
		List<ChampionStats> statList = rankedStats.getChampions(); //Get ranked champion statistics

		for(ChampionStats championStats : statList)
		{
			if(championStats.getId() != 0) //id 0 for overall ranked stats
			{
				AggregatedStats aggregatedStats = championStats.getStats(); //Get all stats
				championClass temp = new championClass(api.getDataChampion(championStats.getId()), aggregatedStats);
				championData.add(temp);
			}
		}
		championData.sort(championClass.champNameComparator);
	}

	public void displayStats()
	{
		for (Iterator<championClass> e = Player.championData.iterator(); e.hasNext();)
		{
			e.next().showStats();
		}
	}

	public static List<championClass> getList() 
	{
		return championData;
	}

	public String getName() 
	{
		return summonerName;
	}

	public void graph() throws IOException 
	{
		final String kills = "Kills";              
		final String deaths = "Deaths";              
		final String assists = "Assists"; 
		final String gold = "Gold (in Thousands)";
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 

		for (Iterator <championClass> e = Player.getList().iterator(); e.hasNext();)
		{
			championClass current = e.next();
			final String championName = current.getName();
			dataset.addValue(current.getKills(), kills, championName);
			dataset.addValue(current.getDeaths(), deaths, championName);
			dataset.addValue(current.getAssists(), assists, championName);
			dataset.addValue(current.getGold()/1000, gold, championName);
		}

		final JFreeChart chart = ChartFactory.createBarChart3D(
				"Champion Data",      // chart title
				"Champion",               // domain axis label
				"Value",                  // range axis label
				dataset,                  // data
				PlotOrientation.VERTICAL, // orientation
				true,                     // include legend
				true,                     // tooltips
				false                     // urls
				);

		// NOW DO SOME OPTIONAL CUSTOMIZATION OF THE CHART...

		// set the background color for the chart...
		chart.setBackgroundPaint(Color.white);

		// get a reference to the plot for further customization...
		final CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		// set the range axis to display integers only...
		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		// disable bar outlines...
		final BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);

		// set up gradient paints for series...
		final GradientPaint gp0 = new GradientPaint(
				0.0f, 0.0f, Color.green, 
				0.0f, 0.0f, Color.lightGray
				);

		final GradientPaint gp1 = new GradientPaint(
				0.0f, 0.0f, Color.red, 
				0.0f, 0.0f, Color.lightGray
				);

		final GradientPaint gp2 = new GradientPaint(
				0.0f, 0.0f, Color.blue, 
				0.0f, 0.0f, Color.lightGray
				);

		final GradientPaint gp3 = new GradientPaint(
				0.0f, 0.0f, Color.yellow, 
				0.0f, 0.0f, Color.lightGray
				);

		renderer.setSeriesPaint(0, gp0);
		renderer.setSeriesPaint(1, gp1);
		renderer.setSeriesPaint(2, gp2);
		renderer.setSeriesPaint(3, gp3);

		final CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));
		// OPTIONAL CUSTOMIZATION COMPLETED.

		String filename = summonerName + "-chart.jpg";
		ChartUtilities.saveChartAsJPEG(new File(filename), chart, 7000, 1000);

	}

}