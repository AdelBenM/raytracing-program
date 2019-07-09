package optique.lumiere;

import java.awt.Color;

import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;
import optique.couleur.CouleurL;

/**Une lumiere identique dans toutes les directions
 * 
 * @author Adel
 *
 */
public class LumiereDiffuse implements Lumiere {

	CouleurL coul;
	
	//===================================
	//Constructeurs
	public LumiereDiffuse(CouleurL lum) {
		coul=lum;
	}
	
	public LumiereDiffuse(double i) {
		coul = new CouleurL(Color.white,i);
	}

	//=========================================
	
	@Override
	public CouleurL mesurerSelon(VectUnitaire ptDeVue) {
		return coul;
	}
	
	@Override
	public CouleurL mesurerDiffus(VectUnitaire normale) {
		return coul;
	}

	@Override
	public Lumiere reflexion(VectUnitaire normale) {
		return Lumiere.noir;
	}
	

	@Override
	public R3 getDirectionFaisceau() {
		return R3.zero;
	}
	

	@Override
	public double getIntensiteEffective() {
		return coul.getIntensite();
	}
	
	//===========================================
	
	@Override
	public String toString() {
		return (coul.getIntensite()==0)? "Noir" : "Lumiere diffuse : "+coul;
	}
	
	public static void main(String[] args) {
		System.out.println(Lumiere.noir);
		Poynting p = new Poynting(R3.ux, 2);
		System.out.println(p);
	}




}
