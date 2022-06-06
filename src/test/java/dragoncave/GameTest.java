package dragoncave;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest
{
    Game game;

    @BeforeEach
    void setUp()
    {
        game = new Game();
    }

    @Test
    void Dialog_WrongGuess()
    {
        game.playerInput = 1;
        game.rand = 2;

        assertEquals(String.format("You approach the cave... \nIt is dark and spooky...\n" +
                "A large dragon jumps out in front of you! He opens his jaws and...\n" +
                "Eats you. You ded sucker."), game.Dialog());
    }

    @Test
    void Dialog_RightGuess()
    {
        game.playerInput = game.rand;
        assertEquals(String.format("You approach the cave...\nIt is dark and spooky...\n" +
                "A large dragon jumps out in front of you! He opens his jaws and...\n" +
                "Says: 'Well hello there human! Would you like a cupcake?'"), game.Dialog());
    }
}