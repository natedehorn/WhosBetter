import java.util.Scanner;
import main.java.riotapi.RiotApiException;
 
public class WhosBetter {
 
        public static void main(String[] args) throws RiotApiException {
        	
                //Get summoner1 info
                Scanner in = new Scanner(System.in);
                System.out.println("Enter first summoner name: ");
                String summonerName = in.nextLine();
                Player player1 = new Player(summonerName);
                player1.displayStats();
                System.out.println("");
                System.out.println("Enter second summoner name: ");
                summonerName = in.nextLine();
                Player player2 = new Player(summonerName);
                player2.displayStats();
                in.close();
        }
}