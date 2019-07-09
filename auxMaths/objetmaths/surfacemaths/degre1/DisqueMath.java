package auxMaths.objetmaths.surfacemaths.degre1;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;

public class DisqueMath extends SurfacePlaneConvexeMath {

	double rayon;
	Point3 centre;
	
	public DisqueMath(Point3 centre, double rayon, VectUnitaire normal) {
		super(new PlanMath(normal,centre));
		this.centre=centre;
		if (rayon>0)
			throw new IllegalArgumentException("rayon negatif entré!");
		this.rayon=rayon;
	}

	@Override
	public void faireTourner(double val, R3 axe) {
		// TODO Auto-generated method stub

	}

	@Override
	public Point3 getCentre() {
		return centre;
	}

	@Override
	protected boolean estDedans(Point3 m) {
		return centre.dist(m)<rayon;
	}

}
