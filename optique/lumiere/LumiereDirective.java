package optique.lumiere;

import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;
import optique.couleur.CouleurL;

/**Une Lumiere anisotrope dirig�e le long d'un vecteur ;
 * Mod�le : d�croissance de l'intensit� en cos(alpha)**s (non naturel du point de vue de la conservation de l'�nergie)
 * Utile pour le mod�le de Phong.
 * @author Adel
 *
 */
public class LumiereDirective implements Lumiere {

	protected CouleurL lum;
	protected VectUnitaire vect ;
	protected double s;		//le facteur de focalisation 
	
	public LumiereDirective(R3 directionPrivilegiee, CouleurL coul, double s){
		vect=new VectUnitaire(directionPrivilegiee);
		lum=coul;
		this.s=s;
	}
	
	//=====================================================
	//Getters
	
	public CouleurL getCouleur() {
		return lum;
	}
	
	public VectUnitaire getVecteur() {
		return vect;
	}
	
	public double getFacteurFocus() {
		return s;
	}

	//=====================================================
	
	@Override
	public CouleurL mesurerSelon(VectUnitaire ptDeVue) {
		double facteur = ptDeVue.scal(vect);
		if (facteur >0)
			return lum.multiplieIntensite(Math.pow(facteur,s));
		else return CouleurL.noir;
	}
	

	@Override
	public Lumiere reflexion(VectUnitaire normale) {
		return new LumiereDirective(vect.symetrieOrth(normale),lum,s);
	}

	@Override
	public CouleurL mesurerDiffus(VectUnitaire normale) {
		double facteur = normale.scal(vect);
		if (facteur >0)
			return lum.multiplieIntensite(Math.pow(facteur,s));
		else return CouleurL.noir;
	}

	@Override
	public R3 getDirectionFaisceau() {
		return vect;
	}
	

	@Override
	public double getIntensiteEffective() {
		return lum.getIntensite();
	}

}
