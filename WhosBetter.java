import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.Scanner;
import main.java.riotapi.RiotApiException;
 
public class WhosBetter {
 
        public static void main(String[] args) throws RiotApiException {
        	
                DecimalFormat df1 = new DecimalFormat("###.#");
                DecimalFormat df2 = new DecimalFormat("###.##");
                
                //Get summoner1 info
                Scanner in = new Scanner(System.in);
                System.out.println("Enter first summoner name: ");
                String summonerName = in.nextLine();
                in.close();
                Player player1 = new Player(summonerName);
                Player player2 = new Player(summonerName);
                for (Enumeration<championClass> e = player1.championData.elements(); e.hasMoreElements();)
                {
                	championClass temp = e.nextElement();
                	System.out.println("******************************");
                	System.out.println(summonerName + "'s Ranked Stats for: " + temp.championName); //Print championName
                	System.out.println("------------------------------");
                	System.out.println("Total games played: " + df1.format(temp.gamesPlayed)); //Print gamesPlayed
                    System.out.println("Wins: " + df1.format(temp.gamesWon)); //Print Wins
                    System.out.println("Losses: " + df1.format(temp.gamesLost)); //Print Losses
                    System.out.println("Win Percentage: " + df2.format(temp.winPercentage) + "%"); //Print out win percentage and round
                    System.out.println("Gold Earned Per Game: " + df1.format(temp.goldEarned / 1000) + "k"); //Print avgGoldEarned and round
                    System.out.println("Kills Per Game: " + df1.format(temp.avgKills)); //Print avgKills and round
                    System.out.println("Deaths Per Game: " + df1.format(temp.avgDeaths)); //Print avgDeaths and round
                    System.out.println("Assists Per Game: " + df1.format(temp.avgAssists)); //Print avgAssists and round
                    System.out.println("Minion Kills Per Game: " + df2.format(temp.minionKills)); //Print minionKills and round
                    System.out.println("Damage Dealt Per Game: " + df1.format(temp.damageDealt / 1000) + "k"); // Print damageDealt and round
                    System.out.println("Damage Taken Per Game: " + df1.format(temp.damageTaken / 1000) + "k"); //Print damageTaken and round
                    System.out.println("******************************");
                }
                
                //Get summoner2 info
                /*System.out.println("Enter second summoner name: ");
                summonerName = in.nextLine().toLowerCase(); //Read in input and make lowercase
                Summoner summoner2 = api.getSummonersByName(Region.NA, summonerName).get(summonerName); //Set summoner2 */
                
                //System.out.println("Summoners to be compared: " + summoner1.getName() + " vs. " + summoner2.getName());
                //statSummary(summoner1);
        }
}