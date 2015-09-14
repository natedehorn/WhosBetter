import dto.Static.Champion;
import dto.Stats.AggregatedStats;

public class championClass {
	public String championName;
	public float gamesPlayed;
	public float gamesWon;
	public float gamesLost;
	public float winPercentage;
	public float goldEarned;
	public float avgKills;
	public float avgDeaths;
	public float avgAssists;
	public float minionKills;
	public float damageDealt;
	public float damageTaken;

	//constructor
	public championClass(Champion champID, AggregatedStats aggStats)
	{
		float totalGold = 0;
		float totalKills = 0;
		float totalDeaths = 0;
		float totalAssists = 0;
		float totalMinions = 0;
		float totalDamage = 0;
		float totalDamageT = 0;
        
		championName = champID.getName();
		gamesPlayed = aggStats.getTotalSessionsPlayed();
		gamesWon = aggStats.getTotalSessionsWon();
		gamesLost = aggStats.getTotalSessionsLost();
		winPercentage = (gamesWon / gamesPlayed) * 100;
		totalGold = aggStats.getTotalGoldEarned();
		goldEarned = totalGold / gamesPlayed;
		totalKills = aggStats.getTotalChampionKills();
		avgKills = totalKills / gamesPlayed;
		totalDeaths = aggStats.getTotalDeathsPerSession();
		avgDeaths = totalDeaths / gamesPlayed;
		totalAssists = aggStats.getTotalAssists();
		avgAssists = totalAssists / gamesPlayed;
		totalMinions = aggStats.getTotalMinionKills();
		minionKills = totalMinions / gamesPlayed;
		totalDamage = aggStats.getTotalDamageDealt();
		damageDealt = totalDamage / gamesPlayed;
		totalDamageT = aggStats.getTotalDamageTaken();
		damageTaken = totalDamageT / gamesPlayed;
	}
	
}
