package humanvsgoblin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CollisionsTest
{
    Random rand = new Random();
    Game game;
    Collisions collide;
    Human player;
    ArrayList<Goblin> goblins;
    ArrayList<Items> items;

    @BeforeEach
    void setUp()
    {
        game = new Game();
        collide = new Collisions();

    }

    @Test
    void checkBattle()
    {
        Goblin gob = new Goblin(10,10,'g',5,5,5,1);
        game.goblinList.add(gob);
        assertTrue(game.collide.checkBattle(player,goblins));
    }

    @Test
    void checkItem()
    {
        Items health = new Items(5,5,"+");
        game.itemList.add(health);
        assertEquals(items.get(0),game.collide.checkItem(game.player, game.itemList));

    }
}