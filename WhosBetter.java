import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;

import constant.PlayerStatSummaryType;
import constant.Region;
import constant.Season;
import dto.Static.Champion;
import dto.Stats.PlayerStatsSummary;
import dto.Stats.PlayerStatsSummaryList;
import dto.Stats.AggregatedStats;
import dto.Stats.ChampionStats;
import dto.Stats.RankedStats;
import dto.Summoner.Summoner;
import main.java.riotapi.RiotApi;
import main.java.riotapi.RiotApiException;
 
 
public class WhosBetter {
 
        public static void main(String[] args) throws RiotApiException {
               
        		//Set<String> keys = summoners.keySet(); //Gets all keys for a given map
        	
                RiotApi api = new RiotApi("e5688ad1-402b-4e51-94b1-d4eaef98930a"); //Riot API Key
                
                //Get summoner1 info
                Scanner in = new Scanner(System.in);
                System.out.println("Enter first summoner name: ");
                String summonerName = in.nextLine().toLowerCase(); //Read in input and make lowercase
                Summoner summoner1 = api.getSummonersByName(Region.NA, summonerName).get(summonerName); //Set summoner1
                
                //Get summoner2 info
                /*System.out.println("Enter second summoner name: ");
                summonerName = in.nextLine().toLowerCase(); //Read in input and make lowercase
                Summoner summoner2 = api.getSummonersByName(Region.NA, summonerName).get(summonerName); //Set summoner2 */
                in.close();
                
                //System.out.println("Summoners to be compared: " + summoner1.getName() + " vs. " + summoner2.getName());
                //statSummary(summoner1);
                champStatSummary(summoner1);
        }
        
        //This function is passed a type Summoner and prints out the statistics for each game type with wins and losses
        public static void statSummary(Summoner summ) throws RiotApiException {
        	
        	RiotApi api = new RiotApi("e5688ad1-402b-4e51-94b1-d4eaef98930a"); //Riot API Key
        	
        	PlayerStatsSummaryList statsListDto = api.getPlayerStatsSummary(Region.NA, Season.FIVE, summ.getId()); //Get summ statistics summary
            List<PlayerStatsSummary> summaries = statsListDto.getPlayerStatSummaries();
        	//-----Print out summoner win information by game type-----//
            for(PlayerStatsSummary statsSummary : summaries) {
                System.out.println(PlayerStatSummaryType.valueOf(statsSummary.getPlayerStatSummaryType()));
                System.out.println("Wins: " + statsSummary.getWins());
                if(statsSummary.getLosses() != 0) {
                    System.out.println("Losses: " + statsSummary.getLosses());
                }
                System.out.println("---------------");
            }
        }
        
        //This function is passed a type Summoner and prints out ranked statistics by champion with wins, losses, and win percentage
        public static void champStatSummary(Summoner summ) throws RiotApiException {
        	
        	RiotApi api = new RiotApi("e5688ad1-402b-4e51-94b1-d4eaef98930a"); //Riot API Key
        	
        	RankedStats rankedStats = api.getRankedStats(summ.getId()); //Get summoner1 ranked statistics
            List<ChampionStats> statList = rankedStats.getChampions();
        	
        	//-----Print out summoner data by champion-----//
            float winPercentage = 0;
            float totalSessions = 0;
            float totalGold = 0;
            float avgGoldEarned = 0;
            float totalKills = 0;
            float avgKillsPerSession = 0;
            int avgDeathsPerSession = 0;
            for(ChampionStats championStats : statList){
                    if(championStats.getId() != 0) { // id 0 for overall ranked stats
                    		System.out.println("******************************");
                            Champion champion = api.getDataChampion(championStats.getId()); //Getting champion info
                            System.out.println("Ranked Stats for: " + champion.getName()); //Print champ name
                            System.out.println("------------------------------");
                            
                            AggregatedStats aggregatedStats = championStats.getStats(); //Get all stats
                            totalSessions = aggregatedStats.getTotalSessionsPlayed(); //Get sessions played
                            System.out.println("Total games played: " + totalSessions); //Print totalSessions
                            System.out.println("Wins: " + aggregatedStats.getTotalSessionsWon()); //Print Wins
                            System.out.println("Losses: " + aggregatedStats.getTotalSessionsLost()); //Print Losses
                            
                            winPercentage = (aggregatedStats.getTotalSessionsWon() / totalSessions) * 100; //Calculate win percentage
                            System.out.println("Win Percentage: " + BigDecimal.valueOf(winPercentage).setScale(2, RoundingMode.HALF_DOWN) + "%"); //Print out win percentage and round
                            
                            totalGold = aggregatedStats.getTotalGoldEarned(); //Get total gold
                            avgGoldEarned = totalGold / totalSessions; //Avg gold = totalGold / totalSessionsPlayed
                            System.out.println("Average Gold Earned: " + BigDecimal.valueOf(avgGoldEarned/1000).setScale(1, RoundingMode.HALF_DOWN) + "k"); //Print avgGoldEarned and round
                            
                            totalKills = aggregatedStats.getTotalChampionKills();
                            avgKillsPerSession = totalKills / totalSessions;
                            System.out.println("Average Kills Per Game: " + BigDecimal.valueOf(avgKillsPerSession).setScale(1, RoundingMode.HALF_DOWN));
                            
                            avgDeathsPerSession = aggregatedStats.getTotalDeathsPerSession();
                            System.out.println("Average Deaths Per Game: " + avgDeathsPerSession);
                            
                            System.out.println("******************************");
                    }
            }
        }
}


/*
Top ranking metrics
1. Win percentage $$$
2. Gold Earned $$$
3. Kills per game $$$
4. Deaths per game
7. CS or CS per minute (include both?)
6. Assists
7. Damage Done
8. Damage Taken
*/