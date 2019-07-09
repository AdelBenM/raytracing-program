package optique.sources;

import auxMaths.algLin.Point3;
import optique.lumiere.Lumiere;

/**L'interface qui mod�lise le comportement d'une source dans le vide complet : une fonction de l'espace qui � un point associe de la lumi�re.
 * 
 * @author Adel
 *
 */
@FunctionalInterface
public interface Illumination {
	public Lumiere champLumiere(Point3 pt);
	
	
}
