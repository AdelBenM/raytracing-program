package auxMaths.objetmaths.surfacemaths;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;
import corps.tableauCouleurs.Parametres;

public interface Degre1 extends SurfMath {
	
	/**Renvoie la distance qui, dans la direction d, sépare m du plan porteur de l'instance.
	 * 
	 * @param m
	 * @param d
	 * @return
	 */
	default double distPlanInfini(Point3 m, R3 d) {
		d=d.normer();
		double a = d.scal(getNorm());
		if (Math.abs(a)<Parametres.h)
			return Double.POSITIVE_INFINITY;
		else {
			double result= (m.Vecteur(getPoint()).scal(getNorm())/a);
			if (result <Parametres.h)
				return Double.POSITIVE_INFINITY;
			else
				return result;
		}
	}

	Point3 getPoint() ;

	VectUnitaire getNorm(); 
	
	@Override
	default VectUnitaire getNorm(Point3 m) {
		return getNorm();
	}

}
