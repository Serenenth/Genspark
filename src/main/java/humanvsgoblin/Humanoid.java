package humanvsgoblin;

public abstract class Humanoid
{
    private int hp;
    private int strength;
    private char icon;
    private int speed;
    private int x;
    private int y;
    private int ID;

    public Humanoid(int hp, int strength, char icon, int speed, int x, int y, int ID)
    {
        this.hp = hp;
        this.strength = strength;
        this.icon = icon;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.ID = ID;
    }

    public void attack()
    {};

    public void move(){};

    public int getHp()
    {
        return hp;
    }

    public void setHp(int hp)
    {
        this.hp = hp;
    }

    public int getStrength()
    {
        return strength;
    }

    public void setStrength(int strength)
    {
        this.strength = strength;
    }

    public char getIcon()
    {
        return icon;
    }

    public void setIcon(char icon)
    {
        this.icon = icon;
    }

    public int getSpeed()
    {
        return speed;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }
    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }



}
