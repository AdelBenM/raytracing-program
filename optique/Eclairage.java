package optique;

import auxMaths.algLin.Point3;
import auxMaths.transformations.TransformationEspace;
import objets.scene.Stageable;
import optique.lumiere.Lumiere;

@FunctionalInterface
public interface Eclairage {

	/**Renvoie l'influence de la source sur un point donn� dans une sc�ne donn�e.
	 * Prend en compte le type de la source (illumination) et les obstacles pr�sents dans la sc�ne (voilement)
	 * @param pt
	 * @param sc
	 * @return
	 */
	Lumiere getInfluence(Point3 pt, Stageable sc);

	

}