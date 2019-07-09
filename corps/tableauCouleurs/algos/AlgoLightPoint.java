package corps.tableauCouleurs.algos;

import auxMaths.algLin.O3;
import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;
import corps.tableauCouleurs.Parametres;
import optique.couleur.CouleurL;
import optique.lumiere.Lumiere;

/**Heriter de cette classe signifie savoir attribuer à tout point une lumiere.
 * 
 * @author Adel
 *
 */
public abstract class AlgoLightPoint extends AlgoColorPoint {

	Parametres param;
	
	AlgoLightPoint(Parametres p, Point3 centreCadre, O3 baseRef) {
		super(p, centreCadre, baseRef);
		param=p;
	}

	protected abstract Lumiere getLumierePoint(Point3 p) ;
	
	public Lumiere getLumierePixel(int i, int j) {
		return getLumierePoint(pixToPoint3(i,j));
	}
	
	/**La fonction qui fait de la lumiere une couleur.
	 *  
	 * @param l
	 * @return
	 */
	public CouleurL lecture(Lumiere l) {
		return l.mesurerDiffus(base.getC3());
	}
	
	
	public double lectureDiagrammeLum(Point3 p ) {
		return getLumierePoint(p).mesurerSelon(base.getC3()).getIntensite();
	}
	
	public double lectureDiagrammeDev(Point3 p, VectUnitaire ref) {
		R3 d = getLumierePoint(p).getDirectionFaisceau();
		if (d.estNul())
			return 0;
		else
			return d.normer().vect(ref).norme2();
	}
	
	public double lectureDiagrammeIntEffective(Point3 p) {
		return getLumierePoint(p).getIntensiteEffective();
	}
	
	@Override
	protected CouleurL getCouleurPoint(Point3 p) {
		return lecture(getLumierePoint(p));
	}
	
	public Parametres getParam() {
		return param;
	}
	

}
