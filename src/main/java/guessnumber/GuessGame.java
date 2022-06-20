package guessnumber;

import java.util.Scanner;

public class GuessGame
{
    Scanner input = new Scanner(System.in);
    int count;
    String name;
    int min;
    int max;
    int range;
    int rand;
    boolean gameOver;
    boolean playAgain;
    int user;
    boolean valid = false;
    int guess;
    String user1;

    /**
     * GuessGame Constructor
     * @param name
     * @param count
     * @param min
     * @param max
     */
    public GuessGame(String name, int count, int min, int max)
    {
        this.count = count;
        this.name = name;
        this.min = min;
        this.max = max;
        range = (max - min) + 1;
        rand = (int)(Math.random() * range + min);
        gameOver = false;
        playAgain = false;
    }

    /**
     * Where the game's main code is used. It uses a do while loop to repeat until all the guesses are used
     * or until the player guesses the right number.
     * @return true when the player guesses the right number or when the count reaches 0.
     */
    public boolean Play()
    {
        do
        {
            System.out.println("Guesses left: " + count--);
            guess = Guess();
            System.out.println(getAnswer());

            if(guess == rand || count == 0)
                gameOver = true;
        }while(gameOver!= true);

        return TryAgain();
    }

    /**
     * Prints the answer according to the users guess.
     */
    public String getAnswer()
    {
        String x = "";
        if(guess > rand)
            x= "Too high.";
        if(guess < rand)
            x ="Too low.";
        if(guess == rand)
            x = String.format("Congratulations %s! You guessed the right number! I guess humans can stick around\n" +
                    "a little longer.", name);
        if(count == 0)
            x = ("No more guesses human. Now, the world is MINE! Unless...");
        return x;
    }

    /**
     * Validates the guess input, making sure the user types an Int and not something else.
     * @return A valid int.
     */
    private int Guess()
    {
        do
        {
            try
            {
                user = input.nextInt();
                valid = true;
            } catch (Exception e)
            {
                System.out.println("Please guess a number.");
                input.next();
            }
        }while(valid != true);
        valid = false;
        return user;
    }

    /**
     * See whether or not you want to play the game again.
     * @return true if no, false if yes.
     */
    private boolean TryAgain()
    {
        do
        {
            System.out.println("You want to play again? (y or n)");
            user1 = input.next();
            if(user1.equalsIgnoreCase("y"))
                valid = true;
            if(user1.equalsIgnoreCase("n"))
                valid = true;
        }while(valid != true);
        valid = false;

        if(user1.equalsIgnoreCase("y"))
            return false;
        return true;
    }
}
