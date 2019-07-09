package auxMaths.objetmaths.surfacemaths;

import java.util.Iterator;
import java.util.List;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.objetmaths.surfacemaths.degre1.TriangleMath;

public interface PavageTriangles extends SurfMath{

	public List<TriangleMath> getListeTriangles();
	
	@Override
	public default double dist(Point3 m, R3 d) {
		Iterator<TriangleMath> itr = getListeTriangles().iterator();
		double result = Double.POSITIVE_INFINITY;
		while (itr.hasNext())
			result = Double.min(result, itr.next().dist(m,d));
		return result;
	}

	@Override
	public default R3 getNorm(Point3 m) {
		Iterator<TriangleMath> itr = getListeTriangles().iterator();
		
		TriangleMath tmp;
		while (itr.hasNext()) {
			tmp = itr.next();
			if (tmp.estDedans(m))
				return tmp.getNorm();
		}
		throw new RuntimeException("La normale n'a pas été trouvé : bizarre.");		
	}


}
