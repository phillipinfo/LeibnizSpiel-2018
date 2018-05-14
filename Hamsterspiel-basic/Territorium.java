import greenfoot.Actor;
import greenfoot.World;
import greenfoot.Greenfoot;

/**
 * Die Klasse stellt eine Repraesentation des Hamster-Territoriums dar. Die
 * Methoden dienen zum Abfragen bestimmter Zustandswerte des aktuellen
 * Territoriums.
 * 
 * @author Dietrich Boles (Universitaet Oldenburg), modifiziert von MM
 * @version 1.1 (17.09.2008) 
 * 
 */
public class Territorium extends World {

    /**
     * Erzeugt ein neues Territorium mit 10x10 Kacheln
     */
    public Territorium() {
        this(10, 10);
        //damit der Hamster immer oben ist
        setPaintOrder(Hamster.class, Korn.class);

       // prepare();
    }

    /**
     * Erzeugt ein neues Territorium in der angegebenen Groesse
     * 
     * @param reihen
     *            Anzahl an Reihen
     * @param spalten
     *            Anzahl an Spalten
     */
    public Territorium(int reihen, int spalten) {
        super(spalten < 1 ? 10 : spalten, reihen < 1 ? 10 : reihen, 35);
        setBackground("kachel.jpg");
        //damit der Hmaster immer oben ist
        setPaintOrder(Hamster.class, Korn.class);
    }

    /**
     * liefert die Anzahl an Reihen im Territorium
     * 
     * @return die Anzahl an Reihen im Territorium
     */
    public int getAnzahlReihen() {
        return getHeight();
    }

    /**
     * liefert die Anzahl an Spalten im Territorium
     * 
     * @return die Anzahl an Spalten im Territorium
     */
    public int getAnzahlSpalten() {
        return getWidth();
    }

    /**
     * ueberprueft, ob sich auf der Kachel (reihe/spalte) eine Mauer befindet;
     * es wird genau dann true geliefert, wenn sich auf der angegebenen Kachel
     * eine Mauer befindet oder wenn sich die angegebenen Werte ausserhalb des
     * Territoriums befinden
     * 
     * @param reihe
     *            Reihe der Kachel
     * @param spalte
     *            Spalte der Kachel
     * @return true geliefert, wenn sich auf der angegebenen Kachel eine Mauer
     *         befindet oder wenn sich die angegebenen Werte ausserhalb des
     *         Territoriums befinden; sonst false
     */
    public boolean mauerDa(int reihe, int spalte) {
        return getObjectsAt(spalte, reihe, Mauer.class).size() > 0;
    }
    
    public boolean hamsterDa(int reihe, int spalte) {
        return getObjectsAt(spalte, reihe, InfoHamster.class).size() > 0;
    }

    /**
     * liefert die Gesamtzahl an Koernern, die im Territorium auf Kacheln
     * herumliegen
     * 
     * @return die Gesamtzahl an Koernern, die im Territorium auf Kacheln
     *         herumliegen
     */
    public int getAnzahlKoerner() {
        return getObjects(Korn.class).size();
    }

    /**
     * liefert die Anzahl an Koernern auf der Kachel (reihe/spalte) oder 0,
     * falls die Kachel nicht existiert oder durch eine Mauer blockiert ist
     * 
     * @param reihe
     *            Reihe der Kachel
     * @param spalte
     *            Spalte der Kachel
     * @return die Anzahl an Koernern auf der Kachel (reihe/spalte) oder 0,
     *         falls die Kachel nicht existiert oder durch eine Mauer blockiert
     *         ist
     */
    public int getAnzahlKoerner(int reihe, int spalte) {
        return getObjectsAt(spalte, reihe, Korn.class).size();
    }

    /**
     * liefert die Gesamtzahl an existierenden Hamstern im Territorium
     * 
     * @return die Gesamtzahl an existierenden Hamstern im Territorium
     */
    public int getAnzahlHamster() {
        return getObjects(Hamster.class).size();
    }

    /**
     * liefert alle existierenden Hamster im Territorium
     * 
     * @return alle existierenden Hamster im Territorium
     */
    public Hamster[] getHamster() {
        return (Hamster[]) getObjects(Hamster.class).toArray(new Hamster[0]);
    }

    /**
     * liefert die Anzahl an Hamstern auf der Kachel (reihe/spalte) oder 0,
     * falls die Kachel nicht existiert oder durch eine Mauer blockiert ist
     * 
     * @param reihe
     *            Reihe der Kachel
     * @param spalte
     *            Spalte der Kachel
     * @return die Anzahl an Hamstern auf der Kachel (reihe/spalte) oder 0,
     *         falls die Kachel nicht existiert oder durch eine Mauer blockiert
     *         ist
     */
    public int getAnzahlHamster(int reihe, int spalte) {
        return getObjectsAt(spalte, reihe, Hamster.class).size();
    }

    /**
     * liefert alle Hamster, die aktuell auf der Kachel (reihe/spalte) stehen
     * (inkl. dem Standard-Hamster)
     * 
     * @param reihe
     *            Reihe der Kachel
     * @param spalte
     *            Spalte der Kachel
     * @return alle Hamster, die aktuell auf der Kachel (reihe/spalte) stehen
     */
    public Hamster[] getHamster(int reihe, int spalte) {
        return (Hamster[]) getObjectsAt(spalte, reihe, Hamster.class).toArray(
            new Hamster[0]);
    }

    /**
     * Besetzt das Territorium mit einer festgelegten Population
     */
    public void populationGenerieren() {
        Hamster h1 = new Hamster();
        h1.linksUm();
        h1.linksUm();
        h1.linksUm();
        addObject(h1, 0, 0);

        Hamster h2 = new Hamster();
        h2.linksUm();
        addObject(h2, getWidth() - 1, getHeight() - 1);

        Hamster h3 = new Hamster();
        addObject(h3, getWidth() / 2, getHeight() / 2);

        koernerGenerieren(getWidth() * getHeight() / 2);
    }

    /**
     * Platziert zufaellig eine angegebene Anzahl von Koernern im Territorium
     * 
     * @param wieviele
     *            Anzahl der zu platzierenden Koerner
     */
    public void koernerGenerieren(int wieviele) {
        for (int i = 0; i < wieviele; i++) {
            Korn korn = new Korn();
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());
            addObject(korn, x, y);
        }
    }

    /**
     * Bereite die Welt fŸr den Programmstart vor. Das hei§t: Erzeuge die Anfangs-
     * Objekte und fŸge sie der Welt hinzu.
     */
    private void prepare()
    {
        InfoHamster infohamster = new InfoHamster();
        addObject(infohamster, 2, 5);
        Korn korn = new Korn();
        addObject(korn, 5, 3);
    }
}