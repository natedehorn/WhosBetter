import java.util.Enumeration;
import java.util.Scanner;
import main.java.riotapi.RiotApiException;
 
public class WhosBetter {
 
        public static void main(String[] args) throws RiotApiException {
        	
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
                	temp.showStats();
                }
                
                //Get summoner2 info
                /*System.out.println("Enter second summoner name: ");
                summonerName = in.nextLine().toLowerCase(); //Read in input and make lowercase
                Summoner summoner2 = api.getSummonersByName(Region.NA, summonerName).get(summonerName); //Set summoner2 */
                
                //System.out.println("Summoners to be compared: " + summoner1.getName() + " vs. " + summoner2.getName());
                //statSummary(summoner1);
        }
}