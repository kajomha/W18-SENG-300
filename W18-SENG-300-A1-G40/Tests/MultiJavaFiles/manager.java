//Khalid - Tutorial 03

/*
Kombat - The Ultimate Fight Simulator
Version 1.1

This program is a fight simulator containing 2 classes (attacker and defender). The user can select a number of rounds ranging from 1 to 100, failing to pick
a number in that range automatically sets the number of rounds to 10. There are 3 types of attacks and defenses: high, medium and low. The attacker class
randomly selects one of those 3 attacks with probabilities set by the user however if the user fails to set proportions equal to 100 the the simulator assumes
equal proportions. The defender class randomly selects a type of defense to attempt to block the attacker's attack for the first 3 rounds. After the 3rd round
the defender then attempts to learn the attacker's attack patterns by comparing the ratios of each attack type and blocks based on what the highest ratio is. 
If all ratios are equal or 2 ratios are equally greater than the third the defender  will randomly select a defense to counter the attack. Once the attack and
defense types are selected the simulator then compares the 2 values and declares wether the attack was successful (hit) or blocked. For each round simulated,
the simulator prints the round number, which attack and defense were selected by each class and what the outcome of the attack was. Once all rounds are simulated,
the simulator prints a summary of the Kombat showing the proportions used for each attack/defense and how many successful and unsucessful attacks were implemented.
The simulator then declares a winner or a tie. One of the limitations to this program is that we cannot control the user's input. For example, if the user enters
a string when the program is expecting an integer.
*/

import java.util.Scanner;

