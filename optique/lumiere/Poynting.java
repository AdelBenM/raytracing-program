package optique.lumiere;

import java.awt.Color;

import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;
import auxMaths.fonctionsVectorielles.SymetrieVectorielle;
import auxMaths.transformations.Symetrie;
import optique.couleur.CouleurL;

/**Une lumière anistrope dirigée selon un vecteur.
 * Modèle : décroissance de l'intensité en cos(alpha) (naturel du point de vue de la conservation de l'énergie)
 * @author Adel
 *
 */
public class Poynting extends LumiereDirective{

	
	public Poynting(R3 directionPrivilegiee, CouleurL coul){
		super(directionPrivilegiee,coul,1);
	}
	
	public Poynting(R3 directionPrivilegiee, double i){
		this(directionPrivilegiee, new CouleurL(Color.white, i));
	}
	
	//=====================================================
	
	@Override
	public CouleurL mesurerSelon(VectUnitaire ptDeVue) {
		double facteur = ptDeVue.scal(vect);
		if (facteur >0)
			return lum.multiplieIntensite(facteur);
		else return CouleurL.noir;
	}

	@Override
	public Lumiere reflexion(VectUnitaire normale) {
		SymetrieVectorielle sym = new SymetrieVectorielle(normale, Symetrie.PLANE);
		return new Poynting(sym.fonction(vect),lum);
	}
	
	//====================================================
	@Override
	public String toString() {
		return 	(lum.getIntensite()==0)? "Noir" :
				"Lumiere : "+ " ( Direction: " + vect.toStringHor() + " ) \n" +
				"           " + lum;
	}
	
}
