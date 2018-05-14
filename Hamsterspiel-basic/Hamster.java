import greenfoot.Actor;
import greenfoot.World;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import java.util.List;
import java.awt.Color;

/**
 * Repraesentation von objektorientierten Hamstern im Java-Hamster-Modell
 * 
 * @author Dietrich Boles (Universitaet Oldenburg), modifiziert von MM
 * @version 1.1 (17.09.2008)
 * 
 */
public class Hamster extends Actor {

	/**
	 * Blickrichtung Nord
	 */
	public final static int NORD = 0;

	/**
	 * Blickrichtung Ost
	 */
	public final static int OST = 1;

	/**
	 * Blickrichtung Sued
	 */
	public final static int SUED = 2;

	/**
	 * Blickrichtung West
	 */
	public final static int WEST = 3;

	private int blickrichtung; 

	protected int koernerImMaul;

	private GreenfootImage hamsterImage; 

	/**
	 * Konstruktor zum Erzeugen eines neuen Hamsters mit Blickrichtung OST und
	 * keinem Korn im Maul
	 */
	public Hamster() {
		hamsterImage = getImage();
		setBlickrichtung(OST);
		koernerImMaul = 0;
	}


	/**
	 * liefert genau dann true, wenn sich in Blickrichtung vor dem aufgerufenen
	 * Hamster keine Mauer befindet (wenn sich der Hamster in Blickrichtung am
	 * Rand des Territoriums befindet, wird false geliefert)
	 * 
	 * @return true, wenn sich in Blickrichtung vor dem aufgerufenen Hamster
	 *         keine Mauer befindet; sonst false
	 */
	public boolean vornFrei() {
		int x = getX();
		int y = getY();
		switch (blickrichtung) {
		case SUED:
			y++;
			break;
		case OST:
			x++;
			break;
		case NORD:
			y--;
			break;
		case WEST:
			x--;
			break;
		}

		if (x >= getWorld().getWidth() || y >= getWorld().getHeight() || x < 0
				|| y < 0) {
			return false;
		}

		return getWorld().getObjectsAt(x, y, Mauer.class).size() == 0;
	}

	/**
	 * liefert genau dann true, wenn auf der Kachel, auf der sich der
	 * aufgerufene Hamster gerade befindet, mindestens ein Korn liegt
	 * 
	 * @return true, wenn auf der Kachel, auf der sich der aufgerufene Hamster
	 *         gerade befindet, mindestens ein Korn liegt; sonst false
	 */
	public boolean kornDa() {
		return getWorld().getObjectsAt(getX(), getY(), Korn.class).size() > 0;
	}

	/**
	 * liefert genau dann true, wenn der aufgerufene Hamster keine Koerner im
	 * Maul hat
	 * 
	 * @return true, wenn der aufgerufene Hamster keine Koerner im Maul hat;
	 *         sonst false
	 */
	public boolean maulLeer() {
		return koernerImMaul == 0;
	}

	/**
	 * Der aufgerufene Hamster springt auf die in Blickrichtung vor ihm liegende
	 * Kachel.
	 * 
	 * @throws MauerDaException
	 *             wird geworfen, wenn die Kachel in Blickrichtung vor dem
	 *             Hamster durch eine Mauer blockiert ist oder der Hamster in
	 *             Blickrichtung am Rand des Territoriums steht
	 */
	public void vor() throws MauerDaException {
		if (!vornFrei()) {
			throw new MauerDaException(this, getY(), getX());
		}
		switch (blickrichtung) {
		case SUED:
			setLocation(getX(), getY() + 1);
			break;
		case OST:
			setLocation(getX() + 1, getY());
			break;
		case NORD:
			setLocation(getX(), getY() - 1);
			break;
		case WEST:
			setLocation(getX() - 1, getY());
			break;
		}
	
}

	/**
	 * Der aufgerufene Hamster dreht sich linksum.
	 */
	public void linksUm() {
		switch (blickrichtung) {
		case SUED:
			setBlickrichtung(OST);
			break;
		case OST:
			setBlickrichtung(NORD);
			break;
		case NORD:
			setBlickrichtung(WEST);
			break;
		case WEST:
			setBlickrichtung(SUED);
			break;
		}
	}

	/**
	 * Der aufgerufene Hamster frisst ein Korn auf der Kachel, auf der er sich
	 * gerade befindet.
	 * 
	 * @throws KachelLeerException
	 *             wird geworfen, wenn auf der Kachel, auf der sich der Hamster
	 *             gerade befindet, kein Korn liegt
	 */
	public void nimm() throws KachelLeerException {
		if (!kornDa()) {
			throw new KachelLeerException(this, getY(), getX());
		}
		List l = getWorld().getObjectsAt(getX(), getY(), Korn.class);
		if (l.size() > 0) {
			// Suche nach letztem Korn
			Korn g = (Korn) l.get(0);
			int nummer = g.getNumber();
			for (int i = 1; i < l.size(); i++) {
				Korn k = (Korn) l.get(i);
				if (k.getNumber() > nummer) {
					nummer = k.getNumber();
					g = k;
				}
			}
			// entferne letztes Korn
			getWorld().removeObject(g);
			koernerImMaul = koernerImMaul + 1;
		}
	}

