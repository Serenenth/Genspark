package dragoncave;

import java.util.Scanner;

public class Game
{
    Scanner input = new Scanner(System.in);
    int playerInput;
    boolean correct = false;
    int rand;
    String answer;

    /**
     * Randomizes which is the good or bad cave.
     */
    public Game()
    {
        rand = (int)((Math.random() * 2) + 1);
    }

    /**
     * Where the main game is played.
     */
    public void play()
    {
        System.out.printf("You are in a land full of dragons. In front of you, you see two caves. \n" +
                "In one cave, the dragon is friendly and will share his treasure with you.\n" +
                "The other dragon is greedy and hungry and will eat you on sight.\n"+
                "Which cave will you go into? (1 or 2)\n");
        GetGuess();
        System.out.println(Dialog());
    }

    /**
     * Prints the appropriate dialog according to the given guess.
     * @Return String
     */
    public String Dialog()
    {
        if(playerInput != rand)
            answer = String.format("You approach the cave... \nIt is dark and spooky...\n" +
                    "A large dragon jumps out in front of you! He opens his jaws and...\n" +
                    "Eats you. You ded sucker.");
        else
            answer = String.format("You approach the cave...\nIt is dark and spooky...\n" +
                    "A large dragon jumps out in front of you! He opens his jaws and...\n" +
                    "Says: 'Well hello there human! Would you like a cupcake?'");
        return answer;

    }

    /**
     * Returns the players input of 1 or 2. If the player tries to input anything else but 1 or 2, a pop-up appears.
     * @return int
     */
    public void GetGuess()
    {
        do
        {
            try
            {
                playerInput = input.nextInt();
                if(playerInput < 1 || playerInput  > 2)
                {
                    System.out.println("I'm sorry, please choose 1 or 2");
                }
                else
                    correct = true;
            } catch (Exception e)
            {
                System.out.println("I'm sorry, please choose 1 or 2");
                input.next();
            }
        }while(correct != true);

    }
}
