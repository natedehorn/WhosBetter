import java.text.DecimalFormat;

import dto.Static.Champion;
import dto.Stats.AggregatedStats;

public class championClass{
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

	public void showStats()
	{
		DecimalFormat df1 = new DecimalFormat("###.#");
		DecimalFormat df2 = new DecimalFormat("###.##");
		System.out.println("************************************************************");
		System.out.println("Ranked Stats for: " + championName + "  |  Total games played: " + df1.format(gamesPlayed)); //Print championName and gamesPlayed
		System.out.println("------------------------------------------------------------");
		System.out.println("                  Wins | " + df1.format(gamesWon)); //Print Wins
		System.out.println("                Losses | " + df1.format(gamesLost)); //Print Losses
		System.out.println("");
		System.out.print("        Win Percentage | " + (int)(winPercentage) + "% "); //Print out win percentage and round
		addEqualSpace("        Win Percentage | " + (int)(winPercentage) + "% ");
		percentToGraphic(winPercentage);
		System.out.println("");
		System.out.print("  Gold Earned Per Game | " + df1.format(goldEarned / 1000) + "k "); //Print avgGoldEarned and round
		addEqualSpace("  Gold Earned Per Game | " + df1.format(goldEarned / 1000) + "k ");
		goldToCoin(goldEarned/1000);
		System.out.println("");
		System.out.print("        Kills Per Game | " + df1.format(avgKills) + " "); //Print avgKills and round
		addEqualSpace("        Kills Per Game | " + df1.format(avgKills) + " ");
		killsToX(avgKills);
		System.out.print("       Deaths Per Game | " + df1.format(avgDeaths) + " "); //Print avgDeaths and round
		addEqualSpace("       Deaths Per Game | " + df1.format(avgDeaths) + " ");
		deathsToX(avgDeaths);
		System.out.print("      Assists Per Game | " + df1.format(avgAssists) + " "); //Print avgAssists and round
		addEqualSpace("      Assists Per Game | " + df1.format(avgAssists) + " ");
		assistsToX(avgAssists);
		System.out.println("");
		System.out.println(" Minion Kills Per Game | " + df2.format(minionKills)); //Print minionKills and round
		System.out.println(" Damage Dealt Per Game | " + df1.format(damageDealt / 1000) + "k"); // Print damageDealt and round
		System.out.println(" Damage Taken Per Game | " + df1.format(damageTaken / 1000) + "k"); //Print damageTaken and round
		System.out.println("");
		System.out.println("************************************************************");
		System.out.println("************************************************************");
		System.out.println("");
	}

	//-----Visual/Formatting Functions-----\\
	//Adds the correct amount of space between numbers and their visual representation
	private void addEqualSpace(String string) {
		int maxStringLength = 33; //The maximum possible length for an output string
		int stringLength = string.length();
		int spaceValue = maxStringLength - stringLength;
		for(int i = 0; i < spaceValue; i++)
		{
			System.out.print(" ");
		}
	}

	//Visual for Kills
	private void killsToX(float kills) {
		for(int i = 0; i < (int)kills; i++)
		{
			System.out.print("\033[92;40m|X\033[0m");
		}
		System.out.print("\033[92;40m|\033[0m");
		System.out.println("");
	}

	//Visual for Deaths
	private void deathsToX(float deaths) {
		for(int i = 0; i < (int)deaths; i++)
		{
			System.out.print("\033[91;40m|X\033[0m");
		}
		System.out.print("\033[91;40m|\033[0m");
		System.out.println("");
	}

	//Visual for Assists
	private void assistsToX(float assists) {
		for(int i = 0; i < (int)assists; i++)
		{
			System.out.print("\033[93;40m|X\033[0m");
		}
		System.out.print("\033[93;40m|\033[0m");
		System.out.println("");
	}

	//Visual for gold
	private void goldToCoin(float gold) {
		for(int i = 0; i < (int)gold; i++)
		{
			System.out.print("\033[90;103m| \033[0m");
		}
		System.out.print("\033[90;103m|\033[0m");
		System.out.println("");
	}

	//Visual for percent
	private void percentToGraphic(float num) {
		int firstValue = (int)(num * .3);
		int secondValue = 30 - firstValue;
		for(int i = 0; i < firstValue; i++)
		{
			System.out.print("\033[102m \033[0m");
		}
		for(int i = 0; i < secondValue; i++)
		{
			System.out.print("\033[101m \033[0m");
		}
		System.out.print(System.lineSeparator());
	}

	//Method to return championName
	public String getName() 
	{
		return championName;
	}

	//Method to return avgKills
	public float getKills() 
	{
		return avgKills;
	}

	//Method to return avgDeaths
	public float getDeaths() 
	{
		return avgDeaths;
	}

	//Method to return avgAssists
	public float getAssists() 
	{
		return avgAssists;
	}

}