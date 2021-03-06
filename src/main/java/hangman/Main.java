package hangman;

import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        Scanner input = new Scanner(System.in);
        String user;
        boolean valid = false;

        boolean gameOver = false;
        do
        {
            Game game = new Game();
            gameOver = game.setUp();

            do
            {
                System.out.println("Do you wish to play again? y or n? ");
                user = input.next();
                if (user.equalsIgnoreCase("y"))
                {
                    valid = true;
                    gameOver = false;
                }
                if((user.equalsIgnoreCase("n")))
                    valid = true;

            }while(valid != true);

        }while(gameOver == false);
    }

}
