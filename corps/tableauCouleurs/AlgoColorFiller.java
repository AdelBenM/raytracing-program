package corps.tableauCouleurs;

import optique.couleur.CouleurL;

/** Impl�menter cette interface signifie savoir g�n�rer un tableauCouleurs, normalement dans le but de cr�er une image.
 * 
 * @author Adel
 *
 */
public interface AlgoColorFiller {
	
	int getLargeurPx();
	int getHautPx();
	
	/**Renvoie la couleur � attribuer � une pixel donn� ; i et j sont strictement inf�rieurs � l et h resp.
	 * 
	 * @param l
	 * @param h
	 * @return
	 */
	CouleurL getCouleurPixel(int i, int j);
	
}
