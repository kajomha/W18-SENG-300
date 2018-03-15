//Khalid - Tutorial 03

/*
defender class
version 1.1

Learns attack patters, set a string value based the defense selected and helps us keep count of the different types of defenses implemented.
*/

import java.util.Random;

public class defender
{
	final static int HIGH = 1;
	final static int MEDIUM = 2;
	final static int LOW = 3;
	final static int MAX_ROUNDS = 99;

	//constructor
	public defender()
	{

	}

	//method that returns an integer based on which defense was selected
	public int defenseSelector(int highAtkCounter, int mediumAtkCounter, int lowAtkCounter, int rounds, int roundCounter)
	{
		Random defenseTypeGenerator;
		int defense = 0;
		float highRatio;
		float mediumRatio;
		float lowRatio;

		//generating a random number between 1 and 99
		defenseTypeGenerator = new Random();
		int defenseType = defenseTypeGenerator.nextInt(MAX_ROUNDS) + 1;

		//calculating the probability of each attack based on its occurance
		highRatio = (float)highAtkCounter/roundCounter;
		mediumRatio = (float)mediumAtkCounter/roundCounter;
		lowRatio = (float)lowAtkCounter/roundCounter;

		//defender randomly selects a defence type for the first 3 rounds
		if(roundCounter <= 3)
		{
			if(defenseType >= 1 && defenseType <= 33)
			{
				defense = HIGH;
			}

			else if(defenseType > 33 && defenseType <= 66) //medium defense
			{
				defense = MEDIUM;
			}

			else if(defenseType > 66 && defenseType <= 99) //low defense
			{
				defense = LOW;
			}

			else //Redundant (remove it?)
				System.out.println("ERROR...");
		}

		//defender compares each attack probability and selects a block type based on the one with the highest possibility
		if(roundCounter > 3 && roundCounter <= rounds) //AI portion
		{
			if (highRatio > mediumRatio && highRatio > lowRatio)
         		{
				defense = HIGH;
      			}

			else if (mediumRatio > highRatio && mediumRatio > lowRatio)
         		{
				defense = MEDIUM;
			}

      			else if (lowRatio > highRatio && lowRatio > mediumRatio)
			{
         			defense = LOW;
			}

			//randomly selects a defense type if none of the probabilities is larger than the other
			else
			{
				if(defenseType >= 1 && defenseType <= 33)
				{
					defense = HIGH;
				}

				else if(defenseType > 33 && defenseType <= 66) //medium defense
				{
					defense = MEDIUM;
				}

				else if(defenseType > 66 && defenseType <= 99) //low defense
				{
					defense = LOW;
				}
			}
		}

		return defense;
	}

	//returns a string based on which type of defense was selected
	public String printDefense(int defense)
	{
		String defenseSelected = "null";

		if(defense == 1)
		{
			defenseSelected = "High";
		}
		
		if(defense == 2)
		{
			defenseSelected = "Medium";
		}

		if(defense == 3)
		{
			defenseSelected = "Low";
		}

		return defenseSelected;
	}

	// returns 0 or 1 based on which defense was selected
	public int countHighDefs(int defense)
	{
		int highDefCounter = 0;

		if(defense == 1)
		{
			highDefCounter = highDefCounter + 1;
		}

		return highDefCounter;
	}

	// returns 0 or 1 based on which defense was selected
	public int countMediumDefs(int defense)
	{
		int mediumDefCounter = 0;

		if(defense == 2)
		{
			mediumDefCounter = mediumDefCounter + 1;
		}

		return mediumDefCounter;
	}

	// returns 0 or 1 based on which defense was selected
	public int countLowDefs(int defense)
	{
		int lowDefCounter = 0;

		if(defense == 3)
		{
			lowDefCounter = lowDefCounter + 1;
		}

		return lowDefCounter;
	}
}