package auxMaths.objetmaths.surfacemaths.degre1;

import auxMaths.TesteurNullite;
import auxMaths.algLin.O3;
import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;

public class PolygoneConvexe extends SurfacePlaneConvexeMath {
	
	
	private Point3[] listePoints;
	Point3 centre;
	
	/**On suppose que la liste entree est appropriee a la definition d un polygone.
	 * 
	 * @param listePoints
	 */
	protected PolygoneConvexe(Point3[] listePoints, O3 baseRef) {
		super(new PlanMath(listePoints[0],listePoints[1],listePoints[2]),baseRef);
		this.listePoints = listePoints;
		centre = calculerCentre();
	}
	
	/**Construit un polygone avec une liste de points donnes dont on verifie qu ils appartiennent a un plan donne
	 * 
	 * @param listePoints
	 * @param planBase
	 */
	public PolygoneConvexe(Point3[] listePoints, PlanMath planBase) {
		super(planBase);
		if (listePoints.length<3 || !estCoherent(listePoints,planBase))
			throw new IllegalArgumentException("pas possible de creer un tel polygone");
		else {
			this.listePoints = listePoints;
			centre = calculerCentre();
		}
	}
	
	
	
	/**Renvoie si oui ou non les points sur sur le plan
	 * 
	 * @param lstPts
	 * @param plan
	 * @return
	 */
	public static boolean estCoherent(Point3[] lstPts, PlanMath plan) {
		boolean result = true;
		for (int i=0; i<lstPts.length && result; i++)
			result = TesteurNullite.estNul(plan.normal.scal(plan.ptPart.Vecteur(lstPts[i])));
		return result;
	}
	
	//==============================================
	//Getters-Setters protected
	
	/**Renvoie la liste des sommets du polygone, tels qu'on peut la construire en tournant autour de la figure dans un même sens
	 * 
	 * @return
	 */
	public Point3[] getListePoints() {
		return listePoints;
	}
	
	@Override
	public Point3 getCentre() {
		return centre;
	}

	protected void setListePoints(Point3[] listePoints) {
		this.listePoints = listePoints;
	}
	

	
	//====================================================
	//Geometrie
	
	@Override
	public boolean estDedans(Point3 m) {
		return testDeterminant(m, getListePoints(), getNorm(null));
	}
	
	protected Point3 calculerCentre() {
		R3 result=R3.zero;
		for (int i=0; i<getListePoints().length; i++) 
			result = result.plus(Point3.origine.Vecteur(getListePoints()[i]));
		return Point3.origine.plus(result.prod(1.0/getListePoints().length));
	}
	
	
	
	/**Procède au test de déterminant classique des polygones convexes, mais le généralise à une liste quelconque (intérêt?) 
	 * 
	 */
	public static boolean testDeterminant(Point3 m, Point3[] liste, R3 normale) {
		boolean sens = Point3.estOrientationPositive(normale, m, liste[0], liste[1]);
		boolean result = true;
		if (sens)
			for (int i=1; i<liste.length-1 && result; i++) {
				result= Point3.estOrientationPositive(normale, m, liste[i], liste[i+1]);
			}
		else
			for (int i=1; i<liste.length-1 && result; i++) {
				result= !Point3.estOrientationPositive(normale, m, liste[i], liste[i+1]);
			}
		return result;
	}
	
	
	//===========================================================
	//Méthode de similitude
	

	
	
	@Override
	public void faireTourner(double val, R3 axe) {
		O3 rot = O3.rotation(axe, val);
		setBaseRef(getBaseRef().fois(rot));
		for (int i=0; i<getListePoints().length; i++)
			getListePoints()[i] = getListePoints()[i].appliquerRot(centre, axe, val);
	}




	  
	public static void main(String[] args) {
	}



}
