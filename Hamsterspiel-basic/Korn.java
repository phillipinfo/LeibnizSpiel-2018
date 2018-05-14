import greenfoot.Actor;
import greenfoot.World;

import java.util.List;

/**
 * Repraesentation von Koernern im Java-Hamster-Modell
 * 
 * @author Dietrich Boles (Universitaet Oldenburg)
 * @version 1.0 (29.01.2007)
 * 
 */
public class Korn extends Actor {

	private int number = 0;

	public Korn() {
	}

	// wird aufgerufen, wenn das Korn in das Territorium platziert wird
	protected void addedToWorld(World world) {
		// Wenn auf der Kachel schon eine Mauer ist, wird das Korn wieder
		// entfernt
		if (getWorld().getObjectsAt(getX(), getY(), Mauer.class).size() > 0) {
			getWorld().removeObject(this);
			return;
		}

		// Hilfsroutine, damit das Bild mit der richtigen Koerneranzahl
		// angezeigt wird
		number = getWorld().getObjectsAt(getX(), getY(), Korn.class).size();
		setImage("korn" + Math.min(number, 12) + ".png");
		// es werden maximal 12 Koerner angezeigt

		// Hamster nach vorne platzieren
		List l = getWorld().getObjectsAt(getX(), getY(), Hamster.class);
		for (int i = 0; i < l.size(); i++) {
			Hamster h = (Hamster) l.get(i);
			getWorld().removeObject(h);
			getWorld().addObject(h, getX(), getY());
		}
	}

	// liefert die Information, das wie vielte Korn dieses Korn auf der Kachel
	// ist
	protected int getNumber() {
		return number;
	}

}
