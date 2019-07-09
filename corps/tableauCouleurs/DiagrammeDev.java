package corps.tableauCouleurs;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;
import corps.tableauCouleurs.algos.AlgoLightPoint;
import corps.tableauCouleurs.parametres.ParametresMecatro;
import ihm.fenetre1.edition.Editable;

public class DiagrammeDev extends Diagramme implements Editable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1767321485136894551L;
	
	public DiagrammeDev(VectUnitaire ref,Point3 depart, R3 increment, int nbPoints, AlgoLightPoint alg) {
		super(depart,increment,nbPoints, pt -> alg.lectureDiagrammeDev(pt,ref));
	}
	
	public DiagrammeDev(VectUnitaire ref,Point3 depart, Point3 arrivee, int nbPoints, AlgoLightPoint alg) {
		this(ref,depart, genererIncrement(depart,arrivee,nbPoints), nbPoints, alg);
	}
	
	public DiagrammeDev(ParametresMecatro param, AlgoLightPoint alg) {
		super("Graphe déviation");
		f= pt -> alg.lectureDiagrammeDev(pt,param.getBase().getC3());
		reset(param);
		majListeAttributs();
	}
	
	

	//================================================================
	//Methodes d'éditable
	
	/**Met les parametres du diagrammes défauts, 
	 * à savoir tel que le segment représenté par le schéma coïncide avec le segment horizontal central du cadre de l'image actuellement affichée.
	 * Nb de points : 100
	 * @param param
	 */
	public void reset(ParametresMecatro param) {
		Point3 centre = param.getCentre();
		VectUnitaire u = param.getBase().getC2();
		dep=centre.moins(u.prod(param.getLarg()/2));
		Point3 arrivee = centre.plus(u.prod(param.getLarg()/2));
		nbPoints=100;
		increment = genererIncrement(dep,arrivee,nbPoints);
		update();
		
	}

}
