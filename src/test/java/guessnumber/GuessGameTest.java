package guessnumber;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuessGameTest
{
    GuessGame game;

    @BeforeEach
    void setUp()
    {
        game = new GuessGame("Trevor",6,1,20);
    }

    @Test
    void getAnswer_tooHigh()
    {
        game.guess = 6;
        game.rand = 5;
        assertEquals("Too high.",game.getAnswer());

    }

    @Test
    void getAnswer_SameRightAnswer()
    {
        game.guess = 6;
        game.rand = 6;
        assertEquals(String.format("Congratulations %s! You guessed the right number! I guess humans can stick around\n" +
                "a little longer.", game.name),game.getAnswer());
    }

    @Test
    void getAnswer_tooLow()
    {
        game.guess = 5;
        game.rand = 7;
        assertEquals("Too low.",game.getAnswer());
    }


}