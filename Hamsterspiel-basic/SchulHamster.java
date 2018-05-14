import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Eine Unterart des Hamsters, der sich etwas lansamer bewegt,
 * damit man seinen Weg verfolgen kann.
 * 
 * @author UL+MM
 * @version 1.2 September 2008
 */
public class SchulHamster extends Hamster
{
    /**
	 * Konstruktor zum Erzeugen eines neuen SchulhamstersHamsters mit Blickrichtung OST und
	 * n Kšrnern im Maul
	 */	


    public void vor() 
    {
        Greenfoot.delay(5);
        super.vor();
        //System.out.println("vor");
   
    }    
    
    public void linksUm()
    {
        Greenfoot.delay(5);
        super.linksUm();
    }
    
    public void gib()
    {
        Greenfoot.delay(5);
        super.gib();
    }
    
    public void nimm()
    {
        Greenfoot.delay(5);
        super.nimm();
    }  
    
    
    /**
     * Legt dem Hamster n Koerner ins Maul; n muss positiv sein!
     */
    public void setKoernerImMaul(int n)
    {
        koernerImMaul=n;
    }
    
    protected void zufaelligDrehen()
    {
        int neueRichtung=Greenfoot.getRandomNumber(3);
        switch (neueRichtung)
        {
            case(0):
            {
                break;
            }
            case(1):
            {
                linksUm();
                break;
            }
            case(2):
            {
                linksUm();
                linksUm();
                break;
            }
            case(3):
            {
                linksUm();
                linksUm();
                linksUm();
                break;
            }
        }
    }
    
    protected int anzahlKoernerAufFeld()
    {
        World feld = getWorld();
        return feld.getObjects(Korn.class).size();
    }
    
    
}
