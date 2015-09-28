import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
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
	public static Vector<championClass> championData;

	//constructor
	public Player(String summName) throws RiotApiException
	{
		RiotApi api = new RiotApi("e5688ad1-402b-4e51-94b1-d4eaef98930a"); //Riot API Key
		championData = new Vector<championClass>();
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
				championData.addElement(temp);
			}
		}
	}

	public void displayStats()
	{
		for (Enumeration<championClass> e = Player.championData.elements(); e.hasMoreElements();)
		{
			e.nextElement().showStats();
		}
	}

	public static Vector<championClass> getVector() 
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
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 

		for (Enumeration<championClass> e = Player.getVector().elements(); e.hasMoreElements();)
		{
			championClass current = e.nextElement();
			final String championName = current.getName();
			dataset.addValue(current.getKills(), kills, championName);
			dataset.addValue(current.getDeaths(), deaths, championName);
			dataset.addValue(current.getAssists(), assists, championName);
		}
		
		final JFreeChart chart = ChartFactory.createBarChart3D(
				"3D Bar Chart Demo",      // chart title
				"Champion",               // domain axis label
				"Value",                  // range axis label
				dataset,                  // data
				PlotOrientation.VERTICAL, // orientation
				true,                     // include legend
				true,                     // tooltips
				false                     // urls
				);
		
		String filename = summonerName + "-chart.jpg";
		ChartUtilities.saveChartAsJPEG(new File(filename), chart, 500, 300);
		
	}
	
	
}