package auxMaths.objetmaths.surfacemaths.degre1;

import java.util.ArrayList;

import auxMaths.algLin.Point3;
import auxMaths.pavage.Triangle;

public class TriangleMath extends PolygoneConvexe {

	/**Dangereux: les points peuvent coïncider
	 * 
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	protected TriangleMath(Point3 p1, Point3 p2, Point3 p3) {
		super(new Point3[] {p1,p2,p3}, new PlanMath(p1,p2,p3));
	}
	
	private TriangleMath(ArrayList<Point3> l) {
		this(l.get(0),l.get(1),l.get(2));
	}
	
	/**Safe : les points sont distincts
	 * 
	 * @param tr
	 */
	public TriangleMath(Triangle<Point3> tr) {
		this(tr.getContent());
	}
	
	@Override
	public String toString() {
		return "Triangle de centre " + getCentre().toStringHor() + " et de normale "+getNorm().toStringHor() + "\n"; 
	}
	
	

}
