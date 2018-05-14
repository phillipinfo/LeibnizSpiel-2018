import greenfoot.GreenfootImage;
import greenfoot.Greenfoot;

/**
 * Oberklasse aller Exception-Klassen des Java-Hamster-Modells. Bei allen
 * Exceptions des Java-Hamster-Modells handelt es sich um Unchecked-Exception,
 * die nicht unbedingt abgefangen bzw. deklariert werden muessen.
 * 
 * @author Dietrich Boles (Universitaet Oldenburg)
 * @version 1.0 (29.01.2007)
 * 
 */
public class HamsterException extends RuntimeException {

	/**
	 * Hamster, der die Exception verschuldet hat
	 */
	private Hamster hamster;

	/**
	 * Konstruktor, der die Exception mit dem Hamster initialisiert, der die
	 * Exception verschuldet hat.
	 * 
	 * @param hamster
	 *            der Hamster, der die Exception verschuldet hat
	 */
	public HamsterException(Hamster hamster) {
		super("");
		this.hamster = hamster;
        GreenfootImage origImage = hamster.getImage();
        for(int i=0; i<3; i++) {
            hamster.setImage("smiley102.png");
            Greenfoot.delay(1);
            hamster.setImage(origImage);
            Greenfoot.delay(1);
        }

	}

	/**
	 * liefert den Hamster, der die Exception verschuldet hat
	 * 
	 * @return der Hamster, der die Exception verschuldet hat
	 */
	public Hamster getHamster() {
		return this.hamster;
	}
}
