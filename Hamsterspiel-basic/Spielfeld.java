import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Spielfeld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spielfeld  extends Territorium
{
    private InfoHamster blau;
    private InfoHamster rot;
    /**
    * Constructor for objects of class Spielfeld.
    * 
    */
    public Spielfeld()
    {    
        // Create a new world with 20x20 cells with a cell size of 10x10 pixels.
        super();
        rot = new InfoHamster("w","s","d","a",1,false);
        blau = new InfoHamster("up","down","right","left",3,true);
        addObject(rot,0,0);
        addObject(blau,9,9);
        Counter roterZaehler = rot.getCounter();
        Counter blauerZaehler = blau.getCounter();
        addObject (roterZaehler,1,9);
        addObject (blauerZaehler,9,0);
        for(int i=0;i<10;i++)
        {
            int x = Greenfoot.getRandomNumber(8)+1;
            int y = Greenfoot.getRandomNumber(8)+1;
            if(mauerDa(x,y))
            {
               i--;
            }
            addObject(new Mauer(),x,y);
        }
        for(int i=0;i<20;i++)
        {
            int x = Greenfoot.getRandomNumber(10);
            int y = Greenfoot.getRandomNumber(10);
            if(mauerDa(x,y)||hamsterDa(x,y))
            {
               i--;
            }
            addObject(new Korn(),x,y);
        }
    }
}
