package optique.sources.illumination;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import optique.couleur.CouleurL;
import optique.lumiere.Lumiere;
import optique.lumiere.Poynting;
import optique.sources.Illumination;

/**Mod�le d'une illumination ponctuelle de base : d�croissance en 1/r� de la puissance surfacique
 * 
 * @author Adel
 *
 */
public class IlluminationPonctuelle implements Illumination{

	Point3 reference;			//Le point o� la lumpi�re est la plus intense
	double rayonInfluenceCarre;		//Le carre du rayon d une sphere de r�f�rence dite d'influence o� l'intensit� est uniforme est vaut couleur
	CouleurL couleur;			//La couleur de la lumiere sur la sph�re d'influence
	
	public IlluminationPonctuelle(Point3 ref, CouleurL lum, double rayon) {
		reference = ref;
		couleur = lum;
		rayonInfluenceCarre = rayon*rayon;
	}
	

	
	public CouleurL getCouleur() {
		return couleur;
	}
	
	@Override
	public Lumiere champLumiere(Point3 pt) {
		if (pt.equals(reference))
			return Lumiere.noir;
		else {
			R3 om = reference.Vecteur(pt);
			return new Poynting(om,couleur.multiplieIntensite(1/om.norme2car()));
		}
	}

}
