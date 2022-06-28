package humanvsgoblin;

import java.util.ArrayList;

public class Collisions
{
    public boolean checkBattle(Human player, ArrayList<Goblin> goblins)
    {
        for(Goblin el : goblins)
        {
            if(player.getX() == el.getX() && player.getY() == el.getY())
                return true;
        }

        return false;
    }

    public Items checkItem(Human player, ArrayList<Items> items)
    {
        for(Items el : items)
        {
            if(player.getX() == el.getX() && player.getY() == el.getY())
                return el;
        }
        return null;
    }

}
