//Khalid - Tutorial 03

/*
attacker class
Version 1.1

Selects attack as outlined in manager, returns a string value based on the type of attack selected and helps keep count of different types of attacks
*/

import java.util.Random;

public class attacker
{
	final static int HIGH = 1;
	final static int MEDIUM = 2;
	final static int LOW = 3;
	final static int MAX_ROUNDS = 100;

	//constructor
	public attacker()
	{

	}
	
	//declaring variable
	String attackSelected;

	//method to select attack that return an integer that represents which attack was selected. proportions are set based on manager
	public int attackSelector(int high, int medium, int low)
	{
		Random attackTypeGenerator;
		int attackType;
		int attack = 0;
		//String attackSelected;

		//generating a random number between 1 and 100
		attackTypeGenerator = new Random();
		attackType = attackTypeGenerator.nextInt(MAX_ROUNDS) + 1;

		if(attackType <= high) //high attack
		{
			attack = HIGH;
		}

		else if(attackType > high && attackType <= (high + medium)) //medium attack
		{
			attack = MEDIUM;
		}

		else if(attackType > (high + medium) && attackType <= (high + medium + low)) //low attack
		{
			attack = LOW;
		}

		else //Redundant (remove it?)
			System.out.println("ERROR...");

		return attack;
	}

	//method that returns a string value based on which attack was selected
	public String printAttack(int attack)
	{
		if(attack == 1)
		{
			attackSelected = "High";
		}
		
		if(attack == 2)
		{
			attackSelected = "Medium";
		}

		if(attack == 3)
		{
			attackSelected = "Low";
		}

		return attackSelected;
	}

	//method that returns 0 or 1 based on which attack was selected
	public int countHighAtks(int attack)
	{
		int highAtkCounter = 0;

		if(attack == 1)
		{
			highAtkCounter = highAtkCounter + 1;
		}

		return highAtkCounter;
	}

	//method that returns 0 or 1 based on which attack was selected
	public int countMediumAtks(int attack)
	{
		int mediumAtkCounter = 0;

		if(attack == 2)
		{
			mediumAtkCounter = mediumAtkCounter + 1;
		}

		return mediumAtkCounter;
	}

	//method that returns 0 or 1 based on which attack was selected
	public int countLowAtks(int attack)
	{
		int lowAtkCounter = 0;

		if(attack == 3)
		{
			lowAtkCounter = lowAtkCounter + 1;
		}

		return lowAtkCounter;
	}
}