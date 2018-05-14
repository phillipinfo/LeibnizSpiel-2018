import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Eine Unterart des SchulHamsters, zur Ÿbersichtlicheren Programmierung
 * 
 * @author MM+UL 
 * @version 1.1 August 2008
 */
public class InfoHamster2  extends SchulHamster
{
    Actor blau;    

    public InfoHamster2() 
    {
        super();
        umdrehen();
    }

    public void act() 
    {
        // Hier kommt dein Quelltext hinein
       realisiereTastendruck();
       sucheKorn();
       
       if(anzahlKoernerAufFeld()==0)
       {
           //System.out.println("roter Hamster: "+ getAnzahlKoerner());
           Greenfoot.stop();
       }
       
      
    }
    
    public void sucheKorn()
    {
       if(kornDa())
       {
           nimm();
       }
    }
    
    public void realisiereTastendruck()
    {
        if(Greenfoot.isKeyDown("a"))
        {
            if(getBlickrichtung() == 1)
            {
                umdrehen();
                vorWennFrei();
            }
            else
            {
                if(getBlickrichtung() == 2)
                {
                    rechtsUm();
                    vorWennFrei();
                }
                else
                {
                    if(getBlickrichtung() == 3)
                    {
                        vorWennFrei();
                    }
                    else
                    {
                        linksUm();
                        vorWennFrei();
                    }
                }
            }
                      
        }
        
        if(Greenfoot.isKeyDown("d"))
        {
            if(getBlickrichtung() == 1)
            {
                vorWennFrei();
            }
            else
            {
                if(getBlickrichtung() == 2)
                {
                    linksUm();
                    vorWennFrei();
                }
                else
                {
                    if(getBlickrichtung() == 3)
                    {
                        umdrehen();
                        vorWennFrei();
                    }
                    else
                    {
                        rechtsUm();
                        vorWennFrei();
                    }
                }
            }
        }
        
        if(Greenfoot.isKeyDown("w"))
        {
            if(getBlickrichtung() == 1)
            {
                linksUm();
                vorWennFrei();
            }
            else
            {
                if(getBlickrichtung() == 2)
                {
                    umdrehen();
                    vorWennFrei();
                }
                else
                {
                    if(getBlickrichtung() == 3)
                    {
                        rechtsUm();
                        vorWennFrei();
                    }
                    else
                    {
                        vorWennFrei();
                    }
                }
            }
        }
        
        if(Greenfoot.isKeyDown("s"))
        {
            if(getBlickrichtung() == 1)
            {
                rechtsUm();
                vorWennFrei();
            }
            else
            {
                if(getBlickrichtung() == 2)
                {
                    vorWennFrei();
                }
                else
                {
                    if(getBlickrichtung() == 3)
                    {
                        linksUm();
                        vorWennFrei();
                    }
                    else
                    {
                        umdrehen();
                        vorWennFrei();
                    }
                }
            }
        }
    }
    
    public void vorWennFrei()
    {
        if(vornFrei())
        {
            vor();
        }
    }
    
    public void zufallsVerhalten()
    {
       if(kornDa())
       {
           nimm();
       }
       zufaelligDrehen();
       if(vornFrei())
       {
           vor();
       }
       else
       {
           zufaelligDrehen();
       }
    }
    
    public void rechtsUm()
    {
        linksUm();
        linksUm();
        linksUm();
    }
    
    public void umdrehen()
    {
        linksUm();
        linksUm();
    }
    
    
 
}
