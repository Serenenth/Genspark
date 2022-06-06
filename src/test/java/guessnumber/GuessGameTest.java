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

}