package corps.tableauCouleurs;

import optique.couleur.CouleurL;

/** Implémenter cette interface signifie savoir générer un tableauCouleurs, normalement dans le but de créer une image.
 * 
 * @author Adel
 *
 */
public interface AlgoColorFiller {
	
	int getLargeurPx();
	int getHautPx();
	
	/**Renvoie la couleur à attribuer à une pixel donné ; i et j sont strictement inférieurs à l et h resp.
	 * 
	 * @param l
	 * @param h
	 * @return
	 */
	CouleurL getCouleurPixel(int i, int j);
	
}
