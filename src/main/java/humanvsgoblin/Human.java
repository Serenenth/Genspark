package humanvsgoblin;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class Human extends Humanoid
{
    Scanner input = new Scanner(System.in);
    String user;
    boolean valid = false;

    public Human(int hp, int strength, char icon, int speed, int x, int y,int ID)
    {
        super(hp, strength, icon, speed, x, y, ID);
    }

    public void move(char  key)
    {
        switch(key)
        {
            case 'w': setY(getY() - 1);
                break;
            case 's': setY(getY() + 1);
                break;
            case 'a': setX(getX() - 1);
                break;
            case 'd': setX(getX() + 1);
                break;
            default: System.out.println("Error");
        }
    }

}
