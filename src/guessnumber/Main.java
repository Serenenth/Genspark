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
        int range = max-min + 1;
        int rand = (int)(Math.random() * range + min);
        int user = 0;
        int count = 6;
        String playAgain = "Y";

        System.out.println("Hello! What's your name?");
        String name = (input.next());
        System.out.println(name + " Think of a number between 1 and 20 and take a guess.");

        do
        {
            System.out.println("Guesses left: " + count--);
            user = input.nextInt();

            if(user > rand)
                System.out.println("Your guess is too high.");
            else if (user < rand)
                System.out.println("your guess is too low.");
            if(count == 0 || rand == user)
            {
               if(count == 0)
                   System.out.println("You failed to guess correctly.");
                else if(rand == user)
                    System.out.println("Congrats! You guessed my number in " + (6- count) + " guesses!");

                System.out.println("Would you like to play again? (y or n)");
                playAgain = input.next();
                System.out.println(playAgain);
                count = 6;
            }

        }while(!playAgain.equalsIgnoreCase("n"));
    }

}
