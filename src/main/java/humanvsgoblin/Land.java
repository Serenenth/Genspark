package humanvsgoblin;
import java.util.ArrayList;

public class Land
{
    private int topBoundary;
    private int bottomBoundary;
    private int leftBoundary;
    private int rightBoundary;
    private int dimension;
    public Land(int dimension)
    {
        topBoundary = 0;
        leftBoundary = 0;
        bottomBoundary = dimension;
        rightBoundary = dimension;
        this.dimension = dimension;

    }

    public void Draw(Human player, ArrayList<Goblin> goblins, ArrayList<Items> items)
    {
        boolean valid = true;
        for(int i = 0; i < dimension; i++)
        {
            for(int j = 0; j < dimension; j++)
            {
                if(!items.isEmpty())
                {
                    for(Items el : items)
                    {
                        if(el.getY() == i && el.getX() == j)
                        {
                            System.out.printf(" %s ",el.getIcon());
                            valid = false;
                        }
                    }
                }
                if(!goblins.isEmpty())
                {
                    for(Goblin el : goblins)
                    {
                        if(el.getX() == j && el.getY() == i)
                        {
                            System.out.printf(" %s ",el.getIcon());
                            valid = false;
                        }
                    }
                }
                if(i == player.getY() && j == player.getX())
                {
                    System.out.print(" " + player.getIcon() + " ");
                    valid = false;
                }

                if(valid)
                    System.out.printf(" %s ", '~');
                valid = true;

            }
            System.out.println();

        }
    }

    private void moveGoblins(ArrayList<Goblin> goblins)
    {
        for(Goblin el : goblins)
        {
            el.move();
        }
    }

}
