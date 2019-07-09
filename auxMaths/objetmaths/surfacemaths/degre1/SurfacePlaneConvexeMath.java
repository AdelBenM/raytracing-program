package auxMaths.objetmaths.surfacemaths.degre1;

import auxMaths.algLin.O3;
import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;
import auxMaths.objetmaths.surfacemaths.Degre1;
import objets.AFaireTourner;

public abstract class SurfacePlaneConvexeMath implements Degre1, AFaireTourner{
	PlanMath planBase;
	O3 baseRef;
	
	public SurfacePlaneConvexeMath(PlanMath planBase, O3 baseRef) {
		this.planBase=planBase;
		this.baseRef = baseRef;
	}
	public SurfacePlaneConvexeMath(PlanMath planBase) {
		this(planBase, O3.base(planBase.normal));
	}
	
	
	@Override
	public VectUnitaire getNorm() {
		return planBase.getNorm();
	}
	
	public abstract Point3 getCentre();
	
	@Override
	public Point3 getPoint() {
		return getCentre();
	}
	
	/** On suppose que m est dans le plan qui porte la surface.
	 * 
	 */
	protected abstract boolean estDedans(Point3 m);
	
	
	@Override
	public double dist(Point3 m, R3 d) {
		double l = distPlanInfini(m, d);
		if (l< Double.POSITIVE_INFINITY) {
			Point3 projection = m.plus(d.normer().prod(l));
			if (estDedans(projection))
					return l;
			else
				return Double.POSITIVE_INFINITY;
		}
		else
			return Double.POSITIVE_INFINITY;
	}
	
	@Override
	public O3 getBaseRef() {
		return baseRef;
	}
	
	protected void setBaseRef(O3 ref) {
		baseRef = ref;
	}
}
