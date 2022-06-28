package humanvsgoblin;

public class Items
{
    private int x;
    private int y;

    private String icon;

    public Items(int x, int y, String icon)
    {
        this.x = x;
        this.y = y;
        this.icon = icon;
    }

    public void ability(Human p)
    {
        if(icon == "+")
            p.setHp(p.getHp() + 5);
        if(icon == "*")
            p.setStrength(p.getStrength() + 2);
        if(icon == "$")
            p.setSpeed(p.getSpeed() + 2);
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

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }
}
