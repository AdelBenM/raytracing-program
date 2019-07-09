package objets.objetMecatro;

import java.awt.Color;
import java.util.function.Function;

import auxMaths.algLin.O3;
import auxMaths.algLin.Point3;
import auxMaths.objetmaths.surfacemaths.PavageGraphe;
import auxMaths.pavage.R2;
import objets.objetPhong.Surface;

public abstract class MiroirGraphePave extends MiroirPavageTriangles<PavageGraphe> {

	protected MiroirGraphePave(Surface<PavageGraphe> s) {
		super(s);
	}
	
	protected MiroirGraphePave(String nom, PavageGraphe surfMath) {
		super(nom, surfMath, Color.red);
	}
	
	public MiroirGraphePave(String nom, Function<R2,Double> f, double facteur,Point3 ptOrigine, O3 base, int nbRangees, int nbCotes) {
		this(nom,new PavageGraphe(f,facteur,ptOrigine,base,nbRangees,nbCotes));
	}


}
