package corps.tableauCouleurs;

import corps.tableauCouleurs.algos.AlgoLightPoint;
import optique.couleur.CouleurL;
import optique.lumiere.Lumiere;

public class TableauLumiere extends TableauCouleurs {
	
	protected Lumiere[][] tabLumiere;

	
	/**Ce constructeur ne fait qu'initialiser les tableaux. Il y a encore à faire!
	 * 
	 * @param largeur
	 * @param hauteur
	 */
	protected TableauLumiere(int largeur, int hauteur) {
		super(largeur,hauteur);
		tabLumiere = new Lumiere[largeur][hauteur];
	}
	
	public TableauLumiere(AlgoLightPoint alg) {
		this(alg.getLargeurPx(),alg.getHautPx());
		fillLumiere(alg);
		intBlanc = intMax;
		}
	
	
	/**Remplit le tableau en utilisant un algorithme donné et fait en sorte que intMax soit juste.
	 * 
	 * @param alg
	 */
	protected void fillLumiere(AlgoLightPoint alg) {
		int l = contenu.length;
		int h = contenu[0].length;
		intMax=0;
		
		Lumiere lum;
		for (int i=0;i<l;i++) {		//on manipulera tab[i][j]
			if (i%10==0)
				System.out.printf("%d%% %n", 100*i/l);
			for (int j=0;j<h;j++) {
				lum = alg.getLumierePixel(i,j);
				CouleurL c = alg.lecture(lum);
				contenu[i][j]= c;
				if (intMax < c.getIntensite())
					intMax= c.getIntensite();
			}
		}
	}

}
