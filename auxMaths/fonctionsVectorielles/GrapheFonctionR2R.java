package auxMaths.fonctionsVectorielles;

import java.util.function.Function;

import auxMaths.algLin.O3;
import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.pavage.R2;

public class GrapheFonctionR2R implements Function<Function<R2,Double>, Function<R2,Point3>> {

	Point3 ptOrigine;
	O3 baseRef;			//R2 est plongé dans l'espace selon ptOrigine+Vect(C1,C2)
	double facteur;
	
	/**Facteur: le rayon du disque sur lequel on aggrandit le disque unité.
	 * PtOrigine : point correspondant à (0,0) dans R2.
	 * baseRef : base telle que l'espace vectoriel centré sur ptOrigine et orienté sur O3 coïncide avec le graphe vectoriel de la fonction (x,y,f(x,y)). 
	 * @param ptOrigine
	 * @param baseRef
	 */
	public GrapheFonctionR2R(double facteur, Point3 ptOrigine, O3 baseRef) {
		this.ptOrigine=ptOrigine;
		this.baseRef=baseRef;
		this.facteur = facteur;
	}
	
	public Function<R2,R3> grapheVectoriel(Function<R2,Double> f){
		Function<R2,R3> result = cple -> new R3(cple.get1()*facteur,cple.get2()*facteur,f.apply(cple)*facteur);
		return result;
	}

	@Override
	public Function<R2, Point3> apply(Function<R2, Double> f) {
		Function<R2,Point3> result = cple -> ptOrigine.plus( baseRef.fois( grapheVectoriel(f).apply(cple) ) );
		return result;
	}
	
	

}
