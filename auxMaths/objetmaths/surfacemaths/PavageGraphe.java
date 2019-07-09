package auxMaths.objetmaths.surfacemaths;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import auxMaths.algLin.M3;
import auxMaths.algLin.O3;
import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;
import auxMaths.fonctionsVectorielles.GrapheFonctionR2R;
import auxMaths.objetmaths.surfacemaths.degre1.TriangleMath;
import auxMaths.pavage.PavageR2;
import auxMaths.pavage.R2;
import auxMaths.pavage.Rotation2D;
import auxMaths.pavage.Triangle;


/**Pave avec des triangles le graphe d'une fonction de R2 dans R3.
 * Pour ce faire, restreint R2 au disque unité et l'approche par un polygone régulier.
 *  
 * @author Adel
 *
 */
public class PavageGraphe implements PavageTriangles {

	PavageR2 triangleRpztatif;
	Function<R2,Point3> graphe;
	int nbCotes;
	
	
	/**f est supposee supposee s'écrire (x,y) -> F(x,y,g(x,y)) où g est radiale et F une similitude directe
	 * 
	 * @param f
	 * @param nbRangees
	 * @param nbCotes
	 */
	private PavageGraphe(Function<R2,Point3> f, int nbRangees, int nbCotes) {
		triangleRpztatif = new PavageR2(nbRangees, nbCotes);
		this.nbCotes = nbCotes;
		graphe = f;
	}
	
	/**R2 = ptOrigine+ Vect(C1,C2)
	 * 
	 * @param g
	 * @param ptOrigine
	 * @param baseRef
	 * @param nbRangees
	 * @param nbCotes
	 */
	public PavageGraphe(Function<R2,Double> g, double facteur, Point3 ptOrigine, O3 baseRef,  int nbRangees, int nbCotes) {
		this( (new GrapheFonctionR2R(facteur,ptOrigine,baseRef)).apply(g) , nbRangees, nbCotes);		
	}
	
	public PavageGraphe(Function<R2,Double> g, double facteur, Point3 ptOrigine, VectUnitaire axe,  int nbRangees, int nbCotes) {
		this(g,facteur, ptOrigine,O3.base(axe).permutationColonnes(2),nbRangees,nbCotes);
	}
		

	@Override
	/**Renvoie la liste des triangles constitutifs du pavage total du graphe.
	 * 
	 */
	public List<TriangleMath> getListeTriangles() {
		List<Triangle<R2>> listeTrianglesRpztatifs= triangleRpztatif.getTrianglesRpztatifs(1);
		
		List<TriangleMath> result = new ArrayList<TriangleMath>();
		
		Triangle<Point3> tmp;
		Rotation2D operation;
		for (int i=1; i<=nbCotes; i++) {	//Pour chaque côté du polygone actualiser la rotation
			Iterator<Triangle<R2>> itr1 = listeTrianglesRpztatifs.iterator();
			operation = new Rotation2D(2*(i-1)*Math.PI/nbCotes);
			
			while(itr1.hasNext()) {			//pour chaque triangle du pavage rpztatif ajouter un triangle au résultat
				tmp = itr1.next().appliquer(operation).appliquer(graphe);	//on appelle la fonction après la rotation
				result.add( new TriangleMath (tmp) );
			}
		}
		
		return result;
	}
	
	
	public static void main(String[] args) {
		Function<R2,Double> f = cple -> cple.normeCarree();
		Point3 ptOrigine = Point3.origine;
		O3 base = M3.id;
		int nbCotes= 4;
		int nbRangees = 1;
		PavageGraphe pav= new PavageGraphe(f,1, ptOrigine, base, nbRangees, nbCotes);
		
		Point3 ptDeVue = Point3.origine.plus(R3.uz);
		R3 dir = R3.uz.plus(R3.uy);
		//System.out.println(pav.dist(ptDeVue, dir));
		System.out.println(pav.getListeTriangles());
	}

}
