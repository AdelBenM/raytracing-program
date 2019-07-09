package auxMaths.objetmaths.surfacemaths.degre1;

import auxMaths.algLin.M3;
import auxMaths.algLin.O3;
import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import objets.AFaireTourner;

public class RectangleMath extends PolygoneConvexe implements AFaireTourner{

	
	double l1;
	double l2;
	
	/*
	 * La liste de points est orientée dans le sens indirect
	 */

	public RectangleMath(O3 base, Point3 centre, double longueur, double largeur) {
		super(initialize(base,centre,longueur,largeur),base);
		planBase = new PlanMath(base.getC1(), centre);
		l1=longueur;
		l2=largeur;
	}
	
	public RectangleMath(R3 n, Point3 centre, double longueur, double largeur) {
		this(O3.base(n),centre,longueur,largeur);
	}
	
	private static Point3[] initialize(O3 base, Point3 centre, double longueur, double largeur) {
		Point3[] tab =new Point3[4];
		R3 u = R3.prod(longueur/2,base.getC2());
		R3 v = R3.prod(largeur/2,base.getC3());
		tab[0]= centre.plus(u).plus(v);
		tab[1]= centre.plus(u).moins(v);
		tab[2]= centre.moins(u).moins(v);
		tab[3]= centre.moins(u).plus(v);
		return tab;
	}
	

	public Point3 getCentre() {
		return centre;
	}


	public double getLongueur1() {
		return l1;
	}

	public double getLongueur2() {
		return l2;
	}


	

	

	public static void main(String[] args) {
		RectangleMath r = new RectangleMath(R3.uz, Point3.origine, 2,2);
		Point3 m = Point3.origine.plus(R3.uz);
		System.out.println(r.estDedans(Point3.origine.plus(R3.ux)));
	}



















	
}
