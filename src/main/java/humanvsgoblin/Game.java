package humanvsgoblin;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game
{
    Scanner input = new Scanner(System.in);
    Random rand = new Random();
    Collisions collide;
    ArrayList<Goblin> goblinList = new ArrayList<>();
    ArrayList<Items> itemList = new ArrayList<>();
    private boolean valid = false;
    private boolean gameOver;
    private Items item;
    private int itemID = 0;
    private Land map;
    Human player;
    String key;

    public Game()
    {
        collide = new Collisions();
        item = null;
        player = new Human(100,10,'\uFFED',5,1,2, 1);

        for(int i = 0; i < 5; i++)
        {
            Goblin gob = new Goblin(rand.nextInt(50) + 5,rand.nextInt(10)+1,'G',
                    rand.nextInt(10)+1, rand.nextInt(9)+1, rand.nextInt(9)+1, i);
            goblinList.add(gob);
        }
        Items health = new Items(rand.nextInt(9) + 1,rand.nextInt(9),"+");
        itemList.add(health);
        Items health2 = new Items(rand.nextInt(9),rand.nextInt(9),"+");
        itemList.add(health2);
        Items strength1 = new Items(rand.nextInt(9),rand.nextInt(9),"*");
        itemList.add(strength1);
        Items strength2 = new Items(rand.nextInt(9),rand.nextInt(9),"*");
        itemList.add(strength2);
        Items speed1 = new Items(rand.nextInt(9),rand.nextInt(9),"$");
        itemList.add(speed1);
        Items speed2 = new Items(rand.nextInt(9),rand.nextInt(9),"$");
        itemList.add(speed2);
        map = new Land(10);
        gameOver = false;
        key = "";
    }

    public void play()
    {
        map.Draw(player,goblinList, itemList);
        do
        {
            System.out.printf("Player HP: %s  Player Str: %s  Player Spd: %s \n" +
                    "Please choose a,s,d,w to move.",player.getHp(), player.getStrength(), player.getSpeed());
            do
            {
                key = input.next();
            }while(key.length() > 1);
            if(key.equalsIgnoreCase(String.valueOf('w')) ||key.equalsIgnoreCase(String.valueOf('s'))
                    || key.equalsIgnoreCase(String.valueOf('a'))||key.equalsIgnoreCase(String.valueOf('d')))
            {
                if(!goblinList.isEmpty())
                {
                    player.move(key.charAt(0));

                    moveGoblins();
                    if(collide.checkBattle(player,goblinList))
                        battle();

                    if(collide.checkItem(player,itemList) != null)
                    {
                        item = collide.checkItem(player,itemList);
                        item.ability(player);
                        itemID = itemList.indexOf(item);
                        itemList.remove(itemID);
                        item = null;
                    }

                }
                if(goblinList.isEmpty())
                    gameOver = true;
            }
            map.Draw(player,goblinList, itemList);

        } while(!gameOver);

        if(player.getHp() > 0)
            System.out.println("Congrats on beating all the goblins!");
        else
            System.out.println("Wow, you died.");

    }

    private void moveGoblins()
    {
        for(Goblin el : goblinList)
            el.move(player);
    }


    private void battle()
    {
        Humanoid first = null;
        Humanoid second = null;
        int ID = 0;
        for(Goblin el : goblinList)
        {
            if(el.getX() == player.getX() && el.getY() == player.getY())
            {
                if(el.getStrength() > player.getStrength())
                {
                    first = el;
                    second = player;
                }
                else
                {
                    first = player;
                    second = el;
                }

                while(!valid)
                {
                    second.setHp(second.getHp() - first.getStrength());
                    first.setHp(first.getHp() - second.getStrength());
                    if(first.getHp() <= 0 || second.getHp() <= 0)
                    {
                        if((first instanceof Human && first.getHp() <=0) || (second instanceof Human && second.getHp() <=0))
                        {
                            gameOver = true;
                            valid = true;
                        }

                        else if (second instanceof Human && second.getHp() > 0)
                        {
                            ID = goblinList.indexOf(first);
                            valid = true;
                        }
                        else if (first instanceof Human && first.getHp() > 0)
                        {
                            ID = goblinList.indexOf(second);
                            valid = true;
                        }
                    }
                }
            }
        }
        valid = false;
        goblinList.remove(ID);
    }


}