	/**
	 * Der aufgerufene Hamster legt ein Korn auf der Kachel ab, auf der er sich
	 * gerade befindet.
	 * 
	 * @throws MaulLeerException
	 *             wird geworfen, wenn der Hamster keine Koerner im Maul hat
	 */
	public void gib() throws MaulLeerException {
		if (maulLeer()) {
			throw new MaulLeerException(this);
		}
		koernerImMaul = koernerImMaul - 1;
		getWorld().addObject(new Korn(), getX(), getY());
		
		//folgende Funktion wurde von MM deaktiviert, da sie nicht funktionert und
		//da sie mit der methode setPaintOrder in territorium kollidiert, die das gewŸnschte leistet.
		// damit der Hamster immer als letztes gezeichnet wird
		//getWorld().addObject(new Hamster(this), getX(), getY());
		//getWorld().removeObject(this);
	}

	/**
	 * gibt den uebergebenen String (in einer Dialogbox) auf den Bildschirm aus
	 * 
	 * @param zeichenkette
	 *            der auszugebende String
	 */
	/*
	 * public void schreib(String zeichenkette) { 
	 *     // kann mit der aktuellen Greenfoot-Version nicht implementiert werden
	 * }
	 */

	/**
	 * gibt den uebergebenen String auf den Bildschirm aus und fordert den
	 * Benutzer auf, einen String einzugeben; der eingegebene String wird als
	 * Wert geliefert
	 * 
	 * @param aufforderung
	 *            der auszugebende String
	 * @return der vom Benutzer eingegebene String
	 */
	/*
	 * public String liesZeichenkette(String aufforderung) { 
	 *    // kann mit der aktuellen Greenfoot-Version nicht implementiert werden
	 * }
	 */

	/**
	 * gibt den uebergebenen String auf den Bildschirm aus und fordert den
	 * Benutzer auf, eine Zahl einzugeben; die eingegebene Zahl wird als Wert
	 * geliefert (wenn der Benutzer eine ungueltige Zahl eingibt, wird der Wert
	 * 0 geliefert)
	 * 
	 * @param aufforderung
	 *            der auszugebende String
	 * @return die vom Benutzer eingegebene Zahl
	 */
	/*
	 * public int liesZahl(String aufforderung) { 
	 *     // kann mit der aktuellen Greenfoot-Version nicht implementiert werden
	 * }
	 */

	/**
	 * liefert die Reihe der Kachel des Territoriums, auf der sich der
	 * aufgerufene Hamster gerade befindet
	 * 
	 * @return die Reihe der Kachel des Territoriums, auf der sich der
	 *         aufgerufene Hamster gerade befindet
	 */
	public int getReihe() {
		return getY();
	}

	/**
	 * liefert die Spalte der Kachel des Territoriums, auf der sich der
	 * aufgerufene Hamster gerade befindet
	 * 
	 * @return die Spalte der Kachel des Territoriums, auf der sich der
	 *         aufgerufene Hamster gerade befindet
	 */
	public int getSpalte() {
		return getX();
	}

	/**
	 * liefert die Blickrichtung, in die der aufgerufene Hamster gerade schaut
	 * (die gelieferten Werte entsprechen den obigen Konstanten)
	 * 
	 * @return die Blickrichtung, in die der aufgerufene Hamster gerade schaut
	 */
	public int getBlickrichtung() {
		return blickrichtung;
	}

	/**
	 * liefert die Anzahl der Koerner, die der aufgerufene Hamster gerade im
	 * Maul hat
	 * 
	 * @return die Anzahl der Koerner, die der aufgerufene Hamster gerade im
	 *         Maul hat
	 */
	public int getAnzahlKoerner() {
		return koernerImMaul;
	}

	/**
	 * liefert die Gesamtzahl an existierenden Hamstern im
	 * Territorium
	 * 
	 * @return die Gesamtzahl an existierenden Hamstern im
	 *         Territorium
	 */
	public int getAnzahlHamster() {
		return getWorld().getObjects(Hamster.class).size();
	}

	// Copy-Konstruktor
	private Hamster(Hamster h) {
		hamsterImage = getImage();
		setBlickrichtung(h.blickrichtung);
		koernerImMaul = h.koernerImMaul;
	}

	// Blickrichtung setzen
	//geaendert in protected by MM
	protected void setBlickrichtung(int richtung) {
		blickrichtung = richtung;
		switch (blickrichtung) {
		case SUED:
			setRotation(90);
			break;
		case OST:
			setRotation(0);
			break;
		case NORD:
			setRotation(270);
			break;
		case WEST:
			setRotation(180);
			break;
		default:
			break;
		}
	}

	// wird aufgerufen, wenn der Hamster in das Territorium platziert wird
	protected void addedToWorld(World world) {
		// Hamster kann nicht auf Mauer platziert werden
		List l = getWorld().getObjectsAt(getX(), getY(), Mauer.class);
		if (l.size() > 0) {
			getWorld().removeObject(this);
			return;
		}
	}
}