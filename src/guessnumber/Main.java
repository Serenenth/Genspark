package guessnumber;

import java.util.Random;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {

        Scanner input = new Scanner(System.in);
        int max = 20;
        int min = 1;
        int range = (max-min) + 1;
        int rand = (int)(Math.random() * range + min);
        int user = 0;
        int count = 6;
        String playAgain = "Y";
        boolean gameOver = false;

        System.out.println("Hello! What's your name?");
        String name = (input.next());
        System.out.println(name + " Think of a number between 1 and 20 and take a guess.");

        do
        {
            System.out.println("Guesses left: " + count--);
            try
            {
                user = input.nextInt();
                if(user > rand)
                    System.out.println("Your guess is too high.");
                else if (user < rand)
                    System.out.println("your guess is too low.");

                if(count == 0 || rand == user) // Game-over check
                {
                    if(count == 0)
                        System.out.println("You failed to guess correctly.");
                    else
                        System.out.println("Congrats! You guessed my number in " + (6- count) + " guesses!");

                    do
                    {
                        System.out.println("Would you like to play again (y or n)?");
                        playAgain = input.next();
                        if(playAgain.equalsIgnoreCase("n"))
                            gameOver = true;
                        else if(playAgain.equalsIgnoreCase("y"))
                            break;
                    }while(gameOver != true);

                    if(playAgain.equalsIgnoreCase("y")) // Resets the number and count.
                    {
                        count = 6;
                        rand = (int)(Math.random() * range + min);
                    }
                }
            } catch (Exception e)
            {
                System.out.println("Please choose a number between 1 and 20.");
                input.next();
            }

        }while(gameOver == false);
    }
}