public class manager
{
	public static void main (String [] args)
	{
		//Create instances of the Scanner attacker and defender classes
        	attacker theAttacker = new attacker();
        	defender theDefender = new defender();
		Scanner in = new Scanner (System.in);

		//Initializing variabeles
		int rounds = 10;
		int roundCounter = 1; //has to start from 1
		int hitCount = 0;
		int blockCount = 0;

		int high = 33;
		int medium = 33;
		int low = 33;

		int highAtkCounter = 0;
		int mediumAtkCounter = 0;
		int lowAtkCounter = 0;

		int highDefCounter = 0;
		int mediumDefCounter = 0;
		int lowDefCounter = 0;

		String printResult = "null";

		System.out.println("How many rounds would you like to simulate? (Minimum 1, Maximum 100): ");
		rounds = in.nextInt();

		//checks if user's input is within the required range
		if (rounds > 100 || rounds < 1)
		{
			rounds = 10;
			System.out.println("You have entered an invalid number of rounds, the simulator has set the number of rounds to 10.");
		}

		System.out.println("Enter percentages for the number of attacks that will be directed: low, medium, high. The total of the three percentages must sum to 100%");

		System.out.print("Percentage of attacks that will be aimed low: ");
		low = in.nextInt();
	
		System.out.print("Percentage of attacks that will be aimed at medium height: ");
		medium = in.nextInt();
	
		System.out.print("Percentage of attacks that will be aimed high: ");
		high = in.nextInt();

		//Checks if the users inputs adds up to 100 as required
		if ((high + medium + low) != 100)
		{
			high = 33;
			medium = 33;
			low = 33;
			System.out.println("Your attack percentages did not add up to 100, the simulator has set equal attack percentages for all attacks.");
		}

		System.out.println();
		System.out.println("Kombat Begins!");
		System.out.println("--------------");
		
		//while loop that runs for as many rounds the user input or the adjusted number of rounds
		while(roundCounter <= rounds)
		{
			//selecting an attack based on the user's input or adjusted proportions
			int attack = theAttacker.attackSelector(high, medium, low);

			//checks which attack was selected instead using 3 if statements (not sure why i used this approach...), values reset every time the while loop runs/method is called
			int highAtkTracker = theAttacker.countHighAtks(attack);
			int mediumAtkTracker = theAttacker.countMediumAtks(attack);
			int lowAtkTracker = theAttacker.countLowAtks(attack);

			//adds 1 to a variable based on the attack carried out by the attacker
			highAtkCounter = highAtkCounter + highAtkTracker;
			mediumAtkCounter = mediumAtkCounter + mediumAtkTracker;
			lowAtkCounter = lowAtkCounter + lowAtkTracker;

			//selects a defense type (see defender class for more details)
			int defense = theDefender.defenseSelector(highAtkCounter, mediumAtkCounter, lowAtkCounter, rounds, roundCounter);

			//checks which defense was selected instead using 3 if statements (not sure why i used this approach...), values reset every time the while loop runs/method is called
			int highDefTracker = theDefender.countHighDefs(defense);
			int mediumDefTracker = theDefender.countMediumDefs(defense);
			int lowDefTracker = theDefender.countLowDefs(defense);

			//adds 1 to a variable based on the defense carried out by the defender
			highDefCounter = highDefCounter + highDefTracker;
			mediumDefCounter = mediumDefCounter + mediumDefTracker;
			lowDefCounter = lowDefCounter + lowDefTracker;

			//prints which attack/defense type were carried out
			String printAttackType = theAttacker.printAttack(attack);
			String printDefenseType = theDefender.printDefense(defense);

			//keeps count of how many hits and blocks were made and stores the result as a string so we can later print it
			if(attack == defense)
			{
				printResult = "Block";
				blockCount = blockCount + 1;
			}

			//could have used an else statement here but this better represents our logic
			else if(attack != defense)
			{
				printResult = "Hit";
				hitCount = hitCount + 1;
			}

			//just to make the display more appealing (my operating system may have been interpreting my output awkwardly since i only had to do it for 3 cases instead of 9)
			if(printAttackType == "High")
			{
				System.out.println("Round " + roundCounter + "..." + "\tAttacker: " + printAttackType + "   " + "  Defender: " + printDefenseType + "\t" + printResult);
			}

			else if(printAttackType == "Medium")
			{
				System.out.println("Round " + roundCounter + "..." + "\tAttacker: " + printAttackType + "   " + "Defender: " + printDefenseType + "\t" + printResult);
			}

			else if(printAttackType == "Low")
			{
				System.out.println("Round " + roundCounter + "..." + "\tAttacker: " + printAttackType + "   " + "   Defender: " + printDefenseType + "\t" + printResult);
			}

			//adds 1 after each round is done
			roundCounter = roundCounter + 1;
		}

		System.out.println();
		System.out.println("Summary of Kombat");
		System.out.println("Total hits: " + hitCount + "   Total blocks: " + blockCount);
		
		//attacker proportions stats
		System.out.print("Attacker proportions:   Low ");
		System.out.printf("%.2f", (((float)lowAtkCounter/rounds)*100));
		System.out.print("% \tMedium:   ");
		System.out.printf("%.2f", (((float)mediumAtkCounter/rounds)*100));
		System.out.print("% \tHigh:   ");
		System.out.printf("%.2f", (((float)highAtkCounter/rounds)*100));
		System.out.println("%");
		
		//Defender proportions stats
		System.out.print("Defender proportions:   Low ");
		System.out.printf("%.2f", (((float)lowDefCounter/rounds)*100));
		System.out.print("% \tMedium:   ");
		System.out.printf("%.2f", (((float)mediumDefCounter/rounds)*100));
		System.out.print("% \tHigh:   ");
		System.out.printf("%.2f", (((float)highDefCounter/rounds)*100));
		System.out.print("%" + "\n");
		
		//not required by the prof
		System.out.println();

		if(hitCount > blockCount)
		{
			System.out.println("ATTACKER WON!!!");
		}

		else if(blockCount > hitCount)
		{
			System.out.println("DEFENDER WON!!!");
		}

		else if(blockCount == hitCount)
		{
			System.out.println("WE HAVE A TIE!!!");
		}
	}
}