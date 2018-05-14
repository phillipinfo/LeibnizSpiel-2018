import greenfoot.Actor;
import greenfoot.World;

import java.util.List;

/**
 * Repraesentation von Mauern im Java-Hamster-Modell
 * 
 * @author Dietrich Boles (Universitaet Oldenburg)
 * @version 1.0 (29.01.2007)
 * 
 */
public class Mauer extends Actor {
	public Mauer() {
	}

	protected void addedToWorld(World world) {
		// bereits existierende Aktoren auf der Kachel werden geloescht
		List l = getWorld().getObjectsAt(getX(), getY(), null);
		for (int i = 0; i < l.size(); i++) {
			Actor actor = (Actor) l.get(i);
			if (actor != this) {
				getWorld().removeObject(actor);
			}
		}
	}
}