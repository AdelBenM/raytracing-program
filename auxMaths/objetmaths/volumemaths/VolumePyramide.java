package auxMaths.objetmaths.volumemaths;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.objetmaths.surfacemaths.degre1.PolygoneConvexe;
import auxMaths.objetmaths.surfacemaths.degre1.RectangleMath;

public class VolumePyramide implements VolumeMath {

	Point3 sommet;
	R3[] faces;		//des plans orientés, définis par un vecteur normal dans le bon sens (vers l'intérieur)
	R3[] directionAretes; 
	R3 directionRef;	//le barycentre des DirectionAretes
	
	
	/**Construit un volume défini par une pyramide à hauteur infinie; 
	 * Le vecteur aretes contient les aretes ordonnées de telle sorte qu'on peut le construire en tournant autour du sommet de la pyramide dans un sens
	 * 
	 * @param sommet
	 * @param aretes
	 */
	private VolumePyramide(Point3 sommet, R3[] aretes) {
		this.sommet=sommet;
		this.directionAretes=aretes;
		construireRef();
		construireFaces();
	}
	
	
	/**Construit la pyramide à hauteur infinie de sommet donné,
	 * et dont les arêtes sont définies par le sommet et les sommets du polygoneconvexe
	 * 
	 * @param sommet
	 * @param p
	 */
	public VolumePyramide(Point3 sommet, PolygoneConvexe p) {
		this(sommet,p.getListePoints());
	}
	
	/**Construit la pyramide à hauteur infinie de sommet donné,
	 * et dont les arêtes sont définies par le sommet et les points de la liste.
	 * A l'utilisateur de s'assurer que la liste de points définit bien un truc convexe...
	 * @param sommet
	 * @param p
	 */
	private VolumePyramide(Point3 sommet, Point3[] liste) {
		this.sommet = sommet;
		directionAretes= new R3[liste.length];
		for (int i=0; i< directionAretes.length ; i++)
			directionAretes[i]= sommet.Vecteur(liste[i]);
		construireRef();
		construireFaces();
	}


	/**Construit un vecteur tel que sommet+ce vecteur appartient au volume
	 * 
	 */
	private void construireRef() {
		directionRef= R3.barycentre(directionAretes);
	}
	
	/**Construit les faces connaissant les arêtes
	 * 
	 */
	private void construireFaces() {
		int n = directionAretes.length;
		faces = new R3[n];
		for (int i=0; i<n-1;i++)
			faces[i]= directionRef.bonSens( directionAretes[i].vect(directionAretes[i+1]) ).opp();
		faces[n-1]= directionRef.bonSens( directionAretes[n-1].vect(directionAretes[0]) ).opp();
	}
	
	
	
	@Override
	public boolean estDedans(Point3 p) {
		boolean result =true;
		if (faces.length>1) {
			int compteur = 0;
			while (result && compteur < faces.length) {
				result = DemiEspace.estDedans(p, sommet, faces[compteur]);
				compteur ++;
			}
		}
		return result;
	}
	
	
	


	
	public static void main(String[] args) {
		Point3[] liste = {Point3.origine.plus(R3.uz.plus(R3.ux)),  Point3.origine.plus(R3.uz.moins(R3.uy)),  Point3.origine.plus(R3.uz.moins(R3.ux)),  Point3.origine.plus(R3.uz.plus(R3.uy))};
		//VolumePyramide p = new VolumePyramide(Point3.origine, new RectangleMath(R3.uz, Point3.origine.plus(R3.uz), 1, 1));
		VolumePyramide p = new VolumePyramide(Point3.origine, liste);
		System.out.println(p.estDedans(Point3.origine.plus(new R3(0.4,0.5,1))));
	}

}
