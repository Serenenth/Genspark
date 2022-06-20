package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Game
{
    Scanner scanner;
    Scanner input = new Scanner(System.in);
    Random rand = new Random();

    private String difficulty;
    private int wrongGuess;
    Set wrongAnswers = new HashSet();
    Map<Character, Boolean> correctAnswers = new HashMap();
    String word;
    char[] wordArray;
    ArrayList<String> wordList = new ArrayList<>();
    ArrayList correctGuess;
    String guess;

    int userInt;
    int maxWrongGuess;
    private boolean valid = false;
    private boolean gameOver;

    public Game()
    {
        wrongGuess = 0;
        correctGuess = new ArrayList<>();
        gameOver = false;
        maxWrongGuess = 6;
    }

    /**
     * The main body of the game. Returns true once the play gets a game over.
     * @return true
     */
    public boolean play()
    {
        System.out.println("Welcome to HANGMAN!");
        setDifficulty();
        valid = false;
        setWord();
        do
        {
            printGallows();
            printDialog();
            printGuess();
            checkGuess();
            gameOver = checkGameOver();
            if(gameOver)
                gameOverDisplay();

        }while(gameOver != true);
        return true;
    }

    /**
     * Sets the difficulty of the game. The user inputs an int between 1-3. Anything else is invalid and the user must re-enter
     * their answer.
     */
    private void setDifficulty()
    {
        do
        {
            try
            {
                System.out.println("Please input a difficulty: 1 = easy, 2 = medium, 3 = hard");
                userInt = input.nextInt();
                if(userInt > 3 || userInt < 1)
                {
                    System.out.println("Come on, it's not that hard. Just input a number between 1-3.");
                    valid = false;
                    input.next();
                }
                else
                {
                    switch(userInt)
                    {
                        case 1: difficulty = "easy";
                        break;
                        case 2: difficulty = "medium";
                        break;
                        case 3: difficulty = "hard";
                        break;
                        default: System.out.println("Not working");
                    }
                    valid = true;
                }
            } catch (Exception e)
            {
                System.out.println("Please input a number between 1-3");
                input.next();
            }
        }while(valid != true);
    }


    /**
     * Sets the word based on the difficulty selected.
     */
    private void setWord()
    {
        try
        {
            scanner = new Scanner(new File("src/resources/" + difficulty + "Words.txt"));
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }

        while(scanner.hasNext())
        {
            wordList.add(scanner.nextLine().toLowerCase());
        }

        word = wordList.get(rand.nextInt(wordList.size()));
        wordArray = word.toCharArray();
        for(char el: wordArray)
            correctAnswers.put(el,false);

    }

    /**
     * Prints the letters that have already been guessed. It also prints the which numbers have been guessed correctly
     * and how many are left.
     */
    public void printDialog()
    {
        for(char el : wordArray)
        {
            if(correctAnswers.get(el) == true)
                System.out.print(el);
            else
                System.out.print('_');
        }
        System.out.println();
        System.out.printf("Wrong Guesses: " + wrongAnswers + "\n");

    }

    /**
     * User inputs a guess. Checks to make sure only one char is input at a time. Does not check if it's a number
     * or other type of characters.
     */
    private void printGuess()
    {
        do
        {
            System.out.println("Guess a letter:");
            guess = input.next().toLowerCase();
            if(guess.length() > 1)
            {
                System.out.println("One character at a time child. Sheesh.");
                valid = false;
                input.next();
            }
            if(guess.length() <= 0)
            {
                System.out.println("Guess something. Preferably a character.");
                valid = false;
                input.next();
            }
            else
            {
                valid = true;
                System.out.println(guess);

            }
        }while(valid != true);
    }

    /**
     * Checks if the guess is correct or not. If it is, it's added to the correct answers. If not, wrongGuess increases by one
     * and the guess is added to the wrongAnswers.
     */
    public void checkGuess()
    {
        if(!correctAnswers.containsKey(guess.charAt(0)))
        {
            wrongAnswers.add(guess);
            wrongGuess ++;
        }
        else
            correctAnswers.replace(guess.charAt(0),true);

    }

    /**
     * Checks if the game is over or not by comparing the max number of allowed wrong guesses to the number of current wrong guesses.
     * Then it checks all the keys in the correct answers map and their values to see if there are any false values. If there are, then the game is not over.
     * However, if all the values return are true then it spells game over for the player in their favor.
     * @return true or false
     */
    public boolean checkGameOver()
    {
        if(wrongGuess == maxWrongGuess)
            return true;

        for(char el : correctAnswers.keySet())
        {
            if(correctAnswers.get(el) == false)
                return false;
        }
        return true;
    }

    /**
     * Prints the gallows that holds the hanging man. It also prints the man based off the number of wrong guesses.
     */
    private void printGallows()
    {
        switch(wrongGuess)
        {
            case 1:
                System.out.printf(
                        "+=====+\n" +
                                "|     |\n" +
                                "|   (   )\n" +
                                "|\n" +
                                "|\n" +
                                "|\n" +
                                "|\n" +
                                "|___________\n");
                break;
            case 2:
                System.out.printf(
                        "+=====+\n" +
                                "|     |\n" +
                                "|   (   )\n" +
                                "|     |\n" +
                                "|     |\n" +
                                "|     |\n" +
                                "|\n" +
                                "|___________\n");
                break;
            case 3:
                System.out.printf(
                        "+=====+\n" +
                                "|     |\n" +
                                "|   (   )\n" +
                                "|     |\n" +
                                "|   / |\n" +
                                "|     |\n" +
                                "|\n" +
                                "|___________\n");
                break;
            case 4:
                System.out.printf(
                        "+=====+\n" +
                                "|     |\n" +
                                "|   (   )\n" +
                                "|     |\n" +
                                "|   / | \\\n" +
                                "|     |\n" +
                                "|\n" +
                                "|___________\n");
                break;
            case 5:
                System.out.printf(
                        "+=====+\n" +
                                "|     |\n" +
                                "|   (   )\n" +
                                "|     |\n" +
                                "|   / | \\\n" +
                                "|     | \n" +
                                "|    /\n" +
                                "|___________\n");
                break;
            case 6:
                System.out.printf(
                        "+=====+\n" +
                                "|     |\n" +
                                "|   (   )\n" +
                                "|     |\n" +
                                "|   / | \\\n" +
                                "|     | \n" +
                                "|    / \\\n" +
                                "|___________\n");
                break;
            default:
                System.out.printf(
                        "+=====+\n" +
                                "|     |\n" +
                                "|\n" +
                                "|\n" +
                                "|\n" +
                                "|\n" +
                                "|\n" +
                                "|___________\n");

        }
    }

    /**
     * Displays the appropriate game over message when called.
     */
    private void gameOverDisplay()
    {
        if(wrongGuess == maxWrongGuess)
        {
            printGallows();
            System.out.printf("You lose. And because of that, the man on the gallows lost as well.\n" +
                    "I hope you're happy with yourself.\n" +
                    "By the way, the word was %s.\n", word);
        }
        else
        {
            System.out.printf("Wow, you won! Congratulations! How do you feel about saving a\n" +
                    "a murderer? I hope you're happy with yourself.\n");
        }
    }
}
