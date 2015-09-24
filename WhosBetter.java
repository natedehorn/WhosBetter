import java.util.Scanner;

import org.jfree.ui.RefineryUtilities;

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
		
		
		
		//Make into function call later
		BarChart_AWT player1chart = new BarChart_AWT("Champion data for: " + player1.getName(), "Performance by Champion", player1.getVector());
		player1chart.pack( );        
		RefineryUtilities.centerFrameOnScreen( player1chart );        
		player1chart.setVisible( true );
		
		BarChart_AWT player2chart = new BarChart_AWT("Champion data for: " + player2.getName(), "Performance by Champion", player2.getVector());
		player2chart.pack( );        
		RefineryUtilities.centerFrameOnScreen( player2chart );        
		player2chart.setVisible( true );
	}
}