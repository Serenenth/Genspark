package hangman;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;


public class Game
{
    // TODO change all loops to streams. Change input loops to recursive calls.
    Scanner input = new Scanner(System.in);
    Random rand = new Random();

    private int difficulty;
    private String name;
    private int wrongGuess;
    private Set wrongAnswers = new HashSet();
    private Map<Character, Boolean> correctAnswers = new HashMap();
    private String word;
    private char[] wordArray; // TODO too much mutation
    ArrayList<String> wordList = new ArrayList<>();
    private ArrayList correctGuess;
    private String guess;

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
     * Sets the game up and calls the gameBody to start the game.
     * @return true
     */
    public boolean setUp() throws IOException
    {
        System.out.println("Welcome to HANGMAN!");
        setName();
        setDifficulty();
        setWord();
        gameBody();
        System.out.println();
        return true;
    }

    /**
     * The main body of the game. It's recursive until game over;
     */
    private void gameBody()
    {
        printGallows();
        printDialog();
        printGuess();
        checkGuess();
        gameOver = checkGameOver();
        if(gameOver)
            gameOverDisplay();
        else
            gameBody();

    }

    private void setName()
    {
        System.out.printf("Please input a name: \n");
        name = input.nextLine();
    }


    /**
     * Sets the difficulty of the game. The user inputs an int between 1-3. Anything else is invalid and the user must re-enter
     * their answer.
     */
    private void setDifficulty()
    {
        try
        {
            System.out.println(name + " ,please input a difficulty: 1 = easy, 2 = medium, 3 = hard");
            userInt = input.nextInt();
            if(userInt > 3 || userInt < 1)
            {
                System.out.println("Come on " + name + ", it's not that hard. Just input a number between 1-3.");
                input.next();
                setDifficulty();
            }
            else
            {
                difficulty = userInt;
            }
        } catch (Exception e)
        {
            System.out.println("Please input a number between 1-3");
            input.next();
            setDifficulty();
        }
    }


    /**
     * Sets the word based on the difficulty selected.
     */
    private void setWord()
    {
        try
        {
            word = Files.lines(Paths.get("src/resources/words" + difficulty + ".txt"))
                    .skip(rand.nextInt((int)Files.lines(Paths.get("src/resources/words" + difficulty + ".txt")).count()))
                    .findFirst()
                    .get().toLowerCase();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }

//        try
//        {
//            scanner = new Scanner(new File("src/resources/Words" + difficulty + ".txt"));
//        } catch (FileNotFoundException e)
//        {
//            throw new RuntimeException(e);
//        }

        //Stream<String> wordList2 = Files.lines(Paths.get("src/resources/words" + difficulty + ".txt"));
        //word = wordList2.skip((rand.nextInt((int)wordList2.count()))).findFirst().get().toLowerCase();




//        while(scanner.hasNext())
//        {
//            wordList.add(scanner.nextLine().toLowerCase());
//        }

//        word = wordList.get(rand.nextInt(wordList.size()));
//        wordArray = word.toCharArray();
//        for(char el: wordArray)
//            correctAnswers.put(el,false);

    }

    /**
     * Prints the letters that have already been guessed. It also prints which letters have been guessed correctly
     * and how many are left.
     */
    public void printDialog()
    {

        Stream.of(word.split("")).forEach(x->{
            if(correctGuess.contains(x))
                System.out.print(x);
            else
                System.out.print("_");
        });

        System.out.println();
        System.out.print("Wrong Answers: ");
        wrongAnswers.stream().map(x->System.out.printf("%s, ",x));

        System.out.println();

//        System.out.print()
//        for(char el : wordArray)
//        {
//            if(correctAnswers.get(el) == true)
//                System.out.print(el);
//            else
//                System.out.print('_');
//        }
//        System.out.println();
//        System.out.printf("Wrong Guesses: " + wrongAnswers + "\n");

    }

    /**
     * User inputs a guess. Checks to make sure only one char is input at a time. Does not check if it's a number
     * or other type of characters.
     */
    private void printGuess()
    {
        System.out.println("Guess a letter:");
        guess = input.next().toLowerCase();
        if(guess.length() > 1)
        {
            System.out.println("One character at a time child. Sheesh.");
            input.next();
            printGuess();
        }
        if(guess.length() <= 0)
        {
            System.out.println("Guess something. Preferably a character.");
            input.next();
            printGuess();
        }
        else
        {
            System.out.println(guess);
        }
    }

    /**
     * Checks if the guess is correct or not. If it is, it's added to the correct answers. If not, wrongGuess increases by one
     * and the guess is added to the wrongAnswers.
     */
    public void checkGuess()
    {
        if(!correctGuess.contains(guess) && !word.contains(guess))
        {
            wrongAnswers.add(guess);
            wrongGuess ++;
        }
        else
            if(!correctGuess.contains(guess))
                correctGuess.add(guess);

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
        Stream.of(word.split("")).forEach(x->{
            if(!correctGuess.contains(x))
                valid = false;
            else
                valid = true;
        });
        if(valid == true)
            return true;

//        for(char el : correctAnswers.keySet())
//        {
//            if(correctAnswers.get(el) == false)
//                return false;
//        }
        return false;
    }

    /**
     * Prints the gallows that holds the hanging man. It also prints the man based off the number of wrong guesses.
     */
    private void printGallows()
    {
        try
        {
            Stream stream = Files.lines(Paths.get("src/resources/hangman_" + wrongGuess + ".txt"));
            stream.forEach(System.out::println);
        }
        catch(IOException e)
        {
            e.printStackTrace();
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
        printScore();
    }

    private void printScore()
    {
        String fileDestination = "src/resources/scorecard.txt";
        int numScore = (word.length() + difficulty) - wrongGuess;
        String score = String.format("%-1s %10s",name,numScore);
        System.out.println(score);

        try
        {
            Stream<String> update = Files.lines(Paths.get("src/resources/scorecard.txt"));
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
