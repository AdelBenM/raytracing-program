package corps.tableauCouleurs;

import java.awt.image.BufferedImage;

import optique.couleur.CouleurL;

public class TableauCouleurs{
	protected CouleurL[][] contenu;
	protected double intBlanc;	//L'intensit� de la lumi�re � partir de laquelle la couleur du rendu est satur�e ; editable � souhait.
	
	protected double intMax;		//L'intensit� max d'une lumi�re dans contenu
	
	
	
	/**Ce constructeur ne fait qu'initialiser contenu. Il y a encore � faire!
	 * 
	 * @param largeur
	 * @param hauteur
	 */
	protected TableauCouleurs(int largeur, int hauteur) {
		contenu = new CouleurL[largeur][hauteur];
	}
	
	/**Construit le tableau (selon les dimensions dans alg), le remplit selon alg, cr�e le juste intMax et initialise intBlanc � intMax.
	 * 
	 * @param largeur
	 * @param hauteur
	 * @param alg
	 */
	public TableauCouleurs(AlgoColorFiller alg) {
		this(alg.getLargeurPx(),alg.getHautPx());
		fill(alg);
		intBlanc = intMax;
	}
		
	
	//===============================================
	//Contenu 
	public CouleurL get(int l, int h) {
		return contenu[l][h];
	}
	
	/**Remplit le tableau en utilisant un algorithme donn� et fait en sorte que intMax soit juste.
	 * 
	 * @param alg
	 */
	private void fill(AlgoColorFiller alg) {
		int l = contenu.length;
		int h = contenu[0].length;
		intMax=0;
		
		CouleurL lum;
		for (int i=0;i<l;i++) {		//on manipulera tab[i][j]
			if (i%10==0)
				System.out.printf("%d%% %n", 100*i/l);
			for (int j=0;j<h;j++) {
				lum = alg.getCouleurPixel(i,j);
				contenu[i][j]= lum;
				if (intMax < lum.getIntensite())
					intMax= lum.getIntensite();
			}
		}
	}
			
		
	
	//=================================================
	//Rendu
	
	
	public double getBlanc() {
		return intBlanc;
	}	
	
	public void setBlanc(double i) {
		if (i>0)
			intBlanc=i;
		else throw new IllegalArgumentException("intBlanc doit �tre >0!");
	}
	
	/**Assigne intBlanc � l'intensit� maximale des �l�ments du tableau
	 * 
	 */
	public void setBlancAuMax() {
		intBlanc = intMax;
	}
	
	
	
	
	/**Renvoie l'image correspondant au tableau de lumi�res 
	 * 
	 * @return
	 */
	public BufferedImage getImage() {
		int l = contenu.length;
		int h = contenu[0].length;
		BufferedImage result = new BufferedImage(l,h, BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < l; i++) 
			for (int j = 0; j < h; j++) 
				result.setRGB(i, j,  (contenu[i][j].appliquerIntGlobale(intBlanc)).getRGB() );
		return result;
	}
	
	
}
