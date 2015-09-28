import java.util.Scanner;

public class WhosBetter {

	public static void main(String[] args) throws Exception {

		//Get summoner1 info
		Scanner in = new Scanner(System.in);
		System.out.println("Enter first summoner name: ");
		String summonerName = in.nextLine();
		Player player1 = new Player(summonerName);
		//player1.displayStats();
		player1.graph();
		System.out.println("");
		System.out.println("Enter second summoner name: ");
		summonerName = in.nextLine();
		Player player2 = new Player(summonerName);
		//player2.displayStats();
		player2.graph();
		in.close();
	}
}