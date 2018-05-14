import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Eine Unterart des SchulHamsters, zur Ÿbersichtlicheren Programmierung
 * 
 * @author MM+UL 
 * @version 1.1 August 2008
 */



public class InfoHamster extends SchulHamster
{
    private String nachOben;
    private String nachUnten;
    private String nachRechts;
    private String nachLinks;
    private boolean istBlau;
    private Counter counter;
    
    public InfoHamster()
    {
        nachLinks="left";
        nachRechts="right";
        nachOben="up";
        nachUnten="down";
    }
    
    public InfoHamster(String oben, String unten, String rechts, String links, int blickrichtung, boolean farbe){
        nachOben = oben;
        nachUnten = unten;
        nachRechts = rechts;
        nachLinks = links;
        istBlau = farbe;
        setBlickrichtung(blickrichtung);
        boolean farbeDesHamsters=farbe;
        if(istBlau == false)
        {
            setImage("hamsterR.png");
            counter = new Counter ("rot: ");
        }
        else
        {
            counter = new Counter ("blau: ");
        }
    }
    
    public void act() 
    {
       realisiereTastendruck();
       nimmKorn();
       if(anzahlKoernerAufFeld()==0)
       {
            Greenfoot.stop();
       }
    }
    
    public void umDrehen(){
        linksUm();
        linksUm();
    }
    
    public void rechtsUm(){
        linksUm();
        linksUm();
        linksUm();
    }

    public void nimmKorn(){
        if (kornDa()){
            nimm();
            counter.add(1);
        }
    }
    
    public void vorWennFrei(){
        if(vornFrei()){
            vor();
        }
    }
    
    public void realisiereTastendruck(){
        if(Greenfoot.isKeyDown(nachLinks)){
            while(getBlickrichtung()!=3){
                linksUm();
            }
            vorWennFrei();
        }
        if(Greenfoot.isKeyDown(nachRechts)){
            while(getBlickrichtung()!=1){
                linksUm();
            }
            vorWennFrei();
        }
        if(Greenfoot.isKeyDown(nachUnten)){
            while(getBlickrichtung()!=2){
               linksUm();
            }
            vorWennFrei();
        }
        if(Greenfoot.isKeyDown(nachOben)){
            while(getBlickrichtung()!=0){
                linksUm();
            }
            vorWennFrei();
        }
    }
    
    public Counter getCounter(){
        return counter;
    }
    }
