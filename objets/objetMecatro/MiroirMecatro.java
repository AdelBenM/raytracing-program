package objets.objetMecatro;

import java.awt.Color;

import auxMaths.objetmaths.surfacemaths.SurfMath;
import objets.PreMiroir;
import objets.objetPhong.Surface;

public abstract class MiroirMecatro<G extends SurfMath> extends Surface<G> implements PreMiroir {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8920979303046948989L;

	protected MiroirMecatro(Surface<G> surface) {
		this(surface.getNom(),surface.getGeometrie(),surface.getListeCouleurs()[0].getColor());
	}
	
	protected MiroirMecatro(String nom, G surfMath, Color c, Color...colors) {
		super(nom,surfMath,c, colors);
	}
	


}
