import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

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
		RankedStats rankedStats = api.getRankedStats(summoner.getId()); //Get summoner1 ranked statistics
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
}