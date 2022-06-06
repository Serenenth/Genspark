package guessnumber;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {

        Scanner input = new Scanner(System.in);
        boolean gameOver = false;
        String name;
        int count = 6;
        System.out.println("Hello! What's your name?");
        name = input.next();

        do
        {
            GuessGame game = new GuessGame(name, count,1,20);
            System.out.printf("Are you ready %s? I am thinking of a number between 1 and 20.\n" +
                    "Can you guess it within %s guesses?\n",name, count);
            gameOver = game.Play();
        }while(gameOver!= true);
    }
}
