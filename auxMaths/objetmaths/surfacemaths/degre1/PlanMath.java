package auxMaths.objetmaths.surfacemaths.degre1;

import java.io.Serializable;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;
import auxMaths.objetmaths.surfacemaths.Degre1;
import corps.tableauCouleurs.Parametres;

public class PlanMath implements Degre1, Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 8920204650877402817L;
	VectUnitaire normal;
	Point3 ptPart;






	public PlanMath( R3 n, Point3 p) {
		normal=n.normer();
		ptPart=p;
	}

	/**Definit le plan passant par 3 points donnes, et tels que le triplet (p1p2,p1p3,n) soit une base orthogonale directe
	 * 
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	public PlanMath(Point3 p1, Point3 p2, Point3 p3) {
		ptPart = p1;
		normal = (p1.Vecteur(p2)).vect((p1.Vecteur(p3))).normer();
	}

	public void reset(R3 n ,Point3 p) {
		normal=n.normer();
		ptPart=p;
	}
	
	@Override
	public Point3 getPoint() {
		return ptPart;
	}

	@Override
	public VectUnitaire getNorm() {
		return normal;
	}



	@Override
	public double dist(Point3 m, R3 d) {
		return distPlanInfini(m,d);
	}


	@Override
	public String toString() {
		return String.format("Degré 1 : { Normal = %s ; Point particulier = %s }", getNorm(Point3.origine),getPoint() );
	}



	public static void main(String[] args) {
		PlanMath p = new PlanMath(R3.ux,Point3.origine);
		Point3 m = Point3.origine.plus(new R3(3,0,0));
		System.out.println(p.dist(m, new R3(-1,1,0)));
	}




}
