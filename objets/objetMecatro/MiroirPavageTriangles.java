package objets.objetMecatro;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import auxMaths.objetmaths.surfacemaths.PavageTriangles;
import auxMaths.objetmaths.surfacemaths.degre1.TriangleMath;
import objets.objetPhong.Surface;
import objets.scene.Stageable;
import optique.Source;

public abstract class MiroirPavageTriangles<P extends PavageTriangles> extends MiroirMecatro<P> {

	List<MiroirPlanConvexe<TriangleMath>> listeMiroirs;
	
	protected MiroirPavageTriangles(String nom, P surfMath, Color c, Color... colors) {
		super(nom, surfMath, c, colors);
		construireListeMiroirs();
	}
	
	protected MiroirPavageTriangles(Surface<P> s) {
		super(s);
		construireListeMiroirs();
	}
	
	
	private void construireListeMiroirs() {
		listeMiroirs = new ArrayList<MiroirPlanConvexe<TriangleMath>>();
		
		Iterator<TriangleMath> itr = getGeometrie().getListeTriangles().iterator();

		int increment = 0;
		Color c = getListeCouleurs()[0].getColor();
		while (itr.hasNext()) {		//pour chaque trianglemath on cree un trianglemiroir
			increment++;
			listeMiroirs.add( new MiroirPlanConvexe<TriangleMath>( "Miroir triangulaire no "+increment, itr.next(), c));
			
		}
	}

	@Override
	public List<Source> apply(Source src, Stageable sc) {
		List<Source> result = new ArrayList<Source>();
		
		Iterator<MiroirPlanConvexe<TriangleMath>> itr = listeMiroirs.iterator();
		while(itr.hasNext())
			result.addAll(itr.next().apply(src, sc));
		return result;
	}

	

}
