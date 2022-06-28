package humanvsgoblin;

public class Goblin extends Humanoid
{
    private int ID;
    public Goblin(int hp, int strength, char icon, int speed, int x, int y, int ID)
    {
        super(hp, strength, icon, speed, x, y, ID);
    }

    public void move(Human player)
    {
        int playerX = player.getX();
        int playerY = player.getY();
        int goblinX = getX();
        int goblinY = getY();
        if(playerX > goblinX)
            setX(getX() + 1);
        else if (playerX < goblinX)
            setX(getX() - 1);
        if(playerY > goblinY)
            setY(getY() + 1);
        else if(playerY < goblinY)
            setY(getY() - 1);
    }
}
