package auxMaths.transformations;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;
import auxMaths.fonctionsVectorielles.SymetrieVectorielle;

public class Symetrie extends TransformationAffinisee{

	public static final boolean AXIALE = false;
	public static final boolean PLANE = true;
	
	public Symetrie(Point3 origine, SymetrieVectorielle f) {
		super(origine, f);
	}
	
	/**si type= Symetrie.AXIALE, alors pt et v d�finissent une droite
	 * si type = Symetrie.PLANE, alors pt et v d�finissent un plan
	 * @param pt
	 * @param v
	 * @param type
	 */
	public Symetrie(Point3 pt, VectUnitaire v, boolean type) {
		this(pt,new SymetrieVectorielle(v,type));
	}
	
	/**si type= Symetrie.AXIALE, alors pt et v d�finissent une droite
	 * si type = Symetrie.PLANE, alors pt et v d�finissent un plan
	 * @param pt
	 * @param v
	 * @param type
	 */
	public Symetrie(Point3 pt, R3 v, boolean type) {
		this(pt,new VectUnitaire(v),type);
	}

	@Override
	public Symetrie inverse() {
		return this;
	}
	
	public static void main(String[] args) {
		Symetrie s = new Symetrie(Point3.origine, R3.ux, Symetrie.PLANE);
		Point3 p = Point3.origine.plus(R3.ux);
		System.out.println(s.agirSur(p));
	}
	

}
