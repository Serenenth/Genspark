package dragoncave;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.printf("You are in a land full of dragons. In front of you, you see two caves. \n" +
                "In one cave, the dragon is friendly and will share his treasure with you.\n" +
                "The other dragon is greedy and hungry and will eat you on sight.\n"+
                "Which cave will you go into? (1 or 2)\n");

        int playerInput = 0; // input is saved to this variable.
        boolean correct = false;

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

        if(playerInput == 1)
        {
            System.out.printf("You approach the cave... \nIt is dark and spooky...\n" +
                    "A large dragon jumps out in front of you! He opens his jaws and...\n" +
                    "Eats you. You ded sucker.");
        }
        else
        {
            System.out.printf("You approach the cave...\nIt is dark and spooky...\n" +
                    "A large dragon jumps out in front of you! He opens his jaws and...\n" +
                    "Says: 'Well hello there human! Would you like a cupcake?'");
        }
    }
}